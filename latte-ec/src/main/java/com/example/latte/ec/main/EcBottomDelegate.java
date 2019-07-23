package com.example.latte.ec.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.latte.ec.main.cart.ShopCartDelegate;
import com.example.latte.ec.main.discover.DiscoverDelegate;
import com.example.latte.ec.main.index.IndexDelegate;
import com.example.latte.ec.main.personal.PersonalDelegate;
import com.example.latte.ec.main.sort.SortDelegate;
import com.example.latte_core.delegates.bottom.BaseBottomDelegate;
import com.example.latte_core.delegates.bottom.BottomTabBean;
import com.example.latte_core.delegates.bottom.BottomItemDelegate;
import com.example.latte_core.delegates.bottom.ItemBuilder;

import java.util.LinkedHashMap;

/**
 * 作者：贪欢
 * 时间：2019/6/5
 * 描述：
 */
public class EcBottomDelegate extends BaseBottomDelegate {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","主页"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}","分类"),new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}","发现"),new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"),new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}","我的"),new PersonalDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
