package com.example.asus.startproject.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.latte_core.delegates.web.event.Event;

/**
 * 作者：贪欢
 * 时间：2019/8/10
 * 描述：
 */
public class ShareEvent extends Event {
    @Override
    public String execute(String params) {

        final JSONObject object = JSON.parseObject(params).getJSONObject(params);
        String title = object.getString("title");
        String url = object.getString("url");
        String imageUrl = object.getString("imageUrl");
        String text = object.getString("text");


        return null;
    }
}
