package com.iglesias.c.mercuriomovil.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.iglesias.c.mercuriomovil.R;

import java.util.List;

public class PatternActivity extends AppCompatActivity {
    PatternLockView mPatternLockView;
    TextView txtIngrese;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Patrón");
        mPatternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
        mPatternLockView.addPatternLockListener(mPatternLockViewListener);

        txtIngrese = findViewById(R.id.id_txt_ingrese);
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

            if (PatternLockUtils.patternToString(mPatternLockView, pattern).trim().equalsIgnoreCase("3157")) {
                Intent i = new Intent(PatternActivity.this, MainActivity.class);
                startActivity(i);
            } else {
                txtIngrese.setVisibility(View.VISIBLE);
                mPatternLockView.clearPattern();
            }
        }

        @Override
        public void onCleared() {

        }
    };
}
