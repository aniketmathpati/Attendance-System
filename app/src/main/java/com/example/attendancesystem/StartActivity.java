package com.example.attendancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class StartActivity extends AppCompatActivity {

    private Button login,register;
    private TextView alert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        login=(Button)findViewById(R.id.btnlogin);
        register = findViewById(R.id.btnregister);
        alert =findViewById(R.id.connalert);

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        Network network = cm.getActiveNetwork();

        if(cm != null && network !=null){
//            Toast.makeText(this, "connected", Toast.LENGTH_SHORT).show();
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(StartActivity.this,LoginActivity.class));
                    finish();
                }
            });

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(StartActivity.this,RegisterActivity.class));
                }
            });
        }
        else{
//            Toast.makeText(this, "No Internet, check connection!", Toast.LENGTH_SHORT).show();
            alert.setVisibility(View.VISIBLE);
            login.setEnabled(false);
            register.setEnabled(false);

        }


    }



}
