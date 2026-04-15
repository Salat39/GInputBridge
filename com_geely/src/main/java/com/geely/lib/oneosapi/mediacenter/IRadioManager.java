package com.geely.lib.oneosapi.mediacenter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.geely.lib.oneosapi.mediacenter.bean.Frequency;
import com.geely.lib.oneosapi.mediacenter.listener.IRadioDiagCallback;
import com.geely.lib.oneosapi.mediacenter.listener.IRadioStateListener;
import java.util.List;

/* loaded from: classes.dex */
public interface IRadioManager extends IInterface {

    public static class Default implements IRadioManager {
        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public void addAnnouncementListener() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public boolean addCollectionStation(Frequency frequency, boolean ifdelete) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public boolean closeRadio(IRadioStateListener cb) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public int getBand() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public List<Frequency> getCollectionStationsList() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public Frequency getCurrentFrequency(int band) throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public int[] getDABAnnouncementStatus() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public String getDABDiagSelector() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public String getDABIconFilePath(String serviceName, String ensembleName, int iconId, int frequency) throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public int getFrequency() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public List<Frequency> getRadioStationsNameList() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public int getRadioStatus() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public List<Frequency> getScanStationsList() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public boolean getSettingSwitchStatus(String tag) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public boolean isFirstUseRadio() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public boolean openPsdRadioAsync(IRadioStateListener cb) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public boolean openRadioAsync(IRadioStateListener cb) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public boolean pause() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public boolean play() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public int playRadioFavor() throws RemoteException {
            return 0;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public void removeAnnouncementListener() throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public void saveDABAnnouncementStatus(int[] enabledAnnouncementTypes) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public void saveSettingSwitchStatus(String tag, boolean isOpen) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public boolean scanAsync() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public boolean seekAsync(int direction) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public boolean setBandAsync(int newBand) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public void setDabSeamlessSwitch(boolean isEnble) throws RemoteException {
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public boolean setFrequency(int frequency) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public boolean setParameters(String key, String value, IRadioDiagCallback cb) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public boolean startCarouselPlay(int time) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public boolean stopCarouselPlay() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public boolean stopSeekOrScanAsync() throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public boolean toNextStation(int direction) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public boolean tuneAsync(int direction) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public boolean tuneDABDiagSelector(int dabFeq, int serviceId) throws RemoteException {
            return false;
        }

        @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
        public boolean tuneFrequency(Frequency frequency) throws RemoteException {
            return false;
        }
    }

    void addAnnouncementListener() throws RemoteException;

    boolean addCollectionStation(Frequency frequency, boolean ifdelete) throws RemoteException;

    boolean closeRadio(IRadioStateListener cb) throws RemoteException;

    int getBand() throws RemoteException;

    List<Frequency> getCollectionStationsList() throws RemoteException;

    Frequency getCurrentFrequency(int band) throws RemoteException;

    int[] getDABAnnouncementStatus() throws RemoteException;

    String getDABDiagSelector() throws RemoteException;

    String getDABIconFilePath(String serviceName, String ensembleName, int iconId, int frequency) throws RemoteException;

    int getFrequency() throws RemoteException;

    List<Frequency> getRadioStationsNameList() throws RemoteException;

    int getRadioStatus() throws RemoteException;

    List<Frequency> getScanStationsList() throws RemoteException;

    boolean getSettingSwitchStatus(String tag) throws RemoteException;

    boolean isFirstUseRadio() throws RemoteException;

    boolean openPsdRadioAsync(IRadioStateListener cb) throws RemoteException;

    boolean openRadioAsync(IRadioStateListener cb) throws RemoteException;

    boolean pause() throws RemoteException;

    boolean play() throws RemoteException;

    int playRadioFavor() throws RemoteException;

    void removeAnnouncementListener() throws RemoteException;

    void saveDABAnnouncementStatus(int[] enabledAnnouncementTypes) throws RemoteException;

    void saveSettingSwitchStatus(String tag, boolean isOpen) throws RemoteException;

    boolean scanAsync() throws RemoteException;

    boolean seekAsync(int direction) throws RemoteException;

    boolean setBandAsync(int newBand) throws RemoteException;

    void setDabSeamlessSwitch(boolean isEnble) throws RemoteException;

    boolean setFrequency(int frequency) throws RemoteException;

    boolean setParameters(String key, String value, IRadioDiagCallback cb) throws RemoteException;

    boolean startCarouselPlay(int time) throws RemoteException;

    boolean stopCarouselPlay() throws RemoteException;

    boolean stopSeekOrScanAsync() throws RemoteException;

    boolean toNextStation(int direction) throws RemoteException;

    boolean tuneAsync(int direction) throws RemoteException;

    boolean tuneDABDiagSelector(int dabFeq, int serviceId) throws RemoteException;

    boolean tuneFrequency(Frequency frequency) throws RemoteException;

    public static abstract class Stub extends Binder implements IRadioManager {
        private static final String DESCRIPTOR = "com.geely.lib.oneosapi.mediacenter.IRadioManager";
        static final int TRANSACTION_addAnnouncementListener = 26;
        static final int TRANSACTION_addCollectionStation = 20;
        static final int TRANSACTION_closeRadio = 2;
        static final int TRANSACTION_getBand = 12;
        static final int TRANSACTION_getCollectionStationsList = 16;
        static final int TRANSACTION_getCurrentFrequency = 30;
        static final int TRANSACTION_getDABAnnouncementStatus = 25;
        static final int TRANSACTION_getDABDiagSelector = 36;
        static final int TRANSACTION_getDABIconFilePath = 29;
        static final int TRANSACTION_getFrequency = 13;
        static final int TRANSACTION_getRadioStationsNameList = 17;
        static final int TRANSACTION_getRadioStatus = 21;
        static final int TRANSACTION_getScanStationsList = 14;
        static final int TRANSACTION_getSettingSwitchStatus = 33;
        static final int TRANSACTION_isFirstUseRadio = 15;
        static final int TRANSACTION_openPsdRadioAsync = 23;
        static final int TRANSACTION_openRadioAsync = 1;
        static final int TRANSACTION_pause = 19;
        static final int TRANSACTION_play = 18;
        static final int TRANSACTION_playRadioFavor = 22;
        static final int TRANSACTION_removeAnnouncementListener = 27;
        static final int TRANSACTION_saveDABAnnouncementStatus = 24;
        static final int TRANSACTION_saveSettingSwitchStatus = 32;
        static final int TRANSACTION_scanAsync = 6;
        static final int TRANSACTION_seekAsync = 5;
        static final int TRANSACTION_setBandAsync = 3;
        static final int TRANSACTION_setDabSeamlessSwitch = 28;
        static final int TRANSACTION_setFrequency = 4;
        static final int TRANSACTION_setParameters = 34;
        static final int TRANSACTION_startCarouselPlay = 10;
        static final int TRANSACTION_stopCarouselPlay = 11;
        static final int TRANSACTION_stopSeekOrScanAsync = 7;
        static final int TRANSACTION_toNextStation = 9;
        static final int TRANSACTION_tuneAsync = 8;
        static final int TRANSACTION_tuneDABDiagSelector = 35;
        static final int TRANSACTION_tuneFrequency = 31;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRadioManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRadioManager)) {
                return (IRadioManager) queryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            Frequency createFromParcel;
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean openRadioAsync = openRadioAsync(IRadioStateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(openRadioAsync ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean closeRadio = closeRadio(IRadioStateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(closeRadio ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean bandAsync = setBandAsync(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(bandAsync ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean frequency = setFrequency(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(frequency ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean seekAsync = seekAsync(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(seekAsync ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean scanAsync = scanAsync();
                    parcel2.writeNoException();
                    parcel2.writeInt(scanAsync ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean stopSeekOrScanAsync = stopSeekOrScanAsync();
                    parcel2.writeNoException();
                    parcel2.writeInt(stopSeekOrScanAsync ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean tuneAsync = tuneAsync(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(tuneAsync ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean nextStation = toNextStation(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(nextStation ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean startCarouselPlay = startCarouselPlay(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(startCarouselPlay ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean stopCarouselPlay = stopCarouselPlay();
                    parcel2.writeNoException();
                    parcel2.writeInt(stopCarouselPlay ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    int band = getBand();
                    parcel2.writeNoException();
                    parcel2.writeInt(band);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    int frequency2 = getFrequency();
                    parcel2.writeNoException();
                    parcel2.writeInt(frequency2);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<Frequency> scanStationsList = getScanStationsList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(scanStationsList);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isFirstUseRadio = isFirstUseRadio();
                    parcel2.writeNoException();
                    parcel2.writeInt(isFirstUseRadio ? 1 : 0);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<Frequency> collectionStationsList = getCollectionStationsList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(collectionStationsList);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<Frequency> radioStationsNameList = getRadioStationsNameList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(radioStationsNameList);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean play = play();
                    parcel2.writeNoException();
                    parcel2.writeInt(play ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean pause = pause();
                    parcel2.writeNoException();
                    parcel2.writeInt(pause ? 1 : 0);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    createFromParcel = parcel.readInt() != 0 ? Frequency.CREATOR.createFromParcel(parcel) : null;
                    boolean addCollectionStation = addCollectionStation(createFromParcel, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(addCollectionStation ? 1 : 0);
                    if (createFromParcel != null) {
                        parcel2.writeInt(1);
                        createFromParcel.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    int radioStatus = getRadioStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(radioStatus);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    int playRadioFavor = playRadioFavor();
                    parcel2.writeNoException();
                    parcel2.writeInt(playRadioFavor);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean openPsdRadioAsync = openPsdRadioAsync(IRadioStateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(openPsdRadioAsync ? 1 : 0);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] createIntArray = parcel.createIntArray();
                    saveDABAnnouncementStatus(createIntArray);
                    parcel2.writeNoException();
                    parcel2.writeIntArray(createIntArray);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] dABAnnouncementStatus = getDABAnnouncementStatus();
                    parcel2.writeNoException();
                    parcel2.writeIntArray(dABAnnouncementStatus);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    addAnnouncementListener();
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeAnnouncementListener();
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDabSeamlessSwitch(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    String dABIconFilePath = getDABIconFilePath(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeString(dABIconFilePath);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    Frequency currentFrequency = getCurrentFrequency(parcel.readInt());
                    parcel2.writeNoException();
                    if (currentFrequency != null) {
                        parcel2.writeInt(1);
                        currentFrequency.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    createFromParcel = parcel.readInt() != 0 ? Frequency.CREATOR.createFromParcel(parcel) : null;
                    boolean tuneFrequency = tuneFrequency(createFromParcel);
                    parcel2.writeNoException();
                    parcel2.writeInt(tuneFrequency ? 1 : 0);
                    if (createFromParcel != null) {
                        parcel2.writeInt(1);
                        createFromParcel.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    saveSettingSwitchStatus(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean settingSwitchStatus = getSettingSwitchStatus(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(settingSwitchStatus ? 1 : 0);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean parameters = setParameters(parcel.readString(), parcel.readString(), IRadioDiagCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(parameters ? 1 : 0);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean tuneDABDiagSelector = tuneDABDiagSelector(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(tuneDABDiagSelector ? 1 : 0);
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    String dABDiagSelector = getDABDiagSelector();
                    parcel2.writeNoException();
                    parcel2.writeString(dABDiagSelector);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IRadioManager {
            public static IRadioManager sDefaultImpl;
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

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public boolean openRadioAsync(IRadioStateListener cb) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openRadioAsync(cb);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public boolean closeRadio(IRadioStateListener cb) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().closeRadio(cb);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public boolean setBandAsync(int newBand) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(newBand);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setBandAsync(newBand);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public boolean setFrequency(int frequency) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(frequency);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setFrequency(frequency);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public boolean seekAsync(int direction) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(direction);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().seekAsync(direction);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public boolean scanAsync() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().scanAsync();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public boolean stopSeekOrScanAsync() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopSeekOrScanAsync();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public boolean tuneAsync(int direction) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(direction);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().tuneAsync(direction);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public boolean toNextStation(int direction) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(direction);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().toNextStation(direction);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public boolean startCarouselPlay(int time) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(time);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startCarouselPlay(time);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public boolean stopCarouselPlay() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopCarouselPlay();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public int getBand() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBand();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public int getFrequency() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFrequency();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public List<Frequency> getScanStationsList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getScanStationsList();
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(Frequency.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public boolean isFirstUseRadio() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isFirstUseRadio();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public List<Frequency> getCollectionStationsList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCollectionStationsList();
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(Frequency.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public List<Frequency> getRadioStationsNameList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRadioStationsNameList();
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(Frequency.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public boolean play() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().play();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public boolean pause() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().pause();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public boolean addCollectionStation(Frequency frequency, boolean ifdelete) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (frequency != null) {
                        obtain.writeInt(1);
                        frequency.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(ifdelete ? 1 : 0);
                    if (!this.mRemote.transact(20, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addCollectionStation(frequency, ifdelete);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    if (obtain2.readInt() != 0) {
                        frequency.readFromParcel(obtain2);
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public int getRadioStatus() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(21, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRadioStatus();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public int playRadioFavor() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(22, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().playRadioFavor();
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public boolean openPsdRadioAsync(IRadioStateListener cb) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    if (!this.mRemote.transact(23, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openPsdRadioAsync(cb);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public void saveDABAnnouncementStatus(int[] enabledAnnouncementTypes) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeIntArray(enabledAnnouncementTypes);
                    if (!this.mRemote.transact(24, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().saveDABAnnouncementStatus(enabledAnnouncementTypes);
                    } else {
                        obtain2.readException();
                        obtain2.readIntArray(enabledAnnouncementTypes);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public int[] getDABAnnouncementStatus() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(25, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDABAnnouncementStatus();
                    }
                    obtain2.readException();
                    return obtain2.createIntArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public void addAnnouncementListener() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(26, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addAnnouncementListener();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public void removeAnnouncementListener() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(27, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeAnnouncementListener();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public void setDabSeamlessSwitch(boolean isEnble) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(isEnble ? 1 : 0);
                    if (!this.mRemote.transact(28, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDabSeamlessSwitch(isEnble);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public String getDABIconFilePath(String serviceName, String ensembleName, int iconId, int frequency) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(serviceName);
                    obtain.writeString(ensembleName);
                    obtain.writeInt(iconId);
                    obtain.writeInt(frequency);
                    if (!this.mRemote.transact(29, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDABIconFilePath(serviceName, ensembleName, iconId, frequency);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public Frequency getCurrentFrequency(int band) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(band);
                    if (!this.mRemote.transact(30, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentFrequency(band);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? Frequency.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public boolean tuneFrequency(Frequency frequency) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (frequency != null) {
                        obtain.writeInt(1);
                        frequency.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(31, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().tuneFrequency(frequency);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    if (obtain2.readInt() != 0) {
                        frequency.readFromParcel(obtain2);
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public void saveSettingSwitchStatus(String tag, boolean isOpen) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(tag);
                    obtain.writeInt(isOpen ? 1 : 0);
                    if (!this.mRemote.transact(32, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().saveSettingSwitchStatus(tag, isOpen);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public boolean getSettingSwitchStatus(String tag) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(tag);
                    if (!this.mRemote.transact(33, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSettingSwitchStatus(tag);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public boolean setParameters(String key, String value, IRadioDiagCallback cb) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(key);
                    obtain.writeString(value);
                    obtain.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    if (!this.mRemote.transact(34, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setParameters(key, value, cb);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public boolean tuneDABDiagSelector(int dabFeq, int serviceId) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(dabFeq);
                    obtain.writeInt(serviceId);
                    if (!this.mRemote.transact(35, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().tuneDABDiagSelector(dabFeq, serviceId);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.geely.lib.oneosapi.mediacenter.IRadioManager
            public String getDABDiagSelector() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(36, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDABDiagSelector();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRadioManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IRadioManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}