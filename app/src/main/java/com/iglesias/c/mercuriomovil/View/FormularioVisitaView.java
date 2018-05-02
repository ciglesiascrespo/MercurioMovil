package com.iglesias.c.mercuriomovil.View;

import android.content.Context;

/**
 * Created by dell on 02/05/2018.
 */

public interface FormularioVisitaView {
    void showLoading();

    void hideLoading();

    Context getContextApp();


    void showDialogError(String msj);
}
