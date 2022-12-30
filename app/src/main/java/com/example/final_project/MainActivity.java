package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button startGame;
    private Button rank;
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
        rank.setOnClickListener(listener);
        explain.setOnClickListener(listener);
        exit.setOnClickListener(listener);
    }

    private void initView() {

        startGame = findViewById(R.id.btn_start);
        rank = findViewById(R.id.btn_rank);
        explain =  findViewById(R.id.btn_explain);
        exit =  findViewById(R.id.btn_exit);
    }

    class BtnListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_start:
                    Intent intent1 = new Intent(TAG, GameActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.btn_rank:
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