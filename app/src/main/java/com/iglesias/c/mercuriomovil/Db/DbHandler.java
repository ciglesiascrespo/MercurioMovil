package com.iglesias.c.mercuriomovil.Db;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.iglesias.c.mercuriomovil.Pojo.ItemVisita;
import com.iglesias.c.mercuriomovil.Pojo.SitioItem;

import java.util.ArrayList;

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
        Log.e(TAG,sql);
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
        } finally {
            if (c != null && !c.isClosed()) {
                c.close();
            }
        }


        return result;
    }

    public ArrayList<SitioItem> getListSitioItems() {
        ArrayList<SitioItem> list = new ArrayList<>();
        String sql = "SELECT v.id_visita, f.id_formulario,f.nombre " +
                "FROM visitas as v " +
                "inner join formularios  as f on(v.id_formulario = f.id_formulario);";
        Cursor c = null;
        try {

            c = dbHelper.execSql(sql);

            if (c.moveToFirst()) {
                do {

                    int id = 0;
                    String nombre = "";

                    if (!c.isNull(c.getColumnIndex(VisitaDb.KEY_ID))) {
                        id = c.getInt(c.getColumnIndex(VisitaDb.KEY_ID));
                    }

                    if (!c.isNull(c.getColumnIndex(FormularioDb.KEY_NOMBRE))) {
                        nombre = c.getString(c.getColumnIndex(FormularioDb.KEY_NOMBRE));
                    }

                    SitioItem item = new SitioItem(nombre, id);
                    list.add(item);

                } while (c.moveToNext());

            }
        } catch (Exception e) {
            e.printStackTrace();
            if (c != null && !c.isClosed()) {
                c.close();
            }

        } finally {
            if (c != null && !c.isClosed()) {
                c.close();
            }
        }
        return list;
    }

    public ArrayList<ItemVisita> getListitemsVisita(int idVisita) {
        ArrayList<ItemVisita> list = new ArrayList<>();

        String sql = "SELECT  id_item_visita,  id_visita, descripcion,  comando_sql,  campo_valor,  campo_visualizacion, " +
                " depende_de FROM items_visita;";// where id_visita = " + idVisita + " order by orden;";
        Cursor c = null;
        try {

            c = dbHelper.execSql(sql);

            if (c.moveToFirst()) {
                do {

                    int idItemVisita = -1;
                    String descripcion = "";
                    String comandoSql = "";
                    int campoValor = -1;
                    String campoVisualizacion = "";
                    String dependeDe = "";

                    if (!c.isNull(c.getColumnIndex(ItemVisitaDb.KEY_ID))) {
                        idItemVisita = c.getInt(c.getColumnIndex(ItemVisitaDb.KEY_ID));
                    }

                    if (!c.isNull(c.getColumnIndex(ItemVisitaDb.KEY_CAMPO_VALOR))) {
                        campoValor = c.getInt(c.getColumnIndex(ItemVisitaDb.KEY_CAMPO_VALOR));
                    }

                    if (!c.isNull(c.getColumnIndex(ItemVisitaDb.KEY_DESCRIPCION))) {
                        descripcion = c.getString(c.getColumnIndex(ItemVisitaDb.KEY_DESCRIPCION));
                    }

                    if (!c.isNull(c.getColumnIndex(ItemVisitaDb.KEY_COMANDO_SQL))) {
                        comandoSql = c.getString(c.getColumnIndex(ItemVisitaDb.KEY_COMANDO_SQL));
                    }

                    if (!c.isNull(c.getColumnIndex(ItemVisitaDb.KEY_CAMPO_VISUALIZACION))) {
                        campoVisualizacion = c.getString(c.getColumnIndex(ItemVisitaDb.KEY_CAMPO_VISUALIZACION));
                    }

                    if (!c.isNull(c.getColumnIndex(ItemVisitaDb.KEY_DEPENDE_DE))) {
                        dependeDe = c.getString(c.getColumnIndex(ItemVisitaDb.KEY_DEPENDE_DE));
                    }

                    ItemVisita item = new ItemVisita(idItemVisita, idVisita, descripcion, comandoSql, campoValor, campoVisualizacion, dependeDe);
                    list.add(item);

                } while (c.moveToNext());

            }
        } catch (Exception e) {
            e.printStackTrace();
            if (c != null && !c.isClosed()) {
                c.close();
            }

        } finally {
            if (c != null && !c.isClosed()) {
                c.close();
            }
        }
        return list;
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
