package com.geely.lib.oneosapi.mediacenter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.mediacenter.ICarSpeedManager;
import com.geely.lib.oneosapi.mediacenter.IHeartbeatPacket;
import com.geely.lib.oneosapi.mediacenter.IMusicManager;
import com.geely.lib.oneosapi.mediacenter.IRadioManager;
import com.geely.lib.oneosapi.mediacenter.listener.IRebootPlayChangeListener;
import com.geely.lib.oneosapi.mediacenter.listener.ISourceStateListener;

/* loaded from: classes.dex */
public interface IMediaCenter extends IInterface {

    public static class Default implements IMediaCenter {
        @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
        public void addPsdSourceStateListener(ISourceStateListener listener) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
        public void addRebootPlayChangeListener(IRebootPlayChangeListener listener) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
        public void addSourceStateListener(ISourceStateListener listener) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
        public String getAppPackageName(int audioSource, int appSource) throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
        public ICarSpeedManager getCarManager() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
        public int getCurrentAppSource() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
        public int getCurrentAudioSource() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
        public int getCurrentPsdAppSource() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
        public int getCurrentPsdAudioSource() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
        public int getFocusedAppSource() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
        public int getFocusedAudioSource() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
        public IMusicManager getMusicManager() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
        public int getPsdFocusedAppSource() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
        public int getPsdFocusedAudioSource() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
        public IRadioManager getRadioManager() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
        public int getRebootPlay() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
        public boolean isPsdBtAudioSource(int audioSource, int appSource) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
        public boolean isPsdBtDeviceConnected() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
        public void requestAudioSource(int audioSource, int appSource) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
        public void setHeartbeatPacket(IHeartbeatPacket packet) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
        public void setPsdBtAudioSource(int audioSource, int appSource, boolean enable) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
        public void setPsdBtChannel(int app, boolean enable) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
        public void setRebootPlay(int rebootPlayValue) throws RemoteException {
        }
    }

    void addPsdSourceStateListener(ISourceStateListener listener) throws RemoteException;

    void addRebootPlayChangeListener(IRebootPlayChangeListener listener) throws RemoteException;

    void addSourceStateListener(ISourceStateListener listener) throws RemoteException;

    String getAppPackageName(int audioSource, int appSource) throws RemoteException;

    ICarSpeedManager getCarManager() throws RemoteException;

    int getCurrentAppSource() throws RemoteException;

    int getCurrentAudioSource() throws RemoteException;

    int getCurrentPsdAppSource() throws RemoteException;

    int getCurrentPsdAudioSource() throws RemoteException;

    int getFocusedAppSource() throws RemoteException;

    int getFocusedAudioSource() throws RemoteException;

    IMusicManager getMusicManager() throws RemoteException;

    int getPsdFocusedAppSource() throws RemoteException;

    int getPsdFocusedAudioSource() throws RemoteException;

    IRadioManager getRadioManager() throws RemoteException;

    int getRebootPlay() throws RemoteException;

    boolean isPsdBtAudioSource(int audioSource, int appSource) throws RemoteException;

    boolean isPsdBtDeviceConnected() throws RemoteException;

    void requestAudioSource(int audioSource, int appSource) throws RemoteException;

    void setHeartbeatPacket(IHeartbeatPacket packet) throws RemoteException;

    void setPsdBtAudioSource(int audioSource, int appSource, boolean enable) throws RemoteException;

    void setPsdBtChannel(int app, boolean enable) throws RemoteException;

