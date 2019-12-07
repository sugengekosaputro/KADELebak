package com.inspektorat.kadelebak.view.kade_forum.presenter;

import com.inspektorat.kadelebak.model.ListUser;
import com.inspektorat.kadelebak.model.User;
import com.inspektorat.kadelebak.view.kade_forum.data.ForumCache;
import com.inspektorat.kadelebak.view.kade_forum.view.ForumView;

import java.util.List;

import io.reactivex.Observable;

public class ForumPresenter {

    private ForumView.View view;


    public ForumPresenter(ForumView.View view) {
        this.view = view;
    }

    public void initialize() {
        this.getData();
    }

    private void showData(List<User> users) {
        Observable<User> observable = Observable.fromIterable(users);
        view.showDataVillage(observable.toList().blockingGet());
    }

    private void getData() {
        ForumPresenter.this.showData(new ListUser().getUsers());
    }


}
