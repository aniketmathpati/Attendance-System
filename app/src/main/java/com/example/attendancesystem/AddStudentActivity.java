package com.example.attendancesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AddStudentActivity extends AppCompatActivity {
    EditText etfname,etlname,etrollno;
    Button add,cancel;
    Spinner branch,Class,div;
    String selectedbranch,selectedClass,selectedDiv,userEmail;
    private String[] branchString = new String[] { "cse","extc","it"};
    private String[] classString = new String[] {"FY","SY","TY","BTECH"};
    private String[] divString =new String[]{"A","B"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent);

        etfname = findViewById(R.id.etfname);
        etlname = findViewById(R.id.etlname);
        Class  = findViewById(R.id.etclass);
        div = findViewById(R.id.etdiv);
        etrollno = findViewById(R.id.etrollno);
        branch =findViewById(R.id.etbranch);
        add = findViewById(R.id.btnadd);
        cancel = findViewById(R.id.btncancel);

        userEmail = getIntent().getStringExtra("email");

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent=new Intent(AddStudentActivity.this, HomeActivity.class);
                newIntent.putExtra("email",userEmail);
                startActivity(newIntent);
                finish();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String msg=etfname.getText().toString()+etlname.getText().toString()+etdiv.getText().toString()+etrollno.getText().toString();
//                Log.d(LOG_TAG,msg);
                register();
            }
        });

        branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedbranch=(String)branch.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, branchString);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branch.setAdapter(adapter1);

        Class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedClass=(String)Class.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, classString);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Class.setAdapter(adapter2);

        div.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDiv=(String)div.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adapter3=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, divString);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        div.setAdapter(adapter3);
    }

    private void register(){
        String fname,lname,Class,div,rollno,branch;
        fname=etfname.getText().toString().toUpperCase();
        lname=etlname.getText().toString().toUpperCase();
        Class=selectedClass;
        div=selectedDiv;
        rollno=etrollno.getText().toString().toUpperCase();
        branch=selectedbranch;
//
//        long id=new FeedReaderDbHelper(getBaseContext()).dbinsert(fname,lname,Class,div,rollno,branch);
//        Log.d(LOG_TAG,Long.toString(id));
//        if(id>0){
//            Toast.makeText(this,"Added Successfully",Toast.LENGTH_SHORT).show();
//        }

        if(fname.isEmpty()||lname.isEmpty()||rollno.isEmpty()){
            Toast.makeText(this,"one or more fields may be empty",Toast.LENGTH_SHORT).show();
        }

        else {
//            Toast.makeText(this,fname+lname+rollno,Toast.LENGTH_SHORT).show();
            HashMap<String,Object> map=new HashMap<>();
            map.put("FirstName",fname);
            map.put("LastName",lname);
            map.put("Attendance",0);
            userEmail=userEmail.replaceAll(".","");
            Log.d("useremail",userEmail);

            DatabaseReference ref=FirebaseDatabase.getInstance().getReference();
            ref.child(userEmail).child(branch).child(Class).child(div).child(rollno).updateChildren(map);
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Toast.makeText(AddStudentActivity.this,"Student added successfully",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
//            if(t.isSuccessful()){
//                Toast.makeText(this,"Stored in Database",Toast.LENGTH_SHORT).show();
//            }
//            else{
//                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
//            }
        }

    }
}
