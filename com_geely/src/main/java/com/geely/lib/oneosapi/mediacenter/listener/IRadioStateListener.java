package com.geely.lib.oneosapi.mediacenter.listener;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.mediacenter.bean.Frequency;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public interface IRadioStateListener extends IInterface {

    public static class Default implements IRadioStateListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
        public void onCarouselPlayStart(boolean succeeded) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
        public void onCarouserPlayStop(int frequency) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
        public void onCarouserWaiting(int frequency, long leftTime) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
        public void onCollectionStationListChanged(List<Frequency> frequencyList, int frequency, boolean collection) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
        public void onCollectionStationListReachesMax() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
        public void onCurrentBand(int band) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
        public void onCurrentFrequency(Frequency frequency) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
        public void onOpenRadioResult(boolean succeeded) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
        public void onRadioClosed(boolean succeeded) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
        public void onRadioError(int errorCode) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
        public void onRadioMuteState(int state) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
        public void onRadioNameListChanged(List<Frequency> frequencyList) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
        public void onRadioStatusChanged(int status) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
        public void onScanStarted(int direction) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
        public void onScanStationListChanged(List<Frequency> frequencyList) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
        public void onScanStopped(boolean succeeded) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
        public void onSeekStarted(int direction) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
        public void onSeekStopped(boolean succeeded) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
        public void onSignalQualityChanged(int level) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
        public void onStationFrequency(Frequency frequency) throws RemoteException {
        }
    }

    void onCarouselPlayStart(boolean succeeded) throws RemoteException;

    void onCarouserPlayStop(int frequency) throws RemoteException;

    void onCarouserWaiting(int frequency, long leftTime) throws RemoteException;

    void onCollectionStationListChanged(List<Frequency> frequencyList, int frequency, boolean collection) throws RemoteException;

    void onCollectionStationListReachesMax() throws RemoteException;

    void onCurrentBand(int band) throws RemoteException;

    void onCurrentFrequency(Frequency frequency) throws RemoteException;

    void onOpenRadioResult(boolean succeeded) throws RemoteException;

    void onRadioClosed(boolean succeeded) throws RemoteException;

    void onRadioError(int errorCode) throws RemoteException;

    void onRadioMuteState(int state) throws RemoteException;

    void onRadioNameListChanged(List<Frequency> frequencyList) throws RemoteException;

    void onRadioStatusChanged(int status) throws RemoteException;

    void onScanStarted(int direction) throws RemoteException;

    void onScanStationListChanged(List<Frequency> frequencyList) throws RemoteException;

    void onScanStopped(boolean succeeded) throws RemoteException;

    void onSeekStarted(int direction) throws RemoteException;

    void onSeekStopped(boolean succeeded) throws RemoteException;

    void onSignalQualityChanged(int level) throws RemoteException;

    void onStationFrequency(Frequency frequency) throws RemoteException;

    public static abstract class Stub extends Binder implements IRadioStateListener {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener";
        static final int TRANSACTION_onCarouselPlayStart = 11;
        static final int TRANSACTION_onCarouserPlayStop = 12;
        static final int TRANSACTION_onCarouserWaiting = 13;
        static final int TRANSACTION_onCollectionStationListChanged = 16;
        static final int TRANSACTION_onCollectionStationListReachesMax = 15;
        static final int TRANSACTION_onCurrentBand = 3;
        static final int TRANSACTION_onCurrentFrequency = 4;
        static final int TRANSACTION_onOpenRadioResult = 1;
        static final int TRANSACTION_onRadioClosed = 2;
        static final int TRANSACTION_onRadioError = 20;
        static final int TRANSACTION_onRadioMuteState = 19;
        static final int TRANSACTION_onRadioNameListChanged = 14;
        static final int TRANSACTION_onRadioStatusChanged = 18;
        static final int TRANSACTION_onScanStarted = 8;
        static final int TRANSACTION_onScanStationListChanged = 17;
        static final int TRANSACTION_onScanStopped = 9;
        static final int TRANSACTION_onSeekStarted = 6;
        static final int TRANSACTION_onSeekStopped = 7;
        static final int TRANSACTION_onSignalQualityChanged = 10;
        static final int TRANSACTION_onStationFrequency = 5;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRadioStateListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRadioStateListener)) {
                return (IRadioStateListener) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Frequency createFromParcel;
            if (code == 1598968902) {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    onOpenRadioResult(data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    onRadioClosed(data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    onCurrentBand(data.readInt());
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    createFromParcel = data.readInt() != 0 ? Frequency.CREATOR.createFromParcel(data) : null;
                    onCurrentFrequency(createFromParcel);
                    reply.writeNoException();
                    if (createFromParcel != null) {
                        reply.writeInt(1);
                        createFromParcel.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    createFromParcel = data.readInt() != 0 ? Frequency.CREATOR.createFromParcel(data) : null;
                    onStationFrequency(createFromParcel);
                    reply.writeNoException();
                    if (createFromParcel != null) {
                        reply.writeInt(1);
                        createFromParcel.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    onSeekStarted(data.readInt());
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    onSeekStopped(data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    onScanStarted(data.readInt());
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    onScanStopped(data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    onSignalQualityChanged(data.readInt());
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    onCarouselPlayStart(data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    onCarouserPlayStop(data.readInt());
                    reply.writeNoException();
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    onCarouserWaiting(data.readInt(), data.readLong());
                    reply.writeNoException();
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    ArrayList createTypedArrayList = data.createTypedArrayList(Frequency.CREATOR);
                    onRadioNameListChanged(createTypedArrayList);
                    reply.writeNoException();
                    reply.writeTypedList(createTypedArrayList);
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    onCollectionStationListReachesMax();
                    reply.writeNoException();
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    ArrayList createTypedArrayList2 = data.createTypedArrayList(Frequency.CREATOR);
                    onCollectionStationListChanged(createTypedArrayList2, data.readInt(), data.readInt() != 0);
                    reply.writeNoException();
                    reply.writeTypedList(createTypedArrayList2);
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    ArrayList createTypedArrayList3 = data.createTypedArrayList(Frequency.CREATOR);
                    onScanStationListChanged(createTypedArrayList3);
                    reply.writeNoException();
                    reply.writeTypedList(createTypedArrayList3);
                    return true;
                case 18:
                    data.enforceInterface(DESCRIPTOR);
                    onRadioStatusChanged(data.readInt());
                    reply.writeNoException();
                    return true;
                case 19:
                    data.enforceInterface(DESCRIPTOR);
                    onRadioMuteState(data.readInt());
                    reply.writeNoException();
                    return true;
                case 20:
                    data.enforceInterface(DESCRIPTOR);
                    onRadioError(data.readInt());
                    reply.writeNoException();
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IRadioStateListener {
            public static IRadioStateListener sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
            public void onOpenRadioResult(boolean succeeded) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(succeeded ? 1 : 0);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onOpenRadioResult(succeeded);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
            public void onRadioClosed(boolean succeeded) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(succeeded ? 1 : 0);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRadioClosed(succeeded);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
            public void onCurrentBand(int band) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(band);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCurrentBand(band);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
            public void onCurrentFrequency(Frequency frequency) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (frequency != null) {
                        obtain.writeInt(1);
                        frequency.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCurrentFrequency(frequency);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        frequency.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
            public void onStationFrequency(Frequency frequency) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (frequency != null) {
                        obtain.writeInt(1);
                        frequency.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onStationFrequency(frequency);
                        return;
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        frequency.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
            public void onSeekStarted(int direction) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(direction);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSeekStarted(direction);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
            public void onSeekStopped(boolean succeeded) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(succeeded ? 1 : 0);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSeekStopped(succeeded);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
            public void onScanStarted(int direction) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(direction);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onScanStarted(direction);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
            public void onScanStopped(boolean succeeded) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(succeeded ? 1 : 0);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onScanStopped(succeeded);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
            public void onSignalQualityChanged(int level) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(level);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSignalQualityChanged(level);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
            public void onCarouselPlayStart(boolean succeeded) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(succeeded ? 1 : 0);
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCarouselPlayStart(succeeded);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
            public void onCarouserPlayStop(int frequency) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(frequency);
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCarouserPlayStop(frequency);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
            public void onCarouserWaiting(int frequency, long leftTime) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(frequency);
                    obtain.writeLong(leftTime);
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCarouserWaiting(frequency, leftTime);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
            public void onRadioNameListChanged(List<Frequency> frequencyList) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(frequencyList);
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRadioNameListChanged(frequencyList);
                    } else {
                        obtain2.readException();
                        obtain2.readTypedList(frequencyList, Frequency.CREATOR);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
            public void onCollectionStationListReachesMax() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCollectionStationListReachesMax();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
            public void onCollectionStationListChanged(List<Frequency> frequencyList, int frequency, boolean collection) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(frequencyList);
                    obtain.writeInt(frequency);
                    obtain.writeInt(collection ? 1 : 0);
                    if (!this.mRemote.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCollectionStationListChanged(frequencyList, frequency, collection);
                    } else {
                        obtain2.readException();
                        obtain2.readTypedList(frequencyList, Frequency.CREATOR);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
            public void onScanStationListChanged(List<Frequency> frequencyList) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(frequencyList);
                    if (!this.mRemote.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onScanStationListChanged(frequencyList);
                    } else {
                        obtain2.readException();
                        obtain2.readTypedList(frequencyList, Frequency.CREATOR);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
            public void onRadioStatusChanged(int status) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(status);
                    if (!this.mRemote.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRadioStatusChanged(status);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
            public void onRadioMuteState(int state) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(state);
                    if (!this.mRemote.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRadioMuteState(state);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener
            public void onRadioError(int errorCode) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(errorCode);
                    if (!this.mRemote.transact(20, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onRadioError(errorCode);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRadioStateListener impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IRadioStateListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}