    void setRebootPlay(int rebootPlayValue) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaCenter {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.mediacenter.IMediaCenter";
        static final int TRANSACTION_addPsdSourceStateListener = 9;
        static final int TRANSACTION_addRebootPlayChangeListener = 23;
        static final int TRANSACTION_addSourceStateListener = 4;
        static final int TRANSACTION_getAppPackageName = 20;
        static final int TRANSACTION_getCarManager = 7;
        static final int TRANSACTION_getCurrentAppSource = 2;
        static final int TRANSACTION_getCurrentAudioSource = 1;
        static final int TRANSACTION_getCurrentPsdAppSource = 13;
        static final int TRANSACTION_getCurrentPsdAudioSource = 12;
        static final int TRANSACTION_getFocusedAppSource = 17;
        static final int TRANSACTION_getFocusedAudioSource = 16;
        static final int TRANSACTION_getMusicManager = 5;
        static final int TRANSACTION_getPsdFocusedAppSource = 19;
        static final int TRANSACTION_getPsdFocusedAudioSource = 18;
        static final int TRANSACTION_getRadioManager = 6;
        static final int TRANSACTION_getRebootPlay = 22;
        static final int TRANSACTION_isPsdBtAudioSource = 10;
        static final int TRANSACTION_isPsdBtDeviceConnected = 15;
        static final int TRANSACTION_requestAudioSource = 3;
        static final int TRANSACTION_setHeartbeatPacket = 8;
        static final int TRANSACTION_setPsdBtAudioSource = 11;
        static final int TRANSACTION_setPsdBtChannel = 14;
        static final int TRANSACTION_setRebootPlay = 21;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaCenter asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IMediaCenter)) {
                return (IMediaCenter) queryLocalInterface;
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
                    int currentAudioSource = getCurrentAudioSource();
                    parcel2.writeNoException();
                    parcel2.writeInt(currentAudioSource);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    int currentAppSource = getCurrentAppSource();
                    parcel2.writeNoException();
                    parcel2.writeInt(currentAppSource);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestAudioSource(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    addSourceStateListener(ISourceStateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    IMusicManager musicManager = getMusicManager();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(musicManager != null ? musicManager.asBinder() : null);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    IRadioManager radioManager = getRadioManager();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(radioManager != null ? radioManager.asBinder() : null);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    ICarSpeedManager carManager = getCarManager();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(carManager != null ? carManager.asBinder() : null);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    setHeartbeatPacket(IHeartbeatPacket.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    addPsdSourceStateListener(ISourceStateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isPsdBtAudioSource = isPsdBtAudioSource(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(isPsdBtAudioSource ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPsdBtAudioSource(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    int currentPsdAudioSource = getCurrentPsdAudioSource();
                    parcel2.writeNoException();
                    parcel2.writeInt(currentPsdAudioSource);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    int currentPsdAppSource = getCurrentPsdAppSource();
                    parcel2.writeNoException();
                    parcel2.writeInt(currentPsdAppSource);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPsdBtChannel(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isPsdBtDeviceConnected = isPsdBtDeviceConnected();
                    parcel2.writeNoException();
                    parcel2.writeInt(isPsdBtDeviceConnected ? 1 : 0);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    int focusedAudioSource = getFocusedAudioSource();
                    parcel2.writeNoException();
                    parcel2.writeInt(focusedAudioSource);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    int focusedAppSource = getFocusedAppSource();
                    parcel2.writeNoException();
                    parcel2.writeInt(focusedAppSource);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    int psdFocusedAudioSource = getPsdFocusedAudioSource();
                    parcel2.writeNoException();
                    parcel2.writeInt(psdFocusedAudioSource);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    int psdFocusedAppSource = getPsdFocusedAppSource();
                    parcel2.writeNoException();
                    parcel2.writeInt(psdFocusedAppSource);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    String appPackageName = getAppPackageName(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(appPackageName);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    setRebootPlay(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    int rebootPlay = getRebootPlay();
                    parcel2.writeNoException();
                    parcel2.writeInt(rebootPlay);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    addRebootPlayChangeListener(IRebootPlayChangeListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IMediaCenter {
            public static IMediaCenter sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
            public int getCurrentAudioSource() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentAudioSource();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
            public int getCurrentAppSource() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentAppSource();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
            public void requestAudioSource(int audioSource, int appSource) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(audioSource);
                    obtain.writeInt(appSource);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestAudioSource(audioSource, appSource);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
            public void addSourceStateListener(ISourceStateListener listener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addSourceStateListener(listener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
            public IMusicManager getMusicManager() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMusicManager();
                    }
                    obtain2.readException();
                    return IMusicManager.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
            public IRadioManager getRadioManager() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRadioManager();
                    }
                    obtain2.readException();
                    return IRadioManager.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
            public ICarSpeedManager getCarManager() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarManager();
                    }
                    obtain2.readException();
                    return ICarSpeedManager.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
            public void setHeartbeatPacket(IHeartbeatPacket packet) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(packet != null ? packet.asBinder() : null);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setHeartbeatPacket(packet);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
            public void addPsdSourceStateListener(ISourceStateListener listener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addPsdSourceStateListener(listener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
            public boolean isPsdBtAudioSource(int audioSource, int appSource) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(audioSource);
                    obtain.writeInt(appSource);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPsdBtAudioSource(audioSource, appSource);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
            public void setPsdBtAudioSource(int audioSource, int appSource, boolean enable) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(audioSource);
                    obtain.writeInt(appSource);
                    obtain.writeInt(enable ? 1 : 0);
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPsdBtAudioSource(audioSource, appSource, enable);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
            public int getCurrentPsdAudioSource() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentPsdAudioSource();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
            public int getCurrentPsdAppSource() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentPsdAppSource();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
            public void setPsdBtChannel(int app, boolean enable) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(app);
                    obtain.writeInt(enable ? 1 : 0);
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setPsdBtChannel(app, enable);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
            public boolean isPsdBtDeviceConnected() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPsdBtDeviceConnected();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
            public int getFocusedAudioSource() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFocusedAudioSource();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
            public int getFocusedAppSource() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFocusedAppSource();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
            public int getPsdFocusedAudioSource() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPsdFocusedAudioSource();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
            public int getPsdFocusedAppSource() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPsdFocusedAppSource();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
            public String getAppPackageName(int audioSource, int appSource) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(audioSource);
                    obtain.writeInt(appSource);
                    if (!this.mRemote.transact(20, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppPackageName(audioSource, appSource);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
            public void setRebootPlay(int rebootPlayValue) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(rebootPlayValue);
                    if (!this.mRemote.transact(21, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRebootPlay(rebootPlayValue);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
            public int getRebootPlay() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(22, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRebootPlay();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IMediaCenter
            public void addRebootPlayChangeListener(IRebootPlayChangeListener listener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (!this.mRemote.transact(23, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addRebootPlayChangeListener(listener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMediaCenter impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IMediaCenter getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}