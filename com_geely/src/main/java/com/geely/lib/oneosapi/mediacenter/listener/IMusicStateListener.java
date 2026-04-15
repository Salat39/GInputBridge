package com.geely.lib.oneosapi.mediacenter.listener;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.mediacenter.bean.MediaData;
import java.util.List;

/* loaded from: classes.dex */
public interface IMusicStateListener extends IInterface {

    public static class Default implements IMusicStateListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IMusicStateListener
        public void onFavorStateChanged(int source, MediaData mediaData) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IMusicStateListener
        public void onLrcLoad(int source, String lrc, long time) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IMusicStateListener
        public void onMediaDataChanged(int source, MediaData mediaData) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IMusicStateListener
        public void onPlayListChanged(int source, List<MediaData> list) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IMusicStateListener
        public void onPlayModeChange(int source, int mode) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IMusicStateListener
        public void onPlayPositionChanged(int source, long current, long total) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IMusicStateListener
        public void onPlayStateChanged(int source, int state) throws RemoteException {
        }
    }

    void onFavorStateChanged(int source, MediaData mediaData) throws RemoteException;

    void onLrcLoad(int source, String lrc, long time) throws RemoteException;

    void onMediaDataChanged(int source, MediaData mediaData) throws RemoteException;

    void onPlayListChanged(int source, List<MediaData> list) throws RemoteException;

    void onPlayModeChange(int source, int mode) throws RemoteException;

    void onPlayPositionChanged(int source, long current, long total) throws RemoteException;

    void onPlayStateChanged(int source, int state) throws RemoteException;

    public static abstract class Stub extends Binder implements IMusicStateListener {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.mediacenter.listener.IMusicStateListener";
        static final int TRANSACTION_onFavorStateChanged = 5;
        static final int TRANSACTION_onLrcLoad = 6;
        static final int TRANSACTION_onMediaDataChanged = 1;
        static final int TRANSACTION_onPlayListChanged = 4;
        static final int TRANSACTION_onPlayModeChange = 7;
        static final int TRANSACTION_onPlayPositionChanged = 2;
        static final int TRANSACTION_onPlayStateChanged = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMusicStateListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IMusicStateListener)) {
                return (IMusicStateListener) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    onMediaDataChanged(data.readInt(), data.readInt() != 0 ? MediaData.CREATOR.createFromParcel(data) : null);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    onPlayPositionChanged(data.readInt(), data.readLong(), data.readLong());
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    onPlayStateChanged(data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    onPlayListChanged(data.readInt(), data.createTypedArrayList(MediaData.CREATOR));
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    onFavorStateChanged(data.readInt(), data.readInt() != 0 ? MediaData.CREATOR.createFromParcel(data) : null);
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    onLrcLoad(data.readInt(), data.readString(), data.readLong());
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    onPlayModeChange(data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IMusicStateListener {
            public static IMusicStateListener sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IMusicStateListener
            public void onMediaDataChanged(int source, MediaData mediaData) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(source);
                    if (mediaData != null) {
                        obtain.writeInt(1);
                        mediaData.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMediaDataChanged(source, mediaData);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IMusicStateListener
            public void onPlayPositionChanged(int source, long current, long total) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(source);
                    obtain.writeLong(current);
                    obtain.writeLong(total);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPlayPositionChanged(source, current, total);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IMusicStateListener
            public void onPlayStateChanged(int source, int state) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(source);
                    obtain.writeInt(state);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPlayStateChanged(source, state);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IMusicStateListener
            public void onPlayListChanged(int source, List<MediaData> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(source);
                    obtain.writeTypedList(list);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPlayListChanged(source, list);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IMusicStateListener
            public void onFavorStateChanged(int source, MediaData mediaData) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(source);
                    if (mediaData != null) {
                        obtain.writeInt(1);
                        mediaData.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFavorStateChanged(source, mediaData);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IMusicStateListener
            public void onLrcLoad(int source, String lrc, long time) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(source);
                    obtain.writeString(lrc);
                    obtain.writeLong(time);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onLrcLoad(source, lrc, time);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IMusicStateListener
            public void onPlayModeChange(int source, int mode) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(source);
                    obtain.writeInt(mode);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPlayModeChange(source, mode);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMusicStateListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IMusicStateListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}