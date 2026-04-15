package com.geely.lib.oneosapi.linkmanager;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.linkmanager.ILinkListener;
import com.geely.lib.oneosapi.linkmanager.IModemCallStateListener;
import com.geely.lib.oneosapi.linkmanager.IMusicStateListener;
import com.geely.lib.oneosapi.linkmanager.ITryConnectCallback;

/* loaded from: classes.dex */
public interface ILinkManager extends IInterface {

    public static class Default implements ILinkManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
        public void fastBackward() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
        public void fastForward() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
        public int getConnectedSessionBrand() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
        public int getConnectedSessionType() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
        public boolean isSessionConnected() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
        public boolean isSourceActivated() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
        public void next() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
        public void play() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
        public void previous() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
        public void registerListener(ILinkListener listener, String packageName) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
        public void registerModemCallStateListener(IModemCallStateListener listener, String packageName) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
        public void registerMusicStateListener(IMusicStateListener listener, String packageName) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
        public void sessionConnected(int brand, int type) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
        public void sessionDisconnected(int brand, int type) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
        public void setModemCallState(int state) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
        public void setMusicInfo(String artistName, String albumName, String coverArt, String lyrics, long totalTimesMs, String title, String authorName, String writerName, String composerName, int playingCurrentTimeMs, boolean isFavorite, boolean isPlaying) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
        public void stop() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
        public void tryConnect(int brand, int type, ITryConnectCallback callback) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
        public void unregisterListener(ILinkListener listener, String packageName) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
        public void unregisterModemCallStateListener(IModemCallStateListener listener, String packageName) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
        public void unregisterMusicStateListener(IMusicStateListener listener, String packageName) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
        public void updatePlayState(int state, int brand) throws RemoteException {
        }
    }

    void fastBackward() throws RemoteException;

    void fastForward() throws RemoteException;

    int getConnectedSessionBrand() throws RemoteException;

    int getConnectedSessionType() throws RemoteException;

    boolean isSessionConnected() throws RemoteException;

    boolean isSourceActivated() throws RemoteException;

    void next() throws RemoteException;

    void play() throws RemoteException;

    void previous() throws RemoteException;

    void registerListener(ILinkListener listener, String packageName) throws RemoteException;

    void registerModemCallStateListener(IModemCallStateListener listener, String packageName) throws RemoteException;

    void registerMusicStateListener(IMusicStateListener listener, String packageName) throws RemoteException;

    void sessionConnected(int brand, int type) throws RemoteException;

    void sessionDisconnected(int brand, int type) throws RemoteException;

    void setModemCallState(int state) throws RemoteException;

    void setMusicInfo(String artistName, String albumName, String coverArt, String lyrics, long totalTimesMs, String title, String authorName, String writerName, String composerName, int playingCurrentTimeMs, boolean isFavorite, boolean isPlaying) throws RemoteException;

    void stop() throws RemoteException;

    void tryConnect(int brand, int type, ITryConnectCallback callback) throws RemoteException;

    void unregisterListener(ILinkListener listener, String packageName) throws RemoteException;

    void unregisterModemCallStateListener(IModemCallStateListener listener, String packageName) throws RemoteException;

    void unregisterMusicStateListener(IMusicStateListener listener, String packageName) throws RemoteException;

    void updatePlayState(int state, int brand) throws RemoteException;

    public static abstract class Stub extends Binder implements ILinkManager {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.linkmanager.ILinkManager";
        static final int TRANSACTION_fastBackward = 16;
        static final int TRANSACTION_fastForward = 15;
        static final int TRANSACTION_getConnectedSessionBrand = 4;
        static final int TRANSACTION_getConnectedSessionType = 5;
        static final int TRANSACTION_isSessionConnected = 3;
        static final int TRANSACTION_isSourceActivated = 10;
        static final int TRANSACTION_next = 13;
        static final int TRANSACTION_play = 11;
        static final int TRANSACTION_previous = 14;
        static final int TRANSACTION_registerListener = 6;
        static final int TRANSACTION_registerModemCallStateListener = 19;
        static final int TRANSACTION_registerMusicStateListener = 21;
        static final int TRANSACTION_sessionConnected = 1;
        static final int TRANSACTION_sessionDisconnected = 2;
        static final int TRANSACTION_setModemCallState = 18;
        static final int TRANSACTION_setMusicInfo = 17;
        static final int TRANSACTION_stop = 12;
        static final int TRANSACTION_tryConnect = 8;
        static final int TRANSACTION_unregisterListener = 7;
        static final int TRANSACTION_unregisterModemCallStateListener = 20;
        static final int TRANSACTION_unregisterMusicStateListener = 22;
        static final int TRANSACTION_updatePlayState = 9;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILinkManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ILinkManager)) {
                return (ILinkManager) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    sessionConnected(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    sessionDisconnected(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isSessionConnected = isSessionConnected();
                    parcel2.writeNoException();
                    parcel2.writeInt(isSessionConnected ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int connectedSessionBrand = getConnectedSessionBrand();
                    parcel2.writeNoException();
                    parcel2.writeInt(connectedSessionBrand);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int connectedSessionType = getConnectedSessionType();
                    parcel2.writeNoException();
                    parcel2.writeInt(connectedSessionType);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerListener(ILinkListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterListener(ILinkListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    tryConnect(parcel.readInt(), parcel.readInt(), ITryConnectCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    updatePlayState(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isSourceActivated = isSourceActivated();
                    parcel2.writeNoException();
                    parcel2.writeInt(isSourceActivated ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    play();
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    stop();
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    next();
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    previous();
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    fastForward();
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    fastBackward();
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    setMusicInfo(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readLong(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    setModemCallState(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerModemCallStateListener(IModemCallStateListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterModemCallStateListener(IModemCallStateListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerMusicStateListener(IMusicStateListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterMusicStateListener(IMusicStateListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ILinkManager {
            public static ILinkManager sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
            public void sessionConnected(int brand, int type) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(brand);
                    obtain.writeInt(type);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sessionConnected(brand, type);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
            public void sessionDisconnected(int brand, int type) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(brand);
                    obtain.writeInt(type);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sessionDisconnected(brand, type);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
            public boolean isSessionConnected() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSessionConnected();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
            public int getConnectedSessionBrand() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConnectedSessionBrand();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
            public int getConnectedSessionType() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConnectedSessionType();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
            public void registerListener(ILinkListener listener, String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListener(listener, packageName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
            public void unregisterListener(ILinkListener listener, String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterListener(listener, packageName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
            public void tryConnect(int brand, int type, ITryConnectCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(brand);
                    obtain.writeInt(type);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().tryConnect(brand, type, callback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
            public void updatePlayState(int state, int brand) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(state);
                    obtain.writeInt(brand);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updatePlayState(state, brand);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
            public boolean isSourceActivated() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSourceActivated();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
            public void play() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().play();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
            public void stop() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stop();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
            public void next() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().next();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
            public void previous() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().previous();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
            public void fastForward() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().fastForward();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
            public void fastBackward() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().fastBackward();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
            public void setMusicInfo(String artistName, String albumName, String coverArt, String lyrics, long totalTimesMs, String title, String authorName, String writerName, String composerName, int playingCurrentTimeMs, boolean isFavorite, boolean isPlaying) throws RemoteException {
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
                    int i = 1;
                    obtain.writeInt(isFavorite ? 1 : 0);
                    if (!isPlaying) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMusicInfo(artistName, albumName, coverArt, lyrics, totalTimesMs, title, authorName, writerName, composerName, playingCurrentTimeMs, isFavorite, isPlaying);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
            public void setModemCallState(int state) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(state);
                    if (!this.mRemote.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setModemCallState(state);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
            public void registerModemCallStateListener(IModemCallStateListener listener, String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerModemCallStateListener(listener, packageName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
            public void unregisterModemCallStateListener(IModemCallStateListener listener, String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(20, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterModemCallStateListener(listener, packageName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
            public void registerMusicStateListener(IMusicStateListener listener, String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(21, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerMusicStateListener(listener, packageName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.linkmanager.ILinkManager
            public void unregisterMusicStateListener(IMusicStateListener listener, String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(22, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterMusicStateListener(listener, packageName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILinkManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ILinkManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}