package com.example.attendancesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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

public class ViewAttendanceByBranch extends AppCompatActivity {

    private ListView listView;
    private String branch, Class, div;
    private ViewAttendanceListAdapter adapter;
    TextView noStudentAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance_by_branch);

        listView = findViewById(R.id.listview);
        noStudentAlert = findViewById(R.id.studentalert);

        branch = getIntent().getExtras().getString("branch");
        Class = getIntent().getExtras().getString("class");
        div = getIntent().getExtras().getString("div");

        final ArrayList<StudentData> StudentList = this.getStudents();

        adapter = new ViewAttendanceListAdapter(this,R.layout.adapter_list_layout_view_attendance,StudentList);
        listView.setAdapter(adapter);


    }

    private ArrayList<StudentData> getStudents() {
        final ArrayList<StudentData> list=new ArrayList<>();
        final LoadingDialog loadingDialog = new LoadingDialog(ViewAttendanceByBranch.this);
        loadingDialog.startLoading();

        class GetStudentsAsyncTask extends AsyncTask<Void, Void, Void> {
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
