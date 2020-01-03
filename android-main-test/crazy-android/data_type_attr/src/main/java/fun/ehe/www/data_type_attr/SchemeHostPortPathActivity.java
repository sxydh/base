package fun.ehe.www.data_type_attr;

import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;

public class SchemeHostPortPathActivity extends android.app.Activity
{
	@Override
	public void onCreate(android.os.Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		android.widget.TextView tv = new android.widget.TextView(this);
		tv.setText("指定Scheme、Host、Port、Path匹配的Activity");
		tv.setTextSize(30);
		setContentView(tv);
	}
}
