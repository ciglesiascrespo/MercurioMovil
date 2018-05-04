package com.iglesias.c.mercuriomovil.Presenter;

import com.iglesias.c.mercuriomovil.Pojo.ItemVisita;

import java.util.List;

/**
 * Created by Ciglesias on 3/05/2018.
 */

public interface FormularioVisitaPresenter {
    void onErrorLoadingItems();

    void onNextItemsVisita(List<ItemVisita> items);

    void onCompleteItemsVisita();
}
