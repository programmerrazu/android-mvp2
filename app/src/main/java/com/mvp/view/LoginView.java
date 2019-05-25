package com.mvp.view;

public interface LoginView {

    void showProgress();

    void hideProgress();

    void setUserNameError();

    void setPasswordError();

    void navigateMain();

    void showAlert(String message);
}