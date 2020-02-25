package com.example.hosteltest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class verifiedReq extends AppCompatActivity {
    DatabaseReference reff;
    ArrayList<String> arrayList2;
    ArrayAdapter<String> adpt2;
    ListView lw2;
    Student[] stu1 = new Student[100];
    int j=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verified_req);
        reff = FirebaseDatabase.getInstance().getReference("Student");
        lw2 = (ListView) findViewById(R.id.listview);
        arrayList2 = new ArrayList<String>();
        adpt2 = new ArrayAdapter<String>(this,R.layout.verified_list_theme,R.id.verifiedStuInfo,arrayList2);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    stu1[j] = ds.getValue(Student.class);
                    j++;
                }
                for(int k=j-1;k>=0;k--){
                    if(stu1[k].status == true) {
                        arrayList2.add(stu1[k].getName()+"\n"+stu1[k].getAcpcrank()+"\n"+stu1[k].getCity());
                    }
                }
                lw2.setAdapter(adpt2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
