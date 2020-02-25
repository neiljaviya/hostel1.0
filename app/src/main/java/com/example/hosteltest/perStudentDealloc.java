package com.example.hosteltest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class perStudentDealloc extends AppCompatActivity {

    TextView dtv1, dtv2, dtv3, dtv4, dtv5;
    Button verifybtn, deallocdiscardbtn;
    //Student stat;
    Student tempholder;
    DatabaseReference reff1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_student_dealloc);

        dtv1 = (TextView)findViewById(R.id.dtv1);
        dtv2 = (TextView)findViewById(R.id.dtv2);
        dtv3 = (TextView)findViewById(R.id.dtv3);
        dtv4 = (TextView)findViewById(R.id.dtv4);
        dtv5 = (TextView)findViewById(R.id.dtv5);
        deallocdiscardbtn = (Button)findViewById(R.id.deallocdiscardbtn);
        tempholder = new Student();
        reff1 = FirebaseDatabase.getInstance().getReference().child("Student");
        tempholder.name = getIntent().getStringExtra("value1");
        tempholder.city = getIntent().getStringExtra("value2");
        tempholder.cast = getIntent().getStringExtra("value3");
        tempholder.acpcrank = getIntent().getStringExtra("value4");
        tempholder.phone = getIntent().getStringExtra("value5");
        tempholder.id = getIntent().getExtras().getInt("value6");
        tempholder.roll = getIntent().getStringExtra("value7");
        dtv1.setText(tempholder.name);
        dtv2.setText(tempholder.city);
        dtv3.setText(tempholder.cast);
        dtv4.setText(tempholder.acpcrank);
        dtv5.setText(tempholder.phone);

        deallocdiscardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tempholder.setStatus(false);
                reff1.child(tempholder.acpcrank+" "+tempholder.id).removeValue();
                Toast.makeText(perStudentDealloc.this,"Discarded..",Toast.LENGTH_LONG).show();

            }
        });
    }

}
