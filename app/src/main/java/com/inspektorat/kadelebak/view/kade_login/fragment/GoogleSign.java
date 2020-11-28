package com.inspektorat.kadelebak.view.kade_login.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.data.MyPreferencesData;
import com.inspektorat.kadelebak.view.kade_login.RegisterActivity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.inspektorat.kadelebak.Constant.EMAIL;
import static com.inspektorat.kadelebak.Constant.GOOGLE_SIGN_EMAIL;
import static com.inspektorat.kadelebak.Constant.GOOGLE_SIGN_ID;
import static com.inspektorat.kadelebak.Constant.GOOGLE_SIGN_NAME;

public class GoogleSign extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    @BindView(R.id.btn_sign)
    SignInButton signInButton;

    private GoogleApiClient googleApiClient;
    private static final int SIGN_IN = 1;
    private MyPreferencesData myPreferencesData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_google_sign, container, false);
        ButterKnife.bind(this, view);

        myPreferencesData.getInstance(getActivity());
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient
                .Builder(Objects.requireNonNull(getActivity()).getApplicationContext())
                .enableAutoManage(getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        return view;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @OnClick(R.id.btn_sign)
    void onClickSign() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                intent.putExtra(GOOGLE_SIGN_EMAIL, result.getSignInAccount().getEmail());
                intent.putExtra(GOOGLE_SIGN_NAME, result.getSignInAccount().getDisplayName());
                intent.putExtra(GOOGLE_SIGN_ID, result.getSignInAccount().getId());
                startActivity(intent);
                Toast.makeText(getActivity(), "Google Sign Success : " + result.getSignInAccount().getEmail(), Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_LONG).show();
            }
        }
    }
}
