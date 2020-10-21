package com.example.attendancesystem;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.example.attendancesystem.format.StudentData;
import java.util.ArrayList;

public class StudentListAdapter extends ArrayAdapter<StudentData> {

    private Context mContext;
    private int mResource;


    public StudentListAdapter(Context context, int resource, ArrayList<StudentData> objects) {
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
        final int[] status = new int[1];

        Log.d("in adapter",fname+lname+ status[0]);

        final StudentData student=new StudentData();
        student.setRollno(rollno);
        student.setLname(lname);
        student.setFname(fname);
//        student.setStatus(status);


        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView rollNo,Name;
        RadioButton present,absent;
        rollNo=convertView.findViewById(R.id.rollNo);
        Name=convertView.findViewById(R.id.name);
//        staus
        present=convertView.findViewById(R.id.present);
        absent=convertView.findViewById(R.id.absent);

        present.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
// set Yes values in ArrayList if RadioButton is checked
                if (isChecked){
//                    status[0] =getItem(position).getStatus();
                    getItem(position).setStatus(1);
                }

            }
        });

        absent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
// set Yes values in ArrayList if RadioButton is checked
                if (isChecked){
//                    status[0] =getItem(position).getStatus();
                    getItem(position).setStatus(0);
                }
            }
        });

        rollNo.setText(rollno);
        Name.setText(fname+" "+lname);
        return convertView;
    }
}
