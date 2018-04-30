package com.iglesias.c.mercuriomovil.Db;

/**
 * Created by Ciglesias on 18/02/2018.
 */

public class EvidenciaDb {
    public static final String TABLE = "evidencias";

    public static final String KEY_ID_FORMULARIO = "id_formulario";
    public static final String KEY_RUTA_ARCHIVO = "ruta_archivo";
    public static final String KEY_ESTADO = "estado";
    public static final String KEY_TIPO = "tipo";


    public static String getCreateTable() {
        String query = "CREATE TABLE `" + TABLE + "` ( `" + KEY_ID_FORMULARIO + "` INTEGER, `" + KEY_RUTA_ARCHIVO + "` TEXT, `" + KEY_ESTADO + "` INTEGER, ´"+KEY_TIPO+"´ INTEGER )";

        return query;
    }
}
