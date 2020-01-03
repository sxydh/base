package cn.net.bhe.androiddevelopers.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int TEMP = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TEMP:
                    textView.setText((String) msg.getData().get("content"));
                    break;
                default:
            }
        }
    };
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.content);

        BroadcastReceiver br = new ExplicitReceiver(handler);
        IntentFilter filter = new IntentFilter("cn.net.bhe.androiddevelopers.broadcastreceiver.MainActivity.ExplicitReceiver");
        registerReceiver(br, filter);
    }

    // start ExplicitReceiver
    public void explicit(View view) {
        String content = ((TextView) findViewById(R.id.editText)).getText().toString();
        Intent intent = new Intent();
        intent.setAction("cn.net.bhe.androiddevelopers.broadcastreceiver.MainActivity.ExplicitReceiver");
        intent.putExtra("content", content);
        sendBroadcast(intent);
    }

    // start ImplicitReceiver
    public void implicit(View view) {
        Intent intent = new Intent();
        intent.setAction("cn.net.bhe.androiddevelopers.broadcastreceiver.ImplicitReceiver");
        sendBroadcast(intent, null);
    }

    public static class ExplicitReceiver extends BroadcastReceiver {

        private Handler handler;

        public ExplicitReceiver(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println(new Object() {
            }.getClass().getEnclosingMethod().getName());

            Message message = new Message();
            message.what = TEMP;
            Bundle bundle = intent.getExtras();
            message.setData(bundle);
            handler.sendMessage(message);
        }
    }
}
