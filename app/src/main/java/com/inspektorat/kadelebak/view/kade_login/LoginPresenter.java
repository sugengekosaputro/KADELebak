package com.inspektorat.kadelebak.view.kade_login;

import android.content.Context;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import com.inspektorat.kadelebak.Constant;
import com.inspektorat.kadelebak.data.BasePresenter;
import com.inspektorat.kadelebak.data.MyPreferencesData;
import com.inspektorat.kadelebak.entity.InstitutionEntity;
import com.inspektorat.kadelebak.entity.PositionEntity;
import com.inspektorat.kadelebak.entity.UserAuthEntity;
import com.inspektorat.kadelebak.model.BadRequest;
import com.inspektorat.kadelebak.model.SuccessMessage;
import com.inspektorat.kadelebak.networking.NetworkClient;
import com.inspektorat.kadelebak.view.kade_login.model.RegisterModel;
import com.inspektorat.kadelebak.view.kade_login.view.LoginView;
import com.inspektorat.kadelebak.view.kade_profile.view.ProfileView;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.inspektorat.kadelebak.Constant.BORN_PLACE;
import static com.inspektorat.kadelebak.Constant.DOB;
import static com.inspektorat.kadelebak.Constant.EMAIL;
import static com.inspektorat.kadelebak.Constant.EMPLOYEE_ID;
import static com.inspektorat.kadelebak.Constant.GENDER;
import static com.inspektorat.kadelebak.Constant.INSTITUTION_ID;
import static com.inspektorat.kadelebak.Constant.NAME;
import static com.inspektorat.kadelebak.Constant.PHONE;
import static com.inspektorat.kadelebak.Constant.POSITION_ID;
import static com.inspektorat.kadelebak.Constant.ROLE_ID;
import static com.inspektorat.kadelebak.Constant.SECTION_ID;
import static com.inspektorat.kadelebak.Constant.STATUS_VERIFIED;
import static com.inspektorat.kadelebak.Constant.UNIT;
import static com.inspektorat.kadelebak.Constant.USER_ID;
import static com.inspektorat.kadelebak.Constant.VERIFIED;


public class LoginPresenter extends BasePresenter {

    private LoginView.login view;
    private LoginView.register register;
    private ProfileView.upload upload;

    private Context context;
    private MyPreferencesData myPreferencesData;

    private int userId;
    private int employeeId;
    private int roleId;
    private String name;
    private String sectionId;

    private String bornPlace;
    private String dob;
    private String gender;
    private String phone;
    private String eml;
    private String unit;
    private int positionId;
    private int institutionId;
    private boolean verified;
    private int statusVerified;

    public LoginPresenter(LoginView.login view, Context context) {
        this.view = view;
        this.context = context;
    }

    public LoginPresenter(LoginView.register register, Context context) {
        this.register = register;
        this.context = context;
    }

    public LoginPresenter(ProfileView.upload upload, Context context) {
        this.upload = upload;
        this.context = context;
    }

    public void checkLogin(String email, String pass) {

        if (pass.length() == 0) {
            view.onPasswordValidationError("Password Harus Diisi");
        } else {
            view.removePasswordValidation();
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.onEmailValidationError("Email Tidak Valid");
        } else {
            view.removeEmailValidation();
        }

        this.retrofitAuth(email, pass);
    }

