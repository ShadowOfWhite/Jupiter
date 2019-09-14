package com.example.latte_core.fragments.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.latte_core.R;
import com.example.latte_core.R2;
import com.example.latte_core.fragments.LatteFragment;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportHelper;

/**
 * 作者：贪欢
 * 时间：2019/6/4
 * 描述：包含几个碎片的碎片
 */
public abstract class BaseBottomFragment extends LatteFragment implements View.OnClickListener {

    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    private final ArrayList<BottomItemFragment> ITEM_DELEGATES = new ArrayList<>();
    private final LinkedHashMap<BottomTabBean, BottomItemFragment> ITEMS = new LinkedHashMap<>();

    private int mCurrentDelegate = 0;
    private int mIndexDelegate = 0; //第一次进来展示的fragment
    private int mClickedColor = Color.RED;

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mIndexDelegate = setIndexDelegate();
        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor();
        }

        ItemBuilder builder = ItemBuilder.builder();
        LinkedHashMap<BottomTabBean, BottomItemFragment> items = setItems(builder);
        ITEMS.putAll(items);

        for (Map.Entry<BottomTabBean, BottomItemFragment> item : ITEMS.entrySet()) {
            BottomTabBean key = item.getKey();
            BottomItemFragment value = item.getValue();

            TAB_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }
    }

    public abstract LinkedHashMap<BottomTabBean, BottomItemFragment> setItems(ItemBuilder builder);

    public abstract int setIndexDelegate();

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    @ColorInt
    public abstract int setClickedColor();

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

        int size = ITEMS.size();

        //解决fragment重叠问题
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        if (fragments == null || fragments.isEmpty()) {
            ISupportFragment[] delagateArray = ITEM_DELEGATES.toArray(new ISupportFragment[size]);
            getSupportDelegate().loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, delagateArray);
        } else {
            ITEM_DELEGATES.clear();
            for (int i = 0; i < fragments.size(); i++) {
                ITEM_DELEGATES.add((BottomItemFragment) fragments.get(i));
            }

            // TODO: 2019-09-14 这里还需要对 ITEMS进行初始化
            //...偷一下懒，暂时不初始化了
        }


        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            //设置每个item点击事件
            item.setTag(i);
            item.setOnClickListener(this);
            IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            BottomTabBean bottomTabBean = TAB_BEANS.get(i);
            //初始化数据
            itemIcon.setText(bottomTabBean.getIcon());
            itemTitle.setText(bottomTabBean.getTitle());

            if (i == mIndexDelegate) {
                itemIcon.setTextColor(mClickedColor);
                itemTitle.setTextColor(mClickedColor);
            }


        }


    }

    private void resetColor() {
        int count = mBottomBar.getChildCount();
        for (int i = 0; i < count; i++) {
            RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemIcon.setTextColor(Color.GRAY);
            itemTitle.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        resetColor();
        RelativeLayout item = (RelativeLayout) v;
        IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemIcon.setTextColor(mClickedColor);
        itemTitle.setTextColor(mClickedColor);

        getSupportDelegate().showHideFragment(ITEM_DELEGATES.get(tag), ITEM_DELEGATES.get(mCurrentDelegate));
        mCurrentDelegate = tag;

        // 查看栈视图Dialog
//        SupportHelper.showFragmentStackHierarchyView((ISupportActivity) _mActivity);
    }
}
