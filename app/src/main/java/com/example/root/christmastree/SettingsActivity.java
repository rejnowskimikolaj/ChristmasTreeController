package com.example.root.christmastree;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "prefs" ;
    @BindView((R.id.toolbar))
    Toolbar toolbar;

    @BindView(R.id.http_edit)
    TextInputEditText httpInput;

    @BindView(R.id.port_edit)
    TextInputEditText portInput;

    @BindView(R.id.port_layout)
    TextInputLayout passwordLayout;
    @BindView(R.id.http_layout)
    TextInputLayout loginLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    private void setTextWatchers(){
        setTextWatcher(httpInput,loginLayout);
        setTextWatcher(portInput,passwordLayout);
    }
    private void setTextWatcher(final TextInputEditText editText, final TextInputLayout inputLayout) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (inputLayout.isErrorEnabled()) {
                    inputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @OnClick(R.id.save_button)
    public void onLoginButtonClicked(){
        boolean error = false;

        if (TextUtils.isEmpty(httpInput.getText().toString())) {
            loginLayout.setError("http cannot be empty!");
            error = true;
        }

        if (TextUtils.isEmpty(portInput.getText().toString())) {
            passwordLayout.setError("url cannot be empty!");
            error = true;
        }

        if (!error) {

            saveSettings();
        }
    }

    private void saveSettings() {
        String port = portInput.getText().toString();
        String http = httpInput.getText().toString();

        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("port", port);
        editor.putString("http", http);
        editor.apply();
    }


}
