package cn.net.bhe.androiddevelopers.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ImplicitReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println(new Object() {
        }.getClass().getEnclosingMethod().getName());
    }
}
