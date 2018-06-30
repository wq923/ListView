package com.github.wq423.listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewControl();

    }

    private void initViewControl() {
        findViewById(R.id.id_main_btn_demo01).setOnClickListener(this);
        findViewById(R.id.id_main_btn_demo02).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_main_btn_demo01: {

                startActivity(new Intent(MainActivity.this, StringListViewActivity.class));
                break;
            }
            case R.id.id_main_btn_demo02: {
                break;
            }
        }
    }
}
