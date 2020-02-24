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

public class perStudentInfo extends AppCompatActivity {

    TextView tv1,tv2,tv3,tv4,tv5;
    Button verifybtn,discardbtn;
    //Student stat;
    Student tempholder;
    DatabaseReference reff1;

    @Override
    public void onBackPressed() {
        Intent i1 = new Intent(this,sortedStudent.class);
        startActivity(i1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_student_info);
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv3 = (TextView)findViewById(R.id.tv3);
        tv4 = (TextView)findViewById(R.id.tv4);
        tv5 = (TextView)findViewById(R.id.tv5);
        verifybtn = (Button)findViewById(R.id.verifybtn);
        discardbtn = (Button)findViewById(R.id.discardbtn);
        tempholder = new Student();
        reff1 = FirebaseDatabase.getInstance().getReference().child("Student");
        tempholder.name = getIntent().getStringExtra("value1");
        tempholder.city = getIntent().getStringExtra("value2");
        tempholder.cast = getIntent().getStringExtra("value3");
        tempholder.acpcrank = getIntent().getStringExtra("value4");
        tempholder.phone = getIntent().getStringExtra("value5");
        tempholder.id = getIntent().getExtras().getInt("value6");
        tempholder.roll = getIntent().getStringExtra("value7");
        tv1.setText(tempholder.name);
        tv2.setText(tempholder.city);
        tv3.setText(tempholder.cast);
        tv4.setText(tempholder.acpcrank);
        tv5.setText(tempholder.phone);

        verifybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempholder.setStatus(true);
                reff1.child(String.valueOf(tempholder.acpcrank)+" "+(tempholder.id)).child("status").setValue(tempholder.status);
                Toast.makeText(perStudentInfo.this,"Verified..",Toast.LENGTH_LONG).show();
            }
        });
        discardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempholder.setStatus(false);
                reff1.child(String.valueOf(tempholder.acpcrank)+" "+(tempholder.id)).child("status").setValue(tempholder.status);
                Toast.makeText(perStudentInfo.this,"Discarded..",Toast.LENGTH_LONG).show();

            }
        });

    }
}