package com.example.hosteltest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Deallocation extends AppCompatActivity {

    DatabaseReference reff;
    ListView lw;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adpt;
    Student[] stud = new Student[110];
    int j=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deallocation);

        reff = FirebaseDatabase.getInstance().getReference("Student");
        lw = (ListView) findViewById(R.id.listview_dealloc);
        arrayList = new ArrayList<String>();
        adpt = new ArrayAdapter<String>(this, R.layout.dealloc_list_theme, R.id.deallocstudinfo, arrayList);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(ds.getValue(Student.class).room!=0)
                    {
                        stud[j] = ds.getValue(Student.class);
                        arrayList.add(stud[j].getName()+"\n"+stud[j].getRoll()+"\n"+stud[j].getCity());
                        j++;
                    }
                }
                lw.setAdapter(adpt);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student temp = new Student();
                temp.name = stud[position].name;
                temp.city = stud[position].city;
                temp.cast = stud[position].cast;
                temp.acpcrank = stud[position].acpcrank;
                temp.phone = stud[position].phone;
                temp.status = stud[position].status;
                temp.id = stud[position].id;
                temp.roll = stud[position].roll;
                Intent in = new Intent(Deallocation.this,perStudentDealloc.class);
                in.putExtra("value1",temp.name);
                in.putExtra("value2",temp.city);
                in.putExtra("value3",temp.cast);
                in.putExtra("value4",temp.acpcrank);
                in.putExtra("value5",temp.phone);
                in.putExtra("value6",temp.id);
                in.putExtra("value7",temp.roll);
                startActivity(in);
            }
        });
    }
}
