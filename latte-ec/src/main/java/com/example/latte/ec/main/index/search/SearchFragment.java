package com.example.latte.ec.main.index.search;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.blankj.utilcode.util.StringUtils;
import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;
import com.example.latte.ec.R;
import com.example.latte.ec.R2;
import com.example.latte_core.fragments.LatteFragment;
import com.example.latte_core.ui.recycler.MultipleItemEntity;
import com.example.latte_core.util.storage.LattePreference;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：贪欢
 * 时间：2019/8/11
 * 描述：
 */
public class SearchFragment extends LatteFragment {

    @BindView(R2.id.rv_search)
    RecyclerView mRecyclerView;

    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchEdit;


    @OnClick(R2.id.tv_top_search)
    void onClickSearch(){
        final String searchItemText = mSearchEdit.getText().toString();
        savaItem(searchItemText);
    }

    @OnClick(R2.id.icon_top_search_back)
    void onClickBack(){
        getSupportDelegate().pop();
    }

    private void savaItem(String item){
        if (!StringUtils.isEmpty(item) && !StringUtils.isSpace(item)){
            List<String> history;
            final String historyStr = LattePreference.getCustomAppProfile(SearchDataConverter.TAG_SEARCH_HISTORY);
            if (StringUtils.isEmpty(historyStr)){
                history = new ArrayList<>();
            }else {
                history = JSON.parseObject(historyStr,ArrayList.class);
            }
            if (! history.contains(item)){
                history.add(item);
            }
            final String json = JSON.toJSONString(history,SerializerFeature.WRITE_MAP_NULL_FEATURES);
            LattePreference.addCustomAppProfile(SearchDataConverter.TAG_SEARCH_HISTORY,json);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_search;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);

        final List<MultipleItemEntity> data = new SearchDataConverter().convert();
        final SearchAdapter adapter = new SearchAdapter(data);
        mRecyclerView.setAdapter(adapter);
        final DividerItemDecoration itemDecoration = new DividerItemDecoration();
        itemDecoration.setDividerLookup(new DividerItemDecoration.DividerLookup() {
            @Override
            public Divider getVerticalDivider(int position) {
                return null;
            }

            @Override
            public Divider getHorizontalDivider(int position) {
                return new Divider.Builder()
                        .size(2)
                        .margin(20,20)
                        .color(Color.GRAY)
                        .build();
            }
        });

        mRecyclerView.addItemDecoration(itemDecoration);
    }
}
