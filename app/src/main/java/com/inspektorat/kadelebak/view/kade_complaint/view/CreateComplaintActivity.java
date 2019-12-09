package com.inspektorat.kadelebak.view.kade_complaint.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputLayout;
import com.inspektorat.kadelebak.Constant;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.data.MyPreferencesData;
import com.inspektorat.kadelebak.model.User;
import com.inspektorat.kadelebak.view.Util;
import com.inspektorat.kadelebak.view.kade_complaint.model.SectionModel;
import com.inspektorat.kadelebak.view.kade_complaint.presenter.ComplaintPresenter;
import com.inspektorat.kadelebak.view.kade_forum.model.Section;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateComplaintActivity extends AppCompatActivity implements ComplaintView.CreateComplaint {


    @BindView(R.id.spinner)
    Spinner spinner;

    @BindView(R.id.switch_anonymous)
    SwitchMaterial switchAnonymous;
    @BindView(R.id.edt_content_create_complaint)
    TextInputLayout edtContent;
    @BindView(R.id.btn_create_complaint_send)
    MaterialButton btnSend;

    private boolean isAnonymous = false;
    private String employeeId;
    private int sectionId;

    private ComplaintPresenter presenter;
    private Context context = this;
    private MyPreferencesData myPreferencesData;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_complaint);
        ButterKnife.bind(this);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Buat Pengaduan");
        presenter = new ComplaintPresenter(this);
        presenter.getSection();

        myPreferencesData = MyPreferencesData.getInstance(context);
        this.employeeId = myPreferencesData.getData(Constant.EMPLOYEE_ID);

        progressDialog = new ProgressDialog(context);
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        switchAnonymous.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String msg;
                if (!compoundButton.isChecked()) {
                    msg = "Operator Dapat Melihat Nama Anda";
                    isAnonymous = false;
                } else {
                    msg = "Nama Anda Disembunyikan";
                    isAnonymous = true;
                }
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void setErrorValidationMessage(String message) {
        edtContent.setError(message);
    }

    @Override
    public void setErrorValidationEnabled(boolean enabled) {
        edtContent.setErrorEnabled(enabled);
    }

    @Override
    public void onCreateSuccess(String message) {
        this.showToast(message);
        this.finish();
    }

    @Override
    public void onCreateFailed(String message) {
        this.showToast(message);
    }

    @Override
    public void renderSpinner(List<SectionModel> sectionModelList) {
        ArrayAdapter<SectionModel> adapter = new ArrayAdapter<SectionModel>(this,
                android.R.layout.simple_spinner_item, sectionModelList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SectionModel sectionModel = (SectionModel) parent.getSelectedItem();
                sectionId = sectionModel.getSectionId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick(R.id.btn_create_complaint_send)
    void sendCompaint(){
        presenter.creteComplaint(
                Integer.valueOf(this.employeeId),
                this.sectionId,
                edtContent.getEditText().getText().toString(),
                this.isAnonymous,
                false);
    }

    private void showToast(String message){
        Toast.makeText(
                context,
                message,
                Toast.LENGTH_SHORT).show();
    }
}
