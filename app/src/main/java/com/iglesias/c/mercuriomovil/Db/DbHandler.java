package com.iglesias.c.mercuriomovil.Db;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

/**
 * Created by Ciglesias on 18/02/2018.
 */

public class DbHandler {

    private DbHelper dbHelper;
    private static DbHandler instance = null;
    public String TAG = getClass().getName();

    protected DbHandler(Context context) {
        Log.e("DbHandler", "creo instancia handler");
        dbHelper = new DbHelper(context);
    }

    public static DbHandler getInstance(Context context) {
        if (instance == null) {
            instance = new DbHandler(context);
        }
        Log.e("DbHandler", "uso instancia handler");
        return instance;
    }

    public Cursor execSql(String sql) {
        return dbHelper.execSql(sql);
    }

    public int verificaUsuario(String usr, String pass) {
        int result = 0;
        Cursor c = null;

        try {
            int cnt = 0;
            String query = "Select " + UserDb.KEY_ID + " as id From " + UserDb.TABLE +
                    " where " + UserDb.KEY_PASS + " = '" + pass + "' and " + UserDb.KEY_NICK + " = '" + usr + "';";
            c = dbHelper.execSql(query);

            if (c.moveToFirst()) {
                if (!c.isNull(c.getColumnIndex("id"))) {
                    result = c.getInt(c.getColumnIndex("id"));
                }
            }


        } catch (Exception e) {
            if (c != null && !c.isClosed()) {
                c.close();
            }
            Log.e(TAG, "Error validando usuario: " + e.getMessage());
            e.printStackTrace();
        }


        return result;
    }

    public String getPatterUser(int id) {
        String result = "";
        Cursor c = null;
        int i = 0;
        try {

            String query = "Select " + UserDb.KEY_PATTERN + ", " + UserDb.KEY_ID + "  From " + UserDb.TABLE +
                    " where " + UserDb.KEY_ID + " = " + id + ";";

            c = dbHelper.execSql(query);

            if (c.moveToFirst()) {

                if (!c.isNull(c.getColumnIndex("pattern"))) {
                    result = c.getString(c.getColumnIndex("pattern"));
                }
                if (!c.isNull(c.getColumnIndex(UserDb.KEY_ID))) {
                    i = c.getInt(c.getColumnIndex(UserDb.KEY_ID));

                    Log.e(TAG, "i: " + i + " c: " + c.getString(0));
                }
            }


        } catch (Exception e) {
            if (c != null && !c.isClosed()) {
                c.close();
            }
            Log.e(TAG, "Error obteniendo patron de desbloqueo: " + e.getMessage());
            e.printStackTrace();
        }


        return result;
    }


}
