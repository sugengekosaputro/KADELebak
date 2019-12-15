package com.inspektorat.kadelebak.view.kade_village.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.inspektorat.kadelebak.Constant;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.entity.InstitutionEntity;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailVillageActivity extends AppCompatActivity {

    @BindView(R.id.iv_village)
    ImageView ivVillage;
    @BindView(R.id.address_office)
    TextView address;
    @BindView(R.id.telephone)
    TextView telephone;
    @BindView(R.id.email)
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_village);
        ButterKnife.bind(this);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Bundle object = intent.getExtras();
        InstitutionEntity institution = (InstitutionEntity) object.getSerializable(Constant.SERIALIZABLE_VILLAGE);

        setView(institution);
    }

    private void setView(InstitutionEntity institution) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(institution.getName());
        address.setText(institution.getAddress());
        telephone.setText(institution.getPhone());
        email.setText(institution.getEmail());
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
