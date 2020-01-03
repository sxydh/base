package com.example.intent;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    public static final String ACTION = "cn.net.bhe.androiddevelopers.intent.DisplayMessageActivity";
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void explicit(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void implicit(View view) {
        Intent intent = new Intent();
        intent.setAction(ACTION);
        intent.addCategory(Intent.CATEGORY_DEFAULT); // default value

        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void anotherApp(View view) {
        Intent intent;
        intent = getPackageManager().getLaunchIntentForPackage("cn.net.bhe.androiddevelopers.notification"); // launch main activity
        /* or you can start the target activity directly
        intent = new Intent();
        intent.setComponent(new ComponentName("com.example.apk", "com.example.apk.ActivityLogin")); // you must add 'android:exported="true"' to target component element, you can find the element in the manifest.xml
        */
        if (intent != null) {
            startActivity(intent);
        }
    }
}
