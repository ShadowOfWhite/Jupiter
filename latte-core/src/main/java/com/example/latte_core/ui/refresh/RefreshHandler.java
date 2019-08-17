package com.example.latte_core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.latte_core.app.Latte;
import com.example.latte_core.net.RestClient;
import com.example.latte_core.net.callback.ISuccess;
import com.example.latte_core.ui.recycler.DataConverter;
import com.example.latte_core.ui.recycler.MultipleItemEntity;
import com.example.latte_core.ui.recycler.MultipleRecyclerAdapter;
import com.example.latte_core.util.log.LatteLogger;
import com.example.latte_core.util.toast.ToastUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * 作者：贪欢
 * 时间：2019/6/10
 * 描述：
 */
public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PaginBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConverter CONVERTER;

    private RefreshHandler(SwipeRefreshLayout refreshLayout,RecyclerView recyclerView,
                          DataConverter converter,PaginBean bean) {
        this.REFRESH_LAYOUT = refreshLayout;
        this.BEAN = bean;
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = converter;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    public static RefreshHandler create(SwipeRefreshLayout refreshLayout,
                                        RecyclerView recyclerView,
                                        DataConverter converter){
        return new RefreshHandler(refreshLayout,recyclerView,converter,new PaginBean());
    }


    private void refresh(){
        REFRESH_LAYOUT.setRefreshing(true);

        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                REFRESH_LAYOUT.setRefreshing(false);
            }
        },3000);
    }

    public void firstPage(String url){
        REFRESH_LAYOUT.setRefreshing(true);
        BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        LatteLogger.json("测试",response);
                        final JSONObject object = JSONObject.parseObject(response);
                        BEAN.setTotal(object.getInteger("total"))
                                .setPageSize(object.getInteger("page_size"));
                        //设置Adapter
                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                        mAdapter.closeLoadAnimation();//关闭动画，可以去掉屏幕闪烁
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this,RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
                        REFRESH_LAYOUT.setRefreshing(false);
                    }
                })
                .build()
                .get();
    }

    private void paging(final String url){
        final int pageSize = BEAN.getPageSize();
        final int currentCount = BEAN.getCurrentCount();
        final int total = BEAN.getTotal();
        final int index = BEAN.getPageIndex();

        if (mAdapter.getData().size()<pageSize || currentCount >= total){
            mAdapter.loadMoreEnd(true);
        }else {
            Latte.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    RestClient.builder()
                            .url(url+index)
                            .success(new ISuccess() {
                                @Override
                                public void onSuccess(String response) {

                                    ArrayList<MultipleItemEntity> convert = CONVERTER.setJsonData(response).convert();

                                    Logger.i("新增数量:"+convert.size());

//                                    mAdapter.notifyItemRangeInserted(mAdapter.getData().size() - convert.size() , convert.size());

                                    mAdapter.notifyDataSetChanged();

                                    Logger.json(response);
                                    //累加数量
                                    BEAN.setCurrentCount(mAdapter.getData().size());
                                    Logger.i("当前数量："+BEAN.getCurrentCount());
                                    mAdapter.loadMoreComplete();
                                    BEAN.addIndex();
                                }
                            })
                            .build()
                            .get();
                }
            },1000);
        }
    }


    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public void onLoadMoreRequested() {

        paging("refresh.php?index=");

    }
}
