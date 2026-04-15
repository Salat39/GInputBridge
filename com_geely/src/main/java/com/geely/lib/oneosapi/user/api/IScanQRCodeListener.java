package com.geely.lib.oneosapi.user.api;

/* loaded from: classes.dex */
public interface IScanQRCodeListener {
    void getQrCodeStatus(String status, String info);

    void scanQrCodeStatus(String status, String info);
}