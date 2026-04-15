package com.ecarx.xui.adaptapi.car;

import android.util.Log;
import android.util.SparseArray;

import com.ecarx.xui.adaptapi.FunctionStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import ecarx.car.hardware.annotation.ApiResult;
import ecarx.car.hardware.signal.SignalFilter;

/* loaded from: classes.dex */
public class VehicleFunction<T> implements IVehicleFunction<T> {
    private static final int PA = 34048;
    private static final int SIGNAL = 28672;
    private static final String TAG = "VehicleFunction";
    private final int function;
    private final SparseArray<SparseArray<ZoneTask<T>>> mZoneTasks = new SparseArray<>();
    private final List<ZoneTask<T>> mTasks = new ArrayList();
    private final Set<Integer> zones = new HashSet(10);
    private final Set<Integer> mPropertyList = new HashSet();
    private final Set<IVehicleFunction.IAssociatedStatus<T>> mAssociatedStatusList = new HashSet();
    private int[] values = new int[0];
    private final AtomicBoolean isRegister = new AtomicBoolean(false);
    private Function<Integer, Integer> convertDriverSide = Function.identity();
    private final SignalFilter mPAFilter = new SignalFilter();
    private final SignalFilter mSignalFilter = new SignalFilter();

    private VehicleFunction(int i) {
        this.function = i;
    }

    public static IVehicleFunction<Integer> intFunction(int i) {
        return new VehicleFunction(i);
    }

    public static IVehicleFunction<Float> customFunction(int i) {
        return new VehicleFunction(i);
    }

    @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction
    public VehicleFunction<T> supportedFunctionValue(int... iArr) {
        this.values = iArr;
        return this;
    }

    @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction
    public IVehicleFunction<T> supportDriverSide(Function<Integer, Integer> function) {
        this.convertDriverSide = function;
        return this;
    }

    @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction
    public IVehicleFunction.IZone<T> createZone(VehicleType vehicleType, int... iArr) {
        ZoneTask<T> zoneTask = new ZoneTask<>(this, vehicleType, iArr);
        zoneTask.supportedFunctionValue(this.values);
        for (int i : iArr) {
            this.zones.add(Integer.valueOf(i));
            addTask(zoneTask);
        }
        return zoneTask;
    }

    public int getFunction() {
        return this.function;
    }

    public ZoneTask<T> getZoneTask(VehicleType vehicleType, int i) {
        SparseArray<ZoneTask<T>> sparseArray = this.mZoneTasks.get(this.convertDriverSide.apply(Integer.valueOf(i)).intValue());
        if (sparseArray != null) {
            return sparseArray.get(vehicleType.ordinal(), sparseArray.get(VehicleType.COMMON.ordinal()));
        }
        return null;
    }

