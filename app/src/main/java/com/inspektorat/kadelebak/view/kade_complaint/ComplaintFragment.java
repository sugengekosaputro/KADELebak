package com.inspektorat.kadelebak.view.kade_complaint;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.inspektorat.kadelebak.Constant;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.data.MyPreferencesData;
import com.inspektorat.kadelebak.model.User;
import com.inspektorat.kadelebak.view.kade_complaint.adapter.ComplaintAdapter;
import com.inspektorat.kadelebak.view.kade_complaint.model.list_page.ComplaintModel;
import com.inspektorat.kadelebak.view.kade_complaint.presenter.ComplaintPresenter;
import com.inspektorat.kadelebak.view.kade_complaint.view.ComplaintView;
import com.inspektorat.kadelebak.view.kade_complaint.view.CreateComplaintActivity;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComplaintFragment extends Fragment implements ComplaintView.View {

    ComplaintAdapter complaintAdapter;
    ComplaintPresenter presenter;
    Context context = getActivity();

    MyPreferencesData myPreferencesData;

    String employeeId;
    String roleId;
    String sectionId;
    boolean stateMenu;

    @BindView(R.id.rv_complaint_fitur)
    RecyclerView recyclerview;

    public ComplaintFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_complaint, container, false);
        MaterialToolbar toolbar = view.findViewById(R.id.appbarlayout_complaint);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Pengaduan");
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_create:
                        Intent intent = new Intent(getActivity(), CreateComplaintActivity.class);
                        getActivity().startActivity(intent);
                }
                return true;
            }
        });

        myPreferencesData = MyPreferencesData.getInstance(context);
        employeeId = myPreferencesData.getData(Constant.EMPLOYEE_ID);
        roleId = myPreferencesData.getData(Constant.ROLE_ID);
        sectionId = myPreferencesData.getData(Constant.SECTION_ID);

        presenter = new ComplaintPresenter(this, Integer.valueOf(employeeId), Integer.valueOf(roleId), sectionId);

        presenter.getComplaintData();
        setRecyclerview();

        return view;
    }

    private void setRecyclerview() {
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerview.setAdapter(complaintAdapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        if (sectionId.length() == 0) {
            this.stateMenu = true;
        }
        if (this.stateMenu){
            inflater.inflate(R.menu.menu_create, menu);
            super.onCreateOptionsMenu(menu, inflater);
        }
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
    public void hideIconCreate(boolean isVisible) {
        this.stateMenu = isVisible;
    }

    @Override
    public void showDataRoleUser(List<ComplaintModel> complaintModelList) {
        List<ComplaintModel> complaintModels = Observable.fromIterable(complaintModelList)
                .filter(complaintModel ->
                                complaintModel.getPublisher().getEmployeeId() == Integer.valueOf(employeeId))
                .toSortedList((t0, t1) -> t1.getDateTime().compareTo(t0.getDateTime())).blockingGet();
        complaintAdapter = new ComplaintAdapter(getActivity(), complaintModels);
        complaintAdapter.notifyDataSetChanged();
        recyclerview.setAdapter(complaintAdapter);
    }

    @Override
    public void showDataRoleOperator(List<ComplaintModel> complaintModelList) {
        List<ComplaintModel> complaintModels = Observable.fromIterable(complaintModelList)
                .filter(complaintModel ->
                        complaintModel.getSectionId().getSectionId() == Integer.valueOf(sectionId))
                .toSortedList((t0, t1) ->
                        t1.getDateTime().compareTo(t0.getDateTime())).blockingGet();

        complaintAdapter = new ComplaintAdapter(getActivity(), complaintModels);
        complaintAdapter.notifyDataSetChanged();
        recyclerview.setAdapter(complaintAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getComplaintData();
    }
}
