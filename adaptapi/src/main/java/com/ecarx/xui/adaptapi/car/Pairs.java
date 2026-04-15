package com.ecarx.xui.adaptapi.car;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class Pairs<F, S> {
    private static final String TAG = "Pairs";
    private final S mDefaultValue;
    private final Map<F, S> mValues;

    private Pairs(S s) {
        this.mValues = new HashMap();
        this.mDefaultValue = s;
    }

    private Pairs(F f, S s) {
        this(null);
        add(f, s);
    }

    public static <F, S> Pairs<F, S> defaultValue(S s) {
        return new Pairs<>(s);
    }

    public static <F, S> Pairs<F, S> of(F f, S s) {
        return new Pairs<>(f, s);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Pairs<F, S> add(F f, S s) {
        if (this.mValues.containsKey(f)) {
            if (f instanceof Integer) {
                Log.w(TAG, "The key [0X" + Integer.toHexString(((Integer) f).intValue()) + "::" + f + "] is exist.");
            } else {
                Log.w(TAG, "The key " + f + " is exist.");
            }
        }
        this.mValues.put(f, s);
        return this;
    }

    public S getValue(F f) {
        return this.mValues.getOrDefault(f, this.mDefaultValue);
    }

    public Pairs<S, F> reverse() {
        Pairs<S, F> pairs = new Pairs<>(null);
        for (F f : this.mValues.keySet()) {
            pairs.add(this.mValues.get(f), f);
        }
        return pairs;
    }
}
