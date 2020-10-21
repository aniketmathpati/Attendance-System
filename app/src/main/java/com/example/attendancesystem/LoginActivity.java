package com.example.attendancesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login,register;
    private FirebaseAuth auth;
    private LoadingDialog loadingDialog = new LoadingDialog(LoginActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);

        login=(Button)findViewById(R.id.btnlogin);
        register = findViewById(R.id.btnregister);
        auth =FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,passwd;
                email=username.getText().toString();
                passwd=password.getText().toString();
                if(email.isEmpty()||passwd.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Empty credentials",Toast.LENGTH_SHORT).show();
//                    Log.d("error","empty");
                }
                else{
                    validate(email,passwd);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }

    private void validate(final String email, String passwd){
        loadingDialog.startLoading();
        auth.signInWithEmailAndPassword(email,passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               loadingDialog.dismissDialog();
               if(task.isSuccessful()){
                   Toast.makeText(LoginActivity.this,"Login successful !!",Toast.LENGTH_SHORT).show();
                   Intent newIntent=new Intent(LoginActivity.this, HomeActivity.class);
                   newIntent.putExtra("email",email);
                   startActivity(newIntent);
                   finish();
               }
               else{
                   Toast.makeText(LoginActivity.this,"Wrong email or password",Toast.LENGTH_SHORT).show();
               }
           }
        });

//       LoginAsyncTask myTask = new LoginAsyncTask();
//       myTask.execute(email, passwd);


//       auth.signInWithEmailAndPassword(email, passwd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//           @Override
//           public void onSuccess(AuthResult authResult) {
//               Toast.makeText(LoginActivity.this,"Login successful !!",Toast.LENGTH_SHORT);
//               startActivity(new Intent(LoginActivity.this,HomeActivity.class));
//               finish();
//           }
//
//
//       });
    }

//    private class LoginAsyncTask extends AsyncTask<String, Void, Boolean>{
//        Boolean result = true;
//        @Override
//        protected Boolean doInBackground(String... strings) {
//            auth.signInWithEmailAndPassword(strings[0], strings[1]).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    Log.d("debug",String.valueOf(task.isSuccessful()));
//                    if(task.isSuccessful())
//                        result = true;
//                    else
//                        result = false;
//                }
//            });
//            return result;
//        }
//
//        @Override
//        protected void onPostExecute(Boolean aBoolean) {
//            super.onPostExecute(aBoolean);
//            afterLogin(aBoolean);
//        }
//    }
//
//    public void afterLogin(Boolean success){
////        loadingDialog.dismissDialog();
//        if(success){
//            Toast.makeText(LoginActivity.this,"Login successful !!",Toast.LENGTH_SHORT).show();
//            Intent newIntent=new Intent(LoginActivity.this, HomeActivity.class);
//            newIntent.putExtra("email",username.toString());
//            startActivity(newIntent);
//
//            finish();
//        }
//        else{
//            Toast.makeText(LoginActivity.this,"Wrong email or password",Toast.LENGTH_SHORT).show();
//        }
//    }
}
