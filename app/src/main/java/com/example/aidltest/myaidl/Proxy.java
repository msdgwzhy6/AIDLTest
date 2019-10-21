package com.example.aidltest.myaidl;

import android.os.IBinder;

/**
 * Binder本地代理类
 * Created by 陈健宇 at 2019/10/21
 */
public class Proxy implements IUserManager {

    private IBinder mRemote;

    Proxy(android.os.IBinder remote) {
        mRemote = remote;
    }

    @Override
    public android.os.IBinder asBinder() {
        return mRemote;
    }

    @Override
    public com.example.aidltest.User getUser(java.lang.String name) throws android.os.RemoteException {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        com.example.aidltest.User _result;
        try {
            _data.writeInterfaceToken(Stub.DESCRIPTOR);//这里引用我们自己写的Stub的DESCRIPTOR
            _data.writeString(name);
            mRemote.transact(Stub.TRANSACTION_getUser, _data, _reply, 0);//这里引用我们自己写的Stub的TRANSACTION_getUser
            _reply.readException();
            if ((0 != _reply.readInt())) {
                _result = com.example.aidltest.User.CREATOR.createFromParcel(_reply);
            } else {
                _result = null;
            }
        } finally {
            _reply.recycle();
            _data.recycle();
        }
        return _result;
    }

}
