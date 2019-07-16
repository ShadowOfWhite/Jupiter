package com.example.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.latte.ec.R;
import com.example.latte.ec.R2;
import com.example.latte.ec.main.EcBottomDelegate;
import com.example.latte_core.delegates.bottom.BottomItemDelegate;
import com.example.latte_core.ui.recycler.BaseDecoration;
import com.example.latte_core.ui.refresh.RefreshHandler;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;

/**
 * 作者：贪欢
 * 时间：2019/6/5
 * 描述：
 */
public class IndexDelegate extends BottomItemDelegate {
    @BindView(R2.id.rv_index)
    RecyclerView rvIndex;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout srlIndex;
    @BindView(R2.id.icon_index_scan)
    IconTextView iconIndexScan;
    @BindView(R2.id.et_search_view)
    AppCompatEditText etSearchView;
    @BindView(R2.id.icon_index_message)
    IconTextView iconIndexMessage;
    @BindView(R2.id.tb_index)
    Toolbar tbIndex;

    private RefreshHandler mRefreshHandler;
    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

        mRefreshHandler = RefreshHandler.create(srlIndex,rvIndex,new IndexDataConverter());
        //toolbar状态改变不会影响其它页面的toolbar
        tbIndex.getBackground().mutate();

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        // TODO: 2019/6/10 这里懒加载什么时候调用？为什么在这里 initRefrshLayout
        super.onLazyInitView(savedInstanceState);
        initRefrshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("index.php");
    }

    private void initRecyclerView(){
        final GridLayoutManager manager = new GridLayoutManager(getContext(),4);
        rvIndex.setLayoutManager(manager);
        rvIndex.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(getContext()
                ,R.color.app_background),5));

        final EcBottomDelegate ecBottomDelegate = getparentDelegate();
        rvIndex.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));

    }

    private void initRefrshLayout(){
        srlIndex.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light,
                android.R.color.holo_green_light
                );

        srlIndex.setProgressViewOffset(true,120,300);
    }




}
