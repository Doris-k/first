package com.example.second;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Score extends AppCompatActivity {
    TextView scorea;
    int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        scorea=findViewById(R.id.scorea);
    }

    private void show(){
        //将score显示到控件中


        scorea.setText(String.valueOf(score));
    }
    private void btn1(View v){
        score += 1;
        show();
    }
    private void btn2(View v){
        score+=2;
        show();
    }
    private void btn3(View v){
        score+=3;
        show();
    }
    private void reset(View v){
        score=0;
        show();
    }
}