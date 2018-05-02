package com.iglesias.c.mercuriomovil.Pojo;

/**
 * Created by dell on 02/05/2018.
 */

public class ItemVisita {
    private int idItemVisita;
    private int idVisita;
    private String descripcion;
    private String comandoSql;
    private int campoValor;
    private String campoVisualizacion;
    private String dependeDe;

    public ItemVisita(int idItemVisita, int idVisita, String descripcion, String comandoSql, int campoValor, String campoVisualizacion, String dependeDe) {
        this.idItemVisita = idItemVisita;
        this.idVisita = idVisita;
        this.descripcion = descripcion;
        this.comandoSql = comandoSql;
        this.campoValor = campoValor;
        this.campoVisualizacion = campoVisualizacion;
        this.dependeDe = dependeDe;
    }

    public int getIdItemVisita() {
        return idItemVisita;
    }

    public int getIdVisita() {
        return idVisita;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getComandoSql() {
        return comandoSql;
    }

    public int getCampoValor() {
        return campoValor;
    }

    public String getCampoVisualizacion() {
        return campoVisualizacion;
    }

    public String getDependeDe() {
        return dependeDe;
    }
}
