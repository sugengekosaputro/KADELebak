package com.inspektorat.kadelebak.view.kade_profile;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.inspektorat.kadelebak.Constant;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.data.MyPreferencesData;
import com.inspektorat.kadelebak.entity.EmployeeEntity;
import com.inspektorat.kadelebak.view.Util;
import com.inspektorat.kadelebak.view.kade_profile.view.ProfileView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.inspektorat.kadelebak.Constant.EMAIL;
import static com.inspektorat.kadelebak.Constant.NAME;
import static com.inspektorat.kadelebak.Constant.PHONE;
import static com.inspektorat.kadelebak.Constant.STATUS_VERIFIED;
import static com.inspektorat.kadelebak.Constant.STATUS_VERIFIED_1;
import static com.inspektorat.kadelebak.Constant.STATUS_VERIFIED_2;
import static com.inspektorat.kadelebak.Constant.STATUS_VERIFIED_3;
import static com.inspektorat.kadelebak.Constant.STATUS_VERIFIED_4;
import static com.inspektorat.kadelebak.Constant.STATUS_VERIFIED_ACCEPTED;
import static com.inspektorat.kadelebak.Constant.STATUS_VERIFIED_NULL;
import static com.inspektorat.kadelebak.Constant.STATUS_VERIFIED_REJECTED;
import static com.inspektorat.kadelebak.Constant.STATUS_VERIFIED_WAITING;
import static com.inspektorat.kadelebak.Constant.VERIFIED;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements ProfileView.view {
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.personal_number)
    TextView personalNumber;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.linearLayoutVerification)
    TextView upload;
    @BindView(R.id.textStatusVerified)
    TextView statusVerified;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;
//    @BindView(R.id.birth_date)
//    TextView birthDate;
//    @BindView(R.id.gender)
//    TextView gender;
//    @BindView(R.id.position)
//    TextView position;
//    @BindView(R.id.nip)
//    TextView nip;
//    @BindView(R.id.orgeh_id)
//    TextView orgehId;
//    @BindView(R.id.avatar_default)
//    CircleImageView avatarDefault;

    ProfilePresenter presenter;
    Context context = getActivity();

    MyPreferencesData myPreferencesData;
    String employeeId;

//    @BindView(R.id.toolbar)
//    MaterialToolbar toolbar;
//    @BindView(R.id.toolbar_title)
//    TextView toolbarTitle;

    public ProfileFragment() {
        // Required empty public constructor
        presenter = new ProfilePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        initToolbar();
        myPreferencesData = MyPreferencesData.getInstance(context);
        employeeId = myPreferencesData.getData(Constant.EMPLOYEE_ID);
        presenter.getProfile(employeeId);
//        showProfile();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initToolbar() {
        //      toolbarTitle.setText(getResources().getString(R.string.t\itle_profile));
    }

    @Override
    public void showDataProfile(EmployeeEntity profile) {
//        String place = profile.getBornPlaces();
//        String dob = profile.getDob();
//
        name.setText(profile.getName());
        personalNumber.setText(profile.getPhone());
        email.setText(profile.getEmail());

        myPreferencesData.saveData(VERIFIED, String.valueOf(profile.isVerified()));
        myPreferencesData.saveData(STATUS_VERIFIED, String.valueOf(profile.getStatusVerified()));

        String verified = myPreferencesData.getData(VERIFIED);
        int statusVer = Integer.parseInt(myPreferencesData.getData(STATUS_VERIFIED));
        String status = STATUS_VERIFIED_1;

        if (Boolean.getBoolean(verified) || statusVer == STATUS_VERIFIED_WAITING) {
            upload.setVisibility(View.INVISIBLE);
        }

        switch (statusVer) {
            case STATUS_VERIFIED_WAITING:
                status = STATUS_VERIFIED_2;
                break;
            case STATUS_VERIFIED_REJECTED:
                status = STATUS_VERIFIED_3;
                break;
            case STATUS_VERIFIED_ACCEPTED:
                status = STATUS_VERIFIED_4;
                break;
        }

        statusVerified.setText(status);

//        myPreferencesData.saveData(STATUS_VERIFIED, profile.isVerified());
//        birthDate.setText(place+", "+ dob);
//        gender.setText(profile.getGender());
//        position.setText(profile.getPosition().getName());
//        nip.setText(profile.getNip());
//        orgehId.setText(profile.getInstitution().getAddress());

    }

    @Override
    public void onErrorData(String msg) {
        Log.d("Profile", "onErrorData: " + msg);
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.linearLayoutVerification)
    void onClickVerification() {
        Intent intent = new Intent(Objects.requireNonNull(getActivity()).getApplicationContext(), UploadActivity.class);
        startActivity(intent);
    }

    private void showProfile() {
        String verified = myPreferencesData.getData(VERIFIED);
        int statusVer = Integer.parseInt(myPreferencesData.getData(STATUS_VERIFIED));
        String status = STATUS_VERIFIED_1;

        name.setText(myPreferencesData.getData(NAME));
        personalNumber.setText(myPreferencesData.getData(PHONE));
        email.setText(EMAIL);


    }
}
