package com.geely.lib.oneosapi.schedule;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.geely.lib.oneosapi.common.ServiceBaseManager;
import com.geely.lib.oneosapi.schedule.CPCallbackInterface;
import com.geely.lib.oneosapi.schedule.ICalendarInfoAPI;
import com.geely.lib.oneosapi.schedule.bean.CPAddScheduleBean;
import com.geely.lib.oneosapi.schedule.bean.CPHolidayInfo;
import com.geely.lib.oneosapi.schedule.bean.CPScheduleInfo;
import java.util.List;

/* loaded from: classes.dex */
public class ScheduleManager implements ServiceBaseManager {
    private static final String TAG = "ScheduleManager";
    private Context mContext;
    private ICalendarInfoAPI mService;

    public ScheduleManager(Context context, IBinder binder) {
        this.mContext = context;
        this.mService = ICalendarInfoAPI.Stub.asInterface(binder);
    }

    @Override // com.geely.lib.oneosapi.common.ServiceBaseManager
    public void setService(IBinder binder) {
        this.mService = ICalendarInfoAPI.Stub.asInterface(binder);
    }

    private boolean isServiceAlive() {
        ICalendarInfoAPI iCalendarInfoAPI = this.mService;
        return iCalendarInfoAPI != null && iCalendarInfoAPI.asBinder().isBinderAlive();
    }

    public void getCalendarImportEvents(BaseCPCallbackInterface callback) {
        if (isServiceAlive()) {
            try {
                this.mService.getCalendarImportEvents(callback);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "getHolidayInfo: service is not alive");
    }

    public void getCalendarEvents(BaseCPCallbackInterface callback) {
        if (isServiceAlive()) {
            try {
                this.mService.getCalendarEvents(callback);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "getScheduleInfo: service is not alive");
    }

    public int setAddCalendarEvent(CPAddScheduleBean info) throws RemoteException {
        if (isServiceAlive()) {
            return this.mService.setAddCalendarEvent(info);
        }
        Log.d(TAG, "setAddCalendarEvent: service is not alive");
        return -1;
    }

    public void launchScheduleHome() throws RemoteException {
        if (isServiceAlive()) {
            this.mService.launchScheduleHome();
        } else {
            Log.d(TAG, "launchScheduleHome: service is not alive");
        }
    }

    public void launchAddScheduleParameter(long eventID) throws RemoteException {
        if (isServiceAlive()) {
            this.mService.launchAddScheduleParameter(eventID);
        } else {
            Log.d(TAG, "launchScheduleHome: service is not alive");
        }
    }

    public void launchAddSchedule() throws RemoteException {
        if (isServiceAlive()) {
            this.mService.launchAddSchedule();
        } else {
            Log.d(TAG, "launchScheduleHome: service is not alive");
        }
    }

    public void launchAddImportScheduleParameter(long eventID) throws RemoteException {
        if (isServiceAlive()) {
            this.mService.launchAddImportScheduleParameter(eventID);
        } else {
            Log.d(TAG, "launchAddImportSchedule: service is not alive");
        }
    }

    public void launchAddImportSchedule() throws RemoteException {
        if (isServiceAlive()) {
            this.mService.launchAddImportSchedule();
        } else {
            Log.d(TAG, "launchAddImportSchedule: service is not alive");
        }
    }

    public static abstract class BaseCPCallbackInterface extends CPCallbackInterface.Stub {
        @Override // com.geely.lib.oneosapi.schedule.CPCallbackInterface
        public void callbackCalendarImportInfo(List<CPHolidayInfo> list) {
            Log.d(ScheduleManager.TAG, "callbackHolidayInfo() called with: list = [" + list + "]");
        }

        @Override // com.geely.lib.oneosapi.schedule.CPCallbackInterface
        public void callbackCalendarEventInfo(List<CPScheduleInfo> list) {
            Log.d(ScheduleManager.TAG, "callbackScheduleInfo() called with: list = [" + list + "]");
        }
    }
}