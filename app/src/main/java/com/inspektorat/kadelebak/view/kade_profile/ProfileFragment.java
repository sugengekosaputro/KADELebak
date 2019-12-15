package com.inspektorat.kadelebak.view.kade_profile;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import com.inspektorat.kadelebak.Constant;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.data.MyPreferencesData;
import com.inspektorat.kadelebak.entity.EmployeeEntity;
import com.inspektorat.kadelebak.view.kade_profile.view.ProfileView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

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
    @BindView(R.id.birth_date)
    TextView birthDate;
    @BindView(R.id.gender)
    TextView gender;
    @BindView(R.id.position)
    TextView position;
    @BindView(R.id.nip)
    TextView nip;
    @BindView(R.id.orgeh_id)
    TextView orgehId;
    @BindView(R.id.avatar_default)
    CircleImageView avatarDefault;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;

    Presenter presenter;
    Context context = getActivity();

    MyPreferencesData myPreferencesData;
    String employeeId;

//    @BindView(R.id.toolbar)
//    MaterialToolbar toolbar;
//    @BindView(R.id.toolbar_title)
//    TextView toolbarTitle;

    public ProfileFragment() {
        // Required empty public constructor
        presenter = new Presenter(this);
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
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initToolbar() {
        //      toolbarTitle.setText(getResources().getString(R.string.title_profile));
    }

    @Override
    public void showDataProfile(EmployeeEntity profile) {
        String place = profile.getBornPlaces();
        String dob = profile.getDob();

        name.setText(profile.getName());
        personalNumber.setText(profile.getPhone());
        email.setText(profile.getEmail());
        birthDate.setText(place+", "+ dob);
        gender.setText(profile.getGender());
        position.setText(profile.getPosition().getName());
        nip.setText(profile.getNip());
        orgehId.setText(profile.getInstitution().getAddress());

    }
}
