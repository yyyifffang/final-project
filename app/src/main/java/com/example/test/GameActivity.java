package com.example.test;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity {

    private ImageView[][] view=new ImageView[4][4];
    private Button btStartWhackAMole,btStopWhackAMole;
    private TextView tvWhackAMoleScore;

    private int time_s=1000; //难度的时间
    private int time=time_s; //地鼠出来时间
    private int score=0; //成绩，打地鼠个数
    private int num=0; //地鼠出来个数
    private int temp_i=0,temp_j=0; //记录上一次出现的地鼠在数组view中的下标
    private int flag=1; //默认为停止状态，0开始，1结束，2运行
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        init();
    }

    private void init() {
        view[0][0]= (ImageView) findViewById(R.id.ivZeroZero);
        view[0][1]= (ImageView) findViewById(R.id.ivZeroOne);
        view[0][2]= (ImageView) findViewById(R.id.ivZeroTwo);
        view[0][3]= (ImageView) findViewById(R.id.ivZeroThree);
        view[1][0]= (ImageView) findViewById(R.id.ivOneZero);
        view[1][1]= (ImageView) findViewById(R.id.ivOneOne);
        view[1][2]= (ImageView) findViewById(R.id.ivOneTwo);
        view[1][3]= (ImageView) findViewById(R.id.ivOneThree);
        view[2][0]= (ImageView) findViewById(R.id.ivTwoZero);
        view[2][1]= (ImageView) findViewById(R.id.ivTwoOne);
        view[2][2]= (ImageView) findViewById(R.id.ivTwoTwo);
        view[2][3]= (ImageView) findViewById(R.id.ivTwoThree);
        view[3][0]= (ImageView) findViewById(R.id.ivThreeZero);
        view[3][1]= (ImageView) findViewById(R.id.ivThreeOne);
        view[3][2]= (ImageView) findViewById(R.id.ivThreeTwo);
        view[3][3]= (ImageView) findViewById(R.id.ivThreeThree);
        btStartWhackAMole= (Button) findViewById(R.id.btStartWhackAMole);
        btStopWhackAMole= (Button) findViewById(R.id.btStopWhackAMole);
        tvWhackAMoleScore= (TextView) findViewById(R.id.tvWhackAMoleScore);
        btStartWhackAMole.setClickable(true);
        btStopWhackAMole.setClickable(false);
    }

    public void whackAMole(View view){
        view.setBackgroundResource(R.drawable.gift);
        view.setClickable(false);
        score++;
        time=time_s-score*20;
        tvWhackAMoleScore.setText("分數："+score);
    }
    public void btnClick(View view){
        switch (view.getId()){
            case R.id.btStartWhackAMole:
                btStartWhackAMole.setClickable(false);
                btStopWhackAMole.setClickable(true);
                num=0;
                score=0;
                flag=0; //开始
                tvWhackAMoleScore.setText("分數：0");
                new MyAsyncTask().execute();
                break;
            case R.id.btStopWhackAMole:
                btStartWhackAMole.setClickable(true);
                btStopWhackAMole.setClickable(false);
                score=0;
                flag=1; //停止
                new MyAsyncTask().execute();
                break;
        }
    }
    class MyAsyncTask extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... strings) {
            //进入运行状态
            while(flag!=1){
                flag=2;
                double r=Math.random();
                int i=((int)(r*10))%4;
                r=Math.random();
                int j=((int)(r*10))%4;
                try {
                    Thread.sleep(time);
                }catch (InterruptedException e){
                    e.printStackTrace();;
                }
                publishProgress(i,j);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) { //主线程
            if(flag==0){ //开始
                view[values[0]][values[1]].setBackgroundResource(R.drawable.emptyhole);
            }else if(flag==2){ //运行
                view[temp_i][temp_j].setBackgroundResource(R.drawable.emptyhole);
                view[temp_i][temp_j].setClickable(false); //上一次出现的设置为不能点击
                view[values[0]][values[1]].setBackgroundResource(R.drawable.deer);
                view[values[0]][values[1]].setClickable(true);
                num++;
                if((num-score)==5){
                    flag=1;
                    btStartWhackAMole.setClickable(true);
                    Toast.makeText(GameActivity.this,"遊戲結束",Toast.LENGTH_SHORT).show();
                }
                temp_i=values[0];
                temp_j=values[1];
            }else if(flag==1){
                view[values[0]][values[1]].setBackgroundResource(R.drawable.emptyhole);
                view[values[0]][values[1]].setClickable(false);
            }
        }
    }
}