package com.example.hosteltest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class roomAllocation extends AppCompatActivity {

    DatabaseReference reff3;
    DatabaseReference reff4;
    int j=0;
    int id=0;
    Button allocateRoom;
    Room rm;
    Student[] stu2 = new Student[100];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_allocation);
        rm = new Room();
        allocateRoom = (Button)findViewById(R.id.allocateRoom);
        reff4 = FirebaseDatabase.getInstance().getReference("Room");
        reff3 = FirebaseDatabase.getInstance().getReference("Student");
        reff3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    Student tmp;
                    tmp = ds.getValue(Student.class);
                    if(tmp.status == true)
                    {
                        stu2[j] = tmp;
                        j++;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        allocateRoom.setOnClickListener(new View.OnClickListener() {
            int l=0,i=1;
            @Override
            public void onClick(View v) {

                while(l < j) {
                    for(int k=0;k<3;k++){
                        stu2[l].room = i;
                        reff3.child(String.valueOf(stu2[l].acpcrank)+" "+(stu2[l].id)).child("room").setValue(stu2[l].room);
                        if(k == 0){
                            reff4.child(String.valueOf(i)).child("stu1").setValue(stu2[l].name);
                        }
                        else if(k == 1){
                            reff4.child(String.valueOf(i)).child("stu2").setValue(stu2[l].name);
                        }
                        else {
                            reff4.child(String.valueOf(i)).child("stu3").setValue(stu2[l].name);
                        }
                        l++;
                    }
                    i++;
                }
            }
        });
        /* //To add Room in database
        reff4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                id = (int) dataSnapshot.getChildrenCount();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        allocateRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff4.child(String.valueOf(id)).setValue(rm);
            }
        });*/
    }
}
