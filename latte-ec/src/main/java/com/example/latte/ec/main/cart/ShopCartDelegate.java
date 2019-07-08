package com.example.latte.ec.main.cart;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.latte.ec.R;
import com.example.latte.ec.R2;
import com.example.latte_core.delegates.buttom.ButtomItemDelegate;
import com.example.latte_core.net.RestClient;
import com.example.latte_core.net.callback.IError;
import com.example.latte_core.net.callback.IFailure;
import com.example.latte_core.net.callback.ISuccess;
import com.example.latte_core.ui.recycler.MultipleItemEntity;
import com.example.latte_core.util.toast.ToastUtil;
import com.joanzapata.iconify.widget.IconTextView;

import java.io.IOError;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：贪欢
 * 时间：2019/6/28
 * 描述：
 */
public class ShopCartDelegate extends ButtomItemDelegate implements ISuccess,IFailure,ICartItemListener {
    @BindView(R2.id.tv_top_shop_cart_clear)
    TextView tvTopShopCartClear;
    @BindView(R2.id.tv_top_shop_cart_remove_selected)
    TextView tvTopShopCartRemoveSelected;
    @BindView(R2.id.rv_shop_cart)
    RecyclerView rvShopCart;
    @BindView(R2.id.icon_cart_select_all)
    IconTextView iconCartSelectAll;
    @BindView(R2.id.stub_no_item)
    ViewStubCompat mStubNoItem;
    @BindView(R2.id.tv_shop_cart_total_price)
    TextView mTotalPrice = null;

    private ShopCartAdapter shopCartAdapter;
    //购物车数量标记
    private int mCurrentCount = 0;

    private int mTotalCount = 0;


    @OnClick(R2.id.icon_cart_select_all)
    void onClickSelectAll(){
        int tag = (int) iconCartSelectAll.getTag();
        if (tag == 0){
            iconCartSelectAll.setTextColor(ContextCompat.getColor(getContext(),R.color.app_main));
            iconCartSelectAll.setTag(1);
            shopCartAdapter.setIsSelectedAll(true);
            //更新RV的显示状态
            shopCartAdapter.notifyItemRangeChanged(0,shopCartAdapter.getItemCount());
        }else {
            iconCartSelectAll.setTextColor(Color.GRAY);
            iconCartSelectAll.setTag(0);
            shopCartAdapter.setIsSelectedAll(false);
            shopCartAdapter.notifyItemRangeChanged(0,shopCartAdapter.getItemCount());
        }
    }

    @OnClick(R2.id.tv_top_shop_cart_remove_selected)
    void onClickRemoveSelectedItem(){
        List<MultipleItemEntity> data = shopCartAdapter.getData();
        //找到要删除的数据
        List<MultipleItemEntity> deleteEntities = new ArrayList<>();
        for (MultipleItemEntity itemEntity : data) {
            boolean isSelected = itemEntity.getField(ShopCartItemFields.IS_SELECTED);
            if (isSelected){
                deleteEntities.add(itemEntity);
            }
        }

        for (MultipleItemEntity entity : deleteEntities) {
            int removePosition;
            final int entityPosition = entity.getField(ShopCartItemFields.POSITION);
            if(entityPosition>mCurrentCount-1){
                removePosition = entityPosition -(mTotalCount -mCurrentCount);
            }else {
                removePosition = entityPosition;
            }
           if (removePosition <= shopCartAdapter.getItemCount()){
               shopCartAdapter.remove(removePosition);
               mCurrentCount = shopCartAdapter.getItemCount();
               //更新数据
               shopCartAdapter.notifyItemRangeChanged(removePosition,shopCartAdapter.getItemCount());
           }
        }
        checkItemCount();



    }

    @OnClick(R2.id.tv_top_shop_cart_clear)
    void onClickClear(){
        shopCartAdapter.getData().clear();
        shopCartAdapter.notifyDataSetChanged();
        checkItemCount();
    }

    @SuppressLint("RestrictedApi")
    private void checkItemCount(){
        final int count = shopCartAdapter.getItemCount();
        if (count == 0){
            final View stubView = mStubNoItem.inflate();
            TextView tvToBuy = stubView.findViewById(R.id.tv_stub_to_buy);
            tvToBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showText("购物");
                }
            });
            rvShopCart.setVisibility(View.GONE);
        }else {
            rvShopCart.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        iconCartSelectAll.setTag(0);

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("shop_cart.php")
                .loader(getContext())
                .success(this)
                .failure(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        ArrayList<MultipleItemEntity> data = new ShopCartDataConverter().setJsonData(response).convert();
        shopCartAdapter = new ShopCartAdapter(data);
        shopCartAdapter.setCartItemListener(this);
//        shopCartAdapter.closeLoadAnimation();//关闭动画，可以去掉屏幕闪烁
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvShopCart.setLayoutManager(manager);
        rvShopCart.setAdapter(shopCartAdapter);
        checkItemCount();
    }



    @Override
    public void onFailure(String msg) {
        Log.e("错误", "onFailure: "+msg );


    }

    @Override
    public void onItemClick(double itemTotalPrice) {
        final double price = shopCartAdapter.getTotalPrice();
        mTotalPrice.setText(String.valueOf(price));

    }
}
