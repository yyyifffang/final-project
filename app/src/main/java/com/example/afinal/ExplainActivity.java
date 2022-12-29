package com.example.afinal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExplainActivity extends Activity {
    private Button btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explain);

        btn_home=findViewById(R.id.btn_home);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}