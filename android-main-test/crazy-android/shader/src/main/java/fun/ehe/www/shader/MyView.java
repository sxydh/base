/**
 *
 */
package fun.ehe.www.shader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends android.view.View
{
	// 声明画笔
	public android.graphics.Paint paint;
	public MyView(android.content.Context context , android.util.AttributeSet set)
	{
		super(context , set);
		paint = new android.graphics.Paint();
		paint.setColor(android.graphics.Color.RED);
	}
	@Override
	protected void onDraw(android.graphics.Canvas canvas)
	{
		super.onDraw(canvas);
		// 使用指定Paint对象画矩形
		canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
	}
}