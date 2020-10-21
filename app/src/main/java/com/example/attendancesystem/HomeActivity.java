package com.example.attendancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    private Button btnadd, btnTakeAttendance, btnViewAttendence,logout;
    private String userEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userEmail = getIntent().getExtras().getString("email");
        btnadd = (Button)findViewById(R.id.btnadd);
        btnTakeAttendance = findViewById(R.id.btnattendance);
        btnViewAttendence = findViewById(R.id.btnviewattend);
        logout = findViewById(R.id.logout);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
            }
        });

        btnViewAttendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAttendance();
            }
        });

        btnTakeAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent=new Intent(HomeActivity.this, TakeAttendance.class);
                startActivity(newIntent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(HomeActivity.this,"logged out successfully!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this,StartActivity.class));
                finish();
            }
        });
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        Intent newIntent=new Intent();
//        startActivity(newIntent);
//    }

    private void addStudent(){
        Intent newIntent=new Intent(HomeActivity.this, AddStudentActivity.class);
        newIntent.putExtra("email",userEmail);
        startActivity(newIntent);
    }

    private void viewAttendance(){
        Intent newIntent = new Intent(HomeActivity.this, ViewAttendance.class);
        startActivity(newIntent);
    }
}
