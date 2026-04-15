package com.geely.lib.oneosapi.recommendation;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IAnalyticsManager extends IInterface {

    public static class Default implements IAnalyticsManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.recommendation.IAnalyticsManager
        public void recordContentClickBehavior(String behaviorId, String subId) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.recommendation.IAnalyticsManager
        public void recordCsdFlowClick(int listItemId, String contentId, String imageUrl, int jumpType) throws RemoteException {
        }
    }

    void recordContentClickBehavior(String behaviorId, String subId) throws RemoteException;

    void recordCsdFlowClick(int listItemId, String contentId, String imageUrl, int jumpType) throws RemoteException;

    public static abstract class Stub extends Binder implements IAnalyticsManager {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.recommendation.IAnalyticsManager";
        static final int TRANSACTION_recordContentClickBehavior = 2;
        static final int TRANSACTION_recordCsdFlowClick = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAnalyticsManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IAnalyticsManager)) {
                return (IAnalyticsManager) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                recordCsdFlowClick(data.readInt(), data.readString(), data.readString(), data.readInt());
                reply.writeNoException();
                return true;
            }
            if (code != 2) {
                if (code == 1598968902) {
                    reply.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(code, data, reply, flags);
            }
            data.enforceInterface(DESCRIPTOR);
            recordContentClickBehavior(data.readString(), data.readString());
            reply.writeNoException();
            return true;
        }

        private static class Proxy implements IAnalyticsManager {
            public static IAnalyticsManager sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.recommendation.IAnalyticsManager
            public void recordCsdFlowClick(int listItemId, String contentId, String imageUrl, int jumpType) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(listItemId);
                    obtain.writeString(contentId);
                    obtain.writeString(imageUrl);
                    obtain.writeInt(jumpType);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().recordCsdFlowClick(listItemId, contentId, imageUrl, jumpType);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.recommendation.IAnalyticsManager
            public void recordContentClickBehavior(String behaviorId, String subId) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(behaviorId);
                    obtain.writeString(subId);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().recordContentClickBehavior(behaviorId, subId);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAnalyticsManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IAnalyticsManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}