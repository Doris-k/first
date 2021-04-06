package com.example.second;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
      private EditText temp;
      private TextView out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        temp = findViewById(R.id.temp);
        out = findViewById(R.id.textview3);

        Button btn=findViewById(R.id.bnt);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String insper=temp.getText().toString();
                Double inps=Double.parseDouble(insper)*1.8+32;
                out.setText(""+insper);
            }

        });
    }


        }




