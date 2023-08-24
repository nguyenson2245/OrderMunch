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

public class ShowDL_MonAn_Activity extends AppCompatActivity {

    public static String PATH_IMAGE = "PATH_IMAGE2";
    public static String PATH_DES = "PATH_DES";
    public static String PATH_TITLE = "PATH_TITLE";
    private TextView txt_showDL,txt_title;
    private ImageView ivBack;

    private ImageView ivMonAn;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.ordermunch.R.layout.activity_show_dl_mon_an);

        txt_title =  findViewById(R.id.txt_title);
        txt_showDL = findViewById(R.id.txt_monAn);
        ivBack = findViewById(R.id.iv_back_monan);
        ivMonAn = findViewById(R.id.iv_mon_an);

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
             GlideHelper.loadImage(path,ivMonAn);
            }
            String des = bundle.getString(PATH_DES);
            if (des!=null && !des.isEmpty()){
               txt_showDL.setText(Html.fromHtml(des));
            }

            String title = bundle.getString(PATH_TITLE);
            Log.d("TAG", "initData: "+title);
            if (title!=null && !title.isEmpty()){
                txt_title.setText(title);
            }
        }
    }
}