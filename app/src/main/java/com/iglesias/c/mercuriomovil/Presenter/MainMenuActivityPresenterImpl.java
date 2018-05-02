package com.iglesias.c.mercuriomovil.Presenter;

import com.iglesias.c.mercuriomovil.Iterator.MainMenuIterator;
import com.iglesias.c.mercuriomovil.R;
import com.iglesias.c.mercuriomovil.View.MainMenuActivityView;

/**
 * Created by Ciglesias on 22/04/2018.
 */

public class MainMenuActivityPresenterImpl implements MainMenuPresenter {
    private MainMenuActivityView view;
    private MainMenuIterator iterator;

    public MainMenuActivityPresenterImpl(MainMenuActivityView view) {

        this.view = view;
        iterator = new MainMenuIterator(this, view.getContext());
    }

    public void onNavigationItemSelected(int id) {
        if (id != R.id.id_menu_importar) {
            view.showFragment(id);
            view.closeDrawer();
        } else {
            view.checkPermission();

        }
    }

    public void importData(String path) {
        view.closeDrawer();
        view.showLoading("Cargando base de datos...");
        iterator.imporData(path);
    }

    @Override
    public void setMaxLoading(int i) {
        view.setMaxProgress(i);
    }

    @Override
    public void setProgress(int i) {
        view.setProgressLoading(i);
    }

    @Override
    public void onCompleteImporData() {
        view.hideLoading();
    }

    @Override
    public void showDialogErrorImport() {
        view.showDialogErrorImport();
    }
}
