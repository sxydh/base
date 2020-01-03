package fun.ehe.www.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends android.view.View {
    public float currentX = 40;
    public float currentY = 50;
    // 定义、并创建画笔
    android.graphics.Paint p = new android.graphics.Paint();

    public DrawView(android.content.Context context) {
        super(context);
    }

    public DrawView(android.content.Context context, android.util.AttributeSet set) {
        super(context, set);
    }

    @Override
    public void onDraw(android.graphics.Canvas canvas) {
        super.onDraw(canvas);
        // 设置画笔的颜色
        p.setColor(android.graphics.Color.RED);
        // 绘制一个小圆（作为小球）
        canvas.drawCircle(currentX, currentY, 15, p);
    }

    // 为该组件的触碰事件重写事件处理方法
    @Override
    public boolean onTouchEvent(android.view.MotionEvent event) {
        // 修改currentX、currentY两个属性
        currentX = event.getX();
        currentY = event.getY();
        // 通知当前组件重绘自己
        invalidate();
        // 返回true表明该处理方法已经处理该事件
        return true;
    }
}
