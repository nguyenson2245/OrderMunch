package com.example.ordermunch.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ordermunch.GlideHelper;
import com.example.ordermunch.R;

public class ShowFullImageActivity extends AppCompatActivity {

    public static String PATH_IMAGE = "PATH_IMAGE";
    private ImageView imageView;
    private ImageView ivBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_full_image);

        imageView = findViewById(R.id.image);
        ivBack = findViewById(R.id.iv_back);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initData();
        ivBack.setOnClickListener(v -> {
             finish();
        });
    }
    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            String path = bundle.getString(PATH_IMAGE);
            if (path!=null && !path.isEmpty()){
                GlideHelper.loadImage(path,imageView);
            }
        }
    }
}