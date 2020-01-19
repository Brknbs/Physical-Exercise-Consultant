package com.brknbs.physicalexerciseconsultant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BackExercises extends AppCompatActivity {

    // Array of strings for ListView Title
    String[] listviewTitle = new String[]{
            "Back Extension", "Bridging", "Cat Stretch", "Knee Rolling",
            "Side Glide", "Single Knee Flexion", "Superman", "The Sphinx"
    };


    int[] listviewImage = new int[]{
            R.drawable.backextension, R.drawable.bridging,R.drawable.catstretch,R.drawable.kneerolling
            ,R.drawable.sideglide, R.drawable.singlekneeflexion,R.drawable.superman,R.drawable.thesphinx
    };

    String[] listviewShortDescription = new String[]{
            "Medium","Hard","Hard","Easy","Easy",
            "Easy","Hard","Hard"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_back_exercises);




        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 8; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[i]);
            hm.put("listview_discription", listviewShortDescription[i]);
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_discription"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_activity, from, to);
        ListView androidListView = (ListView) findViewById(R.id.list_view_back);
        androidListView.setAdapter(simpleAdapter);

        androidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BackExercises.this, exerciseInfo.class);
                Bundle bundle = new Bundle();
                switch (position) {
                    case 0 :
                        bundle.putString("id","backextension");
                        bundle.putString("name",listviewTitle[0]);
                        bundle.putString("desc",listviewShortDescription[0]);
                        break;
                    case 1 :
                        bundle.putString("id","bridging");
                        bundle.putString("name",listviewTitle[1]);
                        bundle.putString("desc",listviewShortDescription[1]);
                        break;
                    case 2 :
                        bundle.putString("id","catstretch");
                        bundle.putString("name",listviewTitle[2]);
                        bundle.putString("desc",listviewShortDescription[2]);
                        break;
                    case 3 :
                        bundle.putString("id","kneerolling");
                        bundle.putString("name",listviewTitle[3]);
                        bundle.putString("desc",listviewShortDescription[3]);
                        break;
                    case 4 :
                        bundle.putString("id","sideglide");
                        bundle.putString("name",listviewTitle[4]);
                        bundle.putString("desc",listviewShortDescription[4]);
                        break;
                    case 5 :
                        bundle.putString("id","singlekneeflexion");
                        bundle.putString("name",listviewTitle[5]);
                        bundle.putString("desc",listviewShortDescription[5]);
                        break;
                    case 6 :
                        bundle.putString("id","superman");
                        bundle.putString("name",listviewTitle[6]);
                        bundle.putString("desc",listviewShortDescription[6]);
                        break;
                    case 7 :
                        bundle.putString("id","thesphinx");
                        bundle.putString("name",listviewTitle[7]);
                        bundle.putString("desc",listviewShortDescription[7]);
                        break;
                }
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


}
