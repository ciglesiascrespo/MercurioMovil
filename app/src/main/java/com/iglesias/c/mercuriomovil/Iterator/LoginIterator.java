package com.iglesias.c.mercuriomovil.Iterator;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import com.iglesias.c.mercuriomovil.Db.DbHandler;
import com.iglesias.c.mercuriomovil.Presenter.LoginPresenter;
import com.iglesias.c.mercuriomovil.R;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ciglesias on 17/02/2018.
 */

public class LoginIterator {
    private final String TAG = getClass().getName();

    private Context context;
    private LoginPresenter presenter;
    private SharedPreferences preferences;
    private DbHandler dbHandler;

    public LoginIterator(Context context, LoginPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
        preferences = context.getSharedPreferences(context.getResources().getString(R.string.name_preference_user), Context.MODE_PRIVATE);
        dbHandler = DbHandler.getInstance(context);
    }

    public void verificarUsuarioLogueado() {
        boolean result = false;
        int id = 0;


        if (preferences != null && preferences.getBoolean(context.getResources().getString(R.string.name_preference_user_logueado), false)) {
            result = true;
            id = preferences.getInt(context.getResources().getString(R.string.name_preference_user_codigo), 0);
        }
        if (result)
            verificarPatron(id);

    }

    private void verificarPatron(final int id) {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(dbHandler.getPatterUser(id));
                subscriber.onCompleted();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io());

        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                presenter.onErrorLogin();
            }

            @Override
            public void onNext(String result) {
                Log.i(TAG, "resultLogin: " + result);

                if (result.trim().length() > 0) {
                    presenter.onUsuarioLogueado(id, result);

                }
            }
        });

    }

    public void login(final String user, final String pass) {
        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(dbHandler.verificaUsuario(user, pass));
                subscriber.onCompleted();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io());

        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                presenter.onErrorLogin();
            }

            @Override
            public void onNext(Integer result) {
                Log.i(TAG, "resultLogin: " + result);

                if (result > 0) {

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean(context.getResources().getString(R.string.name_preference_user_logueado), true);
                    editor.putInt(context.getResources().getString(R.string.name_preference_user_codigo), result);
                    editor.apply();
                    presenter.onSuccesLogin(result);
                } else {
                    presenter.onErrorLogin();
                }
            }
        });


    }
}
