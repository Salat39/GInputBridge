package com.geely.lib.oneosapi.linkmanager;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IMusicStateListener extends IInterface {

    public static class Default implements IMusicStateListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.linkmanager.IMusicStateListener
        public void onMusicStatusChange(String artistName, String albumName, String coverArt, String lyrics, long totalTimesMs, String title, String authorName, String writerName, String composerName, int playingCurrentTimeMs, boolean isFavorite, boolean isPlaying) throws RemoteException {
        }
    }

    void onMusicStatusChange(String artistName, String albumName, String coverArt, String lyrics, long totalTimesMs, String title, String authorName, String writerName, String composerName, int playingCurrentTimeMs, boolean isFavorite, boolean isPlaying) throws RemoteException;

    public static abstract class Stub extends Binder implements IMusicStateListener {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.linkmanager.IMusicStateListener";
        static final int TRANSACTION_onMusicStatusChange = 1;

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
            if (code != 1) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            onMusicStatusChange(data.readString(), data.readString(), data.readString(), data.readString(), data.readLong(), data.readString(), data.readString(), data.readString(), data.readString(), data.readInt(), data.readInt() != 0, data.readInt() != 0);
            reply.writeNoException();
            return true;
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

            @Override // com.geely.lib.oneosapi.linkmanager.IMusicStateListener
            public void onMusicStatusChange(String artistName, String albumName, String coverArt, String lyrics, long totalTimesMs, String title, String authorName, String writerName, String composerName, int playingCurrentTimeMs, boolean isFavorite, boolean isPlaying) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(artistName);
                    obtain.writeString(albumName);
                    obtain.writeString(coverArt);
                    obtain.writeString(lyrics);
                    obtain.writeLong(totalTimesMs);
                    obtain.writeString(title);
                    obtain.writeString(authorName);
                    obtain.writeString(writerName);
                    obtain.writeString(composerName);
                    obtain.writeInt(playingCurrentTimeMs);
                    obtain.writeInt(isFavorite ? 1 : 0);
                    obtain.writeInt(isPlaying ? 1 : 0);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onMusicStatusChange(artistName, albumName, coverArt, lyrics, totalTimesMs, title, authorName, writerName, composerName, playingCurrentTimeMs, isFavorite, isPlaying);
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