    private void retrofitAuth(String email, String pass) {
        Call<UserAuthEntity> call = this.initService().getDataUser(email, pass);
        call.enqueue(new Callback<UserAuthEntity>() {
            @Override
            public void onResponse(Call<UserAuthEntity> call, Response<UserAuthEntity> response) {
                if (response.isSuccessful()) {
                    UserAuthEntity user = response.body();

                    userId = user.getUserId();
                    employeeId = user.getEmployee().getEmployeeId();
                    roleId = user.getEmployee().getRole().getRoleId();
                    name = user.getEmployee().getName();

                    bornPlace = user.getEmployee().getBornPlaces();
                    dob = user.getEmployee().getDob();
                    gender = user.getEmployee().getGender();
                    phone = user.getEmployee().getPhone();
                    eml = user.getEmail();
                    unit = user.getEmployee().getUnit();
                    positionId = user.getEmployee().getPosition().getPositionId();
                    institutionId = user.getEmployee().getInstitution().getInstitutionId();
                    verified = user.getEmployee().isVerified();
                    statusVerified = user.getEmployee().getStatusVerified();

                    if (user.getEmployee().getSection() == null) {
                        sectionId = "";
                    } else {
                        sectionId = String.valueOf(user.getEmployee().getSection().getSectionId());
                    }

                    setSharedPreferences(
                            userId,
                            employeeId,
                            roleId,
                            sectionId,
                            name,
                            bornPlace,
                            dob,
                            gender,
                            phone,
                            eml,
                            unit,
                            positionId,
                            institutionId,
                            String.valueOf(verified),
                            statusVerified
                    );
                    view.onSuccess();
                } else {
                    BadRequest badRequest = LoginPresenter.super.gson.fromJson(
                            response.errorBody().charStream(), BadRequest.class);
                    view.onError(badRequest.getMessage());
                }
            }

            @Override
            public void onFailure(Call<UserAuthEntity> call, Throwable t) {
                t.getMessage();
            }
        });
    }

    private void setSharedPreferences(int userId,
                                      int employeeId,
                                      int roleId,
                                      String sectionId,
                                      String name,
                                      String bornPlace,
                                      String dob,
                                      String gender,
                                      String phone,
                                      String email,
                                      String unit,
                                      int positionId,
                                      int institutionId,
                                      String verified,
                                      int statusVerified) {
        myPreferencesData = MyPreferencesData.getInstance(context);

        String sec;
        if (sectionId.length() == 0) {
            sec = "";
        } else {
            sec = sectionId;
        }

        myPreferencesData.saveData(USER_ID, String.valueOf(userId));
        myPreferencesData.saveData(EMPLOYEE_ID, String.valueOf(employeeId));
        myPreferencesData.saveData(ROLE_ID, String.valueOf(roleId));
        myPreferencesData.saveData(SECTION_ID, sec);
        myPreferencesData.saveData(NAME, name);

        myPreferencesData.saveData(BORN_PLACE, bornPlace);
        myPreferencesData.saveData(DOB, dob);
        myPreferencesData.saveData(GENDER, gender);
        myPreferencesData.saveData(PHONE, phone);
        myPreferencesData.saveData(EMAIL, email);
        myPreferencesData.saveData(UNIT, unit);
        myPreferencesData.saveData(POSITION_ID, String.valueOf(positionId));
        myPreferencesData.saveData(INSTITUTION_ID, String.valueOf(institutionId));
        myPreferencesData.saveData(VERIFIED, verified);
        myPreferencesData.saveData(STATUS_VERIFIED, String.valueOf(statusVerified));
    }

    public void registerAccount(RegisterModel registerModel) {
        if (register.validateInput()) {
            register.removeError(false);
            this.submit(registerModel, Constant.METHOD_POST);
            register.showLoading();
        }
    }

    public void uploadSK(RegisterModel registerModel){
        this.submit(registerModel, Constant.METHOD_PUT);
    }

    public void getPosition() {
        Call<List<PositionEntity>> call = this.initService().getDataPosition();
        call.enqueue(new Callback<List<PositionEntity>>() {
            @Override
            public void onResponse(Call<List<PositionEntity>> call, Response<List<PositionEntity>> response) {
                List<PositionEntity> pos = response.body();
                if (response.isSuccessful() && pos.size() > 0) {
                    register.renderPosition(pos);
                }
            }

            @Override
            public void onFailure(Call<List<PositionEntity>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("COK", "onFailure: " + t.getMessage());
            }
        });
    }

