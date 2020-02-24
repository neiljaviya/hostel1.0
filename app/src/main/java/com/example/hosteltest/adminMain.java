package com.example.hosteltest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class adminMain extends AppCompatActivity {

    private  Button slreq;
    private Button areq;
    private Button verifiedbtn;
    private  Button roomallocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        areq = (Button)findViewById(R.id.areq);
        slreq = (Button)findViewById(R.id.slreq);
        verifiedbtn = (Button)findViewById(R.id.verifiedbtn);
        roomallocation = (Button)findViewById(R.id.roomallocation);
        slreq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAllocation();
            }
        });
        areq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAllReq();
            }
        });
        verifiedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVerifiedReq();
            }
        });
        roomallocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRoomAllocation();
            }
        });
    }
    public void openRoomAllocation(){
        Intent i =new Intent(this,roomAllocation.class);
        startActivity(i);
    }
    public void openVerifiedReq(){
        Intent i = new Intent(this,verifiedReq.class);
        startActivity(i);
    }
    public void openAllReq(){
        Intent i = new Intent(this,allReq.class);
        startActivity(i);
    }
    public void openAllocation() {

        Intent i = new Intent(this, sortedStudent.class);
        startActivity(i);
    }
}
