package com.iglesias.c.mercuriomovil.Iterator;

import com.iglesias.c.mercuriomovil.Pojo.SitioItem;
import com.iglesias.c.mercuriomovil.Presenter.SitioPresenterImpl;

import java.util.ArrayList;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ciglesias on 28/03/2018.
 */

public class SitioIterator {
    SitioPresenterImpl presenter;

    public SitioIterator(SitioPresenterImpl presenter) {
        this.presenter = presenter;
    }

    public void getListItem(){
        ArrayList<SitioItem> list = new ArrayList<>();

        list.add(new SitioItem("Nombre sitio 1","123456","123456789","cra 8 # 4 - 12 (Bucaramanga - santander)",1));
        list.add(new SitioItem("Nombre sitio 2","4654321","123456789","cra 8 # 4 - 12 (San gil - santander)",1));
        list.add(new SitioItem("Nombre sitio 3","321231654","123456789","cra 8 # 4 - 12 (Fundacion - Magdalena)",1));
        list.add(new SitioItem("Nombre sitio 4","321354897","123456789","cra 8 # 4 - 12 (Reten - Magdalena)",1));
        list.add(new SitioItem("Nombre sitio 5","321654987","123456789","cra 8 # 4 - 12 (Aracataca - Magdalena)",1));
        list.add(new SitioItem("Nombre sitio 6","6548213","123456789","cra 8 # 4 - 12 (Santa marta - Magdalena)",1));
        list.add(new SitioItem("Nombre sitio 7","254894154","123456789","cra 8 # 4 - 12 (Giron - santander)",1));
        list.add(new SitioItem("Nombre sitio 8","2579546523","123456789","cra 8 # 4 - 12 (Florodablanca - santander)",1));

        presenter.showListSitios(list);
        presenter.onCompleteListSitios();
    }
}
