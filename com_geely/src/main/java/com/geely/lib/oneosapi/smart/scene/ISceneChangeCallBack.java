package com.geely.lib.oneosapi.smart.scene;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.smart.bean.SceneInfo;
import java.util.List;

/* loaded from: classes.dex */
public interface ISceneChangeCallBack extends IInterface {

    public static class Default implements ISceneChangeCallBack {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.smart.scene.ISceneChangeCallBack
        public void onSceneChange(List<SceneInfo> list) throws RemoteException {
        }
    }

    void onSceneChange(List<SceneInfo> list) throws RemoteException;

    public static abstract class Stub extends Binder implements ISceneChangeCallBack {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.smart.scene.ISceneChangeCallBack";
        static final int TRANSACTION_onSceneChange = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISceneChangeCallBack asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISceneChangeCallBack)) {
                return (ISceneChangeCallBack) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code != 1) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            onSceneChange(data.createTypedArrayList(SceneInfo.CREATOR));
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements ISceneChangeCallBack {
            public static ISceneChangeCallBack sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.geely.lib.oneosapi.smart.scene.ISceneChangeCallBack
            public void onSceneChange(List<SceneInfo> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSceneChange(list);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISceneChangeCallBack impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ISceneChangeCallBack getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}