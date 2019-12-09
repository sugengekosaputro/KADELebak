package com.inspektorat.kadelebak.view.kade_splash.presenter;

import android.content.Context;
import android.widget.Toast;

import com.inspektorat.kadelebak.Constant;
import com.inspektorat.kadelebak.data.MyPreferencesData;
import com.inspektorat.kadelebak.view.kade_splash.view.SplashView;

public class SplashPresenter {

    private SplashView view;
    private MyPreferencesData myPreferencesData;

    public SplashPresenter(SplashView view, Context context) {
        this.view = view;
        this.myPreferencesData = MyPreferencesData.getInstance(context);
        checkPreferences();
    }

    private void checkPreferences(){
        String data = myPreferencesData.getData(Constant.USER_ID);

        if (data.equals("")) {
            view.onLogin();
        } else {
            view.redirectActivity();
        }
    }
}
