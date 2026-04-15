package com.geely.lib.oneosapi.bean;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.Toast;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class BaseSongItemBean implements Parcelable {
    public static final Parcelable.Creator<BaseSongItemBean> CREATOR = new Parcelable.Creator<BaseSongItemBean>() { // from class: com.geely.lib.oneosapi.bean.BaseSongItemBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseSongItemBean createFromParcel(Parcel in) {
            return new BaseSongItemBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseSongItemBean[] newArray(int size) {
            return new BaseSongItemBean[size];
        }
    };
    public static final String TYPE_BOOK = "book";
    public static final String TYPE_NEWS = "news";
    public static final String TYPE_RADIO = "radio";
    public static final String TYPE_SONG = "song";
    String album_id;
    String album_mid;
    String album_name;
    String album_pic_300x300;
    String genre;
    int hot;
    boolean isSelect;
    private String itemAuthor;
    private String itemId;
    private String itemImageUrl;
    private int itemIndex;
    private int itemNextUid;
    private boolean itemRead;
    private String itemTitle;
    private int itemTotal;
    protected String itemType;
    List<SingerItemBean> other_singer_list;
    int playable;
    String public_time;
    String singer_id;
    String singer_mid;
    String singer_name;
    String singer_pic_300x300;
    String singer_title;
    long song_id;
    String song_list_id;
    String song_mid;
    String song_name;
    long song_play_time;
    String song_play_url;
    String song_play_url_hq;
    String song_play_url_sq;
    String song_play_url_standard;
    int song_size;
    int song_size_hq;
    int song_size_sq;
    int song_size_standard;

    private String sourceInfo;
    int try_playable;
    int unplayable_code;
    String unplayable_msg;
    int user_own_rule;
    long uuid;
    int vip;

    public static final class UnplayableCode {
        public static final int ERROR = -1;
        public static final int ERROR0 = 0;
        public static final int ERROR1 = 1;
        public static final int ERROR10 = 11;
        public static final int ERROR2 = 2;
        public static final int ERROR3 = 3;
        public static final int ERROR4 = 4;
        public static final int ERROR5 = 5;
        public static final int ERROR6 = 7;
        public static final int ERROR7 = 8;
        public static final int ERROR8 = 9;
        public static final int ERROR9 = 10;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getItemType() {
        return "song";
    }

    public boolean isItemOver() {
        return false;
    }

    public boolean isItemRead() {
        return false;
    }

    public void setItemOver(boolean flag) {
    }

    protected BaseSongItemBean(Parcel in) {
        this.isSelect = false;
        this.itemType = in.readString();
        this.itemTitle = in.readString();
        this.itemAuthor = in.readString();
        this.itemId = in.readString();
        this.itemIndex = in.readInt();
        this.itemImageUrl = in.readString();
        this.sourceInfo = in.readString();
        this.itemRead = in.readByte() != 0;
        this.itemNextUid = in.readInt();
        this.itemTotal = in.readInt();
        this.uuid = in.readLong();
        this.user_own_rule = in.readInt();
        this.playable = in.readInt();
        this.album_mid = in.readString();
        this.album_id = in.readString();
        this.album_name = in.readString();
        this.singer_pic_300x300 = in.readString();
        this.album_pic_300x300 = in.readString();
        this.singer_title = in.readString();
        this.singer_name = in.readString();
        this.song_id = in.readLong();
        this.song_mid = in.readString();
        this.song_name = in.readString();
        this.song_play_url = in.readString();
        this.song_play_url_standard = in.readString();
        this.song_play_url_hq = in.readString();
        this.song_play_url_sq = in.readString();
        this.song_size = in.readInt();
        this.song_size_standard = in.readInt();
        this.song_size_hq = in.readInt();
        this.song_size_sq = in.readInt();
        this.public_time = in.readString();
        this.singer_id = in.readString();
        this.singer_mid = in.readString();
        this.hot = in.readInt();
        this.isSelect = in.readByte() != 0;
        this.song_play_time = in.readLong();
        this.song_list_id = in.readString();
        this.unplayable_code = in.readInt();
        this.unplayable_msg = in.readString();
        this.try_playable = in.readInt();
        this.vip = in.readInt();
        this.genre = in.readString();
    }

    public void setItemRead(boolean itemRead) {
        this.itemRead = itemRead;
    }

    public int getItemNextUid() {
        return this.itemNextUid;
    }

    public void setItemNextUid(int itemNextUid) {
        this.itemNextUid = itemNextUid;
    }

    public int getItemTotal() {
        return this.itemTotal;
    }

    public void setItemTotal(int itemTotal) {
        this.itemTotal = itemTotal;
    }

    public String getItemImageUrl() {
        return this.itemImageUrl;
    }

    public void setItemImageUrl(String imageUrl) {
        this.itemImageUrl = imageUrl;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public void setItemAuthor(String itemAuthor) {
        this.itemAuthor = itemAuthor;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setItemIndex(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    public String getItemAuthor() {
        return this.itemAuthor;
    }

    public String getItemId() {
        return this.itemId;
    }

    public int getItemIndex() {
        return this.itemIndex;
    }

    public String getSourceInfo() {
        return this.sourceInfo;
    }

    public void setSourceInfo(String sourceInfo) {
        this.sourceInfo = sourceInfo;
    }

    public String getSinger_pic_300x300() {
        return this.singer_pic_300x300;
    }

    public long getSong_id() {
        return this.song_id;
    }

    public String getAlbum_name() {
        return this.album_name;
    }

    public String getSong_play_url_sq() {
        return TextUtils.isEmpty(this.song_play_url_sq) ? getSong_play_url_hq() : this.song_play_url_sq;
    }

    public String getSong_play_url_hq() {
        return TextUtils.isEmpty(this.song_play_url_hq) ? getSong_play_url_standard() : this.song_play_url_hq;
    }

    public String getSong_play_url_standard() {
        return TextUtils.isEmpty(this.song_play_url_standard) ? getSong_play_url() : this.song_play_url_standard;
    }

    public String getSong_mid() {
        return this.song_mid;
    }

    public String getSong_play_url() {
        return this.song_play_url;
    }

    public String getSinger_name() {
        List<SingerItemBean> list = this.other_singer_list;
        String str = "";
        if (list != null && !list.isEmpty()) {
            Iterator<SingerItemBean> it = this.other_singer_list.iterator();
            while (it.hasNext()) {
                str = str + "  " + it.next().getSinger_name();
            }
        }
        return this.singer_name + str;
    }

    public String getSong_name() {
        return this.song_name;
    }

    public String getSinger_title() {
        return this.singer_title;
    }

    public int getSong_size_standard() {
        return this.song_size_standard;
    }

    public int getSong_size() {
        return this.song_size;
    }

    public int getSong_size_hq() {
        return this.song_size_hq;
    }

    public int getSong_size_sq() {
        return this.song_size_sq;
    }

    public String getPublic_time() {
        return this.public_time;
    }

    public List<SingerItemBean> getOther_singer_list() {
        return this.other_singer_list;
    }

    public String getAlbum_pic_300x300() {
        return !TextUtils.isEmpty(this.album_pic_300x300) ? this.album_pic_300x300 : getSinger_pic_300x300();
    }

    public boolean isPlayable() {
        return this.user_own_rule == 1 && this.playable == 1;
    }

    public String getItemTitle() {
        return this.song_name;
    }

    public boolean isItemPlayDirect() {
        return !TextUtils.isEmpty(getSong_play_url_sq());
    }

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setSelect(boolean flag) {
        this.isSelect = flag;
    }

    public long getUuid() {
        return this.uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }

    public String getAlbum_id() {
        return this.album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    public long getSong_play_time() {
        return this.song_play_time;
    }

    public void setSong_play_time(long song_play_time) {
        this.song_play_time = song_play_time;
    }

    public String toString() {
        return "BaseSongItemBean{album_pic_300x300='" + this.album_pic_300x300 + "', singer_name='" + this.singer_name + "', song_mid='" + this.song_mid + "', song_name='" + this.song_name + "'}";
    }

    public int getVip() {
        return this.vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getUnplayable_code() {
        return this.unplayable_code;
    }

    public void setUnplayable_code(int unplayable_code) {
        this.unplayable_code = unplayable_code;
    }

    public String getSong_list_id() {
        return this.song_list_id;
    }

    public void setSong_list_id(String song_list_id) {
        this.song_list_id = song_list_id;
    }

    public String getUnplayable_msg() {
        return this.unplayable_msg;
    }

    public void setUnplayable_msg() {
        this.unplayable_msg = this.unplayable_msg;
    }

    public int getTry_playable() {
        return this.try_playable;
    }

    public void setTry_playable(int try_playable) {
        this.try_playable = try_playable;
    }

    public boolean checkPlayEnable(Context context, boolean autoToast) {
        if (getUnplayable_code() == 0 || this.try_playable == 1) {
            return true;
        }
        if (autoToast) {
            Toast.makeText(context, this.unplayable_msg, 0).show();
        }
        return false;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.itemType);
        parcel.writeString(this.itemTitle);
        parcel.writeString(this.itemAuthor);
        parcel.writeString(this.itemId);
        parcel.writeInt(this.itemIndex);
        parcel.writeString(this.itemImageUrl);
        parcel.writeString(this.sourceInfo);
        parcel.writeByte(this.itemRead ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.itemNextUid);
        parcel.writeInt(this.itemTotal);
        parcel.writeLong(this.uuid);
        parcel.writeInt(this.user_own_rule);
        parcel.writeInt(this.playable);
        parcel.writeString(this.album_mid);
        parcel.writeString(this.album_id);
        parcel.writeString(this.album_name);
        parcel.writeString(this.singer_pic_300x300);
        parcel.writeString(this.album_pic_300x300);
        parcel.writeString(this.singer_title);
        parcel.writeString(this.singer_name);
        parcel.writeLong(this.song_id);
        parcel.writeString(this.song_mid);
        parcel.writeString(this.song_name);
        parcel.writeString(this.song_play_url);
        parcel.writeString(this.song_play_url_standard);
        parcel.writeString(this.song_play_url_hq);
        parcel.writeString(this.song_play_url_sq);
        parcel.writeInt(this.song_size);
        parcel.writeInt(this.song_size_standard);
        parcel.writeInt(this.song_size_hq);
        parcel.writeInt(this.song_size_sq);
        parcel.writeString(this.public_time);
        parcel.writeString(this.singer_id);
        parcel.writeString(this.singer_mid);
        parcel.writeInt(this.hot);
        parcel.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.song_play_time);
        parcel.writeString(this.song_list_id);
        parcel.writeInt(this.unplayable_code);
        parcel.writeString(this.unplayable_msg);
        parcel.writeInt(this.try_playable);
        parcel.writeInt(this.vip);
        parcel.writeString(this.genre);
    }
}