package com.example.attendancesystem;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.attendancesystem.format.StudentData;

import java.util.ArrayList;

public class ViewAttendanceListAdapter extends ArrayAdapter<StudentData> {

    private Context mContext;
    private int mResource;

    public ViewAttendanceListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<StudentData> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //get the students information
        String fname = getItem(position).getFname();
        String lname = getItem(position).getLname();
        String rollno = getItem(position).getRollno();
        int totalattendance =getItem(position).getTotalAttendance();

//        Log.d("in adapter",fname+lname);

        final StudentData student=new StudentData();
        student.setRollno(rollno);
        student.setLname(lname);
        student.setFname(fname);
//        student.setStatus(status);


        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView rollNo, Name, totalAttendance;
        rollNo = convertView.findViewById(R.id.rollNo);
        Name = convertView.findViewById(R.id.name);
        totalAttendance = convertView.findViewById(R.id.totalAttendance);


        rollNo.setText(rollno);
//    boolean isPositive(long[] id) {
//        for (int i = 0; i < id.length; i++) {
//            if(id[i]<0)
//                return false;
//        }
//        return true;
//    }
        Name.setText(fname+" "+lname);
        totalAttendance.setText(String.valueOf(totalattendance));
        return convertView;
    }
}
