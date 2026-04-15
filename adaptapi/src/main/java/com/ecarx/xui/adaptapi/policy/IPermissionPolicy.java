package com.ecarx.xui.adaptapi.policy;

import java.util.List;

public interface IPermissionPolicy {
    int PERMISSION_PERIOD_INVALID = 0;
    int PERMISSION_PERIOD_3_MONTHS = 1;
    int PERMISSION_PERIOD_6_MONTHS = 2;
    int PERMISSION_PERIOD_12_MONTHS = 3;

    interface IPermissionResultCallback {
        void onRequestPermissionsResult(String packageName, String[] permissionNames, int[] results);
    }

    @interface PeriodType {
    }

    // Проверка разрешения для конкретного пакета
    int checkPermission(String packageName, String permissionName);

    // Проверка разрешения для группы приложений
    int checkPermissionForApps(String packageName, String permissionName);

    // Отключает режим Permission Master для указанного разрешения
    void disablePermissionMaster(String permissionName);

    // Включает режим Permission Master для указанного разрешения на заданный период
    void enablePermissionMaster(String permissionName, int period);

    // Возвращает список имен пакетов, находящихся в черном списке
    List<String> getPackageNamesInBlacklist();

    // Возвращает период (срок действия) для режима Permission Master указанного разрешения
    int getPermissionMasterPeriod(String permissionName);

    // Возвращает оставшееся время (например, количество дней) для режима Permission Master указанного разрешения
    int getPermissionMasterRemainDay(String permissionName);

    // Возвращает список имен разрешений, относящихся к приватным функциям
    List<String> getPrivacyPermissionNameList();

    // Предоставляет (грантит) указанные разрешения для приложения
    void grantPermissionsForApp(String[] permissionNames, String packageName);

    // Проверяет, находится ли пакет в черном списке
    boolean isPackageInBlacklist(String packageName);

    // Проверяет, истекло ли действие указанного разрешения (или режима Permission Master)
    boolean isPermissionExpired(String permissionName);

    // Проверяет, включен ли режим Permission Master для указанного разрешения
    boolean isPermissionMasterEnable(String permissionName);

    // Проверяет, содержится ли указанное приватное разрешение в списке
    boolean isPrivacyPermissionInList(String permissionName);

    // Запрашивает разрешения для указанного приложения с обратным вызовом результата
    void requestPermissions(String packageName, String[] permissionNames, IPermissionResultCallback callback);

    // Отзывает указанные разрешения у приложения
    void revokePermissionsForApp(String[] permissionNames, String packageName);

    // Устанавливает период (срок действия) для режима Permission Master указанного разрешения
    void setPermissionMasterPeriod(String permissionName, int period);
}
