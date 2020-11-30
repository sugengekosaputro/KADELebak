package com.inspektorat.kadelebak.view.kade_splash.presenter;

import android.content.Context;
import android.widget.Toast;

import com.inspektorat.kadelebak.Constant;
import com.inspektorat.kadelebak.data.MyPreferencesData;
import com.inspektorat.kadelebak.entity.UserAuthEntity;
import com.inspektorat.kadelebak.model.BadRequest;
import com.inspektorat.kadelebak.networking.NetworkClient;
import com.inspektorat.kadelebak.view.kade_login.LoginPresenter;
import com.inspektorat.kadelebak.view.kade_login.LoginService;
import com.inspektorat.kadelebak.view.kade_splash.view.SplashView;

import org.apache.commons.lang3.StringUtils;

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

public class SplashPresenter {

    private SplashView view;
    private MyPreferencesData myPreferencesData;
    private Context context;
    private String email;
    private String pass;

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

    public SplashPresenter(SplashView view, Context context, String email, String pass) {
        this.view = view;
        this.myPreferencesData = MyPreferencesData.getInstance(context);
        this.email = email;
        this.pass = pass;
        this.context = context;
        checkPreferences();
    }

    private void checkPreferences(){
        if (email.equals("")) {
            view.onLogin();
        } else if (!myPreferencesData.getData(Constant.USER_ID).isEmpty()) {
            view.redirectActivity(email);
        } else {
            retrofitAuth(email, pass);
        }
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
                    view.redirectActivity(eml);
                }
            }

            @Override
            public void onFailure(Call<UserAuthEntity> call, Throwable t) {
                view.onLogin();
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

    private LoginService initService() {
        LoginService service = NetworkClient.getRetrofit()
                .create(LoginService.class);
        return service;
    }
}
