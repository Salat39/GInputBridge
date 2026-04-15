package com.ecarx.xui.adaptapi.car;

import com.ecarx.xui.adaptapi.FunctionStatus;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import ecarx.car.hardware.annotation.ApiResult;
import ecarx.car.hardware.vehicle.PATypes;

/* loaded from: classes.dex */
public interface IVehicleFunction<T> {

    public interface IAssociatedStatus<T> extends IStatus<T> {
        int getAssociatedFunction();
    }

    public interface IMultiSignalStatus<T> extends IStatus<T> {
        IValueTaskBuild<T> onStatusSignalChanged(Supplier<FunctionStatus> supplier);
    }

    public interface IMultiSignalValue<T> extends IValue<T> {
        IFilterCallback<T, Void> onValueSignalChanged(Supplier<T> supplier);
    }

    public interface IStatus<T> extends ITask<T> {
        FunctionStatus getStatus(AbsCarFunction absCarFunction);
    }

    public interface ITask<T> {
        int getFunctionId();

        int getZone();
    }

    public interface IValue<T> extends ITask<T> {
        boolean canNotify(AbsCarFunction absCarFunction);

        Mode getCallbackMode();

        T getValue(AbsCarFunction absCarFunction);
    }

    public enum Mode {
        VALUE_CHANGE,
        ALWAYS
    }

    public interface SupportFunctionValue {
        int[] get();
    }

    IZone<T> createZone(VehicleType vehicleType, int... iArr);

    IVehicleFunction<T> supportDriverSide(Function<Integer, Integer> function);

    IVehicleFunction<T> supportedFunctionValue(int... iArr);

    default IZone<T> createZone() {
        return createZone(Integer.MIN_VALUE);
    }

    default IZone<T> createZone(VehicleType vehicleType) {
        return createZone(vehicleType, Integer.MIN_VALUE);
    }

    default IZone<T> createZone(int... iArr) {
        return createZone(VehicleType.COMMON, iArr);
    }

    public interface IZone<T> extends IStatusTaskBuild<T> {
        static /* synthetic */ int[] lambda$supportedFunctionValue$0(int[] iArr) {
            return iArr;
        }

        IStatusTaskBuild<T> supportedFunctionValue(SupportFunctionValue supportFunctionValue, int... iArr);

