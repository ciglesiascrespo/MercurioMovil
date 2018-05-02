package com.iglesias.c.mercuriomovil.Presenter;

/**
 * Created by Ciglesias on 25/04/2018.
 */

public interface MainMenuPresenter {
    void setMaxLoading(int i);
    void setProgress(int i);
    void onCompleteImporData();
    void showDialogErrorImport();
}
