package com.inspektorat.kadelebak.view.kade_forum.view;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.inspektorat.kadelebak.Constant;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.data.MyPreferencesData;
import com.inspektorat.kadelebak.view.kade_forum.presenter.ForumPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateForumActivity extends AppCompatActivity implements ForumView.CreateForum{

    @BindView(R.id.edt_forum_create)
    TextInputLayout edtContet;
    @BindView(R.id.btn_create_forum_send)
    MaterialButton btnSend;

    private Context context = this;
    private MyPreferencesData myPreferencesData;
    private ForumPresenter presenter;
    private String employeeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_forum);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Buat Forum");

        myPreferencesData = MyPreferencesData.getInstance(context);
        this.employeeId = myPreferencesData.getData(Constant.EMPLOYEE_ID);

        presenter = new ForumPresenter(this);

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

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void setErrorValidationMessage(String message) {
        edtContet.setError(message);
    }

    @Override
    public void setErrorValidationEnabled(boolean enabled) {
        edtContet.setErrorEnabled(enabled);
    }

    @Override
    public void onCreateSuccess() {
        this.finish();
    }

    @Override
    public void onCreateFailed(String message) {

    }

    @OnClick(R.id.btn_create_forum_send)
    void sendForum(){
        presenter.creteForum(
                Integer.valueOf(this.employeeId),
                edtContet.getEditText().getText().toString(),
                false
        );
    }

    private void showToast(String message){
        Toast.makeText(
                context,
                message,
                Toast.LENGTH_SHORT).show();
    }
}