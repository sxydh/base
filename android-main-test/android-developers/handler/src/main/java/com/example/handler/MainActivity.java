package com.example.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final int FILL_TEXT = 1;

    TextView textView;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FILL_TEXT:
                    textView.setText((String) msg.getData().get("body"));
                    break;
                default:
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);

        new Thread() {
            @Override
            public void run() {
                try {
                    String result = HttpUtils.get("https://www.baidu.com/", null).get("body").toString();

                    Message message = new Message();
                    message.what = FILL_TEXT;
                    Bundle bundle = new Bundle();
                    bundle.putString("body", result);
                    message.setData(bundle);
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