    public int[] getZones() {
        return this.zones.stream().mapToInt(new ToIntFunction() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$VehicleFunction$P58eWBo0kKmO_o0JnmucZ4Upfs0
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Integer) obj).intValue();
            }
        }).toArray();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addTask(ZoneTask<T> zoneTask) {
        VehicleType vehicleType = zoneTask.getVehicleType();
        int zone = zoneTask.getZone();
        SparseArray<ZoneTask<T>> sparseArray = this.mZoneTasks.get(zone);
        if (sparseArray == null) {
            sparseArray = new SparseArray<>();
            this.mZoneTasks.put(zone, sparseArray);
        }
        sparseArray.put(vehicleType.ordinal(), zoneTask);
        this.mTasks.add(zoneTask);
    }

    public void addPAOrSignal(int i) {
        int i2 = 65535 & i;
        if (i2 >= PA) {
            this.mPAFilter.add(Integer.valueOf(i));
        } else if (i2 >= SIGNAL) {
            this.mSignalFilter.add(Integer.valueOf(i));
        } else {

        }
    }

    public List<ZoneTask<T>> getZoneTasks() {
        return this.mTasks;
    }

    public Set<Integer> getPropertyList() {
        return this.mPropertyList;
    }

    public Set<IVehicleFunction.IAssociatedStatus<T>> getAssociatedStatusList() {
        return this.mAssociatedStatusList;
    }

    public void tryRegisterFunctionSignal(AbsCarFunction absCarFunction) {

    }

    public static class ZoneTask<T> implements IVehicleFunction.IZone<T> {
        private Function<T, ApiResult> mSetFunctionValueFn;
        private IVehicleFunction.IStatus<T> mStatusTask;
        private IVehicleFunction.IValue<T> mValueTask;
        private final VehicleFunction<T> mVehicleFunction;
        private final VehicleType mVehicleType;
        private final int[] mZone;
        private final Data<T> mData = new Data<>();
        private final List<Integer> properties = new ArrayList();
        private IVehicleFunction.SupportFunctionValue mSupportFunctionValueFn = new IVehicleFunction.SupportFunctionValue() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$VehicleFunction$ZoneTask$MyYoMHfKTCFj_85ALMPCHJjTXnU
            @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.SupportFunctionValue
            public final int[] get() {
                return VehicleFunction.ZoneTask.lambda$new$0();
            }
        };

        static /* synthetic */ int[] lambda$new$0() {
            return new int[0];
        }

        public ZoneTask(VehicleFunction<T> vehicleFunction, VehicleType vehicleType, int... iArr) {
            this.mVehicleFunction = vehicleFunction;
            this.mVehicleType = vehicleType;
            this.mZone = iArr;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IZone
        public IVehicleFunction.IZone<T> supportedFunctionValue(IVehicleFunction.SupportFunctionValue supportFunctionValue, int... iArr) {
            this.mSupportFunctionValueFn = supportFunctionValue;
            if (iArr.length > 0) {
                Collection<? extends Integer> collection = (Collection) Arrays.stream(iArr).boxed().collect(Collectors.toList());
                this.properties.addAll(collection);
                ((VehicleFunction) this.mVehicleFunction).mPropertyList.addAll(collection);
            }
            return this;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IStatusTaskBuild
        public IVehicleFunction.IValueTaskBuild<T> customStatus(Supplier<FunctionStatus> supplier) {
            CustomStatus customStatus = new CustomStatus(this.mVehicleFunction, this, supplier);
            this.mStatusTask = customStatus;
            return customStatus;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IStatusTaskBuild
        public IVehicleFunction.IValueTaskBuild<T> useOtherFunctionStatus(int i) {
            AssociatedStatus associatedStatus = new AssociatedStatus(this.mVehicleFunction, this, i);
            this.mStatusTask = associatedStatus;
            ((VehicleFunction) this.mVehicleFunction).mAssociatedStatusList.add(associatedStatus);
            return associatedStatus;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IStatusTaskBuild
        public <PA_TYPE> IVehicleFunction.IValueTaskBuild<T> useStatusPA(int i, Function<PA_TYPE, FunctionStatus> function) {
            SinglePATask singlePATask = new SinglePATask(this.mVehicleFunction, this, i);
            singlePATask.mapToAdapterApiStatus(function);
            ((VehicleFunction) this.mVehicleFunction).mPropertyList.add(Integer.valueOf(i));
            this.properties.add(Integer.valueOf(i));
            this.mStatusTask = singlePATask;
            return singlePATask;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IStatusTaskBuild
        public IVehicleFunction.IMultiSignalStatus<T> useStatusSignals(int... iArr) {
            MultiSignalTask multiSignalTask = new MultiSignalTask(this.mVehicleFunction, this, iArr);
            Collection<? extends Integer> collection = (Collection) Arrays.stream(iArr).boxed().collect(Collectors.toList());
            ((VehicleFunction) this.mVehicleFunction).mPropertyList.addAll(collection);
            this.properties.addAll(collection);
            this.mStatusTask = multiSignalTask;
            return multiSignalTask;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IStatusTaskBuild
        public IVehicleFunction.IValueTaskBuild<T> useStatusSignal(int i, Function<Integer, FunctionStatus> function) {
            SingleSignalTask singleSignalTask = new SingleSignalTask(this.mVehicleFunction, this, i);
            singleSignalTask.mapToAdapterApiStatus(function);
            ((VehicleFunction) this.mVehicleFunction).mPropertyList.add(Integer.valueOf(i));
            this.properties.add(Integer.valueOf(i));
            this.mStatusTask = singleSignalTask;
            return singleSignalTask;
        }

        public void addProperties(List<Integer> list) {
            this.properties.addAll(list);
        }

        public int getZone() {
            return this.mZone[0];
        }

        public VehicleType getVehicleType() {
            return this.mVehicleType;
        }

        public int[] getValues() {
            int[] iArr = new int[0];
            try {
                return this.mSupportFunctionValueFn.get();
            } catch (Exception e) {
                Log.e(VehicleFunction.TAG, "find a error", e);
                return iArr;
            }
        }

        public IVehicleFunction.IStatus<T> getStatusTask() {
            return this.mStatusTask;
        }

        public IVehicleFunction.IValue<T> getValueTask() {
            return this.mValueTask;
        }

        public Function<T, ApiResult> getSetFunctionValueFn() {
            return this.mSetFunctionValueFn;
        }

        public boolean containerProperty(int i) {
            return Collections.binarySearch(this.properties, Integer.valueOf(i)) >= 0;
        }

        public Data<T> getData(AbsCarFunction absCarFunction) {
            this.mData.setStatus(this.mStatusTask.getStatus(absCarFunction));
            this.mData.setSupportValues(getValues());
            IVehicleFunction.IValue<T> iValue = this.mValueTask;
            if (iValue != null) {
                this.mData.setValue(iValue.getCallbackMode(), this.mValueTask.canNotify(absCarFunction), this.mValueTask.getValue(absCarFunction));
            }
            return this.mData;
        }

        public int getFunctionId() {
            return ((VehicleFunction) this.mVehicleFunction).function;
        }

        public ApiResult callSetFunction(T t) {
            Function<T, ApiResult> function = this.mSetFunctionValueFn;
            if (function != null) {
                return function.apply(t);
            }
            return ApiResult.INVALID;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void lazyCreateZoneTask() {
            Collections.sort(this.properties);
            int[] iArr = this.mZone;
            if (iArr.length == 1) {
                this.mVehicleFunction.addTask(this);
                return;
            }
            for (int i : iArr) {
                ZoneTask zoneTask = new ZoneTask(this.mVehicleFunction, this.mVehicleType, i);
                zoneTask.mSupportFunctionValueFn = this.mSupportFunctionValueFn;
                zoneTask.mStatusTask = getStatusTask();
                zoneTask.mValueTask = getValueTask();
                zoneTask.mSetFunctionValueFn = getSetFunctionValueFn();
                zoneTask.addProperties(this.properties);
                this.mVehicleFunction.addTask(zoneTask);
            }
        }
    }

    public static class CustomStatus<T> extends AbsTask<T> implements IVehicleFunction.IStatus<T> {
        private final Supplier<FunctionStatus> mFunctionStatusFn;

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.ITaskEnd
        public /* bridge */ /* synthetic */ void addTo(Consumer consumer) {
            super.addTo(consumer);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.ITaskEnd
        public /* bridge */ /* synthetic */ IVehicleFunction.IZone createZone(VehicleType vehicleType, int[] iArr) {
            return super.createZone(vehicleType, iArr);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IFilterCallback customValue(Supplier supplier) {
            return super.customValue(supplier);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.ITask
        public /* bridge */ /* synthetic */ int getFunctionId() {
            return super.getFunctionId();
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.ITask
        public /* bridge */ /* synthetic */ int getZone() {
            return super.getZone();
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IValueTaskBuild onSetFunctionValue(Function function) {
            return super.onSetFunctionValue(function);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IFilterCallback useValuePA(int i, Function function) {
            return super.useValuePA(i, function);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IFilterCallback useValueSignal(int i, Function function) {
            return super.useValueSignal(i, function);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IMultiSignalValue useValueSignals(int[] iArr) {
            return super.useValueSignals(iArr);
        }

        public CustomStatus(VehicleFunction<T> vehicleFunction, ZoneTask<T> zoneTask, Supplier<FunctionStatus> supplier) {
            super(vehicleFunction, zoneTask);
            this.mFunctionStatusFn = supplier;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IStatus
        public FunctionStatus getStatus(AbsCarFunction absCarFunction) {
            FunctionStatus functionStatus = FunctionStatus.notavailable;
            try {
                return this.mFunctionStatusFn.get();
            } catch (Exception e) {
                Log.e(VehicleFunction.TAG, "getStatus has a error", e);
                return functionStatus;
            }
        }
    }

    public static class AssociatedStatus<T> extends AbsTask<T> implements IVehicleFunction.IAssociatedStatus<T> {
        private final int functionId;

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.ITaskEnd
        public /* bridge */ /* synthetic */ void addTo(Consumer consumer) {
            super.addTo(consumer);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.ITaskEnd
        public /* bridge */ /* synthetic */ IVehicleFunction.IZone createZone(VehicleType vehicleType, int[] iArr) {
            return super.createZone(vehicleType, iArr);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IFilterCallback customValue(Supplier supplier) {
            return super.customValue(supplier);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.ITask
        public /* bridge */ /* synthetic */ int getFunctionId() {
            return super.getFunctionId();
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.ITask
        public /* bridge */ /* synthetic */ int getZone() {
            return super.getZone();
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IValueTaskBuild onSetFunctionValue(Function function) {
            return super.onSetFunctionValue(function);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IFilterCallback useValuePA(int i, Function function) {
            return super.useValuePA(i, function);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IFilterCallback useValueSignal(int i, Function function) {
            return super.useValueSignal(i, function);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IMultiSignalValue useValueSignals(int[] iArr) {
            return super.useValueSignals(iArr);
        }

        public AssociatedStatus(VehicleFunction<T> vehicleFunction, ZoneTask<T> zoneTask, int i) {
            super(vehicleFunction, zoneTask);
            this.functionId = i;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IStatus
        public FunctionStatus getStatus(AbsCarFunction absCarFunction) {
            return null;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IAssociatedStatus
        public int getAssociatedFunction() {
            return this.functionId;
        }
    }

    public static class SinglePATask<T, PA_TYPE> extends AbsTask<T> implements IVehicleFunction.IStatus<T>, IVehicleFunction.IValue<T>, IVehicleFunction.IFilterCallback<T, PA_TYPE> {
        private IVehicleFunction.Mode mCallbackMode;
        private Predicate<PA_TYPE> mFilter;
        private Function<PA_TYPE, FunctionStatus> mFunctionStatusFn;
        private Function<PA_TYPE, T> mFunctionValueFn;
        private final int paId;

        static /* synthetic */ boolean lambda$new$0(Object obj) {
            return true;
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.ITaskEnd
        public /* bridge */ /* synthetic */ void addTo(Consumer consumer) {
            super.addTo(consumer);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.ITaskEnd
        public /* bridge */ /* synthetic */ IVehicleFunction.IZone createZone(VehicleType vehicleType, int[] iArr) {
            return super.createZone(vehicleType, iArr);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IFilterCallback customValue(Supplier supplier) {
            return super.customValue(supplier);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.ITask
        public /* bridge */ /* synthetic */ int getFunctionId() {
            return super.getFunctionId();
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.ITask
        public /* bridge */ /* synthetic */ int getZone() {
            return super.getZone();
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IValueTaskBuild onSetFunctionValue(Function function) {
            return super.onSetFunctionValue(function);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IFilterCallback useValuePA(int i, Function function) {
            return super.useValuePA(i, function);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IFilterCallback useValueSignal(int i, Function function) {
            return super.useValueSignal(i, function);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IMultiSignalValue useValueSignals(int[] iArr) {
            return super.useValueSignals(iArr);
        }

        public SinglePATask(VehicleFunction<T> vehicleFunction, ZoneTask<T> zoneTask, int i) {
            super(vehicleFunction, zoneTask);
            this.mFilter = new Predicate() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$VehicleFunction$SinglePATask$Mvvo8ohufq0poxr5J8keQH0WK7I
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return VehicleFunction.SinglePATask.lambda$new$0(obj);
                }
            };
            this.mCallbackMode = IVehicleFunction.Mode.VALUE_CHANGE;
            this.paId = i;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IStatus
        public FunctionStatus getStatus(AbsCarFunction absCarFunction) {
            FunctionStatus functionStatus = FunctionStatus.notavailable;
            try {
                return null;
            } catch (Exception e) {
                Log.e(VehicleFunction.TAG, "getStatus has a error", e);
                return functionStatus;
            }
        }

        public void mapToAdapterApiStatus(Function<PA_TYPE, FunctionStatus> function) {
            this.mFunctionStatusFn = function;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IValue
        public T getValue(AbsCarFunction absCarFunction) {
            try {
                return null;
            } catch (Exception e) {
                Log.e(VehicleFunction.TAG, "getValue has a error paId = " + this.paId, e);
                return null;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IValue
        public boolean canNotify(AbsCarFunction absCarFunction) {
            return false;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IValue
        public IVehicleFunction.Mode getCallbackMode() {
            return this.mCallbackMode;
        }

        public void mapToAdapterApiValue(Function<PA_TYPE, T> function) {
            this.mFunctionValueFn = function;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IFilterCallback
        public IVehicleFunction.ITaskEnd<T> filterValue(IVehicleFunction.Mode mode, Predicate<PA_TYPE> predicate) {
            this.mCallbackMode = mode;
            this.mFilter = predicate;
            return this;
        }
    }

    public static class SingleSignalTask<T> extends AbsTask<T> implements IVehicleFunction.IStatus<T>, IVehicleFunction.IValue<T>, IVehicleFunction.IFilterCallback<T, Integer> {
        private IVehicleFunction.Mode mCallbackMode;
        private Predicate<Integer> mFilter;
        private Function<Integer, FunctionStatus> mFunctionStatusFn;
        private Function<Integer, T> mFunctionValueFn;
        private final int signalId;

        static /* synthetic */ boolean lambda$new$0(Integer num) {
            return true;
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.ITaskEnd
        public /* bridge */ /* synthetic */ void addTo(Consumer consumer) {
            super.addTo(consumer);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.ITaskEnd
        public /* bridge */ /* synthetic */ IVehicleFunction.IZone createZone(VehicleType vehicleType, int[] iArr) {
            return super.createZone(vehicleType, iArr);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IFilterCallback customValue(Supplier supplier) {
            return super.customValue(supplier);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.ITask
        public /* bridge */ /* synthetic */ int getFunctionId() {
            return super.getFunctionId();
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.ITask
        public /* bridge */ /* synthetic */ int getZone() {
            return super.getZone();
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IValueTaskBuild onSetFunctionValue(Function function) {
            return super.onSetFunctionValue(function);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IFilterCallback useValuePA(int i, Function function) {
            return super.useValuePA(i, function);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IFilterCallback useValueSignal(int i, Function function) {
            return super.useValueSignal(i, function);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IMultiSignalValue useValueSignals(int[] iArr) {
            return super.useValueSignals(iArr);
        }

        public SingleSignalTask(VehicleFunction<T> vehicleFunction, ZoneTask<T> zoneTask, int i) {
            super(vehicleFunction, zoneTask);
            this.mFilter = new Predicate() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$VehicleFunction$SingleSignalTask$LbuGx9jmeRcbmOAfB9ISrBA6bp4
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return VehicleFunction.SingleSignalTask.lambda$new$0((Integer) obj);
                }
            };
            this.mCallbackMode = IVehicleFunction.Mode.VALUE_CHANGE;
            this.signalId = i;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IStatus
        public FunctionStatus getStatus(AbsCarFunction absCarFunction) {
            FunctionStatus functionStatus = FunctionStatus.notavailable;
            try {
                return null;
            } catch (Exception e) {
                Log.e(VehicleFunction.TAG, "getStatus has a error", e);
                return functionStatus;
            }
        }

        public void mapToAdapterApiStatus(Function<Integer, FunctionStatus> function) {
            this.mFunctionStatusFn = function;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IValue
        public T getValue(AbsCarFunction absCarFunction) {
            try {
                return null;
            } catch (Exception e) {
                Log.e(VehicleFunction.TAG, "getValue has a error signalId = " + this.signalId, e);
                return null;
            }
        }

        public void mapToAdapterApiValue(Function<Integer, T> function) {
            this.mFunctionValueFn = function;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IValue
        public boolean canNotify(AbsCarFunction absCarFunction) {
            return false;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IValue
        public IVehicleFunction.Mode getCallbackMode() {
            return this.mCallbackMode;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IFilterCallback
        public IVehicleFunction.ITaskEnd<T> filterValue(IVehicleFunction.Mode mode, Predicate<Integer> predicate) {
            this.mCallbackMode = mode;
            this.mFilter = predicate;
            return this;
        }
    }

    public static class MultiSignalTask<T> extends AbsTask<T> implements IVehicleFunction.IMultiSignalStatus<T>, IVehicleFunction.IMultiSignalValue<T>, IVehicleFunction.IFilterCallback<T, Void> {
        private IVehicleFunction.Mode mCallbackMode;
        private Predicate<Void> mFilter;
        private Supplier<FunctionStatus> mPAStatusChangeFn;
        private Supplier<T> mPAValueChangeFn;
        private final int[] signals;

        static /* synthetic */ boolean lambda$new$0(Void r0) {
            return true;
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.ITaskEnd
        public /* bridge */ /* synthetic */ void addTo(Consumer consumer) {
            super.addTo(consumer);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.ITaskEnd
        public /* bridge */ /* synthetic */ IVehicleFunction.IZone createZone(VehicleType vehicleType, int[] iArr) {
            return super.createZone(vehicleType, iArr);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IFilterCallback customValue(Supplier supplier) {
            return super.customValue(supplier);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.ITask
        public /* bridge */ /* synthetic */ int getFunctionId() {
            return super.getFunctionId();
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.ITask
        public /* bridge */ /* synthetic */ int getZone() {
            return super.getZone();
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IValueTaskBuild onSetFunctionValue(Function function) {
            return super.onSetFunctionValue(function);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IFilterCallback useValuePA(int i, Function function) {
            return super.useValuePA(i, function);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IFilterCallback useValueSignal(int i, Function function) {
            return super.useValueSignal(i, function);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IMultiSignalValue useValueSignals(int[] iArr) {
            return super.useValueSignals(iArr);
        }

        public MultiSignalTask(VehicleFunction<T> vehicleFunction, ZoneTask<T> zoneTask, int... iArr) {
            super(vehicleFunction, zoneTask);
            this.mFilter = new Predicate() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$VehicleFunction$MultiSignalTask$z4NXqt0jzU5FxwXI1s_6DLE9-uQ
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return VehicleFunction.MultiSignalTask.lambda$new$0((Void) obj);
                }
            };
            this.mCallbackMode = IVehicleFunction.Mode.VALUE_CHANGE;
            this.signals = iArr;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IStatus
        public FunctionStatus getStatus(AbsCarFunction absCarFunction) {
            return this.mPAStatusChangeFn.get();
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IMultiSignalStatus
        public IVehicleFunction.IValueTaskBuild<T> onStatusSignalChanged(Supplier<FunctionStatus> supplier) {
            this.mPAStatusChangeFn = supplier;
            return this;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IValue
        public T getValue(AbsCarFunction absCarFunction) {
            try {
                return this.mPAValueChangeFn.get();
            } catch (Exception e) {
                Log.i(VehicleFunction.TAG, "getValue has a error", e);
                return null;
            }
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IMultiSignalValue
        public IVehicleFunction.IFilterCallback<T, Void> onValueSignalChanged(Supplier<T> supplier) {
            this.mPAValueChangeFn = supplier;
            return this;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IValue
        public boolean canNotify(AbsCarFunction absCarFunction) {
            return this.mFilter.test(null);
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IValue
        public IVehicleFunction.Mode getCallbackMode() {
            return this.mCallbackMode;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IFilterCallback
        public IVehicleFunction.ITaskEnd<T> filterValue(IVehicleFunction.Mode mode, Predicate<Void> predicate) {
            this.mCallbackMode = mode;
            this.mFilter = predicate;
            return this;
        }
    }

    public static class CustomValueTask<T> extends AbsTask<T> implements IVehicleFunction.IValue<T>, IVehicleFunction.IFilterCallback<T, Void> {
        private IVehicleFunction.Mode mCallbackMode;
        private Predicate<Void> mFilter;
        private final Supplier<T> mFunctionValueFn;

        static /* synthetic */ boolean lambda$new$0(Void r0) {
            return true;
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.ITaskEnd
        public /* bridge */ /* synthetic */ void addTo(Consumer consumer) {
            super.addTo(consumer);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.ITaskEnd
        public /* bridge */ /* synthetic */ IVehicleFunction.IZone createZone(VehicleType vehicleType, int[] iArr) {
            return super.createZone(vehicleType, iArr);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IFilterCallback customValue(Supplier supplier) {
            return super.customValue(supplier);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.ITask
        public /* bridge */ /* synthetic */ int getFunctionId() {
            return super.getFunctionId();
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.ITask
        public /* bridge */ /* synthetic */ int getZone() {
            return super.getZone();
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IValueTaskBuild onSetFunctionValue(Function function) {
            return super.onSetFunctionValue(function);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IFilterCallback useValuePA(int i, Function function) {
            return super.useValuePA(i, function);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IFilterCallback useValueSignal(int i, Function function) {
            return super.useValueSignal(i, function);
        }

        @Override
        // com.ecarx.xui.adaptapi.car.VehicleFunction.AbsTask, com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public /* bridge */ /* synthetic */ IVehicleFunction.IMultiSignalValue useValueSignals(int[] iArr) {
            return super.useValueSignals(iArr);
        }

        public CustomValueTask(VehicleFunction<T> vehicleFunction, ZoneTask<T> zoneTask, Supplier<T> supplier) {
            super(vehicleFunction, zoneTask);
            this.mFilter = new Predicate() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$VehicleFunction$CustomValueTask$ZU02Wt8zKcD08D1mYJo1dR8pY0A
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return VehicleFunction.CustomValueTask.lambda$new$0((Void) obj);
                }
            };
            this.mCallbackMode = IVehicleFunction.Mode.VALUE_CHANGE;
            this.mFunctionValueFn = supplier;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IValue
        public T getValue(AbsCarFunction absCarFunction) {
            try {
                return this.mFunctionValueFn.get();
            } catch (Exception e) {
                Log.i(VehicleFunction.TAG, "getValue has a error", e);
                return null;
            }
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IValue
        public boolean canNotify(AbsCarFunction absCarFunction) {
            return this.mFilter.test(null);
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IValue
        public IVehicleFunction.Mode getCallbackMode() {
            return this.mCallbackMode;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IFilterCallback
        public IVehicleFunction.ITaskEnd<T> filterValue(IVehicleFunction.Mode mode, Predicate<Void> predicate) {
            this.mCallbackMode = mode;
            this.mFilter = predicate;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static abstract class AbsTask<T> implements IVehicleFunction.ITask<T>, IVehicleFunction.IValueTaskBuild<T>, IVehicleFunction.ITaskEnd<T> {
        private final VehicleFunction<T> mVehicleFunction;
        private final ZoneTask<T> mZoneTask;

        public AbsTask(VehicleFunction<T> vehicleFunction, ZoneTask<T> zoneTask) {
            this.mVehicleFunction = vehicleFunction;
            this.mZoneTask = zoneTask;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public IVehicleFunction.IFilterCallback<T, Void> customValue(Supplier<T> supplier) {
            CustomValueTask customValueTask = new CustomValueTask(this.mVehicleFunction, this.mZoneTask, supplier);
            ((ZoneTask) this.mZoneTask).mValueTask = customValueTask;
            return customValueTask;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public <PA_TYPE> IVehicleFunction.IFilterCallback<T, PA_TYPE> useValuePA(int i, Function<PA_TYPE, T> function) {
            SinglePATask singlePATask = new SinglePATask(this.mVehicleFunction, this.mZoneTask, i);
            singlePATask.mapToAdapterApiValue(function);
            ((ZoneTask) this.mZoneTask).properties.add(Integer.valueOf(i));
            ((VehicleFunction) this.mVehicleFunction).mPropertyList.add(Integer.valueOf(i));
            ((ZoneTask) this.mZoneTask).mValueTask = singlePATask;
            return singlePATask;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public IVehicleFunction.IMultiSignalValue<T> useValueSignals(int... iArr) {
            MultiSignalTask multiSignalTask = new MultiSignalTask(this.mVehicleFunction, this.mZoneTask, iArr);
            Collection collection = (Collection) Arrays.stream(iArr).boxed().collect(Collectors.toList());
            ((ZoneTask) this.mZoneTask).properties.addAll(collection);
            ((VehicleFunction) this.mVehicleFunction).mPropertyList.addAll(collection);
            ((ZoneTask) this.mZoneTask).mValueTask = multiSignalTask;
            return multiSignalTask;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public IVehicleFunction.IFilterCallback<T, Integer> useValueSignal(int i, Function<Integer, T> function) {
            SingleSignalTask singleSignalTask = new SingleSignalTask(this.mVehicleFunction, this.mZoneTask, i);
            singleSignalTask.mapToAdapterApiValue(function);
            ((ZoneTask) this.mZoneTask).properties.add(Integer.valueOf(i));
            ((VehicleFunction) this.mVehicleFunction).mPropertyList.add(Integer.valueOf(i));
            ((ZoneTask) this.mZoneTask).mValueTask = singleSignalTask;
            return singleSignalTask;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.IValueTaskBuild
        public IVehicleFunction.IValueTaskBuild<T> onSetFunctionValue(final Function<T, ApiResult> function) {
            ((ZoneTask) this.mZoneTask).mSetFunctionValueFn = new Function() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$VehicleFunction$AbsTask$0eKG7ry-MeXuijw-q2wwtU698F8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return VehicleFunction.AbsTask.lambda$onSetFunctionValue$0(function, obj);
                }
            };
            return this;
        }

        static /* synthetic */ ApiResult lambda$onSetFunctionValue$0(Function function, Object obj) {
            ApiResult apiResult = ApiResult.FAILED;
            try {
                return (ApiResult) function.apply(obj);
            } catch (Exception e) {
                Log.e(VehicleFunction.TAG, "onSetFunctionValue has a error", e);
                return apiResult;
            }
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.ITask
        public int getFunctionId() {
            return ((VehicleFunction) this.mVehicleFunction).function;
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.ITask
        public int getZone() {
            return this.mZoneTask.getZone();
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.ITaskEnd
        public IVehicleFunction.IZone<T> createZone(VehicleType vehicleType, int... iArr) {
            this.mZoneTask.lazyCreateZoneTask();
            return this.mVehicleFunction.createZone(vehicleType, iArr);
        }

        @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.ITaskEnd
        public void addTo(Consumer<VehicleFunction<T>> consumer) {
            this.mZoneTask.lazyCreateZoneTask();
            consumer.accept(this.mVehicleFunction);
        }
    }

    public static class Data<T> {
        private FunctionStatus mStatus;
        private int[] mSupportValues = new int[0];
        private T mValue;
        private boolean statusChanged;
        private boolean supportValueChanged;
        private boolean valueChanged;

        public FunctionStatus getStatus() {
            return this.mStatus;
        }

        public void setStatus(FunctionStatus functionStatus) {
            this.statusChanged = this.mStatus != functionStatus;
            this.mStatus = functionStatus;
        }

        public T getValue() {
            return this.mValue;
        }

        public void setValue(IVehicleFunction.Mode mode, boolean z, T t) {
            boolean z2 = false;
            if (t != null) {
                this.valueChanged = false;
                if (this.mStatus != FunctionStatus.notavailable) {
                    if (mode == IVehicleFunction.Mode.ALWAYS) {
                        this.valueChanged = z;
                    } else {
                        if (z && !t.equals(this.mValue)) {
                            z2 = true;
                        }
                        this.valueChanged = z2;
                    }
                }
                this.mValue = t;
                return;
            }
            this.valueChanged = false;
        }

        public int[] getSupportValues() {
            return this.mSupportValues;
        }

        public void setSupportValues(int[] iArr) {
            this.supportValueChanged = (this.mStatus == FunctionStatus.notavailable || Arrays.equals(this.mSupportValues, iArr)) ? false : true;
            this.mSupportValues = iArr;
        }

        public boolean isStatusChanged() {
            return this.statusChanged;
        }

        public boolean isValueChanged() {
            return this.valueChanged;
        }

        public boolean isSupportValueChanged() {
            return this.supportValueChanged;
        }

        public String toString() {
            return "Data{mStatus=" + this.mStatus + ", mSupportValues=" + Arrays.toString(this.mSupportValues) + ", mValue=" + this.mValue + ", statusChanged=" + this.statusChanged + ", supportValueChanged=" + this.supportValueChanged + ", valueChanged=" + this.valueChanged + '}';
        }
    }
}
