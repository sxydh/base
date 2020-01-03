package fun.ehe.www.base_adapter;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends android.app.Activity
{
	android.widget.ListView myList;
	@Override
	public void onCreate(android.os.Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		myList = (android.widget.ListView) findViewById(R.id.myList);
		android.widget.BaseAdapter adapter = new android.widget.BaseAdapter()
		{
			@Override
			public int getCount()
			{
				// 指定一共包含40个选项
				return 40;
			}
			@Override
			public Object getItem(int position)
			{
				return null;
			}
			// 重写该方法，该方法的返回值将作为列表项的ID
			@Override
			public long getItemId(int position)
			{
				return position;
			}
			// 重写该方法，该方法返回的View将作为列表框
			@Override
			public android.view.View getView(int position
					, android.view.View convertView , android.view.ViewGroup parent)
			{
				// 创建一个LinearLayout，并向其中添加两个组件
				android.widget.LinearLayout line = new android.widget.LinearLayout(fun.ehe.www.base_adapter.MainActivity.this);
				line.setOrientation(LinearLayout.HORIZONTAL);
				android.widget.ImageView image = new android.widget.ImageView(fun.ehe.www.base_adapter.MainActivity.this);
				image.setImageResource(R.drawable.ic_launcher);
				android.widget.TextView text = new android.widget.TextView(fun.ehe.www.base_adapter.MainActivity.this);
				text.setText("第" + (position +1 ) + "个列表项");
				text.setTextSize(20);
				text.setTextColor(android.graphics.Color.RED);
				line.addView(image);
				line.addView(text);
				// 返回LinearLayout实例
				return line;
			}
		};
		myList.setAdapter(adapter);
	}
}
