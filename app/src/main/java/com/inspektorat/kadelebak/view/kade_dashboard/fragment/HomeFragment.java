package com.inspektorat.kadelebak.view.kade_dashboard.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.inspektorat.kadelebak.Constant;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.data.MyPreferencesData;
import com.inspektorat.kadelebak.view.kade_dashboard.CoronaService;
import com.inspektorat.kadelebak.view.kade_dashboard.adapter.FiturAdapter;
import com.inspektorat.kadelebak.view.kade_dashboard.model.Attribute;
import com.inspektorat.kadelebak.view.kade_dashboard.model.CoronaEntity;
import com.inspektorat.kadelebak.view.kade_dashboard.presenter.DashboardPresenter;
import com.inspektorat.kadelebak.view.kade_dashboard.view.DashboardView;
import com.inspektorat.kadelebak.view.kade_profile.ProfilePresenter;
import com.inspektorat.kadelebak.view.kade_splash.SplashActivity;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements DashboardView.Fitur {


    @BindView(R.id.ll_top_dashboard)
    LinearLayout llTopDashboard;
    @BindView(R.id.rv_dashboard_fitur)
    RecyclerView recyclerView;

    @BindView(R.id.covid_meninggal)
    TextView covidMeninggal;
    @BindView(R.id.covid_positif)
    TextView covidPositif;
    @BindView(R.id.covid_sembuh)
    TextView covidSembuh;

    DashboardPresenter presenter;
    FiturAdapter fiturAdapter;
    MyPreferencesData myPreferencesData;
    @BindView(R.id.tv_fragment_home_name)
    TextView tvName;

    private static Retrofit retrofit;
    ProfilePresenter profilePresenter;

    public static final int PICK_IMAGE = 212;

    public HomeFragment() {
        // Required empty public constructor
        this.profilePresenter = new ProfilePresenter(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myPreferencesData = MyPreferencesData.getInstance(getActivity());
        tvName.setText(myPreferencesData.getData(Constant.NAME));
        checkProfile(myPreferencesData.getData(Constant.EMPLOYEE_ID));
        initPresenter();
        setRecyclerview();

        CoronaService service = getRetrofit()
                .create(CoronaService.class);

        Call<List<CoronaEntity>> call = service.getCoronaBanten();
        call.enqueue(new Callback<List<CoronaEntity>>() {
            @Override
            public void onResponse(Call<List<CoronaEntity>> call, Response<List<CoronaEntity>> response) {
                Log.d("CORONA", "onResponse: " + response.body());

                String positif = null, meninggal = null, sembuh = null;
                for (CoronaEntity entity : response.body()) {
                    if (entity.getAttributes().getfID() == 16) {
                        Log.d("CORONA", "onResponse: " + entity.getAttributes().getProvinsi());
                        meninggal = String.valueOf(entity.getAttributes().getKasusMeni());
                        sembuh = String.valueOf(entity.getAttributes().getKasusSemb());
                        positif = String.valueOf(entity.getAttributes().getKasusPosi());
                    }
                }
                
                covidPositif.setText(positif);
                covidSembuh.setText(sembuh);
                covidMeninggal.setText(meninggal);
            }

            @Override
            public void onFailure(Call<List<CoronaEntity>> call, Throwable t) {
                Log.d("CORONA", "onResponse: " + t.getMessage());
            }
        });
    }

    private void checkProfile(String empId) {
        profilePresenter.checkProfileFirst(empId);
    }

    private void initPresenter() {
        presenter = new DashboardPresenter(this);
        presenter.setFitur(Objects.requireNonNull(getActivity()).getApplicationContext().getResources().getStringArray(R.array.feature_item));
    }

    private void setRecyclerview() {
        recyclerView.setLayoutManager(new GridLayoutManager(Objects.requireNonNull(getActivity()).getApplicationContext(), 2));
        recyclerView.setAdapter(fiturAdapter);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void userNotExist(String message) {
        Log.d("CheckProfile", "showError: " + message);
        showAlert();
    }

    @Override
    public void showDataFitur(List<String> listFitur) {
        fiturAdapter = new FiturAdapter(getActivity().getApplicationContext(), listFitur);
        fiturAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.iv_icon_notification)
    void onClickIcon() {
        new MaterialAlertDialogBuilder(getActivity(), R.style.MyDialog)
                .setTitle("KADE Lebak")
                .setMessage("Anda yakin akan keluar ?")
                .setPositiveButton("Keluar", (dialogInterface, i) -> this.logout())
                .setNegativeButton("Batal", (dialogInterface, i) -> dialogInterface.dismiss())
                .setCancelable(false)
                .show();
    }

    void showAlert() {
        new MaterialAlertDialogBuilder(getActivity(), R.style.MyDialog)
                .setTitle("Peringatan")
                .setMessage("Akun anda sudah dihapus oleh Admin.")
                .setMessage("Untuk info lebih lanjut silahkan hubungi Admin")
                .setPositiveButton("Tutup", (dialogInterface, i) -> this.logout())
                .setCancelable(false)
                .show();
    }

    private void logout() {
        myPreferencesData.clearData();
        Intent intent = new Intent(Objects.requireNonNull(getActivity()).getApplicationContext(), SplashActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
    }


    public static Retrofit getRetrofit(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.kawalcorona.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    @OnClick(R.id.covid_link)
    void onclick(){
        String url = "http://kawalcovid19.id";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
