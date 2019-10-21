package com.example.aidltest.myaidl;

import android.os.Binder;
import android.os.IBinder;


/**
 * Binder本地对象
 * Created by 陈健宇 at 2019/10/21
 */
public abstract class Stub extends Binder implements IUserManager {

    public static final String DESCRIPTOR = "com.example.aidltest.Stub";

    static final int TRANSACTION_getUser = (IBinder.FIRST_CALL_TRANSACTION + 0);

    public Stub() {
        this.attachInterface(this, DESCRIPTOR);
    }

    public static IUserManager asInterface(android.os.IBinder obj) {
        if ((obj == null)) {
            return null;
        }
        android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if (((iin != null) && (iin instanceof IUserManager))) {
            return (IUserManager) iin;
        }
        return new Proxy(obj);
    }

    @Override
    public android.os.IBinder asBinder() {
        return this;
    }

    @Override
    public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
        java.lang.String descriptor = DESCRIPTOR;
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(descriptor);
                return true;
            }
            case TRANSACTION_getUser: {
                data.enforceInterface(descriptor);
                java.lang.String _arg0;
                _arg0 = data.readString();
                com.example.aidltest.User _result = this.getUser(_arg0);
                reply.writeNoException();
                if ((_result != null)) {
                    reply.writeInt(1);
                    _result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
                } else {
                    reply.writeInt(0);
                }
                return true;
            }
            default: {
                return super.onTransact(code, data, reply, flags);
            }
        }
    }
}
