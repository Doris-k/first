package com.example.second;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MyRateListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rate_list);



        ListView listView = findViewById(R.id.mylist);

        List<String> list1 = new ArrayList<String>();
        for (int i = 1; i<100; i++){
            list1.add("item"+1);

        }
        //String[] list_data ={"one","two","three","four"};
        ListAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,list1);
        listView.setAdapter(adapter);
    }
}