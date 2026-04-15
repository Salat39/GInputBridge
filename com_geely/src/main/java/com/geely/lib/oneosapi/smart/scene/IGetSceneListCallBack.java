package com.geely.lib.oneosapi.smart.scene;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.smart.bean.SceneInfo;
import java.util.List;

/* loaded from: classes.dex */
public interface IGetSceneListCallBack extends IInterface {

    public static class Default implements IGetSceneListCallBack {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.smart.scene.IGetSceneListCallBack
        public void onResult(List<SceneInfo> list) throws RemoteException {
        }
    }

    void onResult(List<SceneInfo> list) throws RemoteException;

    public static abstract class Stub extends Binder implements IGetSceneListCallBack {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.smart.scene.IGetSceneListCallBack";
        static final int TRANSACTION_onResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGetSceneListCallBack asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IGetSceneListCallBack)) {
                return (IGetSceneListCallBack) queryLocalInterface;
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
            onResult(data.createTypedArrayList(SceneInfo.CREATOR));
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements IGetSceneListCallBack {
            public static IGetSceneListCallBack sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.smart.scene.IGetSceneListCallBack
            public void onResult(List<SceneInfo> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onResult(list);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGetSceneListCallBack impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IGetSceneListCallBack getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}