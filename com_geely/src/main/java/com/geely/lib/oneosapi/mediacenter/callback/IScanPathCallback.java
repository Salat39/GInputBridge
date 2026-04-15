package com.geely.lib.oneosapi.mediacenter.callback;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.mediacenter.bean.MusicFileData;
import java.util.List;

/* loaded from: classes.dex */
public interface IScanPathCallback extends IInterface {

    public static class Default implements IScanPathCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.callback.IScanPathCallback
        public void onScanPathFinish(int source, List<MusicFileData> musicFileDataList) throws RemoteException {
        }
    }

    void onScanPathFinish(int source, List<MusicFileData> musicFileDataList) throws RemoteException;

    public static abstract class Stub extends Binder implements IScanPathCallback {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.mediacenter.callback.IScanPathCallback";
        static final int TRANSACTION_onScanPathFinish = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IScanPathCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IScanPathCallback)) {
                return (IScanPathCallback) queryLocalInterface;
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
            onScanPathFinish(data.readInt(), data.createTypedArrayList(MusicFileData.CREATOR));
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements IScanPathCallback {
            public static IScanPathCallback sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.mediacenter.callback.IScanPathCallback
            public void onScanPathFinish(int source, List<MusicFileData> musicFileDataList) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(source);
                    obtain.writeTypedList(musicFileDataList);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onScanPathFinish(source, musicFileDataList);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IScanPathCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IScanPathCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}