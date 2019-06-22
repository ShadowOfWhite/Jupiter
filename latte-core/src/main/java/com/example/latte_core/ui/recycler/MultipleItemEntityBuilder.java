package com.example.latte_core.ui.recycler;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * 作者：贪欢
 * 时间：2019/6/20
 * 描述：
 */
public class MultipleItemEntityBuilder {

    private static final LinkedHashMap<Object,Object> FIELDS = new LinkedHashMap<>();

    public MultipleItemEntityBuilder(){
        //先清除之前的数据
        FIELDS.clear();
    }

    public final MultipleItemEntityBuilder setItemType(int itemType){
        FIELDS.put(MultipleFields.ITEM_TYPE,itemType);
        return this;
    }

    public final MultipleItemEntityBuilder setField(Object key,Object value){
        FIELDS.put(key,value);
        return this;
    }

    public final MultipleItemEntityBuilder setFields(WeakHashMap<?,?> map){
        FIELDS.putAll(map);
        return this;
    }

    public final MultipleItemEntity build(){
       return new MultipleItemEntity(FIELDS);

    }
}
