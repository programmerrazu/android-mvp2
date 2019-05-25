package com.mvp.presenter;

import com.mvp.model.LoginModel;
import com.mvp.model.LoginViewImpl;
import com.mvp.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter, LoginModel.OnLoginFinishedListener {

    LoginView loginView;
    LoginModel loginModel;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginModel = new LoginViewImpl();
    }

    @Override
    public void validateCredential(String userName, String password) {
        if (loginView != null) {
            loginView.showProgress();
            loginModel.login(userName, password, this);
        }
    }

    @Override
    public void onDestroy() {
        if (loginView != null) {
            loginView = null;
        }
    }

    @Override
    public void onUserNameError() {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.setUserNameError();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.setPasswordError();
        }
    }

    @Override
    public void onSuccess() {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.navigateMain();
        }
    }

    @Override
    public void onFailure(String message) {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.showAlert(message);
        }
    }
}