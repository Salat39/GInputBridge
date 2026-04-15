package com.geely.lib.oneosapi.mediacenter.bean;

import java.util.List;

/* loaded from: classes.dex */
public class SearchBean {
    public static final String DOMAIN_VALUE = "music";
    public static final String INTENT_VALUE = "play";
    public static final String QUERY_VALUE = "我想听歌";
    public static final String QUERY_VALUE_FM = "我想听电台";
    public static final String QUERY_VALUE_MUSIC = "我想听音乐";
    public Semantic semantic = new Semantic();

    public static class Slot {
        public String type;
        public List<Text> values;
    }

    public static class Text {
        public String text;
    }

    public class Semantic {
        public String domain = "music";
        public String intent = "play";
        public String query;
        public List<Slot> slots;

        public Semantic() {
        }
    }
}