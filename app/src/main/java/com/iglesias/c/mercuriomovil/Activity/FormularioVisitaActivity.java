package com.iglesias.c.mercuriomovil.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.iglesias.c.mercuriomovil.Fragment.SitioFragment;
import com.iglesias.c.mercuriomovil.R;
import com.iglesias.c.mercuriomovil.View.FormularioVisitaView;

public class FormularioVisitaActivity extends AppCompatActivity implements FormularioVisitaView{
    private int idVisita = 0;
    private String nombreVisita="";

    private TextView txtNameVisita;
    private ImageButton imgBtnBack,imgBtnFwrd;
    private SeekBar seekBarVisita;
    private ProgressDialog loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_visita);

        idVisita = getIntent().getIntExtra(SitioFragment.EXTRA_ID_VISITA,0);
        nombreVisita = getIntent().getStringExtra(SitioFragment.EXTRA_NAME_VISITA);

        setupViews();

    }

    private void setupLoading() {
        loading = new ProgressDialog(getApplicationContext());
        loading.setCancelable(false);
        loading.setMessage(getResources().getString(R.string.str_msj_loading_items_visita));
    }


    void setupViews(){
        txtNameVisita = findViewById(R.id.id_txt_title_visita);
        imgBtnBack = findViewById(R.id.id_btn_back_visita);
        imgBtnFwrd = findViewById(R.id.id_btn_forward_visita);
        seekBarVisita = findViewById(R.id.id_seekbar_visita);

        txtNameVisita.setText(nombreVisita);

    }

    @Override
    public void showLoading() {
        if (loading != null && !loading.isShowing()) {
            loading.show();
        }
    }

    @Override
    public void hideLoading() {
        if (loading != null && loading.isShowing()) {
            loading.dismiss();
        }
    }

    @Override
    public Context getContextApp() {
        return getApplicationContext();
    }


    @Override
    public void showDialogError(String msj) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext(), R.style.myDialog);

        builder.setTitle(getResources().getString(R.string.str_title_alert_error_items_visita));
        builder.setMessage(getResources().getString(R.string.str_msj_alert_error_items_visita));
        builder.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
