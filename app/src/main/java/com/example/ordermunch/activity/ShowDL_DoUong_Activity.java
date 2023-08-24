package com.example.ordermunch.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ordermunch.GlideHelper;
import com.example.ordermunch.R;

public class ShowDL_DoUong_Activity extends AppCompatActivity {

    public static String PATH_TITLE_2 = "PATH_TITLE";

    public static String PATH_IMAGE2 = "PATH_IMAGE3";
    public static String PATH_DES2 = "PATH_DES2";
    private TextView txt_showDL,txt_title;
    private ImageView ivBack;

    private ImageView ivDoUong;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_dl_do_uong);

        txt_title =  findViewById(R.id.txt_title_du);
        txt_showDL = findViewById(R.id.txt_DoUong);
        ivBack = findViewById(R.id.iv_back_DoUong);
        ivDoUong = findViewById(R.id.iv_DoUong);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initData();

        ivBack.setOnClickListener(v -> {
            finish();
        });

    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            String path = bundle.getString(PATH_IMAGE2);
            if (path!=null && !path.isEmpty()){
                GlideHelper.loadImage(path,ivDoUong);
            }
            String des = bundle.getString(PATH_DES2);
            if (des!=null && !des.isEmpty()){
                txt_showDL.setText(Html.fromHtml(des));
            }

            String title = bundle.getString(PATH_TITLE_2);
            Log.d("TAG", "initData: "+title);
            if (title!=null && !title.isEmpty()){
                txt_title.setText(title);
            }
        }
    }
}