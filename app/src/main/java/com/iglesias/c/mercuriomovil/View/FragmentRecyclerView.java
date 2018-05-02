package com.iglesias.c.mercuriomovil.View;


import android.content.Context;

import com.iglesias.c.mercuriomovil.Pojo.SitioItem;

import java.util.List;

/**
 * Created by Ciglesias-pc on 07/07/2017.
 */

public interface FragmentRecyclerView {

    void updateList(List<SitioItem> listItems);

    void showLoading();

    void dismissLoading();

    void showErrorDialog();

    Context getContextActivity();
}
