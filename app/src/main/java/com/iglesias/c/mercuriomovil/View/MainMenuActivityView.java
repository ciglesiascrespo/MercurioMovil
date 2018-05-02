package com.iglesias.c.mercuriomovil.View;

import android.content.Context;

/**
 * Created by Ciglesias on 22/04/2018.
 */

public interface MainMenuActivityView {
    void showDialogFile();
    void showFragment(int id);
    void showLoading(String msj);
    void setProgressLoading(int i);
    void setMaxProgress(int i);
    void hideLoading();
    void checkPermission();
    Context getContext();
    void closeDrawer();
    void showDialogErrorImport();
}
