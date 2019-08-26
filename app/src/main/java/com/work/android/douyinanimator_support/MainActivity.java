package com.work.android.douyinanimator_support;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.work.android.loadview.DouYinLoadTwoBallView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DouYinLoadTwoBallView load_ball = findViewById(R.id.load_ball);
        load_ball.startAnimator();
    }
}
