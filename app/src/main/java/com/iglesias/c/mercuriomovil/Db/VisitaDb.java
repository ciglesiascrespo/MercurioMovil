package com.iglesias.c.mercuriomovil.Db;

/**
 * Created by Ciglesias on 22/04/2018.
 */

public class VisitaDb {
    public static final String KEY_ID = "id_visita";
    public static  final String KEY_ID_FORMULARIO = "id_formulario";
    public static final String TABLE ="visitas";


    public static String getCreateTable() {
        String query = "CREATE TABLE `" + TABLE + "` " +
                " (`" + KEY_ID + "` INTEGER, `" + KEY_ID_FORMULARIO + "`, PRIMARY KEY(`" + KEY_ID + "`) )";

        return query;
    }
}
