package com.iglesias.c.mercuriomovil.Presenter;

import com.iglesias.c.mercuriomovil.Iterator.MainMenuIterator;
import com.iglesias.c.mercuriomovil.R;
import com.iglesias.c.mercuriomovil.View.MainMenuActivityView;

/**
 * Created by Ciglesias on 22/04/2018.
 */

public class MainMenuActivityPresenterImpl  implements  MainMenuPresenter{
    private MainMenuActivityView view;
    private MainMenuIterator iterator;

    public MainMenuActivityPresenterImpl(MainMenuActivityView view) {

        this.view = view;
        iterator = new MainMenuIterator(this, view.getContext());
    }

    public void onNavigationItemSelected(int id) {
        if (id != R.id.id_menu_importar) {
            view.showFragment(id);
        } else {
            view.showDialogFile();
        }
    }

    public void importData(String path) {
        view.showLoading("Cargando base de datos...");
        iterator.imporData(path);
    }
}
