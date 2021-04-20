package com.example.second;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {
    private static final String TAG = "MainActivity3";
    EditText input;
    TextView result;
    float dollarRate = 0.15f;
    float euroRate = 0.128f;
    float wonRate = 171.78f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        input = findViewById(R.id.rmb);
        result = findViewById(R.id.textView6);
    }

    public void click(View btn) {
        Log.d(TAG, "click");
        float r = 0.0f;
        int op;
        switch (btn.getId()) {
            case R.id.dollar:
                r = dollarRate;
                break;
            case R.id.euro:
                r = euroRate;
                break;
            case R.id.won:
                r = wonRate;


        }
        //获得用户输入
        String str = input.getText().toString();
        Log.i(TAG, "click:str=" + str);
        if (str == null || str.length() == 0) {//提示
            Toast.makeText(this,"请输入金额",Toast.LENGTH_SHORT).show();
        } else {
            r=r*Float.parseFloat(str);
            result.setText(String.valueOf(r));
            }
        //计算

        }
     public void openConfig(View btn){
        Log.i(TAG,"openConfig:......");
         Intent config = new Intent(this,MainActivity4.class);
         //加入传递的参数
         config.putExtra("dollar_rate_key",dollarRate);
         config.putExtra("euro_rate_key",euroRate);
         config.putExtra("won_rate_key",wonRate);

         Log.i(TAG,"openConfig:dollarRate="+dollarRate);
         Log.i(TAG,"openConfig:euroRate="+euroRate);
         Log.i(TAG,"openConfig:wonRate="+wonRate);

         //startActivity(config);
         startActivityForResult(config,5);
    }
    protected  void onActivityResult(int requestCode,int resultCode,Intent data) {
        if (requestCode == 5 && resultCode == 2) {
            Bundle bundle = data.getExtras();
            dollarRate=bundle.getFloat("key_dollar",0.0f);
            euroRate=bundle.getFloat("key_euro",0.0f);
            wonRate=bundle.getFloat("key_won",0.0f);
            Log.i(TAG,"onActivityResult:dollarRate="+dollarRate);
            Log.i(TAG,"onActivityResult:euroRate="+euroRate);
            Log.i(TAG,"onActivityResult:wonRate="+wonRate);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
