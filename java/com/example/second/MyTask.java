package com.example.second;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MyTask extends AppCompatActivity implements Runnable {
    private static final String TAG = "MyTask";
    private Handler handler;

    public  void  setHandler(Handler h){this.handler=h;}
    @Override
    public void  run() {
        Log.i(TAG,"run:..........");
        URL url =null;
        List<String> ret =new ArrayList<String>();
        try {
            Thread.sleep(5000);
            Document doc =Jsoup.connect("http://www.usd-cny.com/bankofchina.htm").get() ;
            Log.i(TAG,"run:time="+doc.title());//获取时间

            Element publicTime =doc.getElementsByClass("time").first();
            Log.i(TAG,"run:time="+publicTime.html());

            Element tables = doc.getElementsByTag("table").first();
            Log.i(TAG, "run:" + tables);
            Elements trs = tables.getElementsByTag("tr");
            Log.i(TAG, "sss" + trs);
            for (Element td : trs) {
                Elements tds = td.getElementsByTag("td");
                if (tds.size() > 0) {
                    String str = tds.first().text();
                    Log.i(TAG, "run:td=" + str);

                    String val = tds.get(5).text();
                    Log.i(TAG, "run:rate=" + val);
                    ret.add(str + "-->" + val);


                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Message msg = handler.obtainMessage(9, ret);
        handler.sendMessage(msg);


    }
}