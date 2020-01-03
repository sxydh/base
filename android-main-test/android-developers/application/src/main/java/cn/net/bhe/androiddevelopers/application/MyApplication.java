package cn.net.bhe.androiddevelopers.application;

import android.app.Application;
import android.content.res.Configuration;
import android.widget.Toast;

import java.util.Date;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        System.out.println("\r\n" + new Date().toString() + "\r\n" + new Object() {
        }.getClass().getEnclosingMethod().getName());

        Toast.makeText(this, "" + this, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        System.out.println("\r\n" + new Date().toString() + "\r\n" + new Object() {
        }.getClass().getEnclosingMethod().getName());
    }

    //回收内存
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
