package com.mvp.easton.mvp.presenter.impl;

import com.mvp.easton.mvp.model.GithubService;
import com.mvp.easton.mvp.model.Repo;
import com.mvp.easton.mvp.presenter.MainPresenter;
import com.mvp.easton.mvp.view.MainBaseView;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenterImpl implements MainPresenter {

    private MainBaseView mainBaseView;
    private List<Repo> repoList;
    private Subscription subscription;

    @Override
    public void loadGitHubJava() {
        mainBaseView.showProgress();
        String url = "http://github.laowch.com/json/java_daily";
        subscription = GithubService.Factory.create().javaRepositories(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onCompleted() {
                        if (repoList != null) {
                            mainBaseView.showRecyclerView(repoList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainBaseView.showErrorMessage();

                    }

                    @Override
                    public void onNext(List<Repo> repos) {
                        repoList = repos;

                    }
                });


    }

    @Override
    public void attachView(MainBaseView view) {
        this.mainBaseView = view;

    }

    @Override
    public void detachView() {
        mainBaseView = null;
        if (subscription != null) {
            subscription.unsubscribe();
        }

    }
}
