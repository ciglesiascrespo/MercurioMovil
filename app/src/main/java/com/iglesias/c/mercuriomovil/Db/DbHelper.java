package com.iglesias.c.mercuriomovil.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.iglesias.c.mercuriomovil.Pojo.ItemVisita;

/**
 * Created by Ciglesias on 18/02/2018.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "data";
    private final String TAG = getClass().getName();

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e(TAG, "Creo DbHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(UserDb.getCreateTable());
        db.execSQL(VisitaDb.getCreateTable());
        db.execSQL(FormularioDb.getCreateTable());
        db.execSQL(ItemVisitaDb.getCreateTable());

        insertData(db);
    }

    private void insertData(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(UserDb.KEY_ID, 1);
        cv.put(UserDb.KEY_NICK, "nemaroto");
        cv.put(UserDb.KEY_PASS, "123");

        db.insert(UserDb.TABLE, null, cv);

        ContentValues cvc = new ContentValues();
        cvc.put(UserDb.KEY_ID, 2);
        cvc.put(UserDb.KEY_NICK, "ci");
        cvc.put(UserDb.KEY_PASS, "123");
        cvc.put(UserDb.KEY_PATTERN, "3157");
        db.insert(UserDb.TABLE, null, cvc);

        cvc.clear();

        cvc.put(VisitaDb.KEY_ID, 16);
        cvc.put(VisitaDb.KEY_ID_FORMULARIO, 25);
        db.insert(VisitaDb.TABLE, null, cvc);

        cvc.clear();

        cvc.put(FormularioDb.KEY_ID, 25);
        cvc.put(FormularioDb.KEY_NOMBRE, "Pae secretaria gobernación ");
        cvc.put(FormularioDb.KEY_ACTIVO, "S");
        cvc.put(FormularioDb.KEY_FECHA_HORA, "2018-04-22 18:10:25");
        cvc.put(FormularioDb.KEY_ID_TIPO_FORMULARIO, 2);
        db.insert(FormularioDb.TABLE, null, cvc);

        cvc.clear();

        cvc.put(ItemVisitaDb.KEY_ID, 35);
        cvc.put(ItemVisitaDb.KEY_ID_VISITA, 16);
        cvc.put(ItemVisitaDb.KEY_ORDEN, 1);

        db.insert(ItemVisitaDb.TABLE,null,cvc);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + UserDb.TABLE);

        onCreate(db);
    }

    public long insert(String tableName, ContentValues cv) {
        long i = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            i = db.insert(tableName, null, cv);
            //if (db.isOpen()) db.close();
        } catch (Exception e) {
            //db.close();
            Log.e(TAG, "Error insertando en la base de datos: " + e.getMessage());
            e.printStackTrace();

        }
        return i;

    }

    public Cursor execSql(String query) {
        Cursor c = null;
        SQLiteDatabase db = this.getWritableDatabase();

        c = db.rawQuery(query, null);
        // db.close();


        return c;
    }

    public void update(String tableName, ContentValues cv, String condition) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.update(tableName, cv, condition, null);
            //  db.close();
        } catch (Exception e) {
            // if (db.isOpen()) db.close();
            Log.e(TAG, "Error update: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void delete(String tableName, String condition) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            db.delete(tableName, condition, null);
            //  db.close();
        } catch (Exception e) {
            //  if (db.isOpen()) db.close();
            Log.e(TAG, "Error delete: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
