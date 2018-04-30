package com.iglesias.c.mercuriomovil.Db;

/**
 * Created by Ciglesias on 18/02/2018.
 */

public class UserDb {
    public static final String TABLE = "usuarios";

    public static final String KEY_ID = "id_usuario";
    public static final String KEY_NICK = "nick";
    public static final String KEY_PASS = "pass";
    public static final String KEY_PATTERN = "pattern";

    public static final int ID_USUARIO_CyM = 1;

    public static String getCreateTable() {
        String query = "CREATE TABLE `" + TABLE + "` " +
                " (`" + KEY_ID + "` INTEGER, `" + KEY_NICK + "` TEXT, `" + KEY_PASS + "` TEXT, `" + KEY_PATTERN +"` TEXT, PRIMARY KEY(`" + KEY_ID + "`) )";

        return query;
    }
}
