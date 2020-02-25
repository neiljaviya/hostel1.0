package com.example.hosteltest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Sendemail extends AppCompatActivity {

    private TextView sendemail;
    private EditText mEditTextSub;
    private EditText mEditTextMsg;
    Intent intent;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    DatabaseReference ref;
    ListView lw;
    long id;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendemail);

        sendemail = findViewById(R.id.sendemail);
        mEditTextSub = findViewById(R.id.edit_text_sub);
        mEditTextMsg = findViewById(R.id.edit_text_msg);

        String path;
        ref = FirebaseDatabase.getInstance().getReference().child("Student");
        lw=(ListView)findViewById(R.id.mail);
        final ArrayAdapter arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        lw.setAdapter(arrayAdapter);
        Button buttonSend=findViewById(R.id.button_send);
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()){
                    id=dataSnapshot.getChildrenCount();
                }
                for (int i=1;i<id;i=i+9)
                {
                    ref=FirebaseDatabase.getInstance().getReference().child("Student");
                    String value=dataSnapshot.child("sendemail").getValue().toString();
                    arrayList.add(value+",");
                    arrayAdapter.notifyDataSetChanged();

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

        });

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail(arrayList,mEditTextSub,mEditTextMsg);
            }
        });
    }

    private void sendMail(ArrayList<String> emailAddress, EditText data, EditText sub)
    {

        //example1@gmail.com,example2@gmail.com

        String subject=mEditTextSub.getText().toString();
        String msg=mEditTextMsg.getText().toString();



        Intent intent=new Intent(Intent.ACTION_SEND);

        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{String.valueOf(emailAddress)});

        intent.setType("messsage/rfc822");

        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,msg);


        startActivity(Intent.createChooser(intent,  "Choose an email client"));

    }
}
