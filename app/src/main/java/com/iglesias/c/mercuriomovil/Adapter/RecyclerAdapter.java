package com.iglesias.c.mercuriomovil.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iglesias.c.mercuriomovil.Activity.FormularioVisitaActivity;
import com.iglesias.c.mercuriomovil.Pojo.SitioItem;
import com.iglesias.c.mercuriomovil.R;

import java.util.List;

/**
 * Created by Ciglesias on 27/12/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MovieViewHolder> {
    List<SitioItem> listItems;

    Context context;

    public RecyclerAdapter(List<SitioItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;

    }


    public void updateListSitios(List<SitioItem> listItems) {
        this.listItems = listItems;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sitio, parent, false);
        return new RecyclerAdapter.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.MovieViewHolder holder, final int position) {
        final SitioItem itemList = listItems.get(position);

        holder.txtNombre.setText(itemList.getNombre() + " - " + itemList.getCodigo());
        holder.txtDireccion.setText(itemList.getDireccion());
        holder.txtTelefono.setText(itemList.getTelefono());

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, FormularioVisitaActivity.class);
                i.putExtra("name", itemList.getNombre());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNombre, txtTelefono, txtDireccion;
        private View v;

        public MovieViewHolder(View itemView) {
            super(itemView);

            txtNombre = itemView.findViewById(R.id.id_txt_item_sitio_nombre);
            txtTelefono = itemView.findViewById(R.id.id_txt_item_sitio_telefono);
            txtDireccion = itemView.findViewById(R.id.id_txt_item_sitio_direccion);


            v = itemView;
        }
    }
}
