package com.iglesias.c.mercuriomovil.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.iglesias.c.mercuriomovil.Db.DbHandler;
import com.iglesias.c.mercuriomovil.Presenter.LoginPresenterImpl;
import com.iglesias.c.mercuriomovil.R;
import com.iglesias.c.mercuriomovil.View.LoginView;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements LoginView {
    public static final String EXTRA_ID_USUARIO = "id_usuario";
    TextInputLayout tilUser;
    TextInputLayout tilPass;
    TextInputEditText edtUser;
    TextInputEditText edtPass;
    Button btnLogin;
    LinearLayout linearLogin, linearPatter;
    PatternLockView mPatternLockView;
    TextView txtIngrese;
    private LoginPresenterImpl presenter;
    private ProgressDialog loading;
    private int idUsuario = -1;
    private String patternUsuario = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DbHandler.getInstance(this);
        presenter = new LoginPresenterImpl(this);
        setupViews();
        setupLoading();
        presenter.verificarUsuarioLogueado();
    }

    private void setupViews() {

        tilPass = findViewById(R.id.id_til_login_pass);
        tilUser = findViewById(R.id.id_til_login_user);
        edtPass = findViewById(R.id.id_edt_login_pass);
        edtUser = findViewById(R.id.id_edt_login_user);
        btnLogin = findViewById(R.id.id_btn_login_ingresar);
        mPatternLockView = findViewById(R.id.pattern_lock_view);
        mPatternLockView.addPatternLockListener(mPatternLockViewListener);

        txtIngrese = findViewById(R.id.id_txt_ingrese);
        linearLogin = findViewById(R.id.id_linear_login);
        linearPatter = findViewById(R.id.id_linear_pattern);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   showErrorLoginDialog();
                presenter.onClickBtnLogin(edtUser.getText().toString(), edtPass.getText().toString());
            }
        });

    }

    private PatternLockViewListener mPatternLockViewListener = new PatternLockViewListener() {
        @Override
        public void onStarted() {
            txtIngrese.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onProgress(List<PatternLockView.Dot> progressPattern) {

        }

        @Override
        public void onComplete(List<PatternLockView.Dot> pattern) {
            Log.d(getClass().getName(), "Pattern complete: " +
                    PatternLockUtils.patternToString(mPatternLockView, pattern));

            if (PatternLockUtils.patternToString(mPatternLockView, pattern).trim().equalsIgnoreCase(patternUsuario)) {
                goToMainActivity(idUsuario);
            } else {
                txtIngrese.setVisibility(View.VISIBLE);
                mPatternLockView.clearPattern();
            }
        }

        @Override
        public void onCleared() {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.id_menu_patron:

                linearPatter.setVisibility(linearPatter.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                linearLogin.setVisibility(linearLogin.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupLoading() {
        loading = new ProgressDialog(this);
        loading.setCancelable(false);
        loading.setMessage(getResources().getString(R.string.str_loading_login));
    }

    @Override
    public void showLoading() {
        if (!loading.isShowing()) loading.show();
    }

    @Override
    public void hideLoading() {
        if (loading.isShowing()) loading.dismiss();
    }

    @Override
    public void goToMainActivity(int id) {
        Intent i = new Intent(this, MainMenuActivity.class);
        i.putExtra(EXTRA_ID_USUARIO, id);
        startActivity(i);
        finish();
    }

    @Override
    public void showErrorLoginDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this, R.style.myDialog);

        builder.setTitle(getResources().getString(R.string.str_title_alert_error_login));
        builder.setMessage(getResources().getString(R.string.str_msj_alert_error_login));
        builder.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void showTilErrorUser(String msj) {
        tilUser.setError(msj);
    }

    @Override
    public void showTilErrorPass(String msj) {
        tilPass.setError(msj);
    }

    @Override
    public void showPattern(String pattern, int idUsuario) {
        this.patternUsuario = pattern;
        this.idUsuario = idUsuario;
        linearLogin.setVisibility(View.GONE);
        linearPatter.setVisibility(View.VISIBLE);
    }
}
