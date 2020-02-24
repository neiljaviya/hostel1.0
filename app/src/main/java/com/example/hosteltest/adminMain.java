package com.example.hosteltest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class adminMain extends AppCompatActivity {

    private  Button slreq;
    private Button areq;
    private Button verifiedbtn;
    private  Button roomallocation;

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
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
