package com.example.attendancesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.example.attendancesystem.format.StudentData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

import java.util.HashMap;

public class TakeAttendanceByBranch extends AppCompatActivity {
    Button submit;
    private String branch, Class, div;
    ListView listStudent;
    StudentListAdapter adapter;
    TextView noStudentAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance_by_branch);
        submit = findViewById(R.id.confirmAttendence);
        listStudent = findViewById(R.id.listview);
        noStudentAlert = findViewById(R.id.studentalert);



        branch = getIntent().getExtras().getString("branch");
        Class = getIntent().getExtras().getString("class");
        div = getIntent().getExtras().getString("div");


        final ArrayList<StudentData> StudentList= this.getStudents();

        adapter = new StudentListAdapter(this, R.layout.adapter_list_layout_take_attendance, StudentList);
        listStudent.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
        submit.setOnClickListener(new View.OnClickListener() {
            DatabaseReference ref =FirebaseDatabase.getInstance().getReference();
            @Override
            public void onClick(View v) {
                int temp;
                for(StudentData student:StudentList){
                    temp=student.getStatus()+student.getTotalAttendance();
                    HashMap<String,Object> map=new HashMap<>();
                    map.put("Attendance",Integer.toString(temp));
                    ref.child(student.getBranch()).child(student.getclass()).child(student.getDiv()).child(student.getRollno()).updateChildren(map);
                }
                finish();
            }
        });

    }

    private ArrayList<StudentData> getStudents() {
        final ArrayList<StudentData> list=new ArrayList<>();
        final LoadingDialog loadingDialog = new LoadingDialog(TakeAttendanceByBranch.this);
        loadingDialog.startLoading();

        class GetStudentsAsyncTask extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child(branch).child(Class).child(div);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for(DataSnapshot data:dataSnapshot.getChildren()){
                            HashMap<String,Object> map=new HashMap<>();
                            map=(HashMap)data.getValue();
//                    Log.d("fd",map.get("FirstName").toString());
                            StudentData student=new StudentData();
                            student.setFname(map.get("FirstName").toString());
                            student.setLname(map.get("LastName").toString());
                            student.setTotalAttendance(Integer.parseInt(map.get("Attendance").toString()));
                            student.setRollno(data.getKey());
                            student.setBranch(branch);
                            student.setClass(Class);
                            student.setDiv((div));
                            list.add(student);
                        }
                        adapter.notifyDataSetChanged();
                        loadingDialog.dismissDialog();
                        if(list.isEmpty()){
                            noStudentAlert.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) { }
                });
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }

        GetStudentsAsyncTask asyncTask = new GetStudentsAsyncTask();
        asyncTask.execute();
        return list;
    }

}
