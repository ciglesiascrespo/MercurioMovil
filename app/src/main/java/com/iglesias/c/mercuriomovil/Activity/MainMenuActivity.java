package com.iglesias.c.mercuriomovil.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.iglesias.c.mercuriomovil.Fragment.FormulariosFragment;
import com.iglesias.c.mercuriomovil.Fragment.SitioFragment;
import com.iglesias.c.mercuriomovil.Presenter.MainMenuActivityPresenterImpl;
import com.iglesias.c.mercuriomovil.R;
import com.iglesias.c.mercuriomovil.View.MainMenuActivityView;

public class MainMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainMenuActivityView {

    private final int REQUEST_CODE_ARCHIVO_BD = 1;
    MainMenuActivityPresenterImpl presenter;
    private ProgressDialog loading;
    public static final int MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        presenter = new MainMenuActivityPresenterImpl(this);
        setupViews();
        setupLoading();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setupLoading() {
        loading = new ProgressDialog(this);
        loading.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        loading.setCancelable(true);
    }

    @Override
    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE);
        } else {
            showDialogFile();
        }
    }

    private void setupViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        onNavigationItemSelected(null);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item != null ? item.getItemId() : 0;
        presenter.onNavigationItemSelected(id);
        return true;
    }


    @Override
    public void showDialogFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("text/plain");
        startActivityForResult(Intent.createChooser(intent, "Seleccione archivo"), REQUEST_CODE_ARCHIVO_BD);
    }

    @Override
    public void showFragment(int id) {
        Fragment fragment = null;
        switch (id) {
            case R.id.id_menu_sitio:
                fragment = new SitioFragment();
                break;
            default:
                fragment = new FormulariosFragment();
                break;
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.id_frame_container, fragment);
            ft.commit();
        }

    }

    @Override
    public void closeDrawer() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void showLoading(String msj) {
        loading.setMessage(msj);
        if (loading != null && !loading.isShowing()) {
            loading.show();
        }
    }

    @Override
    public void setProgressLoading(int i) {
        loading.setProgress(i);
    }

    @Override
    public void setMaxProgress(int i) {
        loading.setMax(i);
    }

    @Override
    public void hideLoading() {
        if (loading != null && loading.isShowing()) {
            loading.dismiss();
        }
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_ARCHIVO_BD:
                    presenter.importData(data.getData().getPath());
                    break;
            }

        }
    }

    private void showDialogPermisoDenegado() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(getContext().getResources().getString(R.string.str_title_alert_permiso))
                .setMessage(getResources().getString(R.string.str_msj_alert_permiso))
                .setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        finish();
                    }
                });
        dialog.show();
    }

    @Override
    public void showDialogErrorImport() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(getContext().getResources().getString(R.string.str_title_alert_error))
                .setMessage(getResources().getString(R.string.str_msj_alert_error_guardando_formulario))
                .setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                    }
                });
        dialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE: {

                if (grantResults.length > 0
                        && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    showDialogFile();
                } else {

                    showDialogPermisoDenegado();
                }
                break;

            }

        }

    }
}
