package com.example.attendancesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText email,passwd;
    private Button register;
    private FirebaseAuth authenticate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.username);
        passwd = findViewById(R.id.passwd);
        register = findViewById(R.id.btnregister);

        authenticate=FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text_email=email.getText().toString();
                String text_passwd=passwd.getText().toString();

                if(text_email.isEmpty() || text_passwd.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Empty credentials!!",Toast.LENGTH_SHORT).show();
                }
                else if (text_passwd.length()<6){
                    Toast.makeText(RegisterActivity.this,"Password too short(at least 6 characters)",Toast.LENGTH_SHORT).show();
                }
                else{
                    registerUser(text_email,text_passwd);
                }
            }
        });
    }

    private void registerUser(String email, String passwd) {
        authenticate.createUserWithEmailAndPassword(email,passwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"Registered Successfully.",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(RegisterActivity.this,"Registration failed!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
