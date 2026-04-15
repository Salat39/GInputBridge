package com.geely.lib.oneosapi.camera;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ICameraInterface extends IInterface {

    public static class Default implements ICameraInterface {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.camera.ICameraInterface
        public void closeCamera() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.camera.ICameraInterface
        public int getCameraState() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.camera.ICameraInterface
        public boolean isFunctionSupported(int function, int zone) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.camera.ICameraInterface
        public boolean onSetVideoVolume(int mode) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.camera.ICameraInterface
        public void onStopVideoRecord() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.camera.ICameraInterface
        public void onTakeInnerPhoto() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.camera.ICameraInterface
        public void onTakeInnerVideo() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.camera.ICameraInterface
        public void onTakeOuterPhoto() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.camera.ICameraInterface
        public void onTakeOuterVideo() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.camera.ICameraInterface
        public void onTakePhoto() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.camera.ICameraInterface
        public void onTakePhotoAgain() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.camera.ICameraInterface
        public boolean onTakePhotoWithParams(int zone, int delayTime) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.camera.ICameraInterface
        public void onTakeStart() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.camera.ICameraInterface
        public void onTakeTimeLapseRecording() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.camera.ICameraInterface
        public void onTakeTimeTakenPhoto() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.camera.ICameraInterface
        public void onTakeVideo() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.camera.ICameraInterface
        public boolean onTakeVideoToggle(int open, int zone, int delayTime) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.camera.ICameraInterface
        public void onVideoMute() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.camera.ICameraInterface
        public void onVideoUnMute() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.camera.ICameraInterface
        public void openCamera() throws RemoteException {
        }
    }

    void closeCamera() throws RemoteException;

    int getCameraState() throws RemoteException;

    boolean isFunctionSupported(int function, int zone) throws RemoteException;

    boolean onSetVideoVolume(int mode) throws RemoteException;

    void onStopVideoRecord() throws RemoteException;

    void onTakeInnerPhoto() throws RemoteException;

    void onTakeInnerVideo() throws RemoteException;

    void onTakeOuterPhoto() throws RemoteException;

    void onTakeOuterVideo() throws RemoteException;

    void onTakePhoto() throws RemoteException;

    void onTakePhotoAgain() throws RemoteException;

    boolean onTakePhotoWithParams(int zone, int delayTime) throws RemoteException;

    void onTakeStart() throws RemoteException;

    void onTakeTimeLapseRecording() throws RemoteException;

    void onTakeTimeTakenPhoto() throws RemoteException;

    void onTakeVideo() throws RemoteException;

    boolean onTakeVideoToggle(int open, int zone, int delayTime) throws RemoteException;

    void onVideoMute() throws RemoteException;

    void onVideoUnMute() throws RemoteException;

    void openCamera() throws RemoteException;

    public static abstract class Stub extends Binder implements ICameraInterface {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.camera.ICameraInterface";
        static final int TRANSACTION_closeCamera = 2;
        static final int TRANSACTION_getCameraState = 20;
        static final int TRANSACTION_isFunctionSupported = 19;
        static final int TRANSACTION_onSetVideoVolume = 18;
        static final int TRANSACTION_onStopVideoRecord = 13;
        static final int TRANSACTION_onTakeInnerPhoto = 9;
        static final int TRANSACTION_onTakeInnerVideo = 11;
        static final int TRANSACTION_onTakeOuterPhoto = 10;
        static final int TRANSACTION_onTakeOuterVideo = 12;
        static final int TRANSACTION_onTakePhoto = 3;
        static final int TRANSACTION_onTakePhotoAgain = 15;
        static final int TRANSACTION_onTakePhotoWithParams = 16;
        static final int TRANSACTION_onTakeStart = 14;
        static final int TRANSACTION_onTakeTimeLapseRecording = 6;
        static final int TRANSACTION_onTakeTimeTakenPhoto = 5;
        static final int TRANSACTION_onTakeVideo = 4;
        static final int TRANSACTION_onTakeVideoToggle = 17;
        static final int TRANSACTION_onVideoMute = 7;
        static final int TRANSACTION_onVideoUnMute = 8;
        static final int TRANSACTION_openCamera = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICameraInterface asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ICameraInterface)) {
                return (ICameraInterface) queryLocalInterface;
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
                    openCamera();
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    closeCamera();
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTakePhoto();
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTakeVideo();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTakeTimeTakenPhoto();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTakeTimeLapseRecording();
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onVideoMute();
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onVideoUnMute();
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTakeInnerPhoto();
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTakeOuterPhoto();
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTakeInnerVideo();
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTakeOuterVideo();
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    onStopVideoRecord();
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTakeStart();
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTakePhotoAgain();
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean onTakePhotoWithParams = onTakePhotoWithParams(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(onTakePhotoWithParams ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean onTakeVideoToggle = onTakeVideoToggle(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(onTakeVideoToggle ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean onSetVideoVolume = onSetVideoVolume(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(onSetVideoVolume ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isFunctionSupported = isFunctionSupported(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(isFunctionSupported ? 1 : 0);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    int cameraState = getCameraState();
                    parcel2.writeNoException();
                    parcel2.writeInt(cameraState);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ICameraInterface {
            public static ICameraInterface sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.camera.ICameraInterface
            public void openCamera() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().openCamera();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.camera.ICameraInterface
            public void closeCamera() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().closeCamera();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.camera.ICameraInterface
            public void onTakePhoto() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTakePhoto();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.camera.ICameraInterface
            public void onTakeVideo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTakeVideo();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.camera.ICameraInterface
            public void onTakeTimeTakenPhoto() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTakeTimeTakenPhoto();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.camera.ICameraInterface
            public void onTakeTimeLapseRecording() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTakeTimeLapseRecording();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.camera.ICameraInterface
            public void onVideoMute() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onVideoMute();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.camera.ICameraInterface
            public void onVideoUnMute() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onVideoUnMute();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.camera.ICameraInterface
            public void onTakeInnerPhoto() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTakeInnerPhoto();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.camera.ICameraInterface
            public void onTakeOuterPhoto() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTakeOuterPhoto();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.camera.ICameraInterface
            public void onTakeInnerVideo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTakeInnerVideo();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.camera.ICameraInterface
            public void onTakeOuterVideo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTakeOuterVideo();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.camera.ICameraInterface
            public void onStopVideoRecord() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onStopVideoRecord();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.camera.ICameraInterface
            public void onTakeStart() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTakeStart();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.camera.ICameraInterface
            public void onTakePhotoAgain() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onTakePhotoAgain();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.camera.ICameraInterface
            public boolean onTakePhotoWithParams(int zone, int delayTime) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(zone);
                    obtain.writeInt(delayTime);
                    if (!this.mRemote.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onTakePhotoWithParams(zone, delayTime);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.camera.ICameraInterface
            public boolean onTakeVideoToggle(int open, int zone, int delayTime) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(open);
                    obtain.writeInt(zone);
                    obtain.writeInt(delayTime);
                    if (!this.mRemote.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onTakeVideoToggle(open, zone, delayTime);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.camera.ICameraInterface
            public boolean onSetVideoVolume(int mode) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(mode);
                    if (!this.mRemote.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onSetVideoVolume(mode);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.camera.ICameraInterface
            public boolean isFunctionSupported(int function, int zone) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(function);
                    obtain.writeInt(zone);
                    if (!this.mRemote.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isFunctionSupported(function, zone);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.camera.ICameraInterface
            public int getCameraState() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(20, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCameraState();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICameraInterface impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ICameraInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}