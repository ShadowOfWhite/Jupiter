package com.example.latte.ec.main;

import android.graphics.Color;

import com.example.latte.ec.main.cart.ShopCartDelegate;
import com.example.latte.ec.main.discover.DiscoverDelegate;
import com.example.latte.ec.main.index.IndexDelegate;
import com.example.latte.ec.main.sort.SortDelegate;
import com.example.latte_core.delegates.buttom.BaseBottomDelegate;
import com.example.latte_core.delegates.buttom.BottomTabBean;
import com.example.latte_core.delegates.buttom.ButtomItemDelegate;
import com.example.latte_core.delegates.buttom.ItemBuilder;

import java.util.LinkedHashMap;

/**
 * 作者：贪欢
 * 时间：2019/6/5
 * 描述：
 */
public class EcBottomDelegate extends BaseBottomDelegate {


    @Override
    public LinkedHashMap<BottomTabBean, ButtomItemDelegate> setItems(ItemBuilder builder) {
        LinkedHashMap<BottomTabBean, ButtomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","主页"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}","分类"),new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}","发现"),new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"),new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}","我的"),new IndexDelegate());
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
