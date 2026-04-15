package com.geely.lib.oneosapi.phone.inter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.phone.inter.IBluetoothServicesListener;
import com.geely.lib.oneosapi.phone.inter.IBtStateChangeListener;
import com.geely.lib.oneosapi.phone.inter.ICallLogDateListener;
import com.geely.lib.oneosapi.phone.inter.IPhonebookDownloadStateListener;
import com.geely.lib.oneosapi.phone.telecom.GlyCallItem;
import com.geely.lib.oneosapi.phone.telecom.GlyCallLogInfo;
import java.util.List;

/* loaded from: classes.dex */
public interface IBluetoothService extends IInterface {

    public static class Default implements IBluetoothService {
        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public void actionAcceptOrDisconnectCall() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public void actionRejectOrTerminated() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public void answerCall() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public void callbackCall() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public void disconnectCall() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public List<GlyCallLogInfo> getCallLogList() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public GlyCallItem getCallbackCallItem() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public int getDownloadStateListener(int type, int state) throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public List<GlyCallItem> getGlyCallItem() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public GlyCallItem getRebroadcastCallItem() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public void holdCall(boolean isHold) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public boolean isMicrophoneMute() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public void placeCall(String number) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public void playDTM(char digit) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public void rebroadcastCall() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public void registerBtStateListener(IBtStateChangeListener btStateListener, String packageName) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public void registerCallLogListener(ICallLogDateListener callLogDateListener, String packageName) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public void registerDownloadStateListener(IPhonebookDownloadStateListener downloadStateListener, String packageName) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public void registerListener(IBluetoothServicesListener listener, String packageName) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public void rejectCall() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public void rejectRingOrTerminatedActive() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public void setCallViewStatus(int status) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public void setMicrophoneAudio(boolean enable) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public void setRingtoneMute(boolean mute) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public void stopDtmfTone() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public void switchActiveCall() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
        public void unRegisterListener(IBluetoothServicesListener listener) throws RemoteException {
        }
    }

    void actionAcceptOrDisconnectCall() throws RemoteException;

    void actionRejectOrTerminated() throws RemoteException;

    void answerCall() throws RemoteException;

    void callbackCall() throws RemoteException;

    void disconnectCall() throws RemoteException;

    List<GlyCallLogInfo> getCallLogList() throws RemoteException;

    GlyCallItem getCallbackCallItem() throws RemoteException;

    int getDownloadStateListener(int type, int state) throws RemoteException;

    List<GlyCallItem> getGlyCallItem() throws RemoteException;

    GlyCallItem getRebroadcastCallItem() throws RemoteException;

    void holdCall(boolean isHold) throws RemoteException;

    boolean isMicrophoneMute() throws RemoteException;

    void placeCall(String number) throws RemoteException;

    void playDTM(char digit) throws RemoteException;

    void rebroadcastCall() throws RemoteException;

    void registerBtStateListener(IBtStateChangeListener btStateListener, String packageName) throws RemoteException;

    void registerCallLogListener(ICallLogDateListener callLogDateListener, String packageName) throws RemoteException;

    void registerDownloadStateListener(IPhonebookDownloadStateListener downloadStateListener, String packageName) throws RemoteException;

    void registerListener(IBluetoothServicesListener listener, String packageName) throws RemoteException;

    void rejectCall() throws RemoteException;

    void rejectRingOrTerminatedActive() throws RemoteException;

    void setCallViewStatus(int status) throws RemoteException;

    void setMicrophoneAudio(boolean enable) throws RemoteException;

    void setRingtoneMute(boolean mute) throws RemoteException;

    void stopDtmfTone() throws RemoteException;

    void switchActiveCall() throws RemoteException;

    void unRegisterListener(IBluetoothServicesListener listener) throws RemoteException;

