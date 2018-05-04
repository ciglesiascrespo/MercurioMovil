package com.iglesias.c.mercuriomovil.Iterator;

import android.content.Context;
import android.util.Log;

import com.iglesias.c.mercuriomovil.Db.DbHandler;
import com.iglesias.c.mercuriomovil.Pojo.ItemVisita;
import com.iglesias.c.mercuriomovil.Pojo.SitioItem;
import com.iglesias.c.mercuriomovil.Presenter.FormularioVisitaPresenter;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ciglesias on 3/05/2018.
 */

public class FormularioVisitaIterator {
    FormularioVisitaPresenter presenter;
    DbHandler dbHandler;

    public FormularioVisitaIterator(FormularioVisitaPresenter presenter, Context context) {
        this.presenter = presenter;
        dbHandler = DbHandler.getInstance(context);
    }

    public void getItemsVisita(final int idVisita) {
        Log.e(getClass().getName(), "Cargando items visita");
        Observable.create(new Observable.OnSubscribe<ArrayList<ItemVisita>>() {
            @Override
            public void call(Subscriber<? super ArrayList<ItemVisita>> subscriber) {
                subscriber.onNext(dbHandler.getListitemsVisita(idVisita));

                subscriber.onCompleted();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ArrayList<ItemVisita>>() {
            @Override
            public void onCompleted() {
                presenter.onCompleteItemsVisita();
            }

            @Override
            public void onError(Throwable e) {
                presenter.onErrorLoadingItems();
                Log.e(getClass().getName(), "Error cargando items." + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onNext(ArrayList<ItemVisita> list) {
                Log.e(getClass().getName(), "mostrando items: " + list.size());
                presenter.onNextItemsVisita(list);

            }
        });
    }
}
