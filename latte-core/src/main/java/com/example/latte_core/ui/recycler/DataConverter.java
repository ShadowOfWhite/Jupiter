package com.example.latte_core.ui.recycler;

import java.util.ArrayList;

/**
 * 作者：贪欢
 * 时间：2019/6/20
 * 描述：
 */
public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITIMES = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String json){
        this.mJsonData = json;
        return this;
    }


    protected String getJsonData(){
        if (mJsonData == null || mJsonData.isEmpty()){
            throw new NullPointerException("DATA IS NULL");
        }

        return mJsonData;
    }

}
