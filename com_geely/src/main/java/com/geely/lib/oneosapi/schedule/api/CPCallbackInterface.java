package com.geely.lib.oneosapi.schedule.api;

import com.geely.lib.oneosapi.schedule.bean.CPHolidayInfo;
import com.geely.lib.oneosapi.schedule.bean.CPScheduleInfo;
import java.util.List;

/* loaded from: classes.dex */
public interface CPCallbackInterface {
    void callbackCalendarEventInfo(List<CPScheduleInfo> list);

    void callbackCalendarImportInfo(List<CPHolidayInfo> list);
}