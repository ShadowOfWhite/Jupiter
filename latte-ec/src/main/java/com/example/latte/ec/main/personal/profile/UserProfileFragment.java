package com.example.latte.ec.main.personal.profile;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.latte.ec.R;
import com.example.latte.ec.R2;
import com.example.latte.ec.main.personal.list.ListAdapter;
import com.example.latte.ec.main.personal.list.ListBean;
import com.example.latte.ec.main.personal.list.ListItemType;
import com.example.latte.ec.main.personal.settings.NameFragment;
import com.example.latte_core.fragments.LatteFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：贪欢
 * 时间：2019/7/17
 * 描述：
 */
public class UserProfileFragment extends LatteFragment {

    @BindView(R2.id.rv_user_profile)
    RecyclerView mRecyclerView = null;


    @Override
    public Object setLayout() {
        return R.layout.delegate_user_profile;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {


        ListBean image = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_AVATAR)
                .setId(3)
                .setImageUrl("http://i9.qhimg.com/t017d891ca365ef60b5.jpg")
                .build();

        ListBean name = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(4)
                .setText("姓名")
                .setDelegate(new NameFragment())
                .setValue("用户")
                .build();

        ListBean gender = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(5)
                .setText("性别")
                .setValue("男")
                .build();

        ListBean birth = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(6)
                .setText("生日")
                .setValue("未设置")
                .build();
        final List<ListBean> data = new ArrayList<>();
        data.add(image);
        data.add(name);
        data.add(gender);
        data.add(birth);

        //设置RecyclerView
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new UserPfoflieClickListener(this));

    }
}
