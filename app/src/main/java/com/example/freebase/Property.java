package com.example.freebase;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

public class Property<p>  extends ArrayAdapter<propertyDetails>
{

    private FirebaseFirestore myData = FirebaseFirestore.getInstance();
    private CollectionReference myReference = myData.collection("ToLet Data");
    private static final String TAG = "newwFragmentActivity";
    private ArrayList<propertyDetails> list = new ArrayList<propertyDetails>();
    public Property(Context context, ArrayList<propertyDetails> words)
    {
        super(context, 0, words);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItemView = convertView;
        if (listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.prop, parent, false);
        }

        propertyDetails currentWord = (propertyDetails) getItem(position);
        

        TextView first = (TextView) listItemView.findViewById(R.id.first);
        first.setText("PLACE:  " + currentWord.place);
        first.setTextSize(15);
        first.setTextColor(-16777216);


        TextView second = (TextView) listItemView.findViewById(R.id.second);
        second.setText("OWNER NAME:  " + currentWord.ownerName);
        second.setTextSize(15);
        second.setTextColor(-16777216);

        TextView one = (TextView) listItemView.findViewById(R.id.one);
        one.setText("PHONE NUMBER:  " + currentWord.phone);
        one.setTextSize(15);
        one.setTextColor(-16777216);

        TextView two = (TextView) listItemView.findViewById(R.id.two);
        two.setText("PRICE:  " + currentWord.price);
        two.setTextSize(15);
        two.setTextColor(-16777216);

        TextView four = (TextView) listItemView.findViewById(R.id.four);
        four.setText("DETAILS:  " + currentWord.propDetail);
        four.setTextSize(15);
        four.setTextColor(-16777216);

        

        ImageButton b = listItemView.findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                permission(currentWord);
            }
        });




        ImageButton c = listItemView.findViewById(R.id.call);
        {
            c.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                       //call(currentWord.phone);
                    Log.d(TAG, "Calling.");
                    Intent i=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+currentWord.phone.trim()));
                    getContext().startActivity(i);

                }
            });
        }

        ImageButton d = listItemView.findViewById(R.id.whatsapp);
        {
            d.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    whatsapp(currentWord.phone);
                }
            });
        }

        return listItemView;

    }

    public void whatsapp(String phone)
    {

        Uri mUri = Uri.parse("smsto:+91"+phone.trim());
        Intent mIntent = new Intent(Intent.ACTION_SENDTO, mUri);
        mIntent.setPackage("com.whatsapp");
        mIntent.putExtra("sms_body", "The text goes here");
        mIntent.putExtra("chat",true);
        getContext().startActivity(mIntent);

    }

    public void permission(propertyDetails word)
    {
        androidx.appcompat.app.AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(getContext());
        myAlertBuilder.setTitle("DELETE");
        myAlertBuilder.setMessage("Click OK to delete, or Cancel to not:");
        myAlertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        d(word);
                    }
                });
        myAlertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(getContext(), "NOT DELETED.", Toast.LENGTH_LONG).show();
                    }
                });
        myAlertBuilder.show();

    }

    public void d(propertyDetails object)
    {
        myReference.document(object.id).delete().addOnSuccessListener(new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid)
            {
                Log.d(TAG, "DocumentSnapshot successfully deleted!");
                Toast.makeText(getContext(), "DELETED", Toast.LENGTH_LONG).show();
            }
        })
                .addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });
        //Log.d(TAG, "position: "+Integer.toString(position)+"item: ");
        remove(object);
    }

}
