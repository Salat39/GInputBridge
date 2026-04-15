package com.geely.lib.oneosapi.vr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.vr.ISpeechRecognitionCallback;
import com.geely.lib.oneosapi.vr.ITtsCallback;
import com.geely.lib.oneosapi.vr.IVRDialogStatusChangedListener;
import com.geely.lib.oneosapi.vr.IVREngineStatusChangedListener;

/* loaded from: classes.dex */
public interface IVrService extends IInterface {

    public static class Default implements IVrService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.vr.IVrService
        public void closeDialogue(String token, String packageName) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.vr.IVrService
        public void disableVR(String packageName) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.vr.IVrService
        public void enableVR(String packageName) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.vr.IVrService
        public int getVREngineStatus(String packageName) throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.vr.IVrService
        public void onPageChange(String packageName, String pageName) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.vr.IVrService
        public void registerVRDialogStatusChangedListener(IVRDialogStatusChangedListener listener, String packageName) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.vr.IVrService
        public void registerVREngineStatusChangedListener(IVREngineStatusChangedListener listener, String packageName) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.vr.IVrService
        public void speak(String ttsContent, boolean isRender, int priority, String packageName) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.vr.IVrService
        public void speakIncomingCall(String text, String packageName) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.vr.IVrService
        public String startDialogWithCallback(String packageName, String tts, ITtsCallback ttsCallback) throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.vr.IVrService
        public String startDialogue(String query, String packageName) throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.vr.IVrService
        public String startDialogueByType(int type, String packageName) throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.vr.IVrService
        public boolean startSpeechRecognition(String packageName, long recordTimeout, long vadTimeout, ISpeechRecognitionCallback callback) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.vr.IVrService
        public void stopSpeechRecognition(String packageName) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.vr.IVrService
        public void stopSpeechRecognitionByTime(String packageName, long time) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.vr.IVrService
        public String toggleDialogue(String packageName) throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.vr.IVrService
        public void trackData(String appName, String appVersion, String packageName, String event, String dataJson) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.vr.IVrService
        public void unregisterVRDialogStatusChangedListener(IVRDialogStatusChangedListener listener, String packageName) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.vr.IVrService
        public void unregisterVREngineStatusChangedListener(IVREngineStatusChangedListener listener, String packageName) throws RemoteException {
        }
    }

    void closeDialogue(String token, String packageName) throws RemoteException;

    void disableVR(String packageName) throws RemoteException;

    void enableVR(String packageName) throws RemoteException;

    int getVREngineStatus(String packageName) throws RemoteException;

    void onPageChange(String packageName, String pageName) throws RemoteException;

    void registerVRDialogStatusChangedListener(IVRDialogStatusChangedListener listener, String packageName) throws RemoteException;

    void registerVREngineStatusChangedListener(IVREngineStatusChangedListener listener, String packageName) throws RemoteException;

    void speak(String ttsContent, boolean isRender, int priority, String packageName) throws RemoteException;

    void speakIncomingCall(String text, String packageName) throws RemoteException;

    String startDialogWithCallback(String packageName, String tts, ITtsCallback ttsCallback) throws RemoteException;

    String startDialogue(String query, String packageName) throws RemoteException;

    String startDialogueByType(int type, String packageName) throws RemoteException;

    boolean startSpeechRecognition(String packageName, long recordTimeout, long vadTimeout, ISpeechRecognitionCallback callback) throws RemoteException;

    void stopSpeechRecognition(String packageName) throws RemoteException;

    void stopSpeechRecognitionByTime(String packageName, long time) throws RemoteException;

    String toggleDialogue(String packageName) throws RemoteException;

    void trackData(String appName, String appVersion, String packageName, String event, String dataJson) throws RemoteException;

    void unregisterVRDialogStatusChangedListener(IVRDialogStatusChangedListener listener, String packageName) throws RemoteException;

    void unregisterVREngineStatusChangedListener(IVREngineStatusChangedListener listener, String packageName) throws RemoteException;

    public static abstract class Stub extends Binder implements IVrService {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.vr.IVrService";
        static final int TRANSACTION_closeDialogue = 3;
        static final int TRANSACTION_disableVR = 7;
        static final int TRANSACTION_enableVR = 8;
        static final int TRANSACTION_getVREngineStatus = 17;
        static final int TRANSACTION_onPageChange = 10;
        static final int TRANSACTION_registerVRDialogStatusChangedListener = 15;
        static final int TRANSACTION_registerVREngineStatusChangedListener = 18;
        static final int TRANSACTION_speak = 1;
        static final int TRANSACTION_speakIncomingCall = 4;
        static final int TRANSACTION_startDialogWithCallback = 6;
        static final int TRANSACTION_startDialogue = 2;
        static final int TRANSACTION_startDialogueByType = 5;
        static final int TRANSACTION_startSpeechRecognition = 11;
        static final int TRANSACTION_stopSpeechRecognition = 12;
        static final int TRANSACTION_stopSpeechRecognitionByTime = 13;
        static final int TRANSACTION_toggleDialogue = 14;
        static final int TRANSACTION_trackData = 9;
        static final int TRANSACTION_unregisterVRDialogStatusChangedListener = 16;
        static final int TRANSACTION_unregisterVREngineStatusChangedListener = 19;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVrService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IVrService)) {
                return (IVrService) queryLocalInterface;
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
                    speak(parcel.readString(), parcel.readInt() != 0, parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    String startDialogue = startDialogue(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(startDialogue);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    closeDialogue(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    speakIncomingCall(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    String startDialogueByType = startDialogueByType(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(startDialogueByType);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    String startDialogWithCallback = startDialogWithCallback(parcel.readString(), parcel.readString(), ITtsCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeString(startDialogWithCallback);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    disableVR(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    enableVR(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    trackData(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPageChange(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean startSpeechRecognition = startSpeechRecognition(parcel.readString(), parcel.readLong(), parcel.readLong(), ISpeechRecognitionCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(startSpeechRecognition ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopSpeechRecognition(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopSpeechRecognitionByTime(parcel.readString(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    String str = toggleDialogue(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(str);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerVRDialogStatusChangedListener(IVRDialogStatusChangedListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterVRDialogStatusChangedListener(IVRDialogStatusChangedListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    int vREngineStatus = getVREngineStatus(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(vREngineStatus);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerVREngineStatusChangedListener(IVREngineStatusChangedListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterVREngineStatusChangedListener(IVREngineStatusChangedListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IVrService {
            public static IVrService sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.vr.IVrService
            public void speak(String ttsContent, boolean isRender, int priority, String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(ttsContent);
                    obtain.writeInt(isRender ? 1 : 0);
                    obtain.writeInt(priority);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().speak(ttsContent, isRender, priority, packageName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.vr.IVrService
            public String startDialogue(String query, String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(query);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startDialogue(query, packageName);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.vr.IVrService
            public void closeDialogue(String token, String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(token);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().closeDialogue(token, packageName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.vr.IVrService
            public void speakIncomingCall(String text, String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(text);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().speakIncomingCall(text, packageName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.vr.IVrService
            public String startDialogueByType(int type, String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(type);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startDialogueByType(type, packageName);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.vr.IVrService
            public String startDialogWithCallback(String packageName, String tts, ITtsCallback ttsCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(packageName);
                    obtain.writeString(tts);
                    obtain.writeStrongBinder(ttsCallback != null ? ttsCallback.asBinder() : null);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startDialogWithCallback(packageName, tts, ttsCallback);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.vr.IVrService
            public void disableVR(String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disableVR(packageName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.vr.IVrService
            public void enableVR(String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableVR(packageName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.vr.IVrService
            public void trackData(String appName, String appVersion, String packageName, String event, String dataJson) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(appName);
                    obtain.writeString(appVersion);
                    obtain.writeString(packageName);
                    obtain.writeString(event);
                    obtain.writeString(dataJson);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().trackData(appName, appVersion, packageName, event, dataJson);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.vr.IVrService
            public void onPageChange(String packageName, String pageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(packageName);
                    obtain.writeString(pageName);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPageChange(packageName, pageName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.vr.IVrService
            public boolean startSpeechRecognition(String packageName, long recordTimeout, long vadTimeout, ISpeechRecognitionCallback callback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(packageName);
                    obtain.writeLong(recordTimeout);
                    obtain.writeLong(vadTimeout);
                    obtain.writeStrongBinder(callback != null ? callback.asBinder() : null);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        boolean startSpeechRecognition = Stub.getDefaultImpl().startSpeechRecognition(packageName, recordTimeout, vadTimeout, callback);
                        obtain2.recycle();
                        obtain.recycle();
                        return startSpeechRecognition;
                    }
                    obtain2.readException();
                    boolean z = obtain2.readInt() != 0;
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th2) {
//                    th = th2;
                    obtain2.recycle();
                    obtain.recycle();
//                    throw th;
                }
                return false;
            }

            @Override // com.geely.lib.oneosapi.vr.IVrService
            public void stopSpeechRecognition(String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopSpeechRecognition(packageName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.vr.IVrService
            public void stopSpeechRecognitionByTime(String packageName, long time) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(packageName);
                    obtain.writeLong(time);
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopSpeechRecognitionByTime(packageName, time);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.vr.IVrService
            public String toggleDialogue(String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().toggleDialogue(packageName);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.vr.IVrService
            public void registerVRDialogStatusChangedListener(IVRDialogStatusChangedListener listener, String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerVRDialogStatusChangedListener(listener, packageName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.vr.IVrService
            public void unregisterVRDialogStatusChangedListener(IVRDialogStatusChangedListener listener, String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterVRDialogStatusChangedListener(listener, packageName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.vr.IVrService
            public int getVREngineStatus(String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVREngineStatus(packageName);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.vr.IVrService
            public void registerVREngineStatusChangedListener(IVREngineStatusChangedListener listener, String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerVREngineStatusChangedListener(listener, packageName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.vr.IVrService
            public void unregisterVREngineStatusChangedListener(IVREngineStatusChangedListener listener, String packageName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    obtain.writeString(packageName);
                    if (!this.mRemote.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterVREngineStatusChangedListener(listener, packageName);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVrService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IVrService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}