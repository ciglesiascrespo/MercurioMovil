package com.iglesias.c.mercuriomovil.Iterator;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.iglesias.c.mercuriomovil.Db.DbHandler;
import com.iglesias.c.mercuriomovil.Presenter.MainMenuPresenter;
import com.iglesias.c.mercuriomovil.Utils.UtilsFiles;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ciglesias on 25/04/2018.
 */

public class MainMenuIterator {
    MainMenuPresenter presenter;
    DbHandler dbHandler;
    Context context;

    public MainMenuIterator(MainMenuPresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
        dbHandler = DbHandler.getInstance(context);
    }

    public void imporData(final String path) {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                String sqls[] = UtilsFiles.readFile(path).split("<#");
                presenter.setMaxLoading(sqls.length);
                for (int i = 0; i < sqls.length; i++) {
                    dbHandler.execSql(sqls[i]);
                    subscriber.onNext(i);
                }


                subscriber.onCompleted();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                presenter.onCompleteImporData();
            }

            @Override
            public void onError(Throwable e) {
                presenter.showDialogErrorImport();
                Log.e(getClass().getName(), "Error guardando formulario." + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onNext(Integer opcions) {
                presenter.setProgress(opcions);
                Log.e(getClass().getName(),"i: " + opcions);
            }
        });


    }
}
