package com.example.hosteltest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Apply extends AppCompatActivity {

    private Button btnSubmit;
    EditText name,phone,roll, acpcrank,email,p_email;
    Spinner citySpinner,castSpinner;
    Student student;
    DatabaseReference reff;
    //long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);

        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phoneNumber);
        roll = (EditText) findViewById(R.id.rollNumber);
        castSpinner = (Spinner) findViewById(R.id.castSpinner);
        acpcrank = (EditText) findViewById(R.id.acpcrank);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        email = (EditText) findViewById(R.id.sendEmail);
        p_email = (EditText) findViewById(R.id.parentEmail);
        citySpinner = (Spinner) findViewById(R.id.citySpinner);
        student = new Student();
        reff = FirebaseDatabase.getInstance().getReference().child("Student");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    student.id = (int) dataSnapshot.getChildrenCount();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.setName(name.getText().toString().trim());
                student.setPhone(phone.getText().toString().trim());
                student.setRoll(roll.getText().toString().trim());
                student.setSendemail(email.getText().toString().trim());
                student.setParent_email(p_email.getText().toString().trim());
                student.setAcpcrank(acpcrank.getText().toString().trim());
                student.setCast(castSpinner.getSelectedItem().toString().toLowerCase().trim());
                student.setCity(citySpinner.getSelectedItem().toString().trim());
                student.setStatus(false);
                reff.child(String.valueOf(acpcrank.getText().toString())+" "+(student.id)).setValue(student);
                Toast.makeText(Apply.this,"SUCCESS",Toast.LENGTH_LONG).show();
            }
        });
    }
}
