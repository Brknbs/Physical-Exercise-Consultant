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

public class HipExercises extends AppCompatActivity {

    // Array of strings for ListView Title
    String[] listviewTitle = new String[]{
            "Front Hip Stretching", "Hamstring Stretching", "Hip Bending", "Inner Hip Strengthening",
            "Inner Thigh Stretching", "Outer Hip Strengthening", "Strengthening Wall Squat"
    };


    int[] listviewImage = new int[]{
            R.drawable.fronthipstretching, R.drawable.hamstringstretching,R.drawable.hipbending,
            R.drawable.innerhipstrengthening,R.drawable.innerthighstretching,
            R.drawable.outerhipstrengthening, R.drawable.strengtheningwallsquat
    };

    String[] listviewShortDescription = new String[]{
            "Easy","Medium","Easy","Hard","Medium",
            "Medium","Hard"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hip_exercises);




        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 7; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[i]);
            hm.put("listview_discription", listviewShortDescription[i]);
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_discription"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_activity, from, to);
        ListView androidListView = (ListView) findViewById(R.id.list_view_hip);
        androidListView.setAdapter(simpleAdapter);

        androidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HipExercises.this, exerciseInfo.class);
                Bundle bundle = new Bundle();
                switch (position) {
                    case 0 :
                        bundle.putString("id","fronthipstrecthing");
                        bundle.putString("name",listviewTitle[0]);
                        bundle.putString("desc",listviewShortDescription[0]);
                        break;
                    case 1 :
                        bundle.putString("id","hamstringstretching");
                        bundle.putString("name",listviewTitle[1]);
                        bundle.putString("desc",listviewShortDescription[1]);
                        break;
                    case 2 :
                        bundle.putString("id","hipbending");
                        bundle.putString("name",listviewTitle[2]);
                        bundle.putString("desc",listviewShortDescription[2]);
                        break;
                    case 3 :
                        bundle.putString("id","innerhipstrengthening");
                        bundle.putString("name",listviewTitle[3]);
                        bundle.putString("desc",listviewShortDescription[3]);
                        break;
                    case 4 :
                        bundle.putString("id","innerthighstretching");
                        bundle.putString("name",listviewTitle[4]);
                        bundle.putString("desc",listviewShortDescription[4]);
                        break;
                    case 5 :
                        bundle.putString("id","outerhipstrengthening");
                        bundle.putString("name",listviewTitle[5]);
                        bundle.putString("desc",listviewShortDescription[5]);
                        break;
                    case 6 :
                        bundle.putString("id","strengtheningwallsquat");
                        bundle.putString("name",listviewTitle[6]);
                        bundle.putString("desc",listviewShortDescription[6]);
                        break;

                }
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
