package com.example.second;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity3<dollarRate, euroRate, wonRate> extends AppCompatActivity implements Runnable {
    private static final String TAG = "MainActivity3";
    EditText input;
    TextView result;
    float dollarRate = 0.15f;
    float euroRate = 0.128f;
    float wonRate = 171.78f;
    Handler handler;//线程间数据同步

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        input = findViewById(R.id.rmb);
        result = findViewById(R.id.textView6);

        SharedPreferences sharedPreferences = getSharedPreferences("myrate",Activity.MODE_PRIVATE);
        //PreferenceManager.getDefaultSharedPreferences(this);也可以，保存默认值
        dollarRate= sharedPreferences.getFloat("dollar_rate",0.0f);
        euroRate= sharedPreferences.getFloat("euro_rate",0.0f);
        wonRate= sharedPreferences.getFloat("won_rate",0.0f);
        //获取SharedPreferences对象

        Thread t = new Thread(this);
        t.start();
        //开辟子线程
        handler = new Handler(){
            @Override
            public  void  handleMessage(@NonNull Message msg){
                if(msg.what==7){
                    String str = (String)msg.obj;//（）要和信息类型相同
                    Log.i(TAG,"handleMessage:get str ="+str);
                    result.setText(str);//把消息显示在settext上
                }
                super.handleMessage(msg);

                //接受子线程消息
            }
        };
    }

    public void click(View btn) {
        Log.d(TAG, "click");
        float r = 0.0f;
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
         open();

         Log.i(TAG,"openConfig:dollarRate="+dollarRate);
         Log.i(TAG,"openConfig:euroRate="+euroRate);
         Log.i(TAG,"openConfig:wonRate="+wonRate);

    }

    private void open() {
        Intent config = new Intent(this,MainActivity4.class);
        //加入传递的参数
        config.putExtra("dollar_rate_key",dollarRate);
        config.putExtra("euro_rate_key",euroRate);
        config.putExtra("won_rate_key",wonRate);
        //startActivity(config);
        startActivityForResult(config,5);
    }
    private String inputStream2String(InputStream inputStream)//将输入流转化成字符串
            throws IOException{
        final int bufferSize =1024;
        final char[]buffer =new char[bufferSize];
        final StringBuffer out = new StringBuffer();
        Reader in = new InputStreamReader(inputStream,"gb2312");//编码根据网页改
        while (true){
            int rsz = in.read(buffer,0,buffer.length);
            if(rsz < 0)
                break;
            out.append(buffer,0,rsz);
        }
        return  out.toString();
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
            SharedPreferences sp=getSharedPreferences("myrate", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor=sp.edit();
            editor.putFloat("dollar_rate",dollarRate);
            editor.putFloat("euro_rate",euroRate);
            editor.putFloat("won_rate",wonRate);
            editor.apply();
            //修改保存内容

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId()==R.id.menu_setting){
            open();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void run() {
        Log.i(TAG,"run:.........");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //线程中完成的任务,获取网络数据
        URL url=null;
        try {
            url= new URL("www.usd-cny/bankofchina.htm");
            HttpURLConnection http=(HttpURLConnection) url.openConnection();
            InputStream in = http.getInputStream();
            String html =inputStream2String(in);//将网页上的输入流用inputStream2String()转换成字符串
            Log.i(TAG,"run:html="+html);
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        //返回数据给主线程
        Message msg = handler.obtainMessage(7);//填写信息
        msg.obj="from message";//填写内容
        handler.sendMessage(msg);//发送消息

    }


}
