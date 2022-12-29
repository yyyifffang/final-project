package com.example.afinal;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

    private Button startGame;
    private Button continueGame;
    private Button explain;
    private Button exit;


    public final Context TAG = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView(); //初始化控件
        initListener(); //初始化监听器

    }

    private void initListener() {
        BtnListener listener = new BtnListener();
        startGame.setOnClickListener(listener);
        continueGame.setOnClickListener(listener);
        explain.setOnClickListener(listener);
        exit.setOnClickListener(listener);
    }

    private void initView() {

        startGame = (Button) findViewById(R.id.btn_start);
        continueGame = (Button) findViewById(R.id.btn_continue);
        explain = (Button) findViewById(R.id.btn_explain);
        exit = (Button) findViewById(R.id.btn_exit);
    }

    class BtnListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_start:
                    Intent intent1 = new Intent(TAG, GameActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.btn_continue:
                    Intent intent2 = new Intent(TAG, GameActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.btn_explain:
                    Intent intent3 = new Intent(TAG, ExplainActivity.class);
                    startActivity(intent3);
                    break;
                case R.id.btn_exit:
                    finish();
                    break;
            }        }
    }

}