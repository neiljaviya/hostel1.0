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

public class allReq extends AppCompatActivity {

    DatabaseReference reff;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adpt;
    ListView lw;
    int j=0;
    Student[] stu = new Student[100];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_req);

        reff = FirebaseDatabase.getInstance().getReference("Student");
        lw = (ListView) findViewById(R.id.alllistview);
        arrayList = new ArrayList<String>();
        adpt = new ArrayAdapter<String>(this,R.layout.all_list_theme,R.id.allStuInfo,arrayList);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    stu[j] = ds.getValue(Student.class);
                    j++;
                }
                for(int k=j-1;k>=0;k--){

                    arrayList.add(stu[k].getName()+"\n"+stu[k].getAcpcrank()+"\n"+stu[k].getCity());
                }
                lw.setAdapter(adpt);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
