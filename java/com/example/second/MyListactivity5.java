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
import java.util.List;

public class MyListactivity5 extends ListActivity {
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_my_listactivity5);父类是Listactivity 不需要布局，自带布局
        List<String> list1 = new ArrayList<String>();
        for (int i = 1; i<100; i++){
            //list1.add("item"+1);
            
        }
        String[] list_data ={"one","two","three","four"};
        ListAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,list_data);
        setListAdapter(adapter);

        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what ==9){
                    ArrayList<String> list =(ArrayList<String>)msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(MyListactivity5.this,
                            android.R.layout.simple_list_item_1,list);
                    setListAdapter(adapter);
                    Toast.makeText(MyListactivity5.this,"rate size"+list.size(),Toast.LENGTH_LONG).show();
                }
                super.handleMessage(msg);
            }
        };


        MyTask task = new MyTask();
        task.setHandler(handler);
        Thread thread =new Thread(task);
        thread.start();





    }
}