package fun.ehe.www.action_view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends android.app.Activity
{
	@Override
	public void onCreate(android.os.Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		android.widget.Button bn = (android.widget.Button) findViewById(R.id.bn);
		// 为bn按钮添加一个监听器
		bn.setOnClickListener(new android.view.View.OnClickListener()
		{
			@Override
			public void onClick(android.view.View v)
			{
				// 创建Intent
				android.content.Intent intent = new android.content.Intent();
				String data = "http://www.crazyit.org";
				// 根据指定字符串解析出Uri对象
				android.net.Uri uri = android.net.Uri.parse(data);
				// 为Intent设置Action属性
				intent.setAction(android.content.Intent.ACTION_VIEW);
				// 设置Data属性
				intent.setData(uri);
				startActivity(intent);
			}
		});
		android.widget.Button edit = (android.widget.Button) findViewById(R.id.edit);
		// 为edit按钮添加一个监听器
		edit.setOnClickListener(new android.view.View.OnClickListener()
		{
			@Override
			public void onClick(android.view.View v)
			{
				// 创建Intent
				android.content.Intent intent = new android.content.Intent();
				// 为Intent设置Action属性（动作为：编辑）
				intent.setAction(android.content.Intent.ACTION_EDIT);
				String data = "content://com.android.contacts/contacts/1";
				// 根据指定字符串解析出Uri对象
				android.net.Uri uri = android.net.Uri.parse(data);
				// 设置Data属性
				intent.setData(uri);
				startActivity(intent);
			}
		});
		android.widget.Button call = (android.widget.Button) findViewById(R.id.call);
		// 为call按钮添加一个监听器
		call.setOnClickListener(new android.view.View.OnClickListener()
		{
			@Override
			public void onClick(android.view.View v)
			{
				// 创建Intent
				android.content.Intent intent = new android.content.Intent();
				// 为Intent设置Action属性（动作为：拨号）
				intent.setAction(android.content.Intent.ACTION_DIAL);
				String data = "tel:13800138000";
				// 根据指定字符串解析出Uri对象
				android.net.Uri uri = android.net.Uri.parse(data);
				// 设置Data属性
				intent.setData(uri);
				startActivity(intent);
			}
		});
	}
}

