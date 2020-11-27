package com.inspektorat.kadelebak.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.recyclerview.widget.RecyclerView;

import com.inspektorat.kadelebak.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public final static long ONE_SECOND = 1000;
    public final static long SECONDS = 60;

    public final static long ONE_MINUTE = ONE_SECOND * 60;
    public final static long MINUTES = 60;

    public final static long ONE_HOUR = ONE_MINUTE * 60;
    public final static long HOURS = 24;

    public final static long ONE_DAY = ONE_HOUR * 24;


    public static void animate(Activity activity) {
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out_longer);
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static String getDateTimeNow() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return timeStamp;
    }

    public static String nameBuilderCapitalized(String name) {
        StringBuilder capitalizedString = new StringBuilder();
        String[] splited = name.trim().split("\\s+");

        for (String string : splited) {
            String s1 = string.substring(0, 1).toUpperCase();
            String nameCapitalized = s1 + string.substring(1);

            capitalizedString.append(nameCapitalized);
            capitalizedString.append(" ");
        }

        return capitalizedString.toString();
    }

    public static String imageInitial(String name){

        String[] strArray = name.split(" ");
        StringBuilder builder = new StringBuilder();

//First name
        if (strArray.length > 0){
            builder.append(strArray[0], 0, 1);
        }
//Middle name
        if (strArray.length > 1){
            builder.append(strArray[1], 0, 1);
        }

        return builder.toString();
    }

    public static String timeDuration(long duration) {
        StringBuffer res = new StringBuffer();
        long temp = 0;
        if (duration >= ONE_SECOND) {
            temp = duration / ONE_DAY;
            if (temp > 0) {
                duration -= temp * ONE_DAY;
                res.append(temp).append(" day").append(temp > 1 ? "s" : "")
                        .append(duration >= ONE_MINUTE ? ", " : "");
            }

            temp = duration / ONE_HOUR;
            if (temp > 0) {
                duration -= temp * ONE_HOUR;
                res.append(temp).append(" hour").append(temp > 1 ? "s" : "")
                        .append(duration >= ONE_MINUTE ? ", " : "");
            }

            temp = duration / ONE_MINUTE;
            if (temp > 0) {
                duration -= temp * ONE_MINUTE;
                res.append(temp).append(" minute").append(temp > 1 ? "s" : "");
            }

            if (!res.toString().equals("") && duration >= ONE_SECOND) {
                res.append(" and ");
            }

            temp = duration / ONE_SECOND;
            if (temp > 0) {
                res.append(temp).append(" second").append(temp > 1 ? "s" : "");
            }
            return res.toString();
        } else {
            return "0 second";
        }
    }
}
