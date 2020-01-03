package fun.ehe.www.custom_view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class CustomViewActivity extends android.app.Activity {
    @Override
    public void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // 获取布局文件中的LinearLayout容器
        android.widget.LinearLayout root = (android.widget.LinearLayout) findViewById(R.id.root);
        // 创建DrawView组件
        final DrawView draw = new DrawView(this);
        // 设置自定义组件的最小宽度、高度
        draw.setMinimumWidth(300);
        draw.setMinimumHeight(500);
        root.addView(draw);
    }
}

