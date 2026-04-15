package com.geely.lib.oneosapi.bean.util;

import com.geely.lib.oneosapi.bean.BaseMediaBean;
import com.geely.lib.oneosapi.bean.BaseSongItemBean;
import com.geely.lib.oneosapi.bean.NewsItemBean;
import com.geely.lib.oneosapi.bean.NewsMediaBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class BeanUtils {
    public static List<NewsMediaBean> convertNewsFeedList(List<NewsItemBean> newsList) {
        ArrayList arrayList = new ArrayList();
        if (newsList != null && !newsList.isEmpty()) {
            for (NewsItemBean newsItemBean : newsList) {
                NewsMediaBean newsMediaBean = new NewsMediaBean();
                newsMediaBean.setItemId(newsItemBean.getDocid());
                newsMediaBean.setItemType("news");
                newsMediaBean.setItemAuthor(newsItemBean.getSrcfrom());
                newsMediaBean.setItemImageUrl(newsItemBean.getShortcut());
                newsMediaBean.setItemTitle(newsItemBean.getTitle());
                newsMediaBean.setPublishTime(newsItemBean.getPubtime());
                newsMediaBean.setSourceInfo(newsItemBean.getSourceInfo());
                arrayList.add(newsMediaBean);
            }
        }
        return arrayList;
    }

    public static List<BaseSongItemBean> filterUnplayableMusic(List<BaseSongItemBean> songlist) {
        return filterUnplayableMusic(songlist, false);
    }

    public static List<BaseSongItemBean> filterUnplayableMusic(List<BaseSongItemBean> songlist, boolean include) {
        ArrayList arrayList = new ArrayList();
        if (songlist == null || songlist.isEmpty()) {
            return arrayList;
        }
        for (BaseSongItemBean baseSongItemBean : songlist) {
            if (baseSongItemBean != null && (baseSongItemBean.isPlayable() || include)) {
                baseSongItemBean.setItemAuthor(baseSongItemBean.getSinger_name());
                baseSongItemBean.setItemImageUrl(baseSongItemBean.getAlbum_pic_300x300());
                baseSongItemBean.setItemId("" + baseSongItemBean.getSong_id());
                baseSongItemBean.setItemTitle(baseSongItemBean.getSong_name());
                arrayList.add(baseSongItemBean);
                baseSongItemBean.setItemType("song");
            }
        }
        return arrayList;
    }

    public static String getMediaIdString(List<BaseMediaBean> beans) {
        if (beans == null || beans.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<BaseMediaBean> it = beans.iterator();
        while (it.hasNext()) {
            sb.append(it.next().getItemId());
            sb.append(",");
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }
}