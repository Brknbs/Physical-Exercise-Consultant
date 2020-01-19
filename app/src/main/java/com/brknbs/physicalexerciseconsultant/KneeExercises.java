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

public class KneeExercises extends AppCompatActivity {

    // Array of strings for ListView Title
    String[] listviewTitle = new String[]{
            "Inner Range Quads", "Knee Bending", "Knee Extension", "Parallel Wall Holds",
            "Quarter Wall Holds", "Single Knee Bending", "Single Leg Raise", "Static Quads"
    };


    int[] listviewImage = new int[]{
            R.drawable.innerrangequads, R.drawable.kneebending,R.drawable.kneeextension,
            R.drawable.parallelwallholds,R.drawable.quarterwallholds,R.drawable.singlekneebending,
            R.drawable.singlelegraise,R.drawable.staticquads
    };

    String[] listviewShortDescription = new String[]{
            "Medium","Easy","Easy","Hard","Medium",
            "Hard","Medium","Easy"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_knee_exercises);




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
        ListView androidListView = (ListView) findViewById(R.id.list_view_knee);
        androidListView.setAdapter(simpleAdapter);

        androidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(KneeExercises.this, exerciseInfo.class);
                Bundle bundle = new Bundle();
                switch (position) {
                    case 0 :
                        bundle.putString("id","innerrangequads");
                        bundle.putString("name",listviewTitle[0]);
                        bundle.putString("desc",listviewShortDescription[0]);
                        break;
                    case 1 :
                        bundle.putString("id","kneebending");
                        bundle.putString("name",listviewTitle[1]);
                        bundle.putString("desc",listviewShortDescription[1]);
                        break;
                    case 2 :
                        bundle.putString("id","kneeextension");
                        bundle.putString("name",listviewTitle[2]);
                        bundle.putString("desc",listviewShortDescription[2]);
                        break;
                    case 3 :
                        bundle.putString("id","parallelwallholds");
                        bundle.putString("name",listviewTitle[3]);
                        bundle.putString("desc",listviewShortDescription[3]);
                        break;
                    case 4 :
                        bundle.putString("id","quarterwallholds");
                        bundle.putString("name",listviewTitle[4]);
                        bundle.putString("desc",listviewShortDescription[4]);
                        break;
                    case 5 :
                        bundle.putString("id","singlekneebending");
                        bundle.putString("name",listviewTitle[5]);
                        bundle.putString("desc",listviewShortDescription[5]);
                        break;
                    case 6 :
                        bundle.putString("id","singlelegraise");
                        bundle.putString("name",listviewTitle[6]);
                        bundle.putString("desc",listviewShortDescription[6]);
                        break;
                    case 7 :
                        bundle.putString("id","staticquads");
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
