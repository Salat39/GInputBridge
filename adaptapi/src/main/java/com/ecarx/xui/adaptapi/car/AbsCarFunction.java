package com.ecarx.xui.adaptapi.car;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.SparseArray;

import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.car.base.ICarFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

import ecarx.car.hardware.ECarXCarPropertyValue;
import ecarx.car.hardware.annotation.ApiResult;
import ecarx.car.hardware.signal.SignalFilter;
import ecarx.car.hardware.vehicle.CarPAEventCallback;
import ecarx.car.hardware.vehicle.ECarXCarSetManager;

/* loaded from: classes.dex */
public abstract class AbsCarFunction extends AbsVehicle {
    private static final int ALL_FUNCTION_ID = -1;
    private static final int[] EMPTY_SUPPORT_FUNCTION_VALUE = new int[0];
    private static final int REGISTER_TASK = 1;
    private static final String TAG = "AbsCarFunction";
    private final SparseArray<List<IVehicleFunction.IAssociatedStatus<?>>> groupAssociated;
    private boolean isBuildFunctions;
    private final SparseArray<ArrayList<ICarFunction.IFunctionValueWatcher>> mCallbackArray;
    private final Object mCallbackLock;
    private final List<VehicleFunction<Float>> mFloatFunctions;
    private final SparseArray<VehicleFunction<Float>> mFunctionIdToCustomFunction;
    private final SparseArray<VehicleFunction<Integer>> mFunctionIdToIntFunction;
    private final List<VehicleFunction<Integer>> mIntFunctions;
    private final SparseArray<List<VehicleFunction<Float>>> mPropertyToCustomFunction;
    private final SparseArray<List<VehicleFunction<Integer>>> mPropertyToIntFunction;
    private final Handler mRegisterHandler;

    protected abstract void buildFunctions();

    protected abstract void onCarSignalConnected(ECarXCarSetManager eCarXCarSetManager);

