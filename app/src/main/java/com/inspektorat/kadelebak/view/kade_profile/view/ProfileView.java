package com.inspektorat.kadelebak.view.kade_profile.view;

import com.inspektorat.kadelebak.entity.EmployeeEntity;

import java.util.List;

public class ProfileView {

    public interface view {
        void showDataProfile(EmployeeEntity profileList);
        void onErrorData(String msg);
    }

    public interface upload{
        void onUploaded(String msg);
        void onFailUploaded(String msg);
    }
}
