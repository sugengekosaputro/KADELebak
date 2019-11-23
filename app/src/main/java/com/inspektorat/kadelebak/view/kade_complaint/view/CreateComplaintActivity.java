package com.inspektorat.kadelebak.view.kade_complaint.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateComplaintActivity extends AppCompatActivity {


    @BindView(R.id.spinner)
    Spinner spinner;

    @BindView(R.id.switch_anonymous)
    SwitchMaterial switchAnonymous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_complaint);
        ButterKnife.bind(this);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Buat Pengaduan");

        switchAnonymous.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String msg;
                if(!compoundButton.isChecked()){
                    msg = "Operator Dapat Melihat Nama Anda";
                } else {
                    msg = "Nama Anda Disembunyikan";
                }
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });

        List<User> userList = new ArrayList<>();
        User user1 = new User("Jim", 20, "jim@gmail.com");
        userList.add(user1);
        User user2 = new User("John", 23, "john@gmail.com");
        userList.add(user2);
        User user3 = new User("Jenny", 25, "jenny@gmail.com");
        userList.add(user3);

        ArrayAdapter<User> adapter = new ArrayAdapter<User>(this,
                android.R.layout.simple_spinner_item, userList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                User user = (User) parent.getSelectedItem();
                displayUserData(user);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void displayUserData(User user) {
        String name = user.getName();
        int age = user.getAge();
        String mail = user.getMail();

        String userData = "Name: " + name + "\nAge: " + age + "\nMail: " + mail;

        Toast.makeText(this, userData, Toast.LENGTH_SHORT).show();
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

    private Map<String, String> createMap() {
        Map<String, String> myMap = new HashMap<String, String>();
        myMap.put("ID-1", "BAGIAN 1");
        myMap.put("ID-2", "BAGIAN 2");
        myMap.put("ID-3", "BAGIAN 3");
        return myMap;
    }
}
