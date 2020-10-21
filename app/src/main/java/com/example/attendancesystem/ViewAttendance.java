package com.example.attendancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ViewAttendance extends AppCompatActivity {

    Spinner branch,Class,div;
    String selectedBranch,selectedClass,selectedDiv;
    Button submit;
    private String[] branchString = new String[] { "cse","extc","it"};
    private String[] classString = new String[] {"FY","SY","TY","BTECH"};
    private String[] divString =new String[]{"A","B"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);

        branch = findViewById(R.id.spinner1);
        Class = findViewById(R.id.spinner2);
        div= findViewById(R.id.spinner3);

        branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedBranch =(String)branch.getSelectedItem();
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
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        div.setAdapter(adapter3);


        submit=findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent=new Intent(ViewAttendance.this, ViewAttendanceByBranch.class);
                newIntent.putExtra("branch", selectedBranch);
                newIntent.putExtra("class",selectedClass);
                newIntent.putExtra("div",selectedDiv);
                startActivity(newIntent);
            }
        });
    }
}
