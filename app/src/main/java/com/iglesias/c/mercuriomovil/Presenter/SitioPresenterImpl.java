package com.iglesias.c.mercuriomovil.Presenter;

import com.iglesias.c.mercuriomovil.Iterator.SitioIterator;
import com.iglesias.c.mercuriomovil.Pojo.SitioItem;
import com.iglesias.c.mercuriomovil.View.FragmentRecyclerView;

import java.util.List;

/**
 * Created by Ciglesias on 28/03/2018.
 */

public class SitioPresenterImpl implements SitioPresenter {
    FragmentRecyclerView view;
    SitioIterator iterator;

    public SitioPresenterImpl(FragmentRecyclerView view) {
        this.view = view;
        iterator = new SitioIterator(this);
    }

    public void getListItems() {
        view.showLoading();
        iterator.getListItem();
    }

    @Override
    public void onErrorLoadingSitios() {
        view.dismissLoading();
        view.showErrorDialog();
    }

    @Override
    public void showListSitios(List<SitioItem> sitios) {
        view.updateList(sitios);
    }

    @Override
    public void onCompleteListSitios() {
        view.dismissLoading();
    }
}
