package com.ecarx.xui.adaptapi.car;

import android.content.Context;
import android.os.SystemClock;
import android.util.SparseArray;

import com.ecarx.xui.adaptapi.AbsCarSignal;
import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.car.base.ICarFunction;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public abstract class AbsVehicle extends AbsCarSignal implements ICarFunction {
    private static final int ALL_FUNCTION_ID = -1;
    private static final String TAG = "AbsVehicle";
    private final SparseArray<ArrayList<ICarFunction.IFunctionValueWatcher>> mCallbackArray;
    private final Object mCallbackLock;
    private final int mCarModule;

    public int[] getSupportedFunctionValue(int i, int i2) {
        return new int[0];
    }

    public int[] getSupportedFunctionZones(int i) {
        return new int[0];
    }

    public abstract boolean validCarFunctionFlt(int i);

    public abstract boolean validCarFunctionInt(int i);

    protected AbsVehicle(Context context, int i) {
        super(context);
        this.mCallbackLock = new Object();
        this.mCarModule = i;
        SparseArray<ArrayList<ICarFunction.IFunctionValueWatcher>> sparseArray = new SparseArray<>();
        this.mCallbackArray = sparseArray;
        sparseArray.put(-1, new ArrayList<>());
    }

    public int getCarModule() {
        return this.mCarModule;
    }

    public FunctionStatus isFunctionSupported(int i) {
        return isFunctionSupported(i, Integer.MIN_VALUE);
    }

    public FunctionStatus isFunctionSupported(int i, int i2) {
        return isFunctionSupported(i, i2, 0);
    }

    public boolean setFunctionValue(int i, int i2) {
        return setFunctionValue(i, Integer.MIN_VALUE, i2);
    }

    public int getFunctionValue(int i) {
        return getFunctionValue(i, Integer.MIN_VALUE);
    }

    public boolean setCustomizeFunctionValue(int i, float f) {
        return setCustomizeFunctionValue(i, Integer.MIN_VALUE, f);
    }

    public float getCustomizeFunctionValue(int i) {
        return getCustomizeFunctionValue(i, Integer.MIN_VALUE);
    }

    public boolean registerFunctionValueWatcher(ICarFunction.IFunctionValueWatcher iFunctionValueWatcher) {
        synchronized (this.mCallbackLock) {
            ArrayList<ICarFunction.IFunctionValueWatcher> arrayList = this.mCallbackArray.get(-1);
            if (arrayList != null) {
                arrayList.remove(iFunctionValueWatcher);
                arrayList.add(iFunctionValueWatcher);
            }
        }
        return true;
    }

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
        return true;
    }

    public boolean registerFunctionValueWatcher(int[] iArr, ICarFunction.IFunctionValueWatcher iFunctionValueWatcher) {
        synchronized (this.mCallbackLock) {
            for (int i : iArr) {
                Integer numValueOf = Integer.valueOf(i);
                if (this.mCallbackArray.indexOfKey(numValueOf.intValue()) < 0) {
                    this.mCallbackArray.put(numValueOf.intValue(), new ArrayList<>());
                }
                ArrayList<ICarFunction.IFunctionValueWatcher> arrayList = this.mCallbackArray.get(numValueOf.intValue());
                if (arrayList != null && !arrayList.contains(iFunctionValueWatcher)) {
                    arrayList.add(iFunctionValueWatcher);
                }
            }
        }
        return true;
    }

    public boolean unregisterFunctionValueWatcher(ICarFunction.IFunctionValueWatcher iFunctionValueWatcher) {
        synchronized (this.mCallbackLock) {
            int size = this.mCallbackArray.size();
            for (int i = 0; i < size; i++) {
                this.mCallbackArray.valueAt(i).remove(iFunctionValueWatcher);
            }
        }
        return true;
    }

    public void onFunctionChanged(int i) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        Iterator<ICarFunction.IFunctionValueWatcher> it = getCallbackList(i).iterator();
        while (it.hasNext()) {
            try {
                it.next().onFunctionChanged(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

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

    public void onSupportedFunctionStatusChanged(int i, int i2, FunctionStatus functionStatus) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        Iterator<ICarFunction.IFunctionValueWatcher> it = getCallbackList(i).iterator();
        while (it.hasNext()) {
            try {
                it.next().onSupportedFunctionStatusChanged(i, i2, functionStatus);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onSupportedFunctionValueChanged(int i, int[] iArr) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        Iterator<ICarFunction.IFunctionValueWatcher> it = getCallbackList(i).iterator();
        while (it.hasNext()) {
            try {
                it.next().onSupportedFunctionValueChanged(i, iArr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean validCarFunction(int i) {
        return validCarFunctionInt(i) || validCarFunctionFlt(i);
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
}