    protected AbsCarFunction(Context context, int i) {
        super(context, i);
        this.mIntFunctions = new ArrayList(30);
        this.mFloatFunctions = new ArrayList(30);
        this.mFunctionIdToIntFunction = new SparseArray<>();
        this.mFunctionIdToCustomFunction = new SparseArray<>();
        this.mPropertyToIntFunction = new SparseArray<>();
        this.mPropertyToCustomFunction = new SparseArray<>();
        this.groupAssociated = new SparseArray<>();
        this.isBuildFunctions = false;
        this.mCallbackLock = new Object();
        SparseArray<ArrayList<ICarFunction.IFunctionValueWatcher>> sparseArray = new SparseArray<>();
        this.mCallbackArray = sparseArray;
        sparseArray.put(-1, new ArrayList<>());
        this.mRegisterHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$AbsCarFunction$QyLthPn5m9B6qVowO7LfTCs4zm0
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return false;
            }
        });
    }

    public /* synthetic */ boolean lambda$new$0$AbsCarFunction(Message message) {

        return true;
    }

    @Override // com.ecarx.xui.adaptapi.AbsCarSignal
    protected final void onInitCarSignalManager() {
        if (this.isBuildFunctions) {
            return;
        }
        this.isBuildFunctions = true;
        buildFunctions();
        addSignalFilter(557871781);
        addSignalFilter(557871787);
    }

    @Override // com.ecarx.xui.adaptapi.car.AbsVehicle
    public boolean validCarFunction(int i) {
        return validCarFunctionInt(i) || validCarFunctionFlt(i);
    }

    @Override // com.ecarx.xui.adaptapi.car.AbsVehicle
    public boolean validCarFunctionInt(int i) {
        return this.mFunctionIdToIntFunction.indexOfKey(i) >= 0;
    }

    @Override // com.ecarx.xui.adaptapi.car.AbsVehicle
    public boolean validCarFunctionFlt(int i) {
        return this.mFunctionIdToCustomFunction.indexOfKey(i) >= 0;
    }

    @Override // com.ecarx.xui.adaptapi.car.AbsVehicle, com.ecarx.xui.adaptapi.car.base.ICarFunction
    public FunctionStatus isFunctionSupported(int i) {
        return isFunctionSupported(i, Integer.MIN_VALUE);
    }

    @Override // com.ecarx.xui.adaptapi.car.AbsVehicle, com.ecarx.xui.adaptapi.car.base.ICarFunction
    public FunctionStatus isFunctionSupported(int i, int i2) {
        return isFunctionSupported(i, i2, 0);
    }

    @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction
    public FunctionStatus isFunctionSupported(int i, int i2, int i3) {
        IVehicleFunction.IStatus<Float> statusTask;
        IVehicleFunction.IStatus<Integer> statusTask2;
        FunctionStatus status = FunctionStatus.notavailable;
        if (validCarFunctionInt(i)) {
            VehicleFunction<Integer> vehicleFunction = this.mFunctionIdToIntFunction.get(i);
            VehicleFunction.ZoneTask<Integer> zoneTask = vehicleFunction.getZoneTask(getVehicleType(), i2);
            if (zoneTask != null && (statusTask2 = zoneTask.getStatusTask()) != null) {
                status = statusTask2.getStatus(this);
            }
            vehicleFunction.tryRegisterFunctionSignal(this);
        } else if (validCarFunctionFlt(i)) {
            VehicleFunction<Float> vehicleFunction2 = this.mFunctionIdToCustomFunction.get(i);
            VehicleFunction.ZoneTask<Float> zoneTask2 = vehicleFunction2.getZoneTask(getVehicleType(), i2);
            if (zoneTask2 != null && (statusTask = zoneTask2.getStatusTask()) != null) {
                status = statusTask.getStatus(this);
            }
            vehicleFunction2.tryRegisterFunctionSignal(this);
        }
        return status;
    }

    @Override // com.ecarx.xui.adaptapi.car.AbsVehicle, com.ecarx.xui.adaptapi.car.base.ICarFunction
    public int[] getSupportedFunctionZones(int i) {
        if (validCarFunctionInt(i)) {
            return this.mFunctionIdToIntFunction.get(i).getZones();
        }
        if (validCarFunctionFlt(i)) {
            return this.mFunctionIdToCustomFunction.get(i).getZones();
        }
        return EMPTY_SUPPORT_FUNCTION_VALUE;
    }

    @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction
    public int[] getSupportedFunctionValue(int i) {
        return getSupportedFunctionValue(i, Integer.MIN_VALUE);
    }

    @Override // com.ecarx.xui.adaptapi.car.AbsVehicle, com.ecarx.xui.adaptapi.car.base.ICarFunction
    public int[] getSupportedFunctionValue(int i, int i2) {
        VehicleFunction<Integer> vehicleFunction = this.mFunctionIdToIntFunction.get(i);
        if (vehicleFunction != null) {
            VehicleFunction.ZoneTask<Integer> zoneTask = vehicleFunction.getZoneTask(getVehicleType(), i2);
            if (zoneTask != null) {
                return zoneTask.getValues();
            }
            vehicleFunction.tryRegisterFunctionSignal(this);
        } else {
            VehicleFunction<Float> vehicleFunction2 = this.mFunctionIdToCustomFunction.get(i);
            if (vehicleFunction2 != null) {
                VehicleFunction.ZoneTask<Float> zoneTask2 = vehicleFunction2.getZoneTask(getVehicleType(), i2);
                if (zoneTask2 != null) {
                    return zoneTask2.getValues();
                }
                vehicleFunction2.tryRegisterFunctionSignal(this);
            }
        }
        return EMPTY_SUPPORT_FUNCTION_VALUE;
    }

    @Override // com.ecarx.xui.adaptapi.car.AbsVehicle, com.ecarx.xui.adaptapi.car.base.ICarFunction
    public boolean setFunctionValue(int i, int i2) {
        return setFunctionValue(i, Integer.MIN_VALUE, i2);
    }

    @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction
    public boolean setFunctionValue(int i, int i2, int i3) {
        VehicleFunction.ZoneTask<Integer> zoneTask;
        ApiResult apiResultCallSetFunction = ApiResult.FAILED;
        VehicleFunction<Integer> vehicleFunction = this.mFunctionIdToIntFunction.get(i);
        if (vehicleFunction != null && (zoneTask = vehicleFunction.getZoneTask(getVehicleType(), i2)) != null) {
            apiResultCallSetFunction = zoneTask.callSetFunction(Integer.valueOf(i3));
        }
        if (apiResultCallSetFunction != ApiResult.SUCCEED) {
        }
        return apiResultCallSetFunction == ApiResult.SUCCEED;
    }

    @Override // com.ecarx.xui.adaptapi.car.AbsVehicle, com.ecarx.xui.adaptapi.car.base.ICarFunction
    public int getFunctionValue(int i) {
        return getFunctionValue(i, Integer.MIN_VALUE);
    }

    @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction
    public int getFunctionValue(int i, int i2) {
        IVehicleFunction.IValue<Integer> valueTask;
        VehicleFunction<Integer> vehicleFunction = this.mFunctionIdToIntFunction.get(i);
        Integer value = null;
        if (vehicleFunction != null) {
            VehicleFunction.ZoneTask<Integer> zoneTask = vehicleFunction.getZoneTask(getVehicleType(), i2);
            if (zoneTask != null && (valueTask = zoneTask.getValueTask()) != null) {
                value = valueTask.getValue(this);
            }
            vehicleFunction.tryRegisterFunctionSignal(this);
        }
        if (value == null) {
            return 255;
        }
        return value.intValue();
    }

    @Override // com.ecarx.xui.adaptapi.car.AbsVehicle, com.ecarx.xui.adaptapi.car.base.ICarFunction
    public boolean setCustomizeFunctionValue(int i, float f) {
        return setCustomizeFunctionValue(i, Integer.MIN_VALUE, f);
    }

    @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction
    public boolean setCustomizeFunctionValue(int i, int i2, float f) {
        VehicleFunction.ZoneTask<Float> zoneTask;
        ApiResult apiResultCallSetFunction = ApiResult.FAILED;
        VehicleFunction<Float> vehicleFunction = this.mFunctionIdToCustomFunction.get(i);
        if (vehicleFunction != null && (zoneTask = vehicleFunction.getZoneTask(getVehicleType(), i2)) != null) {
            apiResultCallSetFunction = zoneTask.callSetFunction(Float.valueOf(f));
        }
        if (apiResultCallSetFunction != ApiResult.SUCCEED) {
        }
        return apiResultCallSetFunction == ApiResult.SUCCEED;
    }

    @Override // com.ecarx.xui.adaptapi.car.AbsVehicle, com.ecarx.xui.adaptapi.car.base.ICarFunction
    public float getCustomizeFunctionValue(int i) {
        return getCustomizeFunctionValue(i, Integer.MIN_VALUE);
    }

    @Override // com.ecarx.xui.adaptapi.car.base.ICarFunction
    public float getCustomizeFunctionValue(int i, int i2) {
        IVehicleFunction.IValue<Float> valueTask;
        VehicleFunction<Float> vehicleFunction = this.mFunctionIdToCustomFunction.get(i);
        Float value = null;
        if (vehicleFunction != null) {
            VehicleFunction.ZoneTask<Float> zoneTask = vehicleFunction.getZoneTask(getVehicleType(), i2);
            if (zoneTask != null && (valueTask = zoneTask.getValueTask()) != null) {
                value = valueTask.getValue(this);
            }
            vehicleFunction.tryRegisterFunctionSignal(this);
        }
        if (value == null) {
            return Float.MIN_VALUE;
        }
        return value.floatValue();
    }

    boolean registerPAOrSignal(SignalFilter signalFilter, SignalFilter signalFilter2) {
        this.mRegisterHandler.removeMessages(1);
        int filterCount = signalFilter.getFilterCount();
        if (filterCount > 0) {
            for (int i = 0; i < filterCount; i++) {
                addPAFilter(signalFilter.getSignal(i));
            }
        }
        int filterCount2 = signalFilter2.getFilterCount();
        if (filterCount2 > 0) {
            for (int i2 = 0; i2 < filterCount2; i2++) {
                addSignalFilter(signalFilter2.getSignal(i2));
            }
        }
        this.mRegisterHandler.sendEmptyMessageDelayed(1, 100L);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addIntFunction(VehicleFunction<Integer> vehicleFunction) {
        this.mIntFunctions.add(vehicleFunction);
        this.mFunctionIdToIntFunction.put(vehicleFunction.getFunction(), vehicleFunction);
        Iterator<Integer> it = vehicleFunction.getPropertyList().iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            vehicleFunction.addPAOrSignal(iIntValue);
            List<VehicleFunction<Integer>> list = this.mPropertyToIntFunction.get(iIntValue, new ArrayList());
            if (list.isEmpty()) {
                this.mPropertyToIntFunction.put(iIntValue, list);
            }
            list.add(vehicleFunction);
        }
        Set<IVehicleFunction.IAssociatedStatus<Integer>> associatedStatusList = vehicleFunction.getAssociatedStatusList();
        if (associatedStatusList.isEmpty()) {
            return;
        }
        for (IVehicleFunction.IAssociatedStatus<Integer> iAssociatedStatus : associatedStatusList) {
            List<IVehicleFunction.IAssociatedStatus<?>> list2 = this.groupAssociated.get(iAssociatedStatus.getAssociatedFunction(), new ArrayList());
            if (list2.isEmpty()) {
                this.groupAssociated.put(iAssociatedStatus.getAssociatedFunction(), list2);
            }
            list2.add(iAssociatedStatus);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addCustomFunction(VehicleFunction<Float> vehicleFunction) {
        this.mFloatFunctions.add(vehicleFunction);
        this.mFunctionIdToCustomFunction.put(vehicleFunction.getFunction(), vehicleFunction);
        Iterator<Integer> it = vehicleFunction.getPropertyList().iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            vehicleFunction.addPAOrSignal(iIntValue);
            List<VehicleFunction<Float>> list = this.mPropertyToCustomFunction.get(iIntValue, new ArrayList());
            if (list.isEmpty()) {
                this.mPropertyToCustomFunction.put(iIntValue, list);
            }
            list.add(vehicleFunction);
        }
        Set<IVehicleFunction.IAssociatedStatus<Float>> associatedStatusList = vehicleFunction.getAssociatedStatusList();
        if (associatedStatusList.isEmpty()) {
            return;
        }
        for (IVehicleFunction.IAssociatedStatus<Float> iAssociatedStatus : associatedStatusList) {
            List<IVehicleFunction.IAssociatedStatus<?>> list2 = this.groupAssociated.get(iAssociatedStatus.getAssociatedFunction(), new ArrayList());
            if (list2.isEmpty()) {
                this.groupAssociated.put(iAssociatedStatus.getAssociatedFunction(), list2);
            }
            list2.add(iAssociatedStatus);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void triggerCallback(int i, int i2) {
        if (validCarFunctionInt(i)) {
            VehicleFunction.ZoneTask<Integer> zoneTask = this.mFunctionIdToIntFunction.get(i).getZoneTask(getVehicleType(), i2);
            if (zoneTask == null) {
                return;
            } else {
                notifyIntCallback(zoneTask);
                return;
            }
        }
        if (validCarFunctionFlt(i)) {
            VehicleFunction.ZoneTask<Float> zoneTask2 = this.mFunctionIdToCustomFunction.get(i).getZoneTask(getVehicleType(), i2);
            if (zoneTask2 == null) {
            } else {
                notifyCustomCallback(zoneTask2);
            }
        }
    }

    @Override // com.ecarx.xui.adaptapi.AbsCarSignal
    protected void onChangeEvent(ECarXCarPropertyValue eCarXCarPropertyValue) {
        recordSignalDate(eCarXCarPropertyValue);
        final int propertyId = eCarXCarPropertyValue.getPropertyId();
        if (propertyId == 557871781) {
            final VehicleType vehicleType = getVehicleType();
            this.mIntFunctions.stream().flatMap(new Function() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$AbsCarFunction$UahtgWH_IO_dSa2LaK9pcHNdPsY
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((VehicleFunction) obj).getZoneTasks().stream();
                }
            }).filter(new Predicate() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$AbsCarFunction$pDuWCLOc6xhgkEAw8AEJU8-rssM
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return AbsCarFunction.lambda$onChangeEvent$2(vehicleType, (VehicleFunction.ZoneTask) obj);
                }
            }).forEach(new Consumer() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$AbsCarFunction$hU0d3KBbAxS6wr9gi6mWPDjo7W8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {

                }
            });
            this.mFloatFunctions.stream().flatMap(new Function() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$AbsCarFunction$umjUa9F0lKdtqFhzh4fPYBEtvNM
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((VehicleFunction) obj).getZoneTasks().stream();
                }
            }).filter(new Predicate() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$AbsCarFunction$ul2sLxX_2IVxkhU97E-gdTtlg6I
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return AbsCarFunction.lambda$onChangeEvent$4(vehicleType, (VehicleFunction.ZoneTask) obj);
                }
            }).forEach(new Consumer() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$AbsCarFunction$J3cfkeCYCxOsIoOyM2R7dPn6C2E
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {

                }
            });
            return;
        }
        List<VehicleFunction<Integer>> list = this.mPropertyToIntFunction.get(propertyId);
        if (list != null) {
            list.stream().flatMap(new Function() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$AbsCarFunction$JP2JeTEqnvZBglN4_CNYWpe56xM
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return null;
                }
            }).forEach(new Consumer() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$AbsCarFunction$hU0d3KBbAxS6wr9gi6mWPDjo7W8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {

                }
            });
        }
        List<VehicleFunction<Float>> list2 = this.mPropertyToCustomFunction.get(propertyId);
        if (list2 != null) {
            list2.stream().flatMap(new Function() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$AbsCarFunction$t-v41E7OM_AKm6BFm4TaOeNrv9s
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return null;
                }
            }).forEach(new Consumer() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$AbsCarFunction$J3cfkeCYCxOsIoOyM2R7dPn6C2E
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {

                }
            });
        }
    }

    static /* synthetic */ boolean lambda$onChangeEvent$2(VehicleType vehicleType, VehicleFunction.ZoneTask zoneTask) {
        return zoneTask.getVehicleType() == vehicleType;
    }

    static /* synthetic */ boolean lambda$onChangeEvent$4(VehicleType vehicleType, VehicleFunction.ZoneTask zoneTask) {
        return zoneTask.getVehicleType() == vehicleType;
    }

    public /* synthetic */ Stream lambda$onChangeEvent$7$AbsCarFunction(final int i, final VehicleFunction vehicleFunction) {
        return Arrays.stream(vehicleFunction.getZones()).mapToObj(new IntFunction() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$AbsCarFunction$ErdXTeXsrJkzvjw6HRYiokm0kt0
            @Override // java.util.function.IntFunction
            public final Object apply(int i2) {
                return null;
            }
        }).filter(new Predicate() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$AbsCarFunction$HG76Se-rjG8djsmLuzxnzz0dFc8
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return AbsCarFunction.lambda$onChangeEvent$6(i, (VehicleFunction.ZoneTask) obj);
            }
        });
    }

    public /* synthetic */ VehicleFunction.ZoneTask lambda$onChangeEvent$5$AbsCarFunction(VehicleFunction vehicleFunction, int i) {
        return vehicleFunction.getZoneTask(getVehicleType(), i);
    }

    static /* synthetic */ boolean lambda$onChangeEvent$6(int i, VehicleFunction.ZoneTask zoneTask) {
        return zoneTask != null && zoneTask.containerProperty(i);
    }

    public /* synthetic */ Stream lambda$onChangeEvent$10$AbsCarFunction(final int i, final VehicleFunction vehicleFunction) {
        return Arrays.stream(vehicleFunction.getZones()).mapToObj(new IntFunction() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$AbsCarFunction$vHSeJst948F7U_uBl5othGxkYkc
            @Override // java.util.function.IntFunction
            public final Object apply(int i2) {
                return null;
            }
        }).filter(new Predicate() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$AbsCarFunction$E6NdY6hJbJqwPEwNCMDU-kyMxUA
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return AbsCarFunction.lambda$onChangeEvent$9(i, (VehicleFunction.ZoneTask) obj);
            }
        });
    }

    public /* synthetic */ VehicleFunction.ZoneTask lambda$onChangeEvent$8$AbsCarFunction(VehicleFunction vehicleFunction, int i) {
        return vehicleFunction.getZoneTask(getVehicleType(), i);
    }

    static /* synthetic */ boolean lambda$onChangeEvent$9(int i, VehicleFunction.ZoneTask zoneTask) {
        return zoneTask != null && zoneTask.containerProperty(i);
    }

    /* renamed from: com.ecarx.xui.adaptapi.car.AbsCarFunction$1, reason: invalid class name */
    class AnonymousClass1 extends CarPAEventCallback {
        AnonymousClass1() {
        }

        public void onPAChanged(ECarXCarPropertyValue eCarXCarPropertyValue) {
        }

        public /* synthetic */ Stream lambda$onPAChanged$2$AbsCarFunction$1(final int i, final VehicleFunction vehicleFunction) {
            return Arrays.stream(vehicleFunction.getZones()).mapToObj(new IntFunction() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$AbsCarFunction$1$ZttUgQyr7XGcW8x8yRsYtxYdds4
                @Override // java.util.function.IntFunction
                public final Object apply(int i2) {
                    return null;
                }
            }).filter(new Predicate() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$AbsCarFunction$1$-SR5LxuG5KggWTJyDy2WRKPGQ7w
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return AbsCarFunction.AnonymousClass1.lambda$onPAChanged$1(i, (VehicleFunction.ZoneTask) obj);
                }
            });
        }

        public /* synthetic */ VehicleFunction.ZoneTask lambda$onPAChanged$0$AbsCarFunction$1(VehicleFunction vehicleFunction, int i) {
            return vehicleFunction.getZoneTask(AbsCarFunction.this.getVehicleType(), i);
        }

        static /* synthetic */ boolean lambda$onPAChanged$1(int i, VehicleFunction.ZoneTask zoneTask) {
            return zoneTask != null && zoneTask.containerProperty(i);
        }

        public /* synthetic */ Stream lambda$onPAChanged$6$AbsCarFunction$1(final int i, final VehicleFunction vehicleFunction) {
            return Arrays.stream(vehicleFunction.getZones()).mapToObj(new IntFunction() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$AbsCarFunction$1$aLm3iIWSsXwTgb2zNYXwq11RLzo
                @Override // java.util.function.IntFunction
                public final Object apply(int i2) {
                    return null;
                }
            }).filter(new Predicate() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$AbsCarFunction$1$PWxqUbwwTOvxgxJbE70andEzjRI
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return AbsCarFunction.AnonymousClass1.lambda$onPAChanged$5(i, (VehicleFunction.ZoneTask) obj);
                }
            });
        }

        public /* synthetic */ VehicleFunction.ZoneTask lambda$onPAChanged$4$AbsCarFunction$1(VehicleFunction vehicleFunction, int i) {
            return vehicleFunction.getZoneTask(AbsCarFunction.this.getVehicleType(), i);
        }

        static /* synthetic */ boolean lambda$onPAChanged$5(int i, VehicleFunction.ZoneTask zoneTask) {
            return zoneTask != null && zoneTask.containerProperty(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyIntCallback(final VehicleFunction.ZoneTask<Integer> zoneTask) {
        final VehicleFunction.Data<Integer> data = zoneTask.getData(this);
        if (data.isStatusChanged() || data.isValueChanged() || data.isSupportValueChanged()) {
            onFunctionChanged(zoneTask.getFunctionId());
        }
        if (data.isStatusChanged()) {
            onSupportedFunctionStatusChanged(zoneTask.getFunctionId(), zoneTask.getZone(), data.getStatus());
            List<IVehicleFunction.IAssociatedStatus<?>> list = this.groupAssociated.get(zoneTask.getFunctionId());
            if (list != null && !list.isEmpty()) {
                list.stream().filter(new Predicate() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$AbsCarFunction$DRkL9iVvMbgWrByC0Xnxe7fJsno
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return AbsCarFunction.lambda$notifyIntCallback$11(zoneTask, (IVehicleFunction.IAssociatedStatus) obj);
                    }
                }).forEach(new Consumer() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$AbsCarFunction$yP2NbChL1Y7_evDN-yNl72NBxpk
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                    }
                });
            }
        }
        if (data.isSupportValueChanged()) {
            onSupportedFunctionValueChanged(zoneTask.getFunctionId(), data.getSupportValues());
        }
        if (data.isValueChanged()) {
            onFunctionValueChanged(zoneTask.getFunctionId(), zoneTask.getZone(), data.getValue().intValue());
        }
    }

    static /* synthetic */ boolean lambda$notifyIntCallback$11(VehicleFunction.ZoneTask zoneTask, IVehicleFunction.IAssociatedStatus iAssociatedStatus) {
        return iAssociatedStatus.getZone() == zoneTask.getZone();
    }

    public /* synthetic */ void lambda$notifyIntCallback$12$AbsCarFunction(VehicleFunction.Data data, IVehicleFunction.IAssociatedStatus iAssociatedStatus) {
        onFunctionChanged(iAssociatedStatus.getFunctionId());
        onSupportedFunctionStatusChanged(iAssociatedStatus.getFunctionId(), iAssociatedStatus.getZone(), data.getStatus());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyCustomCallback(final VehicleFunction.ZoneTask<Float> zoneTask) {
        final VehicleFunction.Data<Float> data = zoneTask.getData(this);
        if (data.isStatusChanged() || data.isValueChanged() || data.isSupportValueChanged()) {
            onFunctionChanged(zoneTask.getFunctionId());
        }
        if (data.isStatusChanged()) {
            onSupportedFunctionStatusChanged(zoneTask.getFunctionId(), zoneTask.getZone(), data.getStatus());
            List<IVehicleFunction.IAssociatedStatus<?>> list = this.groupAssociated.get(zoneTask.getFunctionId());
            if (list != null && !list.isEmpty()) {
                list.stream().filter(new Predicate() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$AbsCarFunction$QWZmjQMLmdjBY2CIFtzY8e2pt3E
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return AbsCarFunction.lambda$notifyCustomCallback$13(zoneTask, (IVehicleFunction.IAssociatedStatus) obj);
                    }
                }).forEach(new Consumer() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$AbsCarFunction$ZSnSFd2mcEkEHwwmiNyoP_4Z9-U
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                    }
                });
            }
        }
        if (data.isSupportValueChanged()) {
            onSupportedFunctionValueChanged(zoneTask.getFunctionId(), data.getSupportValues());
        }
        if (data.isValueChanged()) {
            onCustomizeFunctionValueChanged(zoneTask.getFunctionId(), zoneTask.getZone(), data.getValue().floatValue());
        }
    }

    static /* synthetic */ boolean lambda$notifyCustomCallback$13(VehicleFunction.ZoneTask zoneTask, IVehicleFunction.IAssociatedStatus iAssociatedStatus) {
        return iAssociatedStatus.getZone() == zoneTask.getZone();
    }

    public /* synthetic */ void lambda$notifyCustomCallback$14$AbsCarFunction(VehicleFunction.Data data, IVehicleFunction.IAssociatedStatus iAssociatedStatus) {
        onFunctionChanged(iAssociatedStatus.getFunctionId());
        onSupportedFunctionStatusChanged(iAssociatedStatus.getFunctionId(), iAssociatedStatus.getZone(), data.getStatus());
    }

    private void registerCarFunctionSignal(int i) {
        if (validCarFunctionInt(i)) {
            this.mFunctionIdToIntFunction.get(i).tryRegisterFunctionSignal(this);
        } else if (validCarFunctionFlt(i)) {
            this.mFunctionIdToCustomFunction.get(i).tryRegisterFunctionSignal(this);
        }
    }

    @Override // com.ecarx.xui.adaptapi.car.AbsVehicle, com.ecarx.xui.adaptapi.car.base.ICarFunction
    public boolean registerFunctionValueWatcher(ICarFunction.IFunctionValueWatcher iFunctionValueWatcher) {
        synchronized (this.mCallbackLock) {
            ArrayList<ICarFunction.IFunctionValueWatcher> arrayList = this.mCallbackArray.get(-1);
            if (arrayList != null) {
                arrayList.remove(iFunctionValueWatcher);
                arrayList.add(iFunctionValueWatcher);
            }
        }
        registerAllSignal();
        return true;
    }

    public void registerAllSignal() {
        Iterator<VehicleFunction<Integer>> it = this.mIntFunctions.iterator();
        while (it.hasNext()) {
            it.next().tryRegisterFunctionSignal(this);
        }
        Iterator<VehicleFunction<Float>> it2 = this.mFloatFunctions.iterator();
        while (it2.hasNext()) {
            it2.next().tryRegisterFunctionSignal(this);
        }
    }

    @Override // com.ecarx.xui.adaptapi.car.AbsVehicle, com.ecarx.xui.adaptapi.car.base.ICarFunction
    public boolean registerFunctionValueWatcher(int i, ICarFunction.IFunctionValueWatcher iFunctionValueWatcher) {
        synchronized (this.mCallbackLock) {
            if (this.mCallbackArray.indexOfKey(i) < 0) {
                this.mCallbackArray.put(i, new ArrayList<>());
            }
            ArrayList<ICarFunction.IFunctionValueWatcher> arrayList = this.mCallbackArray.get(i);
            if (arrayList != null && !arrayList.contains(iFunctionValueWatcher)) {
                arrayList.add(iFunctionValueWatcher);
            }
        }
        registerCarFunctionSignal(i);
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.car.AbsVehicle, com.ecarx.xui.adaptapi.car.base.ICarFunction
    public boolean registerFunctionValueWatcher(int[] iArr, ICarFunction.IFunctionValueWatcher iFunctionValueWatcher) {
        int i;
        synchronized (this.mCallbackLock) {
            for (int i2 : iArr) {
                Integer numValueOf = Integer.valueOf(i2);
                if (this.mCallbackArray.indexOfKey(numValueOf.intValue()) < 0) {
                    this.mCallbackArray.put(numValueOf.intValue(), new ArrayList<>());
                }
                ArrayList<ICarFunction.IFunctionValueWatcher> arrayList = this.mCallbackArray.get(numValueOf.intValue());
                if (arrayList != null && !arrayList.contains(iFunctionValueWatcher)) {
                    arrayList.add(iFunctionValueWatcher);
                }
            }
        }
        for (int i3 : iArr) {
            registerCarFunctionSignal(i3);
        }
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.car.AbsVehicle, com.ecarx.xui.adaptapi.car.base.ICarFunction
    public boolean unregisterFunctionValueWatcher(ICarFunction.IFunctionValueWatcher iFunctionValueWatcher) {
        synchronized (this.mCallbackLock) {
            int size = this.mCallbackArray.size();
            for (int i = 0; i < size; i++) {
                this.mCallbackArray.valueAt(i).remove(iFunctionValueWatcher);
            }
        }
        return true;
    }

    private ArrayList<ICarFunction.IFunctionValueWatcher> getCallbackList(int i) {
        ArrayList<ICarFunction.IFunctionValueWatcher> arrayList = new ArrayList<>();
        synchronized (this.mCallbackLock) {
            ArrayList<ICarFunction.IFunctionValueWatcher> arrayList2 = this.mCallbackArray.get(i, new ArrayList<>());
            ArrayList<ICarFunction.IFunctionValueWatcher> arrayList3 = this.mCallbackArray.get(-1, new ArrayList<>());
            arrayList.addAll(arrayList2);
            Iterator<ICarFunction.IFunctionValueWatcher> it = arrayList3.iterator();
            while (it.hasNext()) {
                ICarFunction.IFunctionValueWatcher next = it.next();
                if (!arrayList.contains(next)) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    @Override // com.ecarx.xui.adaptapi.car.AbsVehicle
    public void onFunctionChanged(int i) {
        SystemClock.uptimeMillis();
        Iterator<ICarFunction.IFunctionValueWatcher> it = getCallbackList(i).iterator();
        while (it.hasNext()) {
            try {
                it.next().onFunctionChanged(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.ecarx.xui.adaptapi.car.AbsVehicle
    public void onSupportedFunctionStatusChanged(int i, int i2, FunctionStatus functionStatus) {
        SystemClock.uptimeMillis();
        Iterator<ICarFunction.IFunctionValueWatcher> it = getCallbackList(i).iterator();
        while (it.hasNext()) {
            try {
                it.next().onSupportedFunctionStatusChanged(i, i2, functionStatus);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.ecarx.xui.adaptapi.car.AbsVehicle
    public void onFunctionValueChanged(int i, int i2, int i3) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        Iterator<ICarFunction.IFunctionValueWatcher> it = getCallbackList(i).iterator();
        while (it.hasNext()) {
            try {
                it.next().onFunctionValueChanged(i, i2, i3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.ecarx.xui.adaptapi.car.AbsVehicle
    public void onCustomizeFunctionValueChanged(int i, int i2, float f) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        Iterator<ICarFunction.IFunctionValueWatcher> it = getCallbackList(i).iterator();
        while (it.hasNext()) {
            try {
                it.next().onCustomizeFunctionValueChanged(i, i2, f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.ecarx.xui.adaptapi.car.AbsVehicle
    public void onSupportedFunctionValueChanged(int i, int[] iArr) {
        SystemClock.uptimeMillis();
        Iterator<ICarFunction.IFunctionValueWatcher> it = getCallbackList(i).iterator();
        while (it.hasNext()) {
            try {
                it.next().onSupportedFunctionValueChanged(i, iArr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getModuleName() {
        return "AbsCarFunction::" + getClass().getSimpleName();
    }
}