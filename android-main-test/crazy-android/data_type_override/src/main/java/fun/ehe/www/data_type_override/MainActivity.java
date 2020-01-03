package fun.ehe.www.data_type_override;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends android.app.Activity
{
	@Override
	public void onCreate(android.os.Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	public void overrideType(android.view.View source)
	{
		android.content.Intent intent = new android.content.Intent();
		// 先为Intent设置Type属性
		intent.setType("abc/xyz");
		// 再为Intent设置Data属性，覆盖Type属性
		intent.setData(android.net.Uri.parse("lee://www.fkjava.org:8888/test"));
		android.widget.Toast.makeText(this, intent.toString(), android.widget.Toast.LENGTH_LONG).show();
	}
	public void overrideData(android.view.View source)
	{
		android.content.Intent intent = new android.content.Intent();
		// 先为Intent设置Data属性
		intent.setData(android.net.Uri.parse("lee://www.fkjava.org:8888/mypath"));
		// 再为Intent设置Type属性，覆盖Data属性
		intent.setType("abc/xyz");
		android.widget.Toast.makeText(this, intent.toString(), android.widget.Toast.LENGTH_LONG).show();
	}
	public void dataAndType(android.view.View source)
	{
		android.content.Intent intent = new android.content.Intent();
		// 同时设置Intent的Data、Type属性
		intent.setDataAndType(android.net.Uri.parse("lee://www.fkjava.org:8888/mypath"),
				"abc/xyz");
		android.widget.Toast.makeText(this, intent.toString(), android.widget.Toast.LENGTH_LONG).show();
	}
}


