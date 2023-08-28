package com.example.ordermunch.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ordermunch.R;

public class SplashScreen extends AppCompatActivity {

    ImageView splashing;
    TextView appname;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        appname =findViewById(R.id.appname);
        lottieAnimationView = findViewById(R.id.lottie);
        appname.animate().translationY(2000).setDuration(1000).setStartDelay(3000);
        lottieAnimationView.animate().setDuration(1000).setStartDelay(3000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        },2000);

    }
}