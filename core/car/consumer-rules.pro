
-keep class com.ecarx.xui.** { *; }
-keepnames class com.ecarx.xui.** { *; }

-keep class ecarx.car.** { *; }
-keepnames class ecarx.car.** { *; }

-keep class ecarx.fw.** { *; }
-keepnames class ecarx.fw.** { *; }

-keep class com.geely.** { *; }
-keepnames class com.geely.** { *; }

-keep class com.tencent.** { *; }
-keepnames class com.tencent.** { *; }

-keep class com.ecarx.xui.adaptapi.binder.IConnectable$IConnectWatcher { *; }

-if @kotlinx.serialization.Serializable class **
-keepclassmembers class <1> {
    public static <1> INSTANCE;
    #noinspection ShrinkerUnresolvedReference
    kotlinx.serialization.KSerializer serializer(...);
}
