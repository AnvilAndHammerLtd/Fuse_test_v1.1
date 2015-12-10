package com.kyriakosalexandrou.fuse_test_v11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements IuiHelper {
    private static final String TAG = MainActivity.class.getName();
    private EditText mCompanyNameUserInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        setListeners();
    }

    @Override
    public void bindViews() {
        mCompanyNameUserInput = (EditText) findViewById(R.id.company_name_user_input);
    }

    @Override
    public void setListeners() {
        mCompanyNameUserInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.v(TAG, "kiki onEditorAction");

                switch (actionId) {
                    case EditorInfo.IME_ACTION_DONE:
                        // TODO send request
                        return true;
                    default:
                        return false;
                }

            }
        });
    }
}