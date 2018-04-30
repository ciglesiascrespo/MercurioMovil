package com.iglesias.c.mercuriomovil.View;

import android.content.Context;

/**
 * Created by Ciglesias on 22/04/2018.
 */

public interface MainMenuActivityView {
    void showDialogFile();
    void showFragment(int id);
    void showLoading(String msj);
    void hideLoading();
    Context getContext();
}
