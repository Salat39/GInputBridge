package com.geely.lib.oneosapi.launcher;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.launcher.bean.ShortcutCompat;
import java.util.List;

/* loaded from: classes.dex */
public interface IShotCutsService extends IInterface {

    public static class Default implements IShotCutsService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.launcher.IShotCutsService
        public List<ShortcutCompat> getShortCuts(String packageName) throws RemoteException {
            return null;
        }
    }

    List<ShortcutCompat> getShortCuts(String packageName) throws RemoteException;

    public static abstract class Stub extends Binder implements IShotCutsService {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.launcher.IShotCutsService";
        static final int TRANSACTION_getShortCuts = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IShotCutsService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IShotCutsService)) {
                return (IShotCutsService) queryLocalInterface;
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
            List<ShortcutCompat> shortCuts = getShortCuts(data.readString());
            reply.writeNoException();
            reply.writeTypedList(shortCuts);
            return true;
        }

        private static class Proxy implements IShotCutsService {
            public static IShotCutsService sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.launcher.IShotCutsService
            public List<ShortcutCompat> getShortCuts(String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getShortCuts(packageName);
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(ShortcutCompat.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IShotCutsService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IShotCutsService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}