package com.ecarx.xui.adaptapi.car.diagnostics;

public interface IDiagnostics {
    IBtDebug getBtDebug();

    IDiagnosticMonitor getDiagMonitor();

    IDtcManager getDtcManager();

    IPartInfos getPartInfoManager();

    IShCommand getShCommandManager();
}
