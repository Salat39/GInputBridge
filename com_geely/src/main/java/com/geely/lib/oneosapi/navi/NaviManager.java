package com.geely.lib.oneosapi.navi;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;

import com.geely.lib.oneosapi.common.ServiceBaseManager;
import com.geely.lib.oneosapi.navi.entitys.MapStatusTypes;
import com.geely.lib.oneosapi.navi.ipc.TransferModel;
import com.geely.lib.oneosapi.navi.model.base.NaviBaseModel;
import com.geely.lib.oneosapi.navi.model.base.NaviErrorModel;
import com.geely.lib.oneosapi.navi.model.base.NaviProtocolID;
import com.geely.lib.oneosapi.navi.model.base.NaviResultCode;
import com.geely.lib.oneosapi.navi.model.base.PoiInfo;
import com.geely.lib.oneosapi.navi.model.client.NaviRoutePlan;
import com.geely.lib.oneosapi.navi.model.client.SearchByKeyword;
import com.geely.lib.oneosapi.navi.model.client.UserGetFavoritePois;
import com.geely.lib.oneosapi.navi.model.client.UserIdBindModel;
import com.geely.lib.oneosapi.navi.model.client.UserLogoutModel;
import com.geely.lib.oneosapi.navi.model.service.RspFavoritePois;
import com.geely.lib.oneosapi.navi.model.service.RspLaunchMap;
import com.geely.lib.oneosapi.navi.model.service.RspMapStatus;
import com.geely.lib.oneosapi.navi.model.service.RspPoiSearchResult;
import com.geely.lib.oneosapi.navi.threadpool.ThreadManager;
import com.geely.lib.oneosapi.navi.utils.Contents;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class NaviManager extends NaviAPI implements ServiceBaseManager {
    private static final int MSG_FUN_LISTENER = 100;
    private static final int MSG_FUN_NOTIFY = 101;
    private static final int MSG_NAVI_ERROR_CALLBACK = 105;
    private static final int MSG_NAVI_INIT_RETRY = 104;
    private static final int MSG_NAVI_SUCCESS_CALLBACK = 106;
    private static final String TAG = "NaviManager";
    private IServiceBinder initService;
    private final Context mContext;
    private INaviEventListener mNaviEventListener;
    private INaviServer naviService;
    private List<PoiInfo> viaList;
    private final ConcurrentHashMap<Integer, INaviAPICallback> mAPICallbacks = new ConcurrentHashMap<>();
    private final Object mNaviLock = new Object();
    private int errorCode = -1;
    private int mapVendor = 10;
    private int mSearchNum = 0;
    private final INaviObserver mNaviObserver = new INaviObserver.Stub() { // from class: com.geely.lib.oneosapi.navi.NaviManager.1
        private String uuid = UUID.randomUUID().toString();

        @Override // com.geely.lib.oneosapi.navi.INaviObserver
        public String getUID() throws RemoteException {
            return this.uuid;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviObserver
        public void onNaviAPICallback(TransferModel callbackModel) throws RemoteException {
            Message obtain = Message.obtain();
            obtain.what = 100;
            obtain.obj = callbackModel;
//            NaviManager.this.mHandler.sendMessage(obtain);
        }
    };
    private final INaviEventNotify inviEventNotify = new INaviEventNotify.Stub() { // from class: com.geely.lib.oneosapi.navi.NaviManager.2
        @Override // com.geely.lib.oneosapi.navi.INaviEventNotify
        public String getUID() throws RemoteException {
            return null;
        }

        @Override // com.geely.lib.oneosapi.navi.INaviEventNotify
        public void onNaviNotify(TransferModel notifyModel) throws RemoteException {
            Message obtain = Message.obtain();
            obtain.what = 101;
            obtain.obj = notifyModel;
//            NaviManager.this.mHandler.sendMessage(obtain);
        }
    };

    static /* synthetic */ int access$2008(NaviManager naviManager) {
        int i = naviManager.mSearchNum;
        naviManager.mSearchNum = i + 1;
        return i;
    }

    private static class SyncHandler extends Handler {
        WeakReference<NaviManager> weakReference;

        private SyncHandler(NaviManager naviManager, Looper looper) {
            super(looper);
            this.weakReference = new WeakReference<>(naviManager);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NaviManager naviManager = this.weakReference.get();
            switch (msg.what) {
                case 100:
                    NaviBaseModel naviBaseModel = ((TransferModel) msg.obj).getNaviBaseModel();
                    INaviAPICallback iNaviAPICallback = (INaviAPICallback) naviManager.mAPICallbacks.remove(Integer.valueOf(naviBaseModel.getCallbackId()));
                    if (iNaviAPICallback == null) {
                        naviManager.log.m16d(NaviManager.TAG, "mAPICallbacks not find callbackId,current result callbackId is " + naviBaseModel.getCallbackId());
                        break;
                    } else if (naviBaseModel instanceof NaviErrorModel) {
                        NaviErrorModel naviErrorModel = (NaviErrorModel) naviBaseModel;
                        iNaviAPICallback.onFail(naviErrorModel);
                        naviManager.log.m16d(NaviManager.TAG, "---出参：" + naviErrorModel.toString());
                        break;
                    } else {
                        iNaviAPICallback.onSuccess(naviBaseModel);
                        naviManager.log.m16d(NaviManager.TAG, "---出参：" + naviBaseModel.toString());
                        break;
                    }
                case 101:
                    if (naviManager.mNaviEventListener != null) {
                        naviManager.mNaviEventListener.onNaviEvent(((TransferModel) msg.obj).getNaviBaseModel());
                        break;
                    }
                    break;
                case 104:
                    naviManager.connectNaviServer(naviManager.mapVendor, false, (INaviAPICallback) msg.obj);
                    break;
                case 105:
                    naviManager.errorCallback(msg.arg1, (INaviAPICallback) msg.obj);
                    break;
                case 106:
                    INaviAPICallback iNaviAPICallback2 = (INaviAPICallback) msg.obj;
                    RspLaunchMap rspLaunchMap = new RspLaunchMap();
                    rspLaunchMap.setMapLaunchedType(0);
                    iNaviAPICallback2.onSuccess(rspLaunchMap);
                    break;
            }
        }
    }

    public NaviManager(Context context, IBinder binder) {
        this.log.m16d(TAG, "NaviManager()");
        this.mContext = context;
//        this.mHandler = new SyncHandler(context.getMainLooper());
        if (binder != null) {
            this.log.m16d(TAG, "NaviManager()-->binder != null");
            this.initService = IServiceBinder.Stub.asInterface(binder);
            connectNaviServer(this.mapVendor, false, null);
        }
    }

    @Override // com.geely.lib.oneosapi.common.ServiceBaseManager
    public void setService(IBinder binder) {
        this.log.m16d(TAG, "setService()");
        if (binder != null) {
            this.log.m16d(TAG, "setService()-->binder != null");
            this.initService = IServiceBinder.Stub.asInterface(binder);
            connectNaviServer(this.mapVendor, false, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectNaviServer(int mapVendor, boolean isLaunchMap, INaviAPICallback naviAPICallback) {
        this.mapVendor = mapVendor;
        this.errorCode = -1;
        synchronized (this.mNaviLock) {
            this.naviService = null;
        }
        if (this.initService == null) {
            this.log.m16d(TAG, "connectNaviServer()---->initService == null");
            return;
        }
        try {
            this.log.m16d(TAG, this.mContext.getPackageName() + " be going to get NaviServer");
            this.log.m16d(TAG, "getNaviServer----> thread name :" + Thread.currentThread().getName());
            this.initService.getNaviServer(mapVendor, isLaunchMap, new BinderC08963(naviAPICallback));
        } catch (RemoteException e) {
            e.printStackTrace();
            synchronized (this.mNaviLock) {
                this.log.m16d(TAG, "connectNaviServer---->printStackTrace:" + this.mContext.getPackageName());
                this.naviService = null;
                Message obtain = Message.obtain();
                obtain.what = 104;
                obtain.obj = naviAPICallback;
//                this.mHandler.sendMessage(obtain);
            }
        }
    }

    /* renamed from: com.geely.lib.oneosapi.navi.NaviManager$3 */
    class BinderC08963 extends IServerCallback.Stub {
        final /* synthetic */ INaviAPICallback val$naviAPICallback;

        BinderC08963(final INaviAPICallback val$naviAPICallback) {
            this.val$naviAPICallback = val$naviAPICallback;
        }

        @Override // com.geely.lib.oneosapi.navi.IServerCallback
        public String getKey() throws RemoteException {
            return NaviManager.this.mContext.getPackageName();
        }

        @Override // com.geely.lib.oneosapi.navi.IServerCallback
        public void initSuccess(INaviServer naviservice) throws RemoteException {
            NaviManager.this.log.m16d(NaviManager.TAG, "connectNaviServer---->initSuccess() : " + getKey());
            synchronized (NaviManager.this.mNaviLock) {
                NaviManager.this.errorCode = 0;
                NaviManager.this.naviService = naviservice;
                ThreadManager.execute(new Runnable() { // from class: com.geely.lib.oneosapi.navi.-$$Lambda$NaviManager$3$o6Hfw5ob5ef4JtDEtmqh4-VuL_U
                    @Override // java.lang.Runnable
                    public final void run() {
                        NaviManager.BinderC08963.this.lambda$initSuccess$0$NaviManager$3();
                    }
                });
//                NaviManager.this.mHandler.removeMessages(104);
                if (this.val$naviAPICallback != null) {
                    Message obtain = Message.obtain();
                    obtain.what = 106;
                    obtain.obj = this.val$naviAPICallback;
//                    NaviManager.this.mHandler.sendMessage(obtain);
                }
            }
        }

        public /* synthetic */ void lambda$initSuccess$0$NaviManager$3() {
            int registerNaviNotifyObserver = NaviManager.this.registerNaviNotifyObserver();
            NaviManager.this.log.m16d(NaviManager.TAG, "initSuccess----> " + registerNaviNotifyObserver + "thread name :" + Thread.currentThread().getName());
        }

        @Override // com.geely.lib.oneosapi.navi.IServerCallback
        public void initFailed(int errorCode, String errorMessage) throws RemoteException {
            NaviManager.this.log.m16d(NaviManager.TAG, "connectNaviServer---->initFailed(),errorCode=" + errorCode + ",errorMessage=" + errorMessage + " packageName= " + getKey());
            synchronized (NaviManager.this.mNaviLock) {
                NaviManager.this.naviService = null;
                if (errorCode != 100002 && errorCode != 100003) {
                    if (errorCode == 1001) {
                        Message obtain = Message.obtain();
                        obtain.what = 105;
                        obtain.arg1 = 1001;
                        obtain.obj = this.val$naviAPICallback;
//                        NaviManager.this.mHandler.sendMessage(obtain);
                    }
                }
                RspMapStatus rspMapStatus = new RspMapStatus(new NaviBaseModel());
                rspMapStatus.setProtocolID(1001);
                rspMapStatus.setMapStatus(MapStatusTypes.APP_FINISH.getCode());
                TransferModel transferModel = new TransferModel();
                transferModel.setNaviBaseModel(rspMapStatus);
                Message obtain2 = Message.obtain();
                obtain2.what = 101;
                obtain2.obj = transferModel;
//                NaviManager.this.mHandler.sendMessage(obtain2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void errorCallback(int errorCode, INaviAPICallback naviAPICallback) {
        if (naviAPICallback == null) {
            return;
        }
        NaviErrorModel naviErrorModel = new NaviErrorModel((NaviBaseModel) null);
        naviErrorModel.setErrorCode(errorCode);
        naviErrorModel.setErrorString(NaviResultCode.getErrorMsg(errorCode));
        this.log.m16d(TAG, "errorCallback---出参:" + naviErrorModel.toString());
        naviAPICallback.onFail(naviErrorModel);
    }

    @Override // com.geely.lib.oneosapi.navi.NaviAPI
    public void release() {
        synchronized (this.mNaviLock) {
            this.naviService = null;
            this.mAPICallbacks.clear();
            this.mNaviEventListener = null;
//            this.mHandler.postDelayed(new Runnable() { // from class: com.geely.lib.oneosapi.navi.NaviManager.4
//                @Override // java.lang.Runnable
//                public void run() {
//                    NaviManager.this.removeHandlerCallbacks();
//                }
//            }, 1000L);
        }
    }

    @Override // com.geely.lib.oneosapi.navi.NaviAPI
    public int launchMap(int mapVendor, INaviAPICallback naviAPICallback) {
        if (mapVendor == 1) {
            this.log.m16d(TAG, "打开百度，通过Intent方式");
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setComponent(new ComponentName(Contents.BAIDU_PACKAGE, "com.baidu.naviauto.SlashActivity"));
//            intent.addFlags(268566528);
            this.mContext.startActivity(intent);
        } else if (mapVendor == 0) {
            this.log.m16d(TAG, "打开高德，通过Intent方式");
            Intent intent2 = new Intent("android.intent.action.MAIN");
            intent2.setComponent(new ComponentName(Contents.AMAP_PACKAGE, "com.autonavi.auto.remote.fill.UsbFillActivity"));
//            intent2.addFlags(268566528);
            this.mContext.startActivity(intent2);
        } else if (mapVendor == 6) {
            this.log.m16d(TAG, "打开吉利，通过Intent方式");
            Intent intent3 = new Intent("android.intent.action.MAIN");
            intent3.setComponent(new ComponentName("com.geely.map", "com.geely.map.activity.MainActivity"));
//            intent3.addFlags(268566528);
            this.mContext.startActivity(intent3);
        } else if (mapVendor == 10) {
            return launchMap(getDefaultMap(), naviAPICallback);
        }
        connectNaviServer(mapVendor, true, naviAPICallback);
        setDefaultMap(mapVendor);
        return 0;
    }

    public int launchMap(int mapVendor, Bundle bundle, INaviAPICallback naviAPICallback) {
        if (mapVendor == 1) {
            this.log.m16d(TAG, "打开百度，通过Intent方式");
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setComponent(new ComponentName(Contents.BAIDU_PACKAGE, "com.baidu.naviauto.SlashActivity"));
//            intent.addFlags(268566528);
            this.mContext.startActivity(intent, bundle);
        } else if (mapVendor == 0) {
            this.log.m16d(TAG, "打开高德，通过Intent方式");
            Intent intent2 = new Intent("android.intent.action.MAIN");
            intent2.setComponent(new ComponentName(Contents.AMAP_PACKAGE, "com.autonavi.auto.remote.fill.UsbFillActivity"));
//            intent2.addFlags(268566528);
            this.mContext.startActivity(intent2, bundle);
        } else if (mapVendor == 6) {
            this.log.m16d(TAG, "打开吉利，通过Intent方式");
            Intent intent3 = new Intent("android.intent.action.MAIN");
            intent3.setComponent(new ComponentName("com.geely.map", "com.geely.map.activity.MainActivity"));
//            intent3.addFlags(268566528);
            this.mContext.startActivity(intent3, bundle);
        } else if (mapVendor == 10) {
            return launchMap(getDefaultMap(), naviAPICallback);
        }
        connectNaviServer(mapVendor, true, naviAPICallback);
        setDefaultMap(mapVendor);
        return 0;
    }

    @Deprecated
    public int launchMap() {
        NaviBaseModel naviBaseModel = new NaviBaseModel();
        naviBaseModel.setProtocolID(NaviProtocolID.APP_LAUNCH_MAP);
        return invokeAPIAsync(naviBaseModel, new INaviAPICallback() { // from class: com.geely.lib.oneosapi.navi.NaviManager.5
            @Override // com.geely.lib.oneosapi.navi.INaviAPICallback
            public void onFail(NaviErrorModel naviErrorModel) {
            }

            @Override // com.geely.lib.oneosapi.navi.INaviAPICallback
            public void onSuccess(NaviBaseModel naviBaseModel2) {
            }
        });
    }

    public boolean addSurface(Surface surface, int type, int height, int width) {
        synchronized (this.mNaviLock) {
            if (this.naviService != null) {
                try {
                    return this.naviService.addSurface(surface, type, height, width);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
    }

    public boolean addSurface(Surface surface, int type, int height, int width, int roadViewHeight, int roadViewWidth) {
        synchronized (this.mNaviLock) {
            if (this.naviService != null) {
                try {
                    return this.naviService.addSurfaceWithRoadView(surface, type, height, width, roadViewHeight, roadViewWidth);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
    }

    public boolean addSurface(Surface surface, int type, int height, int width, int roadViewHeight, int roadViewWidth, int offsetX, int offsetY) {
        synchronized (this.mNaviLock) {
            if (this.naviService != null) {
                try {
                    return this.naviService.addSurfaceWithRoadViewAndOffset(surface, type, height, width, roadViewHeight, roadViewWidth, offsetX, offsetY);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
    }

    public boolean removedSurface(Surface surface, int type) {
        synchronized (this.mNaviLock) {
            if (this.naviService != null) {
                try {
                    return this.naviService.removedSurface(surface, type);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
    }

    public void dispatchTouchEvent(MotionEvent ev) {
        synchronized (this.mNaviLock) {
            if (this.naviService != null) {
                try {
                    this.naviService.dispatchTouchEvent(ev);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int setDefaultMap(int mapVendor) {
        synchronized (this.mNaviLock) {
            if (this.initService == null) {
                return -1;
            }
            try {
                return this.initService.setDefaultMap(mapVendor);
            } catch (RemoteException e) {
                e.printStackTrace();
                return -2;
            }
        }
    }

    public int getDefaultMap() {
        synchronized (this.mNaviLock) {
            if (this.initService == null) {
                return -1;
            }
            try {
                int defaultMap = this.initService.getDefaultMap();
                if (defaultMap == -100) {
                    return 1001;
                }
                return defaultMap;
            } catch (RemoteException e) {
                e.printStackTrace();
                return -2;
            }
        }
    }

    @Override // com.geely.lib.oneosapi.navi.NaviAPI
    public boolean isConnected() {
        boolean z;
        synchronized (this.mNaviLock) {
            z = this.naviService != null;
        }
        return z;
    }

    @Override // com.geely.lib.oneosapi.navi.NaviAPI
    public NaviBaseModel invokeAPISync(NaviBaseModel requestModel) {
        synchronized (this.mNaviLock) {
            if (this.naviService == null) {
                return null;
            }
            try {
                requestModel.setPackageName(this.mContext.getPackageName());
                TransferModel transferModel = new TransferModel();
                transferModel.setNaviBaseModel(requestModel);
                this.log.m16d(TAG, "---入参:" + requestModel.toString());
                return this.naviService.invokeNaviAPI(transferModel).getNaviBaseModel();
            } catch (Exception unused) {
                return null;
            }
        }
    }

    @Override // com.geely.lib.oneosapi.navi.NaviAPI
    public int invokeAPIAsync(NaviBaseModel requestModel, INaviAPICallback naviAPICallback) {
        synchronized (this.mNaviLock) {
            if (this.naviService == null) {
                errorCallback(this.errorCode, naviAPICallback);
                connectNaviServer(this.mapVendor, false, null);
                return -1;
            }
            try {
                requestModel.setPackageName(this.mContext.getPackageName());
                TransferModel transferModel = new TransferModel();
                transferModel.setNaviBaseModel(requestModel);
                if (naviAPICallback != null) {
                    this.mAPICallbacks.put(Integer.valueOf(requestModel.getCallbackId()), naviAPICallback);
                }
                this.log.m16d(TAG, "---入参:" + requestModel.toString() + "mNaviObserver:" + this.mNaviObserver.toString());
                return this.naviService.invokeNaviAPIAsync(transferModel, this.mNaviObserver);
            } catch (Exception e) {
                e.printStackTrace();
                errorCallback(-2, naviAPICallback);
                return -2;
            }
        }
    }

    @Override // com.geely.lib.oneosapi.navi.NaviAPI
    public void setNaviEventListener(INaviEventListener listener) {
        this.mNaviEventListener = listener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int registerNaviNotifyObserver() {
        INaviServer iNaviServer = this.naviService;
        if (iNaviServer == null) {
            return -1;
        }
        try {
            return iNaviServer.addNaviEventNotify(this.inviEventNotify) ? 0 : -2;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -2;
        }
    }

    private void unregisterNaviNotifyObserver() {
        synchronized (this.mNaviLock) {
            if (this.naviService != null) {
                try {
                    this.naviService.removeNaviEventNotify(this.inviEventNotify);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeHandlerCallbacks() {
//        this.mHandler.removeCallbacksAndMessages(null);
    }

    public void multiDestNavi(List<String> destList, int strategy, int action, INaviAPICallback naviAPICallback) {
        synchronized (this.mNaviLock) {
            if (destList != null) {
                if (destList.size() > 0) {
                    this.viaList = new ArrayList();
                    cycleSearchKeyword(destList, strategy, action, naviAPICallback);
                }
            }
            this.log.m16d(TAG, "目的地为null：");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cycleSearchKeyword(final List<String> destList, final int strategy, final int action, final INaviAPICallback naviAPICallback) {
        UserGetFavoritePois userGetFavoritePois;
        if (!"HOME".equals(destList.get(this.mSearchNum)) && !"COMPANY".equals(destList.get(this.mSearchNum))) {
            SearchByKeyword searchByKeyword = new SearchByKeyword(0, destList.get(this.mSearchNum), "");
            searchByKeyword.setAction(0);
            searchByKeyword.setSortRule(0);
            searchByKeyword(searchByKeyword, new INaviAPICallback() { // from class: com.geely.lib.oneosapi.navi.NaviManager.6
                @Override // com.geely.lib.oneosapi.navi.INaviAPICallback
                public void onSuccess(NaviBaseModel naviBaseModel) {
                    NaviManager.this.log.m16d(NaviManager.TAG, "multiDestNavi---searchByKeyword---onSuccess：" + naviBaseModel.toString());
                    NaviManager.access$2008(NaviManager.this);
                    if (NaviManager.this.mSearchNum != destList.size()) {
                        RspPoiSearchResult rspPoiSearchResult = (RspPoiSearchResult) naviBaseModel;
                        PoiInfo poiInfo = new PoiInfo();
                        poiInfo.setLatLng(rspPoiSearchResult.getPOIList().get(0).getLatLng());
                        poiInfo.setName(rspPoiSearchResult.getPOIList().get(0).getName());
                        NaviManager.this.viaList.add(poiInfo);
                        NaviManager.this.cycleSearchKeyword(destList, strategy, action, naviAPICallback);
                        return;
                    }
                    NaviRoutePlan naviRoutePlan = new NaviRoutePlan(null, ((RspPoiSearchResult) naviBaseModel).getPOIList().get(0));
                    naviRoutePlan.setViaPoiInfos(NaviManager.this.viaList);
                    naviRoutePlan.setStrategy(strategy);
                    naviRoutePlan.setAction(action);
                    NaviManager.this.invokeAPIAsync(naviRoutePlan, naviAPICallback);
                    NaviManager.this.mSearchNum = 0;
                    NaviManager.this.viaList = null;
                }

                @Override // com.geely.lib.oneosapi.navi.INaviAPICallback
                public void onFail(NaviErrorModel naviErrorModel) {
                    NaviManager.this.log.m16d(NaviManager.TAG, "multiDestNavi---searchByKeyword---onFail：" + naviErrorModel.toString());
                    NaviManager.this.mSearchNum = 0;
                    NaviManager.this.viaList = null;
                    naviAPICallback.onFail(naviErrorModel);
                }
            });
            return;
        }
        if ("HOME".equals(destList.get(this.mSearchNum))) {
            userGetFavoritePois = new UserGetFavoritePois(1);
        } else {
            userGetFavoritePois = new UserGetFavoritePois(2);
        }
        getFavoritePois(userGetFavoritePois, new INaviAPICallback() { // from class: com.geely.lib.oneosapi.navi.NaviManager.7
            @Override // com.geely.lib.oneosapi.navi.INaviAPICallback
            public void onSuccess(NaviBaseModel naviBaseModel) {
                NaviManager.this.log.m16d(NaviManager.TAG, "multiDestNavi---getFavoritePois---onSuccess：" + naviBaseModel.toString());
                NaviManager.access$2008(NaviManager.this);
                if (NaviManager.this.mSearchNum != destList.size()) {
                    RspFavoritePois rspFavoritePois = (RspFavoritePois) naviBaseModel;
                    PoiInfo poiInfo = new PoiInfo();
                    poiInfo.setLatLng(rspFavoritePois.getFavoritePoiInfoList().get(0).getPoiInfo().getLatLng());
                    poiInfo.setName(rspFavoritePois.getFavoritePoiInfoList().get(0).getPoiInfo().getName());
                    NaviManager.this.viaList.add(poiInfo);
                    NaviManager.this.cycleSearchKeyword(destList, strategy, action, naviAPICallback);
                    return;
                }
                NaviRoutePlan naviRoutePlan = new NaviRoutePlan(null, ((RspFavoritePois) naviBaseModel).getFavoritePoiInfoList().get(0).getPoiInfo());
                naviRoutePlan.setViaPoiInfos(NaviManager.this.viaList);
                naviRoutePlan.setStrategy(strategy);
                naviRoutePlan.setAction(action);
                NaviManager.this.invokeAPIAsync(naviRoutePlan, naviAPICallback);
                NaviManager.this.mSearchNum = 0;
                NaviManager.this.viaList = null;
            }

            @Override // com.geely.lib.oneosapi.navi.INaviAPICallback
            public void onFail(NaviErrorModel naviErrorModel) {
                NaviManager.this.log.m16d(NaviManager.TAG, "multiDestNavi---getFavoritePois---onFail：" + naviErrorModel.toString());
                NaviManager.this.mSearchNum = 0;
                NaviManager.this.viaList = null;
                naviAPICallback.onFail(naviErrorModel);
            }
        });
    }

    public String getCurrectConnectMap() {
        synchronized (this.mNaviLock) {
            if (this.naviService != null) {
                try {
                    return this.naviService.getPackageName();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            return "";
        }
    }

    @Override // com.geely.lib.oneosapi.navi.NaviAPI
    public int bindUserIdToAMap(final UserIdBindModel userIdBindModel, final INaviAPICallback naviAPICallback) {
        String currectConnectMap = getCurrectConnectMap();
        if (currectConnectMap.isEmpty()) {
            return -1;
        }
        if (Contents.AMAP_PACKAGE.equals(currectConnectMap)) {
            return super.bindUserIdToAMap(userIdBindModel, naviAPICallback);
        }
        cancelNavi(new INaviAPICallback() { // from class: com.geely.lib.oneosapi.navi.NaviManager.8
            @Override // com.geely.lib.oneosapi.navi.INaviAPICallback
            public void onSuccess(NaviBaseModel naviBaseModel) {
                Log.d(NaviManager.TAG, "结束导航成功:" + naviBaseModel.toString());
                NaviManager.this.switchMapServerForBindAMap(userIdBindModel, naviAPICallback);
            }

            @Override // com.geely.lib.oneosapi.navi.INaviAPICallback
            public void onFail(NaviErrorModel naviErrorModel) {
                Log.d(NaviManager.TAG, "当前不在导航:" + naviErrorModel.toString());
                NaviManager.this.switchMapServerForBindAMap(userIdBindModel, naviAPICallback);
            }
        });
        return 0;
    }

    @Override // com.geely.lib.oneosapi.navi.NaviAPI
    public int logoutUserIdToAMap(final UserLogoutModel userLogoutModel, final INaviAPICallback naviAPICallback) {
        String currectConnectMap = getCurrectConnectMap();
        if (currectConnectMap.isEmpty()) {
            return -1;
        }
        if (Contents.AMAP_PACKAGE.equals(currectConnectMap)) {
            return super.logoutUserIdToAMap(userLogoutModel, naviAPICallback);
        }
        cancelNavi(new INaviAPICallback() { // from class: com.geely.lib.oneosapi.navi.NaviManager.9
            @Override // com.geely.lib.oneosapi.navi.INaviAPICallback
            public void onSuccess(NaviBaseModel naviBaseModel) {
                Log.d(NaviManager.TAG, "结束导航成功:" + naviBaseModel.toString());
                NaviManager.this.switchMapServerForLogoutAMap(userLogoutModel, naviAPICallback);
            }

            @Override // com.geely.lib.oneosapi.navi.INaviAPICallback
            public void onFail(NaviErrorModel naviErrorModel) {
                Log.d(NaviManager.TAG, "当前不在导航:" + naviErrorModel.toString());
                NaviManager.this.switchMapServerForLogoutAMap(userLogoutModel, naviAPICallback);
            }
        });
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchMapServerForBindAMap(final UserIdBindModel userIdBindModel, final INaviAPICallback naviAPICallback) {
        connectNaviServer(0, false, new INaviAPICallback() { // from class: com.geely.lib.oneosapi.navi.NaviManager.10
            @Override // com.geely.lib.oneosapi.navi.INaviAPICallback
            public void onSuccess(NaviBaseModel naviBaseModel) {
                NaviManager.this.log.m16d(NaviManager.TAG, "switch map success");
                NaviManager.super.bindUserIdToAMap(userIdBindModel, naviAPICallback);
            }

            @Override // com.geely.lib.oneosapi.navi.INaviAPICallback
            public void onFail(NaviErrorModel naviErrorModel) {
                NaviManager.this.log.m16d(NaviManager.TAG, "switch map failed");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchMapServerForLogoutAMap(final UserLogoutModel userLogoutModel, final INaviAPICallback naviAPICallback) {
        connectNaviServer(0, false, new INaviAPICallback() { // from class: com.geely.lib.oneosapi.navi.NaviManager.11
            @Override // com.geely.lib.oneosapi.navi.INaviAPICallback
            public void onSuccess(NaviBaseModel naviBaseModel) {
                NaviManager.this.log.m16d(NaviManager.TAG, "switch map success");
                NaviManager.super.logoutUserIdToAMap(userLogoutModel, naviAPICallback);
            }

            @Override // com.geely.lib.oneosapi.navi.INaviAPICallback
            public void onFail(NaviErrorModel naviErrorModel) {
                NaviManager.this.log.m16d(NaviManager.TAG, "switch map failed");
            }
        });
    }
}