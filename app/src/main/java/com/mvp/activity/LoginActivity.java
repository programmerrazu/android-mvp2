package com.mvp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mvp.R;
import com.mvp.presenter.LoginPresenter;
import com.mvp.presenter.LoginPresenterImpl;
import com.mvp.view.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView {

    EditText etUserName, etPassword;
    Button btnLogin;
    ProgressBar pbLoader;
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName = (EditText) findViewById(R.id.et_user_name);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        pbLoader = (ProgressBar) findViewById(R.id.pb_loader);

        loginPresenter = new LoginPresenterImpl(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.validateCredential(etUserName.getText().toString().trim(), etPassword.getText().toString().trim());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.onDestroy();
    }

    @Override
    public void showProgress() {
        pbLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoader.setVisibility(View.GONE);
    }

    @Override
    public void setUserNameError() {
        etUserName.setError("UserName empty");
    }

    @Override
    public void setPasswordError() {
        etPassword.setError("Password empty");
    }

    @Override
    public void navigateMain() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void showAlert(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}