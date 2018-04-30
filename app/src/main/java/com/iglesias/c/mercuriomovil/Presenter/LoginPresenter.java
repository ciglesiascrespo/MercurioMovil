package com.iglesias.c.mercuriomovil.Presenter;

/**
 * Created by Ciglesias on 17/02/2018.
 */

public interface LoginPresenter {

    void onSuccesLogin(int id);

    void onErrorLogin();

    void onUsuarioLogueado(int id, String pattern);
}
