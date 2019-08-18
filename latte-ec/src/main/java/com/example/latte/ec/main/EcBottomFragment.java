package com.example.latte.ec.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.latte.ec.main.cart.ShopCartFragment;
import com.example.latte.ec.main.discover.DiscoverFragment;
import com.example.latte.ec.main.index.IndexFragment;
import com.example.latte.ec.main.personal.PersonalFragment;
import com.example.latte.ec.main.sort.SortFragment;
import com.example.latte_core.fragments.bottom.BaseBottomFragment;
import com.example.latte_core.fragments.bottom.BottomItemFragment;
import com.example.latte_core.fragments.bottom.BottomTabBean;
import com.example.latte_core.fragments.bottom.ItemBuilder;

import java.util.LinkedHashMap;

/**
 * 作者：贪欢
 * 时间：2019/6/5
 * 描述：
 */
public class EcBottomFragment extends BaseBottomFragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemFragment> setItems(ItemBuilder builder) {
        LinkedHashMap<BottomTabBean, BottomItemFragment> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","主页"),new IndexFragment());
        items.put(new BottomTabBean("{fa-sort}","分类"),new SortFragment());
        items.put(new BottomTabBean("{fa-compass}","发现"),new DiscoverFragment());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"),new ShopCartFragment());
        items.put(new BottomTabBean("{fa-user}","我的"),new PersonalFragment());
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
