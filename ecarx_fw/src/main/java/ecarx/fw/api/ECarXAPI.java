package ecarx.fw.api;

import android.content.Context;

import ecarx.fw.api.business.IEcarXBusiness;
import ecarx.fw.api.business.hardkey.IEcarXHardKey;
import ecarx.fw.api.business.hardkey.IEcarXHardKeyCallback;
import ecarx.fw.api.exceptions.APIInstantiationException;

public final class ECarXAPI {

    private ECarXAPI() {
    }

    public static <T> Creator<T> creator(Class<T> cls) {
        // Returns a stub creator that yields no-op implementations.
        return new Creator<>();
    }

    /**
     * Generic stub creator that returns no-op implementations.
     */
    public static final class Creator<T> {

        /**
         * Returns a lightweight no-op implementation when possible.
         * If T is unknown, throws to mimic "not available in this environment".
         */
        @SuppressWarnings("unchecked")
        public T create(Context context) throws APIInstantiationException {
            if (IEcarXBusiness.class.getName().equals(getErasedName())) {
                return (T) new IEcarXBusiness() {
                    private final IEcarXHardKey hardKey = new IEcarXHardKey() {
                        @Override
                        public int[] requestHardKeyEvent(int[] keys, IEcarXHardKeyCallback cb, int cookie) {
                            return new int[0];
                        }

                        @Override
                        public void abandonHardKeyEvent(IEcarXHardKeyCallback cb, int cookie) { /* no-op */ }

                        @Override
                        public long getInputSettingDuration(int type) {
                            return -1L;
                        }

                        @Override
                        public int getInputSettingValue(int type) {
                            return -1;
                        }

                        @Override
                        public int getSteerWheelType() {
                            return -1;
                        }

                        @Override
                        public int getCustomFunctionType() {
                            return -1;
                        }
                    };

                    @Override
                    public IEcarXHardKey getEcarXHardKey() {
                        return hardKey;
                    }
                };
            }
            // For any other requested type we fail fast in stubs.
            throw new APIInstantiationException("ECarXAPI stub cannot create instance for " + getErasedName());
        }

        // Helper to keep bytecode tiny in stubs.
        private String getErasedName() {
            // In a real impl we’d track the target Class<T>.
            // For stubs we only support IEcarXBusiness path.
            return "ecarx.fw.api.business.IEcarXBusiness";
        }
    }
}
