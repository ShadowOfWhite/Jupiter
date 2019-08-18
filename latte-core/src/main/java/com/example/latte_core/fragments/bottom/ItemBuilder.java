package com.example.latte_core.fragments.bottom;

import java.util.LinkedHashMap;

/**
 * 作者：贪欢
 * 时间：2019/6/4
 * 描述：
 */
public final class ItemBuilder {

    private final LinkedHashMap<BottomTabBean,BottomItemFragment> ITEMS = new LinkedHashMap<>();

    static ItemBuilder builder(){
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomTabBean bean, BottomItemFragment delegate){
        ITEMS.put(bean,delegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean,BottomItemFragment> items){
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTabBean,BottomItemFragment> build(){
        return ITEMS;
    }
}
