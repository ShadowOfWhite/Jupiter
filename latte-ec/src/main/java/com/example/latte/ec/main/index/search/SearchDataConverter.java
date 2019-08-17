package com.example.latte.ec.main.index.search;

import com.alibaba.fastjson.JSONArray;
import com.example.latte_core.ui.recycler.DataConverter;
import com.example.latte_core.ui.recycler.MultipleFields;
import com.example.latte_core.ui.recycler.MultipleItemEntity;
import com.example.latte_core.util.storage.LattePreference;

import java.util.ArrayList;

/**
 * 作者：贪欢
 * 时间：2019/8/11
 * 描述：
 */
public class SearchDataConverter extends DataConverter {

    public static final String TAG_SEARCH_HISTORY = "search_history";

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final String jsonStr = LattePreference.getCustomAppProfile(TAG_SEARCH_HISTORY);
        if (!jsonStr.equals("")){
            final JSONArray array = JSONArray.parseArray(jsonStr);
            final int size = array.size();
            for (int i = 0; i < size; i++) {
                final String historyItemText = array.getString(i);
                MultipleItemEntity itemEntity = MultipleItemEntity.builder()
                        .setItemType(SearchItemType.ITEM_SEARCH)
                        .setField(MultipleFields.TEXT, historyItemText)
                        .build();
                ENTITIMES.add(itemEntity);
            }
        }

        return ENTITIMES;
    }
}
