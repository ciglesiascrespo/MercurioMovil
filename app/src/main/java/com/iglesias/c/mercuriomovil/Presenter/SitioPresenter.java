package com.iglesias.c.mercuriomovil.Presenter;

import com.iglesias.c.mercuriomovil.Pojo.SitioItem;

import java.util.List;

/**
 * Created by Ciglesias on 28/03/2018.
 */

public interface SitioPresenter {
    void onErrorLoadingSitios();

    void showListSitios(List<SitioItem> sitios);

    void onCompleteListSitios();
}
