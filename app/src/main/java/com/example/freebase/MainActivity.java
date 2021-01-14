package com.example.freebase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

public class MainActivity extends AppCompatActivity
{
    
    private static Integer global = 0;
    private FirebaseFirestore myData = FirebaseFirestore.getInstance();
    private CollectionReference myReference = myData.collection("ToLet Data");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void release (View view)
    {
        global+=1;
        EditText e1,e2,e3,e4,e5,e6;
        String s1,s2,s3,s4,s5,s6;
        e1  = (EditText)findViewById(R.id.place) ;
        e2  = (EditText)findViewById(R.id.owner) ;
        e3  = (EditText)findViewById(R.id.phone) ;
        e4  = (EditText)findViewById(R.id.price) ;
        e5  = (EditText)findViewById(R.id.detail) ;
        e6 =  (EditText)findViewById(R.id.pincode);

        s1 = e1.getText().toString().toUpperCase();
        s2 = e2.getText().toString().toUpperCase();
        s3 = e3.getText().toString().toUpperCase();
        s4 = e4.getText().toString().toUpperCase();
        s5 = e5.getText().toString().toUpperCase();
        s6 = e6.getText().toString().toUpperCase();

        if (!s3.matches("[0-9]+")|| s3.length() !=10 )
        {
            Toast.makeText(getApplicationContext(), "ENTER VALID PHONE NUMBER.", Toast.LENGTH_LONG).show();
            e1.setText("");
            e2.setText("");
            e3.setText("");
            e4.setText("");
            e5.setText("");
            e6.setText("");
        }

        else if(s1==null || s2==null || s3==null || s4==null || s5==null || s6==null)
        {
            Toast.makeText(getApplicationContext(), "GIVE COMPLETE DETAILS OF YOUR PROPERTY.", Toast.LENGTH_LONG).show();
            e1.setText("");
            e2.setText("");
            e3.setText("");
            e4.setText("");
            e5.setText("");
            e6.setText("");
        }
        else if(s1.isEmpty() || s2.isEmpty() || s3.isEmpty() || s4.isEmpty() || s5.isEmpty() || s6.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "GIVE COMPLETE DETAILS OF YOUR PROPERTY.", Toast.LENGTH_LONG).show();
            e1.setText("");
            e2.setText("");
            e3.setText("");
            e4.setText("");
            e5.setText("");
            e6.setText("");
        }
        else
        {
            propertyDetails p = new propertyDetails(s1,s2,s3,s4,s5);

            myReference.add(p);
            Toast.makeText(getApplicationContext(), "DATA ADDED QUITE SUCCESSFULLY!.", Toast.LENGTH_LONG).show();
            e1.setText("");
            e2.setText("");
            e3.setText("");
            e4.setText("");
            e5.setText("");
            e6.setText("");
        }
    }

    public void search(View view)
    {
        Intent i = new Intent(this,Search.class);
        startActivity(i);
    }
}