    public static abstract class Stub extends Binder implements IBluetoothService {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.phone.inter.IBluetoothService";
        static final int TRANSACTION_actionAcceptOrDisconnectCall = 13;
        static final int TRANSACTION_actionRejectOrTerminated = 14;
        static final int TRANSACTION_answerCall = 5;
        static final int TRANSACTION_callbackCall = 23;
        static final int TRANSACTION_disconnectCall = 6;
        static final int TRANSACTION_getCallLogList = 18;
        static final int TRANSACTION_getCallbackCallItem = 26;
        static final int TRANSACTION_getDownloadStateListener = 20;
        static final int TRANSACTION_getGlyCallItem = 24;
        static final int TRANSACTION_getRebroadcastCallItem = 25;
        static final int TRANSACTION_holdCall = 9;
        static final int TRANSACTION_isMicrophoneMute = 12;
        static final int TRANSACTION_placeCall = 3;
        static final int TRANSACTION_playDTM = 7;
        static final int TRANSACTION_rebroadcastCall = 22;
        static final int TRANSACTION_registerBtStateListener = 27;
        static final int TRANSACTION_registerCallLogListener = 21;
        static final int TRANSACTION_registerDownloadStateListener = 19;
        static final int TRANSACTION_registerListener = 1;
        static final int TRANSACTION_rejectCall = 4;
        static final int TRANSACTION_rejectRingOrTerminatedActive = 15;
        static final int TRANSACTION_setCallViewStatus = 17;
        static final int TRANSACTION_setMicrophoneAudio = 10;
        static final int TRANSACTION_setRingtoneMute = 11;
        static final int TRANSACTION_stopDtmfTone = 8;
        static final int TRANSACTION_switchActiveCall = 16;
        static final int TRANSACTION_unRegisterListener = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBluetoothService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IBluetoothService)) {
                return (IBluetoothService) queryLocalInterface;
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
                    registerListener(IBluetoothServicesListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unRegisterListener(IBluetoothServicesListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    placeCall(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    rejectCall();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    answerCall();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    disconnectCall();
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    playDTM((char) parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopDtmfTone();
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    holdCall(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    setMicrophoneAudio(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    setRingtoneMute(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isMicrophoneMute = isMicrophoneMute();
                    parcel2.writeNoException();
                    parcel2.writeInt(isMicrophoneMute ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    actionAcceptOrDisconnectCall();
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    actionRejectOrTerminated();
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    rejectRingOrTerminatedActive();
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    switchActiveCall();
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    setCallViewStatus(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<GlyCallLogInfo> callLogList = getCallLogList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(callLogList);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerDownloadStateListener(IPhonebookDownloadStateListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    int downloadStateListener = getDownloadStateListener(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(downloadStateListener);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerCallLogListener(ICallLogDateListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    rebroadcastCall();
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    callbackCall();
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<GlyCallItem> glyCallItem = getGlyCallItem();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(glyCallItem);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    GlyCallItem rebroadcastCallItem = getRebroadcastCallItem();
                    parcel2.writeNoException();
                    if (rebroadcastCallItem != null) {
                        parcel2.writeInt(1);
                        rebroadcastCallItem.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    GlyCallItem callbackCallItem = getCallbackCallItem();
                    parcel2.writeNoException();
                    if (callbackCallItem != null) {
                        parcel2.writeInt(1);
                        callbackCallItem.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerBtStateListener(IBtStateChangeListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IBluetoothService {
            public static IBluetoothService sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public void registerListener(IBluetoothServicesListener listener, String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListener(listener, packageName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public void unRegisterListener(IBluetoothServicesListener listener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unRegisterListener(listener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public void placeCall(String number) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(number);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().placeCall(number);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public void rejectCall() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().rejectCall();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public void answerCall() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().answerCall();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public void disconnectCall() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disconnectCall();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public void playDTM(char digit) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(digit);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().playDTM(digit);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public void stopDtmfTone() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopDtmfTone();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public void holdCall(boolean isHold) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(isHold ? 1 : 0);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().holdCall(isHold);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public void setMicrophoneAudio(boolean enable) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(enable ? 1 : 0);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMicrophoneAudio(enable);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public void setRingtoneMute(boolean mute) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(mute ? 1 : 0);
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRingtoneMute(mute);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public boolean isMicrophoneMute() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isMicrophoneMute();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public void actionAcceptOrDisconnectCall() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().actionAcceptOrDisconnectCall();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public void actionRejectOrTerminated() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().actionRejectOrTerminated();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public void rejectRingOrTerminatedActive() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().rejectRingOrTerminatedActive();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public void switchActiveCall() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().switchActiveCall();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public void setCallViewStatus(int status) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(status);
                    if (!this.mRemote.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCallViewStatus(status);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public List<GlyCallLogInfo> getCallLogList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCallLogList();
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(GlyCallLogInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public void registerDownloadStateListener(IPhonebookDownloadStateListener downloadStateListener, String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(downloadStateListener != null ? downloadStateListener.asBinder() : null);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerDownloadStateListener(downloadStateListener, packageName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public int getDownloadStateListener(int type, int state) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(type);
                    obtain.writeInt(state);
                    if (!this.mRemote.transact(20, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDownloadStateListener(type, state);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public void registerCallLogListener(ICallLogDateListener callLogDateListener, String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(callLogDateListener != null ? callLogDateListener.asBinder() : null);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(21, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerCallLogListener(callLogDateListener, packageName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public void rebroadcastCall() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(22, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().rebroadcastCall();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public void callbackCall() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(23, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callbackCall();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public List<GlyCallItem> getGlyCallItem() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(24, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGlyCallItem();
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(GlyCallItem.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public GlyCallItem getRebroadcastCallItem() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(25, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRebroadcastCallItem();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? GlyCallItem.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public GlyCallItem getCallbackCallItem() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(26, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCallbackCallItem();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? GlyCallItem.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.phone.inter.IBluetoothService
            public void registerBtStateListener(IBtStateChangeListener btStateListener, String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(btStateListener != null ? btStateListener.asBinder() : null);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(27, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerBtStateListener(btStateListener, packageName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBluetoothService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IBluetoothService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}