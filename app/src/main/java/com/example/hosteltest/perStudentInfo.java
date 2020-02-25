package com.example.hosteltest;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class perStudentInfo extends AppCompatActivity {

    TextView tv1,tv2,tv3,tv4,tv5;
    Button verifybtn,discardbtn,sendbtn;
    //Student stat;
    Student tempholder;
    DatabaseReference reff1;
    FirebaseAuth auth;

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
        sendbtn = (Button)findViewById(R.id.sendbutton);
        discardbtn = (Button)findViewById(R.id.discardbtn);
        tempholder = new Student();
        reff1 = FirebaseDatabase.getInstance().getReference().child("Student");
        tempholder.name = getIntent().getStringExtra("value1");
        tempholder.city = getIntent().getStringExtra("value2");
        tempholder.cast = getIntent().getStringExtra("value3");
        tempholder.acpcrank = getIntent().getStringExtra("value4");
        tempholder.phone = getIntent().getStringExtra("value5");
        tempholder.id = getIntent().getExtras().getInt("value6");
        tempholder.status = getIntent().getExtras().getBoolean("value9");
        tempholder.roll = getIntent().getStringExtra("value7");
        tempholder.sendemail = getIntent().getStringExtra("value8");
        tv1.setText(tempholder.name);
        tv2.setText(tempholder.city);
        tv3.setText(tempholder.cast);
        tv4.setText(tempholder.acpcrank);
        tv5.setText(tempholder.sendemail);

        verifybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempholder.setStatus(true);
                reff1.child(String.valueOf(tempholder.acpcrank)+" "+(tempholder.id)).child("status").setValue(tempholder.status);
                Toast.makeText(perStudentInfo.this,"Verified..",Toast.LENGTH_LONG).show();

            }
        });
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempholder.isStatus())
                {
                    sendcredentials();
                    auth = FirebaseAuth.getInstance();
                    auth.createUserWithEmailAndPassword(tempholder.getSendemail(),getpassword()).addOnCompleteListener(perStudentInfo.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(perStudentInfo.this, "Authentication failed." + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(perStudentInfo.this, "Success", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(perStudentInfo.this, "Student not verified..!!", Toast.LENGTH_SHORT).show();
                }
            }
            private void sendcredentials() {
                String recipient = tempholder.getSendemail();
                String[] recipients = recipient.split(",");
                String subject = "Username & Password for Hostel App";
                String password = getpassword();
                String msg = "Username:"+tempholder.getSendemail()+"\nPassword:"+password;
                Intent i = new Intent(Intent.ACTION_SEND);
                i.putExtra(Intent.EXTRA_EMAIL, recipients);
                i.putExtra(Intent.EXTRA_SUBJECT, subject);
                i.putExtra(Intent.EXTRA_TEXT, msg);

                i.setType("message/rfc822");
                startActivity(Intent.createChooser(i,"Choose an email client"));
            }

            private String getpassword() {
                String tmp;
                tmp = tempholder.getName()+tempholder.getAcpcrank();
                return tmp;
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