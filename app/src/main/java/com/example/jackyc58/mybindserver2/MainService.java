package com.example.jackyc58.mybindserver2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by jackyc58 on 2017/3/6.
 */
public class MainService extends Service {

    private String TAG = "MainService";

    public MyBinder myBinder = new MyBinder();

    // 綁定此 Service 的物件
    public class MyBinder extends Binder {
        public MainService getService() {
            return MainService.this;
        }
    }

    // 綁定
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "MainService onBind");

        return myBinder;
    }

    // 解除綁定
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "MainService onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "MainService onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "MainService onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainService onDestroy");
    }

    // Service 測試用的 Method
    public void uu(){
        Log.d(TAG, "MainService uu");
    }
}
