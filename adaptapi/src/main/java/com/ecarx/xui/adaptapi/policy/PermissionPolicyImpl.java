package com.ecarx.xui.adaptapi.policy;

import android.content.Context;

import java.util.List;

/* loaded from: classes.dex */
public class PermissionPolicyImpl implements IPermissionPolicy {

    public PermissionPolicyImpl(Context context) {
    }

    @Override
    public void enablePermissionMaster(String permissionName, int period) {
    }

    @Override
    public void setPermissionMasterPeriod(String permissionName, int period) {
    }

    @Override
    public int getPermissionMasterPeriod(String permissionName) {
        return 0;
    }

    @Override
    public int getPermissionMasterRemainDay(String permissionName) {
        return -1;
    }

    @Override
    public void disablePermissionMaster(String permissionName) {
    }

    @Override
    public boolean isPermissionMasterEnable(String permissionName) {
        return false;
    }

    @Override
    public void grantPermissionsForApp(String[] permissionNames, String packageName) {
    }

    @Override
    public void revokePermissionsForApp(String[] permissionNames, String packageName) {
    }

    @Override
    public List<String> getPackageNamesInBlacklist() {
        return null;
    }

    @Override
    public List<String> getPrivacyPermissionNameList() {
        return null;
    }

    @Override
    public boolean isPackageInBlacklist(String packageName) {
        return false;
    }

    @Override
    public boolean isPrivacyPermissionInList(String permissionName) {
        return false;
    }

    @Override
    public int checkPermission(String packageName, String permissionName) {
        return -1;
    }

    @Override
    public int checkPermissionForApps(String packageName, String permissionName) {
        return -1;
    }

    @Override
    public boolean isPermissionExpired(String permissionName) {
        return false;
    }

    @Override
    public void requestPermissions(String packageName, String[] permissionNames, final IPermissionPolicy.IPermissionResultCallback permissionResultCallback) {
    }
}
