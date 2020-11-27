package com.inspektorat.kadelebak.view.kade_profile;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.inspektorat.kadelebak.Constant;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.data.MyPreferencesData;
import com.inspektorat.kadelebak.view.kade_login.LoginPresenter;
import com.inspektorat.kadelebak.view.kade_login.model.RegisterModel;
import com.inspektorat.kadelebak.view.kade_profile.view.ProfileView;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UploadActivity extends AppCompatActivity implements ProfileView.upload {

    @BindView(R.id.appbarlayout_support)
    MaterialToolbar toolbar;

    @BindView(R.id.iv_preview)
    ImageView imageView;

    @BindView(R.id.btn_upload)
    MaterialButton btnUpload;

    private ActionBar actionBar;
    private LoginPresenter presenter;
    private RegisterModel registerModel;
    private MyPreferencesData myPreferencesData;

    Bitmap bitmap;
    File file;
    String imagePath;
    boolean enabledIconUpload = false;

    public static final int PICK_IMAGE = 212;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Upload SK");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        btnUpload.setVisibility(View.GONE);
        myPreferencesData = MyPreferencesData.getInstance(getApplicationContext());
        presenter = new LoginPresenter(this, getApplicationContext());
    }

    @OnClick(R.id.btn_upload)
    void uploadSK(){
        registerModel = new RegisterModel();
        registerModel.setName(myPreferencesData.getData(Constant.NAME));
        registerModel.setDob(myPreferencesData.getData(Constant.DOB));
        registerModel.setBornPlace(myPreferencesData.getData(Constant.BORN_PLACE));
        registerModel.setGender(myPreferencesData.getData(Constant.GENDER));
        registerModel.setPhone(myPreferencesData.getData(Constant.PHONE));
        registerModel.setEmail(myPreferencesData.getData(Constant.EMAIL));
        registerModel.setUnit(myPreferencesData.getData(Constant.UNIT));
        registerModel.setInstitutionId(myPreferencesData.getData(Constant.INSTITUTION_ID));
        registerModel.setPositionId(myPreferencesData.getData(Constant.POSITION_ID));
        registerModel.setRoleId(myPreferencesData.getData(Constant.ROLE_ID));
        registerModel.setSectionId(myPreferencesData.getData(Constant.SECTION_ID));
        registerModel.setFile(file);
        registerModel.setPassword("");
        registerModel.setEmployeeId(myPreferencesData.getData(Constant.EMPLOYEE_ID));
        this.presenter.uploadSK(registerModel);
    }

    @OnClick(R.id.btn_choose_image)
    void pickFromGallery() {
        //Create an Intent with action as ACTION_PICK
        Intent intent = new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        // Launching the Intent
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();

            try {
                //getting image from gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                Picasso.with(getApplicationContext())
                        .load(filePath)
                        .fit()
                        .centerCrop()
                        .into(imageView);

                //Setting image to ImageView
                imageView.setBackground(null);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                imagePath = getPath(filePath);

                file = new File(imagePath);
                enabledIconUpload = true;


            } catch (Exception e) {
                e.printStackTrace();
            }

            if (enabledIconUpload) {
                btnUpload.setVisibility(View.VISIBLE);
            }
        }
    }

    private String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (enabledIconUpload){
            getMenuInflater().inflate(R.menu.menu_upload, menu);
        }
        return true;
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
    public void onUploaded(String msg) {
        this.finish();
    }

    @Override
    public void onFailUploaded(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
    }
}
