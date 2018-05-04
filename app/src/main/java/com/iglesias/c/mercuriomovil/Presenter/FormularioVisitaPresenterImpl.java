package com.iglesias.c.mercuriomovil.Presenter;

import com.iglesias.c.mercuriomovil.Iterator.FormularioVisitaIterator;
import com.iglesias.c.mercuriomovil.Pojo.ItemVisita;
import com.iglesias.c.mercuriomovil.View.FormularioVisitaView;

import java.util.List;

/**
 * Created by Ciglesias on 3/05/2018.
 */

public class FormularioVisitaPresenterImpl implements FormularioVisitaPresenter {
    FormularioVisitaView view;
    FormularioVisitaIterator iterator;

    public FormularioVisitaPresenterImpl(FormularioVisitaView view) {
        this.view = view;
        iterator = new FormularioVisitaIterator(this, view.getContextApp());
    }

    public void getItemsVisita(int idVisita) {
        view.showLoading();
        iterator.getItemsVisita(idVisita);

    }

    @Override
    public void onErrorLoadingItems() {
        view.hideLoading();
        view.showDialogError("Error cargando items de la visita.");

    }

    @Override
    public void onNextItemsVisita(List<ItemVisita> items) {
        view.setMaxSeekBar(items.size());
    }

    @Override
    public void onCompleteItemsVisita() {
        view.hideLoading();
    }


}
