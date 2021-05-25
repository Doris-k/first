package com.example.second;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class GridViewActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view5);

        GridView gridView= findViewById(R.id.mygridview);

        List<String> list1 = new ArrayList<String>();
        for (int i = 1; i<100; i++){
            list1.add("item-"+1);

        }
        //String[] list_data ={"one","two","three","four"};
        ListAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,list1);
        gridView.setAdapter(adapter);
        gridView.setEmptyView(findViewById(R.id.nodata));//如果没有数据，显示No Data

    }
}