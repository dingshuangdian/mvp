package com.mvp.easton.mvp.presenter.impl;

import com.mvp.easton.mvp.model.GithubService;
import com.mvp.easton.mvp.model.Repository;
import com.mvp.easton.mvp.presenter.UserRepoPresenter;
import com.mvp.easton.mvp.view.UserRepoBaseView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserRepoPresenterImpl implements UserRepoPresenter {
    private UserRepoBaseView userRepoBaseView;
    private List<Repository> repositoryList;

    @Override
    public void loadGitHubUserRepo(String userName) {
        userRepoBaseView.showProgress();
        GithubService.Factory.create().publicRepositories(userName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Repository>>() {
                    @Override
                    public void onCompleted() {
                        if (repositoryList != null) {
                            userRepoBaseView.showRecyclerView(repositoryList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        userRepoBaseView.showErrorMessage();

                    }

                    @Override
                    public void onNext(List<Repository> repositories) {
                        repositoryList = repositories;
                    }
                });
    }

    @Override
    public void attachView(UserRepoBaseView view) {
        this.userRepoBaseView = view;

    }

    @Override
    public void detachView() {
        this.userRepoBaseView = null;

    }
}
