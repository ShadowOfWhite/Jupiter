package com.example.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.latte.ec.database.DatabaseManager;
import com.example.latte.ec.database.UserProfile;
import com.example.latte_core.app.AccountManager;

/**
 * 作者：贪欢
 * 时间：2019/6/1
 * 描述：
 */
public class SignHandler {

    public static void onSignUp(String response,ISignListener signListener){

    /*   final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        long userId = profileJson.getLong("userId");
        String name = profileJson.getString("name");
        String avatar = profileJson.getString("avatar");
        String gender = profileJson.getString("gender");
        String address = profileJson.getString("address");

        final UserProfile userProfile = new UserProfile();
        userProfile.setUserId(userId);
        userProfile.setName(name);
        userProfile.setAddress(address);
        userProfile.setAvatar(avatar);
        userProfile.setGender(gender);
        DatabaseManager.getInstance().getDao().insert(userProfile);*/

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }

    public static void onSignIn(String response,ISignListener signListener){

    /*   final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        long userId = profileJson.getLong("userId");
        String name = profileJson.getString("name");
        String avatar = profileJson.getString("avatar");
        String gender = profileJson.getString("gender");
        String address = profileJson.getString("address");

        final UserProfile userProfile = new UserProfile();
        userProfile.setUserId(userId);
        userProfile.setName(name);
        userProfile.setAddress(address);
        userProfile.setAvatar(avatar);
        userProfile.setGender(gender);
        DatabaseManager.getInstance().getDao().insert(userProfile);*/

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }
}
