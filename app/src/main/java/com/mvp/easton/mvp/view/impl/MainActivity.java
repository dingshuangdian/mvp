package com.mvp.easton.mvp.view.impl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mvp.easton.mvp.R;
import com.mvp.easton.mvp.adapter.RepoListAdapter;
import com.mvp.easton.mvp.model.Repo;
import com.mvp.easton.mvp.presenter.impl.MainPresenterImpl;
import com.mvp.easton.mvp.view.MainBaseView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainBaseView {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private TextView text_description;
    private TextView text_info;
    private ProgressBar progressBar;
    private MainPresenterImpl mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycler);
        text_description = findViewById(R.id.text_description);
        progressBar = findViewById(R.id.progress);
        text_info = findViewById(R.id.text_info);
        //setSupportActionBar(toolbar);
        text_description.setText("GitHub Java");
        mainPresenter = new MainPresenterImpl();
        mainPresenter.attachView(this);
        mainPresenter.loadGitHubJava();

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        text_info.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);

    }

    @Override
    public void showErrorMessage() {
        progressBar.setVisibility(View.GONE);
        text_info.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

    }

    @Override
    public void showRecyclerView(List<Repo> repos) {
        progressBar.setVisibility(View.GONE);
        text_info.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        RepoListAdapter adapter = new RepoListAdapter(this, repos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }
}
