package fun.ehe.www.shader;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends android.app.Activity
		implements android.view.View.OnClickListener
{
	// 声明位图渲染对象
	private android.graphics.Shader[] shaders = new android.graphics.Shader[5];
	// 声明颜色数组
	private int[] colors;
	MyView myView;
	// 自定义视图类
	@Override
	public void onCreate(android.os.Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		myView = (MyView)findViewById(R.id.my_view);
		// 获得Bitmap实例
		android.graphics.Bitmap bm = android.graphics.BitmapFactory.decodeResource(getResources()
				, R.mipmap.water);
		// 设置渐变的颜色组，也就是按红、绿、蓝的方式渐变
		colors = new int[] { android.graphics.Color.RED, android.graphics.Color.GREEN, android
                .graphics.Color.BLUE };
		// 实例化BitmapShader，x坐标方向重复图形，y坐标方向镜像图形
		shaders[0] = new android.graphics.BitmapShader(bm, android.graphics.Shader.TileMode.REPEAT,
				android.graphics.Shader.TileMode.MIRROR);
		// 实例化LinearGradient
		shaders[1] = new android.graphics.LinearGradient(0, 0, 100, 100
				, colors, null, android.graphics.Shader.TileMode.REPEAT);
		// 实例化RadialGradient
		shaders[2] = new android.graphics.RadialGradient(100, 100, 80, colors, null,
				android.graphics.Shader.TileMode.REPEAT);
		// 实例化SweepGradient
		shaders[3] = new android.graphics.SweepGradient(160, 160, colors, null);
		// 实例化ComposeShader
		shaders[4] = new android.graphics.ComposeShader(shaders[1], shaders[2],
				android.graphics.PorterDuff.Mode.DARKEN);
		android.widget.Button bn1 = (android.widget.Button)findViewById(R.id.bn1);
		android.widget.Button bn2 = (android.widget.Button)findViewById(R.id.bn2);
		android.widget.Button bn3 = (android.widget.Button)findViewById(R.id.bn3);
		android.widget.Button bn4 = (android.widget.Button)findViewById(R.id.bn4);
		android.widget.Button bn5 = (android.widget.Button)findViewById(R.id.bn5);
		bn1.setOnClickListener(this);
		bn2.setOnClickListener(this);
		bn3.setOnClickListener(this);
		bn4.setOnClickListener(this);
		bn5.setOnClickListener(this);
	}
	@Override
	public void onClick(android.view.View source)
	{
		switch(source.getId())
		{
			case R.id.bn1:
				myView.paint.setShader(shaders[0]);
				break;
			case R.id.bn2:
				myView.paint.setShader(shaders[1]);
				break;
			case R.id.bn3:
				myView.paint.setShader(shaders[2]);
				break;
			case R.id.bn4:
				myView.paint.setShader(shaders[3]);
				break;
			case R.id.bn5:
				myView.paint.setShader(shaders[4]);
				break;
		}
		// 重绘界面
		myView.invalidate();
	}
}
