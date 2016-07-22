package com.socks.androiddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MyView myView= (MyView) findViewById(R.id.aa);
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myView.addHeart();
            }
        });

    }
}
