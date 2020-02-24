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

public class sortedStudent extends AppCompatActivity {

    DatabaseReference reff;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adpt;
    ListView lw;
    int j=0,i=0;
    int c=0,t=0,ob=0,op=0;
    Student[] stud = new Student[110];
    Student[] sort = new Student[110];

    @Override
    public void onBackPressed() {
        Intent i1 = new Intent(this,adminMain.class);
        startActivity(i1);
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocation);

        reff = FirebaseDatabase.getInstance().getReference("Student");
        lw = (ListView) findViewById(R.id.listview);
        arrayList = new ArrayList<String>();
        adpt = new ArrayAdapter<String>(this,R.layout.list_theme,R.id.stuinfo,arrayList);

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                        stud[j] = ds.getValue(Student.class);
                        j++;
                }
                for(int k=j-1;k>=0;k--)
                {
                    if((stud[k].getCast()).contentEquals("sc") && c<15)
                    {
                        sort[i]=stud[k];
                        arrayList.add(sort[i].getName()+"\n"+sort[i].getAcpcrank()+"\n"+"SC");
                        c++;
                        i++;
                    }
                    else if((stud[k].getCast()).contentEquals("st") && t<7)
                    {
                        sort[i] = stud[k];
                        arrayList.add(sort[i].getName()+"\n"+sort[i].getAcpcrank()+"\n"+"ST");
                        t++;
                        i++;
                    }
                    else if((stud[k].getCast()).contentEquals("obc") && ob<27)
                    {
                        sort[i] = stud[k];
                        arrayList.add(sort[i].getName()+"\n"+sort[i].getAcpcrank()+"\n"+"OBC");
                        ob++;
                        i++;
                    }
                    else if(((stud[k].getCast()).contentEquals("sc")||(stud[k].getCast()).contentEquals("st") ||(stud[k].getCast()).contentEquals("obc") ||(stud[k].getCast()).contentEquals("open") )&&op<=51)                    {
                        sort[i] = stud[k];
                        arrayList.add(sort[i].getName()+"\n"+sort[i].getAcpcrank()+"\n"+sort[i].getCast());
                        op++;
                        i++;
                    }
                    else{}
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
                    temp.name = sort[position].name;
                    temp.city = sort[position].city;
                    temp.cast = sort[position].cast;
                    temp.acpcrank = sort[position].acpcrank;
                    temp.phone = sort[position].phone;
                    temp.status = sort[position].status;
                    temp.id = sort[position].id;
                    temp.roll = sort[position].roll;
                    Intent in = new Intent(sortedStudent.this,perStudentInfo.class);
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




        /*reff.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                reff = FirebaseDatabase.getInstance().getReference().child("Student");
                if(dataSnapshot.exists())
                {
                    id = dataSnapshot.getChildrenCount();
                }
                for(int i=1;i<=id;i=i+9) {
                    Student value = dataSnapshot.getValue(Student.class);
                    String name = value.getName();
                    String city = value.getCity();
                    String phone = value.getPhone();
                    String roll = value.getRoll();
                    String acpcrank = value.getAcpcrank();
                    String cast = value.getCast();
                    Boolean stat = value.isStatus();
                    student[j] = value;
                    if(stat == false)
                    {
                        arrayList.add(student[j].getName()+"\n"+student[j].getPhone()+"\n"+student[j].getAcpcrank());
                    }
                    j++;
                }


            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }
}
