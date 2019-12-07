package com.inspektorat.kadelebak.view.kade_complaint.presenter;

import com.inspektorat.kadelebak.model.ListUser;
import com.inspektorat.kadelebak.model.User;
import com.inspektorat.kadelebak.view.kade_complaint.view.ComplaintView;
import com.inspektorat.kadelebak.view.kade_forum.presenter.ForumPresenter;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

public class ComplaintPresenter {

    private ComplaintView.View viewFitur;

    public ComplaintPresenter(ComplaintView.View viewFitur) {
        this.viewFitur = viewFitur;
    }

    public void initialize() {
        this.getData();
    }

    private void showData(List<User> users) {
        Observable<User> observable = Observable.fromIterable(users);
        viewFitur.showDataComplain(observable.toList().blockingGet());
    }

    private void getData() {
        ComplaintPresenter.this.showData(new ListUser().getUsers());
    }
}
