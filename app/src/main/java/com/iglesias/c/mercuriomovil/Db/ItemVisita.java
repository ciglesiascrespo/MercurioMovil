package com.iglesias.c.mercuriomovil.Db;

/**
 * Created by Ciglesias on 22/04/2018.
 */

public class ItemVisita {
    public static final String TABLE = "items_visita";

    public static final String KEY_ID = "id_item_visita";
    public static final String KEY_ID_VISITA = "id_visita";
    public static final String KEY_DESCRIPCION = "descripcion";
    public static final String KEY_COMANDO_SQL = "comando_sql";
    public static final String KEY_ORDEN = "orden";
    public static final String KEY_CAMPO_VALOR = "campo_valor";
    public static final String KEY_CAMPO_VISUALIZACION = "campo_visualizacion";
    public static final String KEY_DEPENDE_DE = "depende_de";


    public static String getCreateTable() {
        String query = "CREATE TABLE `" + TABLE + "` ( `" + KEY_ID + "` INTEGER, `" + KEY_ID_VISITA + "` INTEGER, `" + KEY_DESCRIPCION + "` TEXT, `"
                + KEY_COMANDO_SQL + "` TEXT, `" + KEY_CAMPO_VISUALIZACION + "` TEXT, `" + KEY_DEPENDE_DE + "` TEXT, `"
                + KEY_CAMPO_VALOR + "` TEXT, `" + KEY_ORDEN + "` INTEGER,  PRIMARY KEY(`" + KEY_ID + "`) )";

        return query;
    }
}
