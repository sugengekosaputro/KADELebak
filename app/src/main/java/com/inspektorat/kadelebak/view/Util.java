package com.inspektorat.kadelebak.view;

import android.app.Activity;

import com.inspektorat.kadelebak.R;

public class Util {

    public static void animate(Activity activity) {
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out_longer);
    }
}
