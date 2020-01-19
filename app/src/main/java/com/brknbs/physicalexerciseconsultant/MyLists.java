package com.brknbs.physicalexerciseconsultant;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MyLists extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    FirebaseUser user;
    String userID;

    public HashMap<String,HashMap<String,String>> lists;

    Button clearTheList;
    ListView list_view_my_list;

    ArrayList<String> ids;
    ArrayList<String> exNames;
    ArrayList<String> sets;
    ArrayList<String> reps;

    ArrayList<String> myArrayListValues;

    ArrayAdapter<String> myArrayAdapterValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lists);

        list_view_my_list = findViewById(R.id.list_view_my_list);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("Values");

        user = mAuth.getCurrentUser();
        userID = user.getUid();

        clearTheList = findViewById(R.id.clearTheList);

        lists = new HashMap<>();

        ids = new ArrayList();
        exNames = new ArrayList();
        reps = new ArrayList();
        sets = new ArrayList();

        myArrayListValues = new ArrayList<>();

        myArrayAdapterValues = new ArrayAdapter<>(MyLists.this,android.R.layout.simple_list_item_1,myArrayListValues);

        list_view_my_list.setAdapter(myArrayAdapterValues);

        /*List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < ids.size(); i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[i]);
            //hm.put("listview_discription",sets.get(i) + " X " + reps.get(i));
            //hm.put("listview_image", ids.get(i));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_discription"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_activity, from, to);
        ListView androidListView = (ListView) findViewById(R.id.list_view_my_list);
        androidListView.setAdapter(simpleAdapter);*/

        myRef.child(userID).child("exerciseList").addListenerForSingleValueEvent(new ValueEventListener() {
            int i = 0;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String values;

                    myArrayListValues.clear();

                    /*
                    String k = ds.getKey();
                    String exName = (String) ds.child("exerciseName").getValue();
                    String rep = ds.child("reps").getValue().toString();
                    String set = (String) ds.child("sets").getValue().toString();

                    ids.add("R.drawable."+k);
                    exNames.add(exName);
                    reps.add(rep);
                    sets.add(set);

                    values = '\n' + exNames.get(i) + "     " + sets.get(i) + " X " + reps.get(i) + '\n';
                    myArrayListValues.add(values);
                    myArrayAdapterValues.notifyDataSetChanged();
                    i++;*/

                    Iterator iterator = dataSnapshot.getChildren().iterator();

                    while (iterator.hasNext()){
                        String key = ((DataSnapshot)iterator.next()).getKey();

                        String exName = dataSnapshot.child(key).child("exerciseName")
                                .getValue().toString();
                        String set = dataSnapshot.child(key).child("sets")
                                .getValue().toString();
                        String rep = dataSnapshot.child(key).child("reps")
                                .getValue().toString();

                        values = '\n' + exName + "     " + set + " X " + rep + '\n';
                        myArrayListValues.add(values);
                        myArrayAdapterValues.notifyDataSetChanged();
                    }
                }
                //Toast.makeText(MyLists.this, ids.get(0), Toast.LENGTH_SHORT).show();
            //}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //listviewTitle = exNames.toArray(new String[exNames.size()]);

        /*list_view_my_list.setOnLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                /*subject_list.remove(position);

                arrayadapter.notifyDataSetChanged();

                Toast.makeText(MyLists.this, "Item Deleted", Toast.LENGTH_LONG).show();
                return true;
            }
        });*/

        clearTheList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove();
            }
        });


    }

    public void remove(){
        myArrayAdapterValues.clear();
        myArrayAdapterValues.notifyDataSetChanged();
        myRef.child(userID).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(MyLists.this, "List Cleared", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
