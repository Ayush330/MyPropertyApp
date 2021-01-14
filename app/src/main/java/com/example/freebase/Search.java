package com.example.freebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Search extends AppCompatActivity
{

    private FirebaseFirestore myData = FirebaseFirestore.getInstance();
    private CollectionReference myReference = myData.collection("ToLet Data");
    private CollectionReference heyThere;
    private static final String TAG = "FragmentActivity";
    public static ArrayList<propertyDetails> P = new ArrayList<propertyDetails>(5);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void go (View view)
    {
        ArrayList<propertyDetails> P = new ArrayList<propertyDetails>();
        P.clear();
        EditText e = (EditText)  findViewById(R.id.searchBar) ;
        String q =  e.getText().toString().toUpperCase();
        //Log.d(TAG, document.getId() + " => " + document.getData());
        myReference.whereEqualTo("place",q).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot document : task.getResult())
                    {
                        Log.d(null, document.getId() + " => " + document.getData()+" document "+document.getClass());
                        propertyDetails property = document.toObject(propertyDetails.class);
                        P.add(new propertyDetails(property.place,property.ownerName,property.phone,property.price,property.propDetail,document.getId()));
                        Log.d(null, property.ownerName);
                        Log.d(null, property.phone);
                        Log.d(null, property.propDetail);
                        Log.d(null, property.price);
                        //Log.d(TAG, "Size: "+Integer.toString(P.size())+" value: "+(P.get(P.size()-1)).ownerName);

                    }
                }
                else
                {
                    //Log.d(null, "Error getting documents: ", task.getException());
                    Toast.makeText(getApplicationContext(), "ERROR.", Toast.LENGTH_LONG).show();
                }
                //Log.d(null, "It is the Size Inside: "+Integer.toString(P.size()));
                if(P.size()==0)
                {
                    Toast.makeText(Search.this, "NO RECORD FOUND FOR YOUR CHOICE.", Toast.LENGTH_LONG).show();
                }

               // for(int i=0;i<P.size();i++)
                //{
                    if(P.size()!=0)
                    {
                        Property<propertyDetails>  prop = new Property<propertyDetails>(Search.this, P);
                        //Log.d(TAG, "Sizeeeeeeeee: "+Integer.toString(P.size())+"value: "+(P.get(P.size()-1)).ownerName);
                        ListView Lview = (ListView) findViewById(R.id.list_view);
                        Lview.setAdapter(prop);
                    }

                    // Create a message handling object as an anonymous class.

                //}

               
            }
        });


    }


}
