package com.geely.lib.oneosapi.user;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface IUserInfo extends IInterface {

    public static class Default implements IUserInfo {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserInfo
        public String getAddress() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserInfo
        public int getAge() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.user.IUserInfo
        public String getAvatarURL() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserInfo
        public String getBirthday() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserInfo
        public String getCarModel() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserInfo
        public String getDateOfBirth() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserInfo
        public String getEmail() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserInfo
        public String getIsNoSecretLogin() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserInfo
        public String getLevel() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserInfo
        public String getLevelName() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserInfo
        public String getLoginAccountType() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserInfo
        public String getMobile() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserInfo
        public String getName() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserInfo
        public String getNickname() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserInfo
        public String getSex() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserInfo
        public String getUserId() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.user.IUserInfo
        public boolean isGIDUser() throws RemoteException {
            return false;
        }
    }

    String getAddress() throws RemoteException;

    int getAge() throws RemoteException;

    String getAvatarURL() throws RemoteException;

    String getBirthday() throws RemoteException;

    String getCarModel() throws RemoteException;

    String getDateOfBirth() throws RemoteException;

    String getEmail() throws RemoteException;

    String getIsNoSecretLogin() throws RemoteException;

    String getLevel() throws RemoteException;

    String getLevelName() throws RemoteException;

    String getLoginAccountType() throws RemoteException;

    String getMobile() throws RemoteException;

    String getName() throws RemoteException;

    String getNickname() throws RemoteException;

    String getSex() throws RemoteException;

    String getUserId() throws RemoteException;

    boolean isGIDUser() throws RemoteException;

    public static abstract class Stub extends Binder implements IUserInfo {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.user.IUserInfo";
        static final int TRANSACTION_getAddress = 12;
        static final int TRANSACTION_getAge = 5;
        static final int TRANSACTION_getAvatarURL = 3;
        static final int TRANSACTION_getBirthday = 15;
        static final int TRANSACTION_getCarModel = 9;
        static final int TRANSACTION_getDateOfBirth = 6;
        static final int TRANSACTION_getEmail = 14;
        static final int TRANSACTION_getIsNoSecretLogin = 17;
        static final int TRANSACTION_getLevel = 10;
        static final int TRANSACTION_getLevelName = 11;
        static final int TRANSACTION_getLoginAccountType = 16;
        static final int TRANSACTION_getMobile = 1;
        static final int TRANSACTION_getName = 2;
        static final int TRANSACTION_getNickname = 13;
        static final int TRANSACTION_getSex = 4;
        static final int TRANSACTION_getUserId = 8;
        static final int TRANSACTION_isGIDUser = 7;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IUserInfo asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IUserInfo)) {
                return (IUserInfo) queryLocalInterface;
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
                    String mobile = getMobile();
                    parcel2.writeNoException();
                    parcel2.writeString(mobile);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    String name = getName();
                    parcel2.writeNoException();
                    parcel2.writeString(name);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    String avatarURL = getAvatarURL();
                    parcel2.writeNoException();
                    parcel2.writeString(avatarURL);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    String sex = getSex();
                    parcel2.writeNoException();
                    parcel2.writeString(sex);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int age = getAge();
                    parcel2.writeNoException();
                    parcel2.writeInt(age);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    String dateOfBirth = getDateOfBirth();
                    parcel2.writeNoException();
                    parcel2.writeString(dateOfBirth);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isGIDUser = isGIDUser();
                    parcel2.writeNoException();
                    parcel2.writeInt(isGIDUser ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    String userId = getUserId();
                    parcel2.writeNoException();
                    parcel2.writeString(userId);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    String carModel = getCarModel();
                    parcel2.writeNoException();
                    parcel2.writeString(carModel);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    String level = getLevel();
                    parcel2.writeNoException();
                    parcel2.writeString(level);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    String levelName = getLevelName();
                    parcel2.writeNoException();
                    parcel2.writeString(levelName);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    String address = getAddress();
                    parcel2.writeNoException();
                    parcel2.writeString(address);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    String nickname = getNickname();
                    parcel2.writeNoException();
                    parcel2.writeString(nickname);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    String email = getEmail();
                    parcel2.writeNoException();
                    parcel2.writeString(email);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    String birthday = getBirthday();
                    parcel2.writeNoException();
                    parcel2.writeString(birthday);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    String loginAccountType = getLoginAccountType();
                    parcel2.writeNoException();
                    parcel2.writeString(loginAccountType);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    String isNoSecretLogin = getIsNoSecretLogin();
                    parcel2.writeNoException();
                    parcel2.writeString(isNoSecretLogin);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IUserInfo {
            public static IUserInfo sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.user.IUserInfo
            public String getMobile() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMobile();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserInfo
            public String getName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getName();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserInfo
            public String getAvatarURL() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvatarURL();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserInfo
            public String getSex() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSex();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserInfo
            public int getAge() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAge();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserInfo
            public String getDateOfBirth() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDateOfBirth();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserInfo
            public boolean isGIDUser() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isGIDUser();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserInfo
            public String getUserId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserId();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserInfo
            public String getCarModel() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarModel();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserInfo
            public String getLevel() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLevel();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserInfo
            public String getLevelName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLevelName();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserInfo
            public String getAddress() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAddress();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserInfo
            public String getNickname() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNickname();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserInfo
            public String getEmail() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEmail();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserInfo
            public String getBirthday() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBirthday();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserInfo
            public String getLoginAccountType() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLoginAccountType();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.user.IUserInfo
            public String getIsNoSecretLogin() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIsNoSecretLogin();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUserInfo impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IUserInfo getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}