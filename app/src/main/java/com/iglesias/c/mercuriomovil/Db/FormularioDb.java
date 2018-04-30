package com.iglesias.c.mercuriomovil.Db;

/**
 * Created by Ciglesias on 18/02/2018.
 */

public class FormularioDb {
    public static final String TABLE = "formularios";

    public static final String KEY_ID = "id_formulario";
    public static final String KEY_NOMBRE = "nombre";
    public static final String KEY_ACTIVO = "activo";
    public static final String KEY_FECHA_HORA = "fecha_creacion";
    public static final String KEY_ID_TIPO_FORMULARIO = "id_tipo_formulario";

    public static String getCreateTable() {
        String query = "CREATE TABLE `" + TABLE + "` ( `" + KEY_ID + "` INTEGER, `" + KEY_FECHA_HORA + "` TEXT, `" + KEY_NOMBRE + "` TEXT, `"
                + KEY_ACTIVO + "` TEXT, `" + KEY_ID_TIPO_FORMULARIO + "` INTEGER,  PRIMARY KEY(`" + KEY_ID + "`) )";

        return query;
    }


}
