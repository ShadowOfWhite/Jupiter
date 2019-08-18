package com.example.latte.ec.detail;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.latte_core.util.toast.ToastUtil;


import java.util.ArrayList;

/**
 * 作者：贪欢
 * 时间：2019/8/13
 * 描述：
 */
public class TabPagerAdapter extends FragmentStatePagerAdapter {


    private final ArrayList<String> TAB_TITLES = new ArrayList<>();
    private final ArrayList<ArrayList<String>> PICTURE = new ArrayList<>();

    public TabPagerAdapter(FragmentManager fm, JSONObject data) {
        super(fm);
        //获取tabs的信息，注意，这里的tabs是一条信息
        final JSONArray tabs = data.getJSONArray("tabs");
        int size = tabs.size();
        for (int i = 0; i < size; i++) {
            final JSONObject eachTab = tabs.getJSONObject(i);
            final String name = eachTab.getString("name");
            final JSONArray pictureUrls = eachTab.getJSONArray("pictures");
            final ArrayList<String> eachTabPicturesArray = new ArrayList<>();
            //存储每个图片
            int pictureSize = pictureUrls.size();
            for (int j = 0; j < pictureSize; j++) {
                eachTabPicturesArray.add(pictureUrls.getString(j));
            }
            TAB_TITLES.add(name);
            PICTURE.add(eachTabPicturesArray);

        }
    }

    @Override
    public Fragment getItem(int position) {

        ToastUtil.showText("当前："+position);
        if (position == 0){
            return ImageFragment.create(PICTURE.get(0));
        }else if (position == 1){
            return ImageFragment.create(PICTURE.get(1));
        }
        return null;
    }

    @Override
    public int getCount() {
        return PICTURE.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES.get(position);
    }
}
