package fun.ehe.www.img_brownse;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MainActivity extends android.app.Activity {
    // 定义一个访问图片的数组
    int[] images = new int[]{
            R.drawable.java,
            R.drawable.javaee,
            R.drawable.swift,
            R.drawable.ajax,
            R.drawable.html,};
    int currentImg = 0;

    @Override
    public void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取LinearLayout布局容器
        android.widget.LinearLayout main = (android.widget.LinearLayout) findViewById(R.id.root);
        // 程序创建ImageView组件
        final android.widget.ImageView image = new android.widget.ImageView(this);
        // 将ImageView组件添加到LinearLayout布局容器中
        main.addView(image);
        // 初始化时显示第一张图片
        image.setImageResource(images[0]);
        image.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                // 改变ImageView里显示的图片
                image.setImageResource(images[++currentImg % images.length]);
            }
        });
    }
}

