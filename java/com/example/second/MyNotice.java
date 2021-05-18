package com.example.second;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MyNotice extends ListActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler() {
            @Override


            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 6) {
                    ArrayList<String> list = (ArrayList<String>) msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(MyNotice.this,
                            android.R.layout.simple_list_item_1, list);
                    setListAdapter(adapter);
                    Toast.makeText(MyNotice.this, "rate size" + list.size(), Toast.LENGTH_LONG).show();
                }
                super.handleMessage(msg);
            }
        };
        Notice notice = new Notice();
        notice.setHandler(handler);
        Thread thread =new Thread(notice);
        thread.start();


    }
}