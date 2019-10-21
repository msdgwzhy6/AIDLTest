package com.example.aidltest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.aidltest.myaidl.IUserManager;
import com.example.aidltest.myaidl.Stub;

public class ClientActivity extends AppCompatActivity {

    private static final String TAG = ClientActivity.class.getSimpleName();

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected, 与服务端连接成功！");
            //1、根据服务端返回的Binder，把它通过Stub.asInterface方法转换为本地代理对象IUserManager.Stub.Proxy
            IUserManager userManager = Stub.asInterface(service);
            try {
                //2、通过本地代理对象远程调用服务端的方法
                User user = userManager.getUser("rain");
                Log.d(TAG, "onServiceConnected，向服务端获取用户信息成功，User = ["
                        + "name = " + user.getName()
                        + "password = " + user.getPassword());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected, 与服务端断开连接");

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindService(
                new Intent(this, RemoteService.class),
                mServiceConnection,
                Context.BIND_AUTO_CREATE
        );
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
    }
}
