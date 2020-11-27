package com.inspektorat.kadelebak.view.kade_complaint.view;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
    private String sectionId;

    private ComplaintPresenter presenter;
    private Context context = this;
    private MyPreferencesData myPreferencesData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_complaint);
        ButterKnife.bind(this);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Buat Pengaduan");
        presenter = new ComplaintPresenter(this);
        presenter.getSection();

        myPreferencesData = MyPreferencesData.getInstance(context);
        this.employeeId = myPreferencesData.getData(Constant.EMPLOYEE_ID);

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

        this.initDialog();
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
        this.initDialog().show();
    }

    @Override
    public void hideLoading() {
        this.initDialog().dismiss();
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
                android.R.layout.simple_spinner_item, sectionModelList){
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };

        /*todo to save section id*/

//        if (myPreferencesData.getData("ComplaintList").length() != sectionModelList.size()) {
//            for (SectionModel sec : sectionModelList)
//                myPreferencesData.saveDataArray(String.valueOf(sec.getSectionId()));
//            }
//        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        SectionModel sm = new SectionModel(0, "Pilih Operator");
        adapter.insert(sm,0);
        spinner.setAdapter(adapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    SectionModel sectionModel = (SectionModel) parent.getSelectedItem();
                    sectionId = String.valueOf(sectionModel.getSectionId());

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean validateInput() {
        if (sectionId == null) {
            this.showToast("Operator harus dipilih");
            return false;
        }

        if (!isValidInput(edtContent)){
            edtContent.requestFocus();
            edtContent.setError(Constant.FORM_ERROR);
            return false;
        }
        return true;
    }

    private boolean isValidInput(TextInputLayout editText) {
        if (editText.getEditText().getText() == null) return false;
        return !editText.getEditText().getText().toString().isEmpty();
    }

    @OnClick(R.id.btn_create_complaint_send)
    void sendCompaint(){
        presenter.creteComplaint(
                Integer.valueOf(this.employeeId),
                this.sectionId,
                edtContent.getEditText().getText().toString(),
                this.isAnonymous,
                true);
    }

    private void showToast(String message){
        Toast.makeText(
                context,
                message,
                Toast.LENGTH_SHORT).show();
    }

    private Dialog initDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(R.layout.progress_dialog);

        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}
