package com.example.second;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MyListTask extends AppCompatActivity {
    private static final String TAG="MyListTask";
    Float rate=0f;
    TextView tw1;
    TextView tw2;
    EditText et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_list_task);
        String detail=getIntent().getStringExtra("title");
        String rate2=getIntent().getStringExtra("detail");
        rate=Float.parseFloat(rate2);
        tw1=(TextView)findViewById(R.id.textView4);
        tw2=(TextView)findViewById(R.id.textView5);
        et=findViewById(R.id.reditTextNumber);
        tw1.setText(detail);
       // tw2.setText(rate2);

    }
    private  void show(float s){
        tw2.setText(String.valueOf(s));
    }
    public  void  caculate(View v){
        Float rmb=Float.parseFloat(et.getText().toString());
        show(100/rate*rmb);

    }
}