package com.mvp.easton.mvp.view;

public interface BaseView<T> {
    void showProgress();

    void showErrorMessage();

    void showRecyclerView(T t);
}
