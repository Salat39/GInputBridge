package com.geely.lib.oneosapi.mediacenter.callback;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.mediacenter.bean.SearchResult;
import java.util.List;

/* loaded from: classes.dex */
public interface ISearchSongCallback extends IInterface {

    public static class Default implements ISearchSongCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.callback.ISearchSongCallback
        public void onSearchSongResult(int source, int app, List<SearchResult> searchResults) throws RemoteException {
        }
    }

    void onSearchSongResult(int source, int app, List<SearchResult> searchResults) throws RemoteException;

    public static abstract class Stub extends Binder implements ISearchSongCallback {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.mediacenter.callback.ISearchSongCallback";
        static final int TRANSACTION_onSearchSongResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISearchSongCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISearchSongCallback)) {
                return (ISearchSongCallback) queryLocalInterface;
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
            onSearchSongResult(data.readInt(), data.readInt(), data.createTypedArrayList(SearchResult.CREATOR));
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements ISearchSongCallback {
            public static ISearchSongCallback sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.mediacenter.callback.ISearchSongCallback
            public void onSearchSongResult(int source, int app, List<SearchResult> searchResults) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(source);
                    obtain.writeInt(app);
                    obtain.writeTypedList(searchResults);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSearchSongResult(source, app, searchResults);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISearchSongCallback impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ISearchSongCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}