package fun.ehe.www.data_type_attr;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends android.app.Activity {
	@Override
	public void onCreate(android.os.Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	public void scheme(android.view.View source)
	{
		android.content.Intent intent = new android.content.Intent();
		// 只设置Intent的Data属性
		intent.setData(android.net.Uri.parse("lee://www.crazyit.org:1234/test"));
		startActivity(intent);
	}
	public void schemeHostPort(android.view.View source)
	{
		android.content.Intent intent = new android.content.Intent();
		// 只设置Intent的Data属性
		intent.setData(android.net.Uri.parse("lee://www.fkjava.org:8888/test"));
		startActivity(intent);
	}
	public void schemeHostPath(android.view.View source)
	{
		android.content.Intent intent = new android.content.Intent();
		// 只设置Intent的Data属性
		intent.setData(android.net.Uri.parse("lee://www.fkjava.org:1234/mypath"));
		startActivity(intent);
	}
	public void schemeHostPortPath(android.view.View source)
	{
		android.content.Intent intent = new android.content.Intent();
		// 只设置Intent的Data属性
		intent.setData(android.net.Uri.parse("lee://www.fkjava.org:8888/mypath"));
		startActivity(intent);
	}
	public void schemeHostPortPathType(android.view.View source)
	{
		android.content.Intent intent = new android.content.Intent();
		// 同时设置Intent的Data、Type属性
		intent.setDataAndType(android.net.Uri.parse("lee://www.fkjava.org:8888/mypath")
			, "abc/xyz");
		startActivity(intent);
	}
}

