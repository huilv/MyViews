package com.socks.androiddemo.dust;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.socks.androiddemo.R;

public class DustActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dust);
        DustView dustView= (DustView) findViewById(R.id.dust_view);
        ImageView imageView= (ImageView) findViewById(R.id.imageview);
        dustView.setImageView(imageView);
        dustView.startValueAnimation();

    }
}
