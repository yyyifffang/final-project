package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //1.定義變量、對象、洞穴座標
    private int i=0; //紀錄打到的地鼠個數
    private ImageView mouse; //定義滑鼠
    private TextView info1; //用於查看洞穴座標
    private Handler handler;
    public int[][] position = new int[][]{
            {396,100},{780,100},{1134,100},
            {350,370},{780,370},{1190,370},
            {297,650},{780,650},{1240,650}};//地鼠位置的陣列

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager. LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //設置橫屏

        //2.綁定元件
        mouse = (ImageView)findViewById(R.id.imageView);
        info1=findViewById(R.id.info);
        //獲得洞穴位置
        info1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        float x =event.getRawX();
                        float y =event.getRawY();
                        Log.i("x:"+x,"y"+y);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });






        //3.地鼠隨機出現
        //創建handler消息處理機制
        //創建線程
        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg){
                int index;
                if(msg.what == 0x101) {
                    index = msg.arg1;
                    mouse.setX(position[index][0]);
                    mouse.setY(position[index][1]);
                    mouse.setVisibility(View.VISIBLE);
                }
                super.handleMessage(msg);
            }
        };
        //創建線程
        Thread t =new Thread(new Runnable() {
            @Override
            public void run() {
                int index=0; //紀錄地鼠位置
                while (!Thread.currentThread().isInterrupted()) { //當前進度不中斷的時候
                    index = new Random().nextInt(position.length); //生產一個隨機整數
                    Message m = handler.obtainMessage(); //創建消息對象
                    m.what = 0x101; //設置消息標誌
                    m.arg1 = index; //保存地鼠位置的索引值
                    handler.sendMessage(m); //發送消息通知handler處理
                    try{
                        Thread.sleep(new Random().nextInt(500)+500); //休息一段時間
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
        //4.打到地鼠後，地鼠顯示&不顯示
        //添加點擊滑鼠後的事件
        mouse.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setVisibility(View.INVISIBLE); //設定地鼠不顯示
                i++;
                Toast.makeText(MainActivity.this,"打到"+i+"隻地鼠了！",Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
}