package com.mvp.presenter;

public interface LoginPresenter {
    void validateCredential(String userName, String password);

    void onDestroy();
}