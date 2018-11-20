package com.mvp.easton.mvp.presenter;

public interface BasePresenter<T> {

    void attachView(T view);

    void detachView();

}
