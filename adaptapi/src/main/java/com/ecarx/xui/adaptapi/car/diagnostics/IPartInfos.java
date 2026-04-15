package com.ecarx.xui.adaptapi.car.diagnostics;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface IPartInfos {
    public static final int PART_INFO_ECU_CORE_ASSEMBLY_NO = 1;
    public static final int PART_INFO_ECU_DELIVERY_ASSEMBLY_NO = 2;
    public static final int PART_INFO_IHU_AP_LOAD_MODULE_NO = 4;
    public static final int PART_INFO_IHU_AP_LOCAL_CONFIG_NO = 6;
    public static final int PART_INFO_IHU_POST_BUILD_NO = 7;
    public static final int PART_INFO_IHU_VP_LOAD_MODULE_NO = 3;
    public static final int PART_INFO_IHU_VP_LOCAL_CONFIG_NO = 5;

    @Retention(RetentionPolicy.SOURCE)
    public @interface PartId {
    }

    String getPartInfoString(int i);
}