        default IStatusTaskBuild<T> supportedFunctionValue(final int... iArr) {
            return supportedFunctionValue(new SupportFunctionValue() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$IVehicleFunction$IZone$p_M3tMOOfdhOjTOE5AYvnHR8Ux4
                @Override // com.ecarx.xui.adaptapi.car.IVehicleFunction.SupportFunctionValue
                public final int[] get() {
                    return IVehicleFunction.IZone.lambda$supportedFunctionValue$0(iArr);
                }
            }, new int[0]);
        }
    }

    public interface IStatusTaskBuild<T> {
        static /* synthetic */ FunctionStatus lambda$fixStatus$1(FunctionStatus functionStatus) {
            return functionStatus;
        }

        IValueTaskBuild<T> customStatus(Supplier<FunctionStatus> supplier);

        IValueTaskBuild<T> useOtherFunctionStatus(int i);

        <PA_TYPE> IValueTaskBuild<T> useStatusPA(int i, Function<PA_TYPE, FunctionStatus> function);

        IValueTaskBuild<T> useStatusSignal(int i, Function<Integer, FunctionStatus> function);

        IMultiSignalStatus<T> useStatusSignals(int... iArr);

        default IValueTaskBuild<T> useStatusPAByIntBase(int i) {
            return useStatusPAByIntBase(i, new Function() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$IVehicleFunction$IStatusTaskBuild$NVeQLYWycG3vRuAuaF3B5G1c83c
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return null;
                }
            });
        }

        /* JADX WARN: Multi-variable type inference failed */
        default IValueTaskBuild<T> useStatusPAByIntBase(int i, Function<PATypes.PA_IntBase, FunctionStatus> function) {
            return useStatusPA(i, function);
        }

        default IValueTaskBuild<T> fixStatus(final FunctionStatus functionStatus) {
            return customStatus(new Supplier() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$IVehicleFunction$IStatusTaskBuild$F8_xR1Oqf7mCHmXp9-ApFFPO4Gw
                @Override // java.util.function.Supplier
                public final Object get() {
                    return IVehicleFunction.IStatusTaskBuild.lambda$fixStatus$1(functionStatus);
                }
            });
        }
    }

    public interface IValueTaskBuild<T> extends ITaskEnd<T> {
        IFilterCallback<T, Void> customValue(Supplier<T> supplier);

        IValueTaskBuild<T> onSetFunctionValue(Function<T, ApiResult> function);

        <PA_TYPE> IFilterCallback<T, PA_TYPE> useValuePA(int i, Function<PA_TYPE, T> function);

        IFilterCallback<T, Integer> useValueSignal(int i, Function<Integer, T> function);

        IMultiSignalValue<T> useValueSignals(int... iArr);

        default IFilterCallback<T, Integer> useValueSignal(int i, Pairs<Integer, T> pairs) {
            Objects.requireNonNull(pairs);
            return null;
        }

        default IFilterCallback<T, PATypes.PA_IntBase> useValuePAByIntBase(int i, Pairs<Integer, T> pairs) {
            Objects.requireNonNull(pairs);
            return null;
        }

        default IFilterCallback<T, PATypes.PA_IntBase> useValuePAByIntBase(int i, final Function<Integer, T> function) {
            return (IFilterCallback<T, PATypes.PA_IntBase>) useValuePA(i, new Function() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$IVehicleFunction$IValueTaskBuild$NtnJJOmWvgIemXGdKKRWRP-Fic8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return null;
                }
            });
        }

        default IValueTaskBuild<T> onSetFunctionValue(Function<Integer, ApiResult> function, Function<T, Integer> function2) {
            return onSetFunctionValue(function2.andThen(function));
        }

        default IValueTaskBuild<T> onSetFunctionValue(final Function<Integer, ApiResult> function, final Function<T, Integer> function2, final Predicate<T> predicate) {
            return onSetFunctionValue(new Function() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$IVehicleFunction$IValueTaskBuild$fhUgA6s8-xw48DI7MmdDP8cCzL0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return IVehicleFunction.IValueTaskBuild.lambda$onSetFunctionValue$1(predicate, function, function2, obj);
                }
            });
        }

        static /* synthetic */ ApiResult lambda$onSetFunctionValue$1(Predicate predicate, Function function, Function function2, Object obj) {
            return predicate.test(obj) ? (ApiResult) function.apply((Integer) function2.apply(obj)) : ApiResult.PARAM_ERROR;
        }

        default IValueTaskBuild<T> onSetFunctionValue(final Function<Integer, ApiResult> function, final Pairs<T, Integer> pairs) {
            Function<Integer, ApiResult> function2 = new Function() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$IVehicleFunction$IValueTaskBuild$pd1iC94f7nRtVNEfjNuoMyK74gQ
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return IVehicleFunction.IValueTaskBuild.lambda$onSetFunctionValue$2(function, (Integer) obj);
                }
            };
            Objects.requireNonNull(pairs);
            return onSetFunctionValue(function2, new Function() { // from class: com.ecarx.xui.adaptapi.car.-$$Lambda$C3BZhjM24o-jCVrnWC-hHkQNLAM
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return null;
                }
            });
        }

        static /* synthetic */ ApiResult lambda$onSetFunctionValue$2(Function function, Integer num) {
            return num == null ? ApiResult.PARAM_ERROR : (ApiResult) function.apply(num);
        }
    }

    public interface IFilterCallback<T, PA_TYPE> extends ITaskEnd<T> {
        ITaskEnd<T> filterValue(Mode mode, Predicate<PA_TYPE> predicate);

        default ITaskEnd<T> filterValue(Predicate<PA_TYPE> predicate) {
            return filterValue(Mode.VALUE_CHANGE, predicate);
        }
    }

    public interface ITaskEnd<T> {
        void addTo(Consumer<VehicleFunction<T>> consumer);

        IZone<T> createZone(VehicleType vehicleType, int... iArr);

        default IZone<T> createZone() {
            return createZone(Integer.MIN_VALUE);
        }

        default IZone<T> createZone(VehicleType vehicleType) {
            return createZone(vehicleType, Integer.MIN_VALUE);
        }

        default IZone<T> createZone(int... iArr) {
            return createZone(VehicleType.COMMON, iArr);
        }
    }
}