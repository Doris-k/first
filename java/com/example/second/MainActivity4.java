package com.example.second;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {
    private  static final String TAG ="MainActivity4";
    EditText dollarEditor,euroEditor,wonEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate);
        //接收数据
        Intent conf = getIntent();
        float dollar= conf.getFloatExtra("dollar_rate_key",0.0f);
        float euro= conf.getFloatExtra("euro_rate_key",0.0f);
        float won= conf.getFloatExtra("won_rate_key",0.0f);

        Log.i(TAG,"onCreate:dollar="+dollar);
        Log.i(TAG,"onCreate:euro="+euro);
        Log.i(TAG,"onCreate:won="+won);
        //获取控件
        dollarEditor=findViewById(R.id.edit_dollar);
        euroEditor=findViewById(R.id.edit_euro);
        wonEditor=findViewById(R.id.edit_won);
        //将汇率值放入控件
        dollarEditor.setText(String.valueOf(dollar));
        euroEditor.setText(String.valueOf(euro));
        wonEditor.setText(String.valueOf(won));
    }
    public  void  save(View btn){
        //获得输入的数据
        float newdollar=Float.parseFloat(dollarEditor.getText().toString());
        float neweuro=Float.parseFloat(euroEditor.getText().toString());
        float newwon=Float.parseFloat(wonEditor.getText().toString());
        //将输入的新汇率带回计算页面
        Intent intent =getIntent();
        Bundle bdl = new Bundle();
        bdl.putFloat("key_dollar",newdollar);
        bdl.putFloat("key_euro",neweuro);
        bdl.putFloat("key_won",newwon);
        intent.putExtras(bdl);
        setResult(2,intent);//设置resultCode 及带回的数据
        //返回到调用页面
        finish();

    }
}