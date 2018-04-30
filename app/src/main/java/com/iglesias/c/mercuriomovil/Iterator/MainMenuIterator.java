package com.iglesias.c.mercuriomovil.Iterator;

import android.content.Context;
import android.util.Log;

import com.iglesias.c.mercuriomovil.Db.DbHandler;
import com.iglesias.c.mercuriomovil.Presenter.MainMenuPresenter;
import com.iglesias.c.mercuriomovil.Utils.UtilsFiles;

/**
 * Created by Ciglesias on 25/04/2018.
 */

public class MainMenuIterator {
    MainMenuPresenter presenter;
    DbHandler dbHandler;
    Context context;

    public MainMenuIterator(MainMenuPresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
        dbHandler = DbHandler.getInstance(context);
    }

    public void imporData(String path) {
        String sqls[] = UtilsFiles.readFile(path).split("<#");

        for (int i = 0; i < sqls.length; i++) {
            dbHandler.execSql(sqls[i]);
        }
        Log.e("Iterator", "Registros insertados");

    }
}
