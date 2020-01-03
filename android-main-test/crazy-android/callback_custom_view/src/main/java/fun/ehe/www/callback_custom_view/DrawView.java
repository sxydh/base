package fun.ehe.www.callback_custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends android.view.View
{
	public float currentX = 40;
	public float currentY = 50;
	// 定义、创建画笔
	android.graphics.Paint p = new android.graphics.Paint();
	public DrawView(android.content.Context context, android.util.AttributeSet set)
	{
		super(context, set);
	}
	@Override
	public void onDraw(android.graphics.Canvas canvas)
	{
		super.onDraw(canvas);
		// 设置画笔的颜色
		p.setColor(android.graphics.Color.RED);
		// 绘制一个小圆（作为小球）
		canvas.drawCircle(currentX, currentY, 15, p);
	}
	@Override
	public boolean onTouchEvent(android.view.MotionEvent event)
	{
		// 当前组件的currentX、currentY两个属性
		this.currentX = event.getX();
		this.currentY = event.getY();
		// 通知改组件重绘
		this.invalidate();
		// 返回true表明处理方法已经处理该事件
		return true;
	}
}
