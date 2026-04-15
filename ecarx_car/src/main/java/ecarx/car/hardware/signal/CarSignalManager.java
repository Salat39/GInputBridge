package ecarx.car.hardware.signal;

import ecarx.car.hardware.ECarXCarPropertyValue;
import ecarx.car.hardware.annotation.ApiResult;

public class CarSignalManager {

    public int getAirVolDec() {
        return 0;
    }

    public int getAirVolInc() {
        return 0;
    }

    public int getAlrmStsAlrmSt() {
        return 0;
    }

    public float getAmbTEstimdAmbTEstimd() {
        return 0.0f;
    }

    public float getAmbTRawAmbTVal() {
        return 0.0f;
    }

    public int getAsyLineLeColor() {
        return 0;
    }

    public int getBtn2ForUsrSwtPanFrntReq() {
        return 0;
    }

    public int getBtn5ForUsrSwtPanFrntReq() {
        return 0;
    }

    public int getBtn7() {
        return 0;
    }

    public int getBtn8() {
        return 0;
    }

    public int getBtn9() {
        return 0;
    }

    public void registerCallback(CarSignalEventCallback callback, SignalFilter filter) {
    }

    public void unregisterCallback(CarSignalEventCallback callback) {
    }

    public void setAskLockStsOfPrsntRecOfVehSurrndgsVinRec(Object value) {
    }

    public ApiResult setAutPrkgSlotNrReq(int value) {
        return null;
    }

    public ApiResult setChrgStationPwr(int value) {
        return null;
    }

    public ApiResult setCnclFaceReq(int value) {
        return null;
    }

    public void setCnclFaceReqGid1(Object value) {
    }

    public void setCnclFaceReqGid2(Object value) {
    }

    public ApiResult setCurrentRoadCongstionPassTime(int value) {
        return null;
    }

    public ApiResult setCurrentRoadcongestionLength(int value) {
        return null;
    }

    public ApiResult setCurrentRoadcongestionLvl(int value) {
        return null;
    }

    public void setDestinationPOI(Object value) {
    }

    public ApiResult setDrvrAsscSysBtnPush(int value) {
        return null;
    }

    public ApiResult setDrvrAsscSysParkMod(int value) {
        return null;
    }

    public ApiResult setDstToDestination(int value) {
        return null;
    }

    public ApiResult setDstToNextCongestionRrd(int value) {
        return null;
    }

    public void setFaceGid1(Object value) {
    }

    public void setFaceGid2(Object value) {
    }

    public ApiResult setFaceSgnInReq(int value) {
        return null;
    }

    public ApiResult setGlvBoxHMIReq(int value) {
        return null;
    }

    public ApiResult setHmiCarLocatorSetReq(int value) {
        return null;
    }

    public ApiResult setHmiResetTcamReq(int value) {
        return null;
    }

    public void setHznData(Object value) {
    }

    public void setHznEdge(Object value) {
    }

    public void setHznPosn(Object value) {
    }

    public void setHznPosnLR(Object value) {
    }

    public void setHznProfLong(Object value) {
    }

    public void setHznProfLongLR(Object value) {
    }

    public void setHznProfSho(Object value) {
    }

    public void setHznSeg(Object value) {
    }

    public ApiResult setHznSplyElectcSts(int value) {
        return null;
    }

    public ApiResult setLockgPrsnlReqFromHmi(int value) {
        return null;
    }

    public ApiResult setNextRoadCongestionLen(int value) {
        return null;
    }

    public ApiResult setNextRoadCongestionPassTi(int value) {
        return null;
    }

    public ApiResult setNextRoadcongestionLvl(int value) {
        return null;
    }

    public ApiResult setObstclTrigReq(int value) {
        return null;
    }

    public ApiResult setPlannedPahDe(int value) {
        return null;
    }

    public ApiResult setPrkgDstCtrlSysSwt(int value) {
        return null;
    }

    public ApiResult setPrkgIntrptReldBtn(int value) {
        return null;
    }

    public ApiResult setSIAutRstTripCompterSet(int value) {
        return null;
    }

    public ApiResult setSIRstMntnMilg(int value) {
        return null;
    }

    public ApiResult setSceneModSeld(int value) {
        return null;
    }

    public void setTiToDestNavRoute(Object value) {
    }

    public ApiResult setTypToDestNavRoute(int value) {
        return null;
    }

    public ApiResult setVisnImgDispModReq(int value) {
        return null;
    }

    public interface CarSignalEventCallback {
        void onChangeEvent(ECarXCarPropertyValue eCarXCarPropertyValue);

        void onErrorEvent(int i, int i2);
    }
}
