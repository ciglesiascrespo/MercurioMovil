package com.iglesias.c.mercuriomovil.Iterator;

import android.content.Context;
import android.util.Log;

import com.iglesias.c.mercuriomovil.Db.DbHandler;
import com.iglesias.c.mercuriomovil.Pojo.SitioItem;
import com.iglesias.c.mercuriomovil.Presenter.SitioPresenterImpl;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ciglesias on 28/03/2018.
 */

public class SitioIterator {
    SitioPresenterImpl presenter;
    DbHandler dbHandler;

    public SitioIterator(SitioPresenterImpl presenter, Context context) {
        this.presenter = presenter;
        this.dbHandler = DbHandler.getInstance(context);
    }

    public void getListItem() {
        Log.e(getClass().getName(), "Cargando sitios");
        Observable.create(new Observable.OnSubscribe<ArrayList<SitioItem>>() {
            @Override
            public void call(Subscriber<? super ArrayList<SitioItem>> subscriber) {
                subscriber.onNext(dbHandler.getListSitioItems());

                subscriber.onCompleted();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ArrayList<SitioItem>>() {
            @Override
            public void onCompleted() {
                presenter.onCompleteListSitios();
            }

            @Override
            public void onError(Throwable e) {
                presenter.onErrorLoadingSitios();
                Log.e(getClass().getName(), "Error cargando sitios." + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onNext(ArrayList<SitioItem> list) {
                Log.e(getClass().getName(),"mostrando sitios: " + list.size());
                presenter.showListSitios(list);

            }
        });

    }
}
