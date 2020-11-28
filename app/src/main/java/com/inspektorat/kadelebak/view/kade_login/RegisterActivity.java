package com.inspektorat.kadelebak.view.kade_login;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.inspektorat.kadelebak.Constant;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.entity.InstitutionEntity;
import com.inspektorat.kadelebak.entity.PositionEntity;
import com.inspektorat.kadelebak.view.Util;
import com.inspektorat.kadelebak.view.kade_dashboard.DashboardActivity;
import com.inspektorat.kadelebak.view.kade_login.model.RegisterModel;
import com.inspektorat.kadelebak.view.kade_login.view.LoginView;
import com.inspektorat.kadelebak.view.kade_splash.presenter.SplashPresenter;
import com.inspektorat.kadelebak.view.kade_splash.view.SplashView;

import org.joda.time.LocalDateTime;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

public class RegisterActivity extends AppCompatActivity implements LoginView.register, SplashView {

    @BindView(R.id.edt_work_unit)
    TextInputLayout edtWorkUnit;
    @BindView(R.id.edt_register_password)
    TextInputLayout edtPassword;
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
    private int mYear, mMonth, mDay, mHour, mMinute;

    RadioButton radioButton;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.btn_register)
    MaterialButton btnRegister;

    @BindView(R.id.btn_choose_image)
    Button btnChoose;

    @BindView(R.id.iv_preview)
    ImageView imageView;

    Bitmap bitmap;
    File file;
    String imagePath, gsEmail, gsName, gsID;

    private LoginPresenter presenter;
    private RegisterModel registerModel;
    private String gender, institutionId, positionId;
    private LocalDateTime localDTStart = LocalDateTime.now();
    SplashPresenter splashPresenter;

    public static final int PICK_IMAGE = 212;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Register");

        gsEmail = getIntent().getStringExtra(Constant.GOOGLE_SIGN_EMAIL);
        gsName = getIntent().getStringExtra(Constant.GOOGLE_SIGN_NAME);
        gsID = getIntent().getStringExtra(Constant.GOOGLE_SIGN_ID);

        Toast.makeText(getApplicationContext(), "Intent email : " + gsEmail, Toast.LENGTH_SHORT).show();
        splashPresenter = new SplashPresenter(this, getApplicationContext(), gsEmail);
        presenter = new LoginPresenter(this, getApplicationContext());

        this.initDataForm();
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

    private void initDataForm() {
        edtRegisterName.getEditText().setText(gsName);
        edtRegisterEmail.getEditText().setText(gsEmail);
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
                edtRegisterName,
                tvDob,
                edtRegisterBornPlace,
                this.gender,
                edtRegisterPhone,
                edtRegisterEmail,
                edtWorkUnit,
                institutionId,
                positionId,
                file,
                edtPassword);

        this.presenter.registerAccount(registerModel);
    }

    private RegisterModel generateForm(
            TextInputLayout name,
            TextView dob,
            TextInputLayout bornPlace,
            String gender,
            TextInputLayout phone,
            TextInputLayout email,
            TextInputLayout unit,
            String institutionId,
            String positionId,
            File file,
            TextInputLayout password) {
        RegisterModel model = new RegisterModel();
        model.setName(name.getEditText().getText().toString());
        model.setDob(dob.getText().toString());
        model.setBornPlace(bornPlace.getEditText().getText().toString());
        model.setGender(gender);
        model.setPhone(phone.getEditText().getText().toString());
        model.setEmail(email.getEditText().getText().toString());
        model.setUnit(unit.getEditText().getText().toString());
        model.setPositionId(positionId);
        model.setInstitutionId(institutionId);
        model.setRoleId("1");
        model.setSectionId(null);
        model.setFile(file);
        model.setPassword(password.getEditText().getText().toString());
        return model;
    }

    @Override
    public boolean validateInput() {
        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (!isValidInput(edtWorkUnit)) {
            edtWorkUnit.requestFocus();
            edtWorkUnit.setError(Constant.FORM_ERROR);
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

        if (!isValidInput(edtPassword) || edtPassword.getEditText().getText().length() < 6 ) {
            edtPassword.requestFocus();
            edtPassword.setError("Password minimal 6 karakter");
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
        edtWorkUnit.setErrorEnabled(status);
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

    @OnClick(R.id.btn_choose_image)
    void pickFromGallery(){
        //Create an Intent with action as ACTION_PICK
        Intent intent=new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        // Launching the Intent
        startActivityForResult(intent,PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();

            try {
                //getting image from gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                //Setting image to ImageView
                imageView.setImageBitmap(bitmap);
                imagePath = getPath(filePath);

                file = new File(imagePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("RES", "onActivityResult: ");
        }
    }

    @Override
    public void onRegisterSuccess() {
        Toast.makeText(getApplicationContext(), "Register Berhasil", Toast.LENGTH_SHORT).show();
        this.finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
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

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    @Override
    public void redirectActivity(String email) {
        redirect(new DashboardActivity());
        Toast.makeText(getApplicationContext(), "redirectActivity() to Dashboard : "+ email, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLogin() {
        Toast.makeText(getApplicationContext(), "onLogin() RegisterActivity", Toast.LENGTH_SHORT).show();
    }

    public void redirect(Activity page) {
            Intent I = new Intent(RegisterActivity.this, page.getClass());
            startActivity(I);
            Util.animate(this);
            finish();
    }
}
