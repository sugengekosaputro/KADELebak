package com.inspektorat.kadelebak.view.kade_login;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.inspektorat.kadelebak.Constant;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.entity.InstitutionEntity;
import com.inspektorat.kadelebak.entity.PositionEntity;
import com.inspektorat.kadelebak.view.kade_login.model.RegisterModel;
import com.inspektorat.kadelebak.view.kade_login.view.LoginView;

import org.joda.time.LocalDateTime;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

public class RegisterActivity extends AppCompatActivity implements LoginView.register {

    @BindView(R.id.edt_register_nip)
    TextInputLayout edtRegisterNip;
    @BindView(R.id.edt_register_name)
    TextInputLayout edtRegisterName;
    @BindView(R.id.edt_register_born_place)
    TextInputLayout edtRegisterBornPlace;
    @BindView(R.id.edt_register_phone)
    TextInputLayout edtRegisterPhone;
    @BindView(R.id.edt_register_email)
    TextInputLayout edtRegisterEmail;
    @BindView(R.id.spn_register_position)
    Spinner spnPosition;
    @BindView(R.id.spn_register_institution)
    Spinner spnInstitution;
    @BindView(R.id.tv_dob)
    TextView tvDob;
    @BindView(R.id.ll_dob_picker)
    LinearLayout llDobPicker;
    @BindView(R.id.edt_register_prefix)
    TextInputLayout edtRegisterPrefix;
    @BindView(R.id.edt_register_suffix)
    TextInputLayout edtRegisterSuffix;
    private int mYear, mMonth, mDay, mHour, mMinute;

    RadioButton radioButton;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.btn_register)
    MaterialButton btnRegister;

    private LoginPresenter presenter;
    private RegisterModel registerModel;
    private String gender, institutionId, positionId;
    private LocalDateTime localDTStart = LocalDateTime.now();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Register");
        presenter = new LoginPresenter(this, getApplicationContext());

        this.initSpinner();
        this.setDateListener();

