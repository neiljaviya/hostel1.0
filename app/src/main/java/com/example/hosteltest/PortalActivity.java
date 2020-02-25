package com.example.hosteltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PortalActivity extends AppCompatActivity {

    Button applyOpen,applyClose;
    DatabaseReference ref;
    Portal p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal);

        applyClose = (Button)findViewById(R.id.applyClose);
        applyOpen = (Button)findViewById(R.id.applyOpen);
        ref = FirebaseDatabase.getInstance().getReference().child("Portal");
        p =new Portal();
        applyClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               p.setApply(false);
               ref.setValue(p);
            }
        });
        applyOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.setApply(true);
                ref.setValue(p);
            }
        });
    }
}
