package com.tutorials180.demofbproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final String COLLECTION_NAME="MyTable";
    private EditText documentIdET,nameET,emailET;
    //Step 1 - Create Firebase Firestore object
    private FirebaseFirestore objectFirebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeFirebaseFirestoreObject();
        connectJavaViewsWithXMLViews();
    }

    private void initializeFirebaseFirestoreObject()
    {
        //Step 2 - initialize Firebase Firestore object
        objectFirebaseFirestore=FirebaseFirestore.getInstance();
    }
    public void addValues(View v)
    {
        if(!documentIdET.getText().toString().isEmpty()
        && !nameET.getText().toString().isEmpty()
                && !emailET.getText().toString().isEmpty()
        )
        {
            //Step 3 -- Creating Map to store values
            Map<String,Object> objectMap=new HashMap<>();

            objectMap.put("name",nameET.getText().toString());
            objectMap.put("email",emailET.getText().toString());

            //Step 4- add objectMap to Firebase Firestore
            objectFirebaseFirestore.collection(COLLECTION_NAME)
                    .document(documentIdET.getText().toString())
                    .set(objectMap)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast objectToast=Toast.makeText(MainActivity.this,
                                    "Values Added", Toast.LENGTH_SHORT);

                            objectToast.setGravity(Gravity.TOP,0,0);
                            objectToast.show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast objectToast=Toast.makeText(MainActivity.this,
                                    "Fails to add values:"+
                                    e.getMessage(), Toast.LENGTH_SHORT);

                            objectToast.setGravity(Gravity.TOP,0,0);
                            objectToast.show();
                        }
                    });
        }
        else if(documentIdET.getText().toString().isEmpty())
        {
            Toast objectToast=Toast.makeText(this,
                    "Please enter document id", Toast.LENGTH_SHORT);

            objectToast.setGravity(Gravity.TOP,0,0);
            objectToast.show();

        }
        else if(nameET.getText().toString().isEmpty())
        {
            Toast objectToast=Toast.makeText(this,
                    "Please enter name", Toast.LENGTH_SHORT);

            objectToast.setGravity(Gravity.TOP,0,0);
            objectToast.show();
        }
        else if(emailET.getText().toString().isEmpty())
        {
            Toast objectToast=Toast.makeText(this,
                    "Please enter email", Toast.LENGTH_SHORT);

            objectToast.setGravity(Gravity.TOP,0,0);
            objectToast.show();
        }
    }
    private void connectJavaViewsWithXMLViews()
    {
        documentIdET=findViewById(R.id.documentIdET);
        nameET=findViewById(R.id.nameET);

        emailET=findViewById(R.id.emailET);
    }
}
