package com.example.a11607.mobileshop1.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.a11607.mobileshop1.R;
import com.example.a11607.mobileshop1.common.BaseActivity;
import com.example.a11607.mobileshop1.common.MobileShopApp;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;



public class AdActivity extends BaseActivity {
    private TextView tv_skip;

    @Override
    public int getContentViewId() {
        return R.layout.activity_ad;
    }

    @Override
    protected void initView() {
        super.initView();
        tv_skip=(TextView)findViewById(R.id.tv_skip);
        tv_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip();
            }
        });
        ImageView imageView=(ImageView)findViewById(R.id.iv_image);
        String url="http://10.0.2.2:8080/MobileShop/img/goods/7.jpg";
        ImageLoader.getInstance().displayImage(url, imageView, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                MobileShopApp.handler.post(jumpTread);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                MobileShopApp.handler.post(jumpTread);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                MobileShopApp.handler.post(jumpTread);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                MobileShopApp.handler.post(jumpTread);
            }
        });

    }

    private void skip(){
        Intent intent=new Intent(AdActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
        c_count=COUNT;
        MobileShopApp.handler.removeCallbacks(jumpTread);



    }
    private static final String SKIP_TEXT="点击跳过 %d";
    private static final int COUNT=5;
    private static final int DELAYED=2000;
    private int c_count=COUNT;
    private Runnable  jumpTread=new Runnable() {
        @Override
        public void run() {
            if (c_count<=0){
                skip();
            }else {
                tv_skip.setText(String.format(SKIP_TEXT,c_count));
                c_count--;
                MobileShopApp.handler.postDelayed(jumpTread,DELAYED);

            }

        }
    };
}
