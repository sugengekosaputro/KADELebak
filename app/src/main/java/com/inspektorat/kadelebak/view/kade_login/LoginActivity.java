package com.inspektorat.kadelebak.view.kade_login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.view.kade_dashboard.fragment.HomeFragment;
import com.inspektorat.kadelebak.view.kade_login.fragment.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_login, new LoginFragment())
                .commit();
    }


}
