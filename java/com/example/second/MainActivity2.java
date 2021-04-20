package com.example.second;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    private TextView texta;
    int scorea = 0;
    int scoreb=0;
    private TextView textb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        texta = findViewById(R.id.textView3);
        textb = findViewById(R.id.textView4);



    }
    private void showa() {
        //将score显示到控件中
        texta.setText(String.valueOf(scorea));
    }

    public void myClicka3(View v) {
        scorea += 3;
        showa();
    }

    public void myClicka2(View v) {
        scorea += 2;
        showa();
    }

    public void myClicka1(View v) {
        scorea += 1;
        showa();
    }

    private void showb() {
        //将score显示到控件中
        textb.setText(String.valueOf(scoreb));
    }
    public void myClickb3(View v){
        scoreb+=3;
        showb();
    }
    public void myClickb2(View v){
        scoreb+=2;
        showb();
    }
    public void myClickb1(View v){
        scoreb+=1;
        showb();
    }
    public void reset(View v){
        scorea=0;
        scoreb=0;
        showa();
        showb();
    }
}
