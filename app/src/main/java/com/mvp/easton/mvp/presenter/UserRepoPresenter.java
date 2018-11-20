package com.mvp.easton.mvp.presenter;

import com.mvp.easton.mvp.view.UserRepoBaseView;

/**
 * <p>Description:
 * 只为UserRepoActivity提供业务逻辑
 *
 * @author xzhang
 */
public interface UserRepoPresenter extends BasePresenter<UserRepoBaseView> {
    //添加UserRepoPresenter中特有的业务逻辑
    void loadGitHubUserRepo(String userName);
}
