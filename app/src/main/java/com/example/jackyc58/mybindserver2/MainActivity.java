package com.example.jackyc58.mybindserver2;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{

    private String TAG = "MainService";

    private Button btn_1;
    private Button btn_2;
    private Button btn_3;

    public MainService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_1 = (Button)findViewById(R.id.button1);
        btn_2 = (Button)findViewById(R.id.button2);
        btn_3 = (Button)findViewById(R.id.button3);

        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch(view.getId())
        {
            case R.id.button1:

                // 綁定 Service
                Intent serviceIntent = new Intent(this, MainService.class);
                this.bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE);

                break;

            case R.id.button2:
                // 使用 Service 內的方法
                myService.uu();

                break;

            case R.id.button3:

                // 解除綁定 Service
                try{
                    this.unbindService(connection);
                    myService = null;
                } catch(Exception e){
                    Log.d(TAG, e.toString());
                }

                break;
        }

    }

    public ServiceConnection connection = new ServiceConnection() {

        // 成功與 Service 建立連線
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myService = ((MainService.MyBinder) service).getService();
            Log.d(TAG, "MainActivity onServiceConnected");
        }

        // 與 Service 建立連線失敗
        @Override
        public void onServiceDisconnected(ComponentName name) {
            //myService = null;
            Log.d(TAG, "MainActivity onServiceFailed");
        }
    };
}

