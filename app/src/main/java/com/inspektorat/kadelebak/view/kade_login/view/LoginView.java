package com.inspektorat.kadelebak.view.kade_login.view;

import com.inspektorat.kadelebak.entity.InstitutionEntity;
import com.inspektorat.kadelebak.entity.PositionEntity;
import com.inspektorat.kadelebak.view.kade_village.entity.Institution;

import java.util.Collection;
import java.util.Collections;

public class LoginView {
    public interface login {

        void onEmailValidationError(String msg);
        void onPasswordValidationError(String msg);
        void removeEmailValidation();
        void removePasswordValidation();
        void onError(String msg);
        void onSuccess();
    }

    public interface register {
        boolean validateInput();
        void removeError(boolean status);
        void renderPosition(Collection<PositionEntity> positionList);
        void renderInstitution(Collection<InstitutionEntity> institutionList);
        void onRegisterSuccess();
        void onRegisterFailed();
    }
}
