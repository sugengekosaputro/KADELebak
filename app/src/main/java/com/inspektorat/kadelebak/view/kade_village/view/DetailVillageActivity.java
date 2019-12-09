package com.inspektorat.kadelebak.view.kade_village.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.inspektorat.kadelebak.Constant;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.entity.InstitutionEntity;
import com.inspektorat.kadelebak.view.kade_village.entity.Institution;

import java.util.Objects;

public class DetailVillageActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_village);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Bundle object = intent.getExtras();
        InstitutionEntity institution = (InstitutionEntity) object.getSerializable(Constant.SERIALIZABLE_VILLAGE);

        setView(institution);
    }

    private void setView(InstitutionEntity institution) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(institution.getName());
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
}
