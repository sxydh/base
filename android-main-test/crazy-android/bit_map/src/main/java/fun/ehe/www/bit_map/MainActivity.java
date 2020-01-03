package fun.ehe.www.bit_map;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends android.app.Activity
{
	String[] images = null;
	android.content.res.AssetManager assets = null;
	int currentImg = 0;
	android.widget.ImageView image;
	@Override
	public void onCreate(android.os.Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		image = (android.widget.ImageView) findViewById(R.id.image);
		try
		{
			assets = getAssets();
			// 获取/assets/目录下所有文件
			images = assets.list("");
		}
		catch (java.io.IOException e)
		{
			e.printStackTrace();
		}
		// 获取next按钮
		final android.widget.Button next = (android.widget.Button) findViewById(R.id.next);
		// 为next按钮绑定事件监听器，该监听器将会查看下一张图片
		next.setOnClickListener(new android.view.View.OnClickListener()
		{
			@Override
			public void onClick(android.view.View sources)
			{
				// 如果发生数组越界
				if (currentImg >= images.length)
				{
					currentImg = 0;
				}
				// 找到下一个图片文件
				while (!images[currentImg].endsWith(".png")
						&& !images[currentImg].endsWith(".jpg")
						&& !images[currentImg].endsWith(".gif"))
				{
					currentImg++;
					// 如果已发生数组越界
					if (currentImg >= images.length)
					{
						currentImg = 0;
					}
				}
				java.io.InputStream assetFile = null;
				try
				{
					// 打开指定资源对应的输入流
					assetFile = assets.open(images[currentImg++]);
				}
				catch (java.io.IOException e)
				{
					e.printStackTrace();
				}
				android.graphics.drawable.BitmapDrawable bitmapDrawable = (android.graphics.drawable.BitmapDrawable) image
					.getDrawable();
				// 如果图片还未回收，先强制回收该图片
				if (bitmapDrawable != null
						&& !bitmapDrawable.getBitmap().isRecycled()) // ①
				{
					bitmapDrawable.getBitmap().recycle();
				}
				// 改变ImageView显示的图片
				image.setImageBitmap(android.graphics.BitmapFactory
					.decodeStream(assetFile)); // ②
			}
		});
	}
}