//        radioGroup = findViewById(R.id.radio_group);
//
//        radioGroup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
//                gender = radioButton.getText().toString();
//                Toast.makeText(getApplicationContext(), gender , Toast.LENGTH_SHORT).show();
//            }
//        });

        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i) {
                case R.id.radio_men:
                    gender = "Laki-laki";
                    break;
                case R.id.radio_woman:
                    gender = "Perempuan";
                    break;
                default:
                    gender = "Laki-laki";
            }
        });
    }

    private void setDateListener() {
        DatePickerDialog.OnDateSetListener date = (datePicker, i, i1, i2) -> {
            localDTStart = localDTStart.withDate(i, i1 + 1, i2);
            String result = localDTStart.toString("YYYY-MM-dd");
            tvDob.setText(result);
        };

        llDobPicker.setOnClickListener(view ->
                new DatePickerDialog(RegisterActivity.this, date,
                        localDTStart.getYear(),
                        localDTStart.getMonthOfYear(),
                        localDTStart.getDayOfMonth()).show());
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

    @OnClick(R.id.btn_register)
    void onClickRegister() {
        registerModel = this.generateForm(
                edtRegisterNip,
                edtRegisterName,
                edtRegisterPrefix,
                edtRegisterSuffix,
                tvDob,
                edtRegisterBornPlace,
                this.gender,
                edtRegisterPhone,
                edtRegisterEmail,
                institutionId,
                positionId);

        this.presenter.registerAccount(registerModel);

//        Log.d("Geng", "onClickRegister: "
//                +model.getNip()+", "
//                +model.getName()+", "
//                +model.getBornPlace()+", "
//                +model.getDob()+", "
//                +model.getGender()+", "
//                +model.getPhone()+", "
//                +model.getPositionId()+", "
//                +model.getSectionId()+", "
//                +model.getPrefixTitle()+", "
//                +model.getSuffixTitle()+", "
//        );
    }

    private RegisterModel generateForm(
            TextInputLayout nip,
            TextInputLayout name,
            TextInputLayout prefix,
            TextInputLayout suffix,
            TextView dob,
            TextInputLayout bornPlace,
            String gender,
            TextInputLayout phone,
            TextInputLayout email,
            String institutionId,
            String positionId) {
        RegisterModel model = new RegisterModel();
        model.setNip(nip.getEditText().getText().toString());
        model.setName(name.getEditText().getText().toString());
        model.setPrefixTitle(prefix.getEditText().getText().toString());
        model.setSuffixTitle(suffix.getEditText().getText().toString());
        model.setDob(dob.getText().toString());
        model.setBornPlace(bornPlace.getEditText().getText().toString());
        model.setGender(gender);
        model.setPhone(phone.getEditText().getText().toString());
        model.setEmail(email.getEditText().getText().toString());
        model.setPositionId(positionId);
        model.setInstitutionId(institutionId);
        model.setRoleId("4");
        model.setSectionId(null);
        return model;
    }

    @Override
    public boolean validateInput() {
        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (!isValidInput(edtRegisterNip)) {
            edtRegisterNip.requestFocus();
            edtRegisterNip.setError(Constant.FORM_ERROR);
            return false;
        }

        if (!isValidInput(edtRegisterName)) {
            edtRegisterName.requestFocus();
            edtRegisterName.setError(Constant.FORM_ERROR);
            return false;
        }

        if (!isValidInput(edtRegisterBornPlace)) {
            edtRegisterBornPlace.requestFocus();
            edtRegisterBornPlace.setError(Constant.FORM_ERROR);
            return false;
        }

        if (!isRadioChecked(selectedId)) {
            Toast.makeText(getApplicationContext(), "Gender Harus Dipilih", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (tvDob.getText().length() == 0){
            Toast.makeText(getApplicationContext(), "Tanggal Lahir Harus Diisi", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (institutionId == null) {
            Toast.makeText(getApplicationContext(), "Institusi Harus Dipilih", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (positionId == null) {
            Toast.makeText(getApplicationContext(), "Posisi Harus Dipilih", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!isValidInput(edtRegisterPhone)) {
            edtRegisterPhone.requestFocus();
            edtRegisterPhone.setError(Constant.FORM_ERROR);
            return false;
        }

        if (!isValidInput(edtRegisterEmail)) {
            edtRegisterEmail.requestFocus();
            edtRegisterEmail.setError(Constant.FORM_ERROR);
            return false;
        }

        return true;
    }

    private boolean isValidInput(TextInputLayout editText) {
        if (editText.getEditText().getText() == null) return false;
        return !editText.getEditText().getText().toString().isEmpty();
    }

    private boolean isRadioChecked(int selectedId) {
        if (selectedId == -1) {
            return false;
        } else {
            return true;
        }
    }

    private void initSpinner() {
        presenter.getPosition();
        presenter.getInstitution();
    }

    @Override
    public void removeError(boolean status) {
        edtRegisterNip.setErrorEnabled(status);
        edtRegisterName.setErrorEnabled(status);
        edtRegisterBornPlace.setErrorEnabled(status);
        edtRegisterPhone.setErrorEnabled(status);
        edtRegisterEmail.setErrorEnabled(status);
    }

    @Override
    public void renderPosition(Collection<PositionEntity> positionList) {
        List<PositionEntity> position = Observable.fromIterable(positionList)
                .toSortedList((t0, t1) -> Integer.compare(t0.getPositionId(), t1.getPositionId()))
                .blockingGet();

        ArrayAdapter<PositionEntity> adapter = new ArrayAdapter<PositionEntity>(this,
                android.R.layout.simple_spinner_item, position) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PositionEntity entity = new PositionEntity(0, "Pilih Posisi");
        adapter.insert(entity, 0);

        spnPosition.setAdapter(adapter);
        spnPosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position > 0) {
                    PositionEntity positionEntity = (PositionEntity) adapterView.getSelectedItem();
                    positionId = String.valueOf(positionEntity.getPositionId());
                    Toast.makeText(getApplicationContext(), "Posisi : " + positionId, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void renderInstitution(Collection<InstitutionEntity> institutionList) {
        List<InstitutionEntity> institution = Observable.fromIterable(institutionList)
                .toSortedList((t0, t1) -> t0.getName().compareTo(t1.getName()))
                .blockingGet();

        ArrayAdapter<InstitutionEntity> adapter = new ArrayAdapter<InstitutionEntity>(this,
                android.R.layout.simple_spinner_item, institution) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        InstitutionEntity entity = new InstitutionEntity(0, "Pilih Institution");
        adapter.insert(entity, 0);

        spnInstitution.setAdapter(adapter);
        spnInstitution.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position > 0) {
                    InstitutionEntity institutionEntity = (InstitutionEntity) adapterView.getSelectedItem();
                    institutionId = String.valueOf(institutionEntity.getInstitutionId());
                    Toast.makeText(getApplicationContext(), "Institusi : " + institutionEntity, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onRegisterSuccess() {
        Toast.makeText(getApplicationContext(), "Register Berhasil", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    @Override
    public void onRegisterFailed() {
        Toast.makeText(getApplicationContext(), "Register Gagal", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        this.btnRegister.setEnabled(false);
    }

    @Override
    public void hideLoading() {

    }

    private Dialog initDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setView(R.layout.progress_dialog);

        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}
