package com.example.latte.ec.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.latte.ec.R;
import com.example.latte.ec.R2;
import com.example.latte_core.fragments.LatteFragment;

import butterknife.BindView;

/**
 * 作者：贪欢
 * 时间：2019/8/12
 * 描述：
 */
public class GoodsInfoFragment extends LatteFragment {

    private static final String ARG_GOODS_INFO = "ARG_GOODS_INFO";

    @BindView(R2.id.tv_goods_info_title)
    AppCompatTextView mTvGoodsInfoTitle;

    @BindView(R2.id.tv_goods_info_desc)
    AppCompatTextView mTvGoodsInfoDesc;

    @BindView(R2.id.tv_goods_info_price)
    AppCompatTextView mTvGoodsInfoPrice;

    private JSONObject mInfo;

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_info;
    }

    public static GoodsInfoFragment create(@NonNull String goodsInfo) {
        final Bundle args = new Bundle();
        args.putString(ARG_GOODS_INFO, goodsInfo);
        final GoodsInfoFragment delegate = new GoodsInfoFragment();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            String goodsInfo = args.getString(ARG_GOODS_INFO);
            mInfo = JSON.parseObject(goodsInfo);
        }
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

        final String name = mInfo.getString("name");
        final String desc = mInfo.getString("description");
        final double price = mInfo.getDouble("price");

        mTvGoodsInfoTitle.setText(name);
        mTvGoodsInfoDesc.setText(desc);
        mTvGoodsInfoPrice.setText(String.valueOf(price));

    }

}