    public void getInstitution() {
        Call<List<InstitutionEntity>> call = this.initService().getDataInstitution();
        call.enqueue(new Callback<List<InstitutionEntity>>() {
            @Override
            public void onResponse(Call<List<InstitutionEntity>> call, Response<List<InstitutionEntity>> response) {
                List<InstitutionEntity> ins = response.body();
                if (response.isSuccessful() && ins.size() > 0) {
                    register.renderInstitution(ins);
                }
            }

            @Override
            public void onFailure(Call<List<InstitutionEntity>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void submit(RegisterModel registerModel, String httpMethod) {

        MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain");
        MediaType MEDIA_TYPE_IMAGE = MediaType.parse("image/*");

        File mFile;
        String mFileName;
        String employeeId;
        int stsverified;

        RequestBody requestFile;
        if (registerModel.getFile() == null) {
            requestFile = RequestBody.create(MEDIA_TYPE_IMAGE, "");
            mFileName = "";
            stsverified = Constant.STATUS_VERIFIED_NULL;
        } else {
            mFile = registerModel.getFile();
            requestFile = RequestBody.create(MEDIA_TYPE_IMAGE, mFile);
            mFileName = registerModel.getFile().getName();
            stsverified = Constant.STATUS_VERIFIED_WAITING;
        }

        employeeId = registerModel.getEmployeeId();
        MultipartBody.Part image = MultipartBody.Part.createFormData("image", mFileName, requestFile);
        RequestBody name = RequestBody.create(MEDIA_TYPE_TEXT, registerModel.getName());
        RequestBody bornPlace = RequestBody.create(MEDIA_TYPE_TEXT, registerModel.getBornPlace());
        RequestBody dob = RequestBody.create(MEDIA_TYPE_TEXT, registerModel.getDob());
        RequestBody gender = RequestBody.create(MEDIA_TYPE_TEXT, registerModel.getGender());
        RequestBody phone = RequestBody.create(MEDIA_TYPE_TEXT, registerModel.getPhone());
        RequestBody email = RequestBody.create(MEDIA_TYPE_TEXT, registerModel.getEmail());
        RequestBody unit = RequestBody.create(MEDIA_TYPE_TEXT, registerModel.getUnit());
        RequestBody positionId = RequestBody.create(MEDIA_TYPE_TEXT, registerModel.getPositionId());
        RequestBody institutionId = RequestBody.create(MEDIA_TYPE_TEXT, registerModel.getInstitutionId());
        RequestBody roleId = RequestBody.create(MEDIA_TYPE_TEXT, registerModel.getRoleId());
        RequestBody verfied = RequestBody.create(MEDIA_TYPE_TEXT, "false");
        RequestBody password = RequestBody.create(MEDIA_TYPE_TEXT, registerModel.getPassword());
        RequestBody statusUpload = RequestBody.create(MEDIA_TYPE_TEXT, httpMethod);
        RequestBody statusVerified = RequestBody.create(MEDIA_TYPE_TEXT, String.valueOf(stsverified));
        RequestBody sectionId;
        if (registerModel.getSectionId() == null) {
            sectionId = RequestBody.create(MEDIA_TYPE_TEXT, "");
        } else {
            sectionId = RequestBody.create(MEDIA_TYPE_TEXT, registerModel.getSectionId());
        }

        if (httpMethod.contentEquals(Constant.METHOD_POST)) {
            Call<SuccessMessage> call1 = this.initService().submit(
                    name,
                    bornPlace,
                    dob,
                    gender,
                    phone,
                    email,
                    unit,
                    positionId,
                    institutionId,
                    roleId,
                    sectionId,
                    image,
                    verfied,
                    password,
                    statusUpload,
                    statusVerified
            );
            call1.enqueue(new Callback<SuccessMessage>() {
                @Override
                public void onResponse(Call<SuccessMessage> call, Response<SuccessMessage> response) {
                    register.onRegisterSuccess();
                }

                @Override
                public void onFailure(Call<SuccessMessage> call, Throwable t) {
                    register.onRegisterFailed();
                }
            });
        } else {
            Call<SuccessMessage> update = this.initService().updateProfile(
                    employeeId,
                    name,
                    bornPlace,
                    dob,
                    gender,
                    phone,
                    email,
                    unit,
                    positionId,
                    institutionId,
                    roleId,
                    sectionId,
                    image,
                    verfied,
                    password,
                    statusUpload,
                    statusVerified);

            update.enqueue(new Callback<SuccessMessage>() {
                @Override
                public void onResponse(Call<SuccessMessage> call, Response<SuccessMessage> response) {
                    upload.onUploaded("Berhasil");
                }

                @Override
                public void onFailure(Call<SuccessMessage> call, Throwable t) {
                    upload.onFailUploaded("Gagal");
                }
            });
        }
    }

    private LoginService initService() {
        LoginService service = NetworkClient.getRetrofit()
                .create(LoginService.class);
        return service;
    }
}
