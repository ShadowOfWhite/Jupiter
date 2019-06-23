package com.example.latte.ec.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.latte_core.ui.recycler.DataConverter;
import com.example.latte_core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：贪欢
 * 时间：2019/6/23
 * 描述：
 */
public class SectionDataConverter  {

    final List<SectionBean> convert(String json){
        final List<SectionBean> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(json).getJSONArray("data");

        int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject jsonObject = dataArray.getJSONObject(i);
            Integer id = jsonObject.getInteger("id");
            String title = jsonObject.getString("section");
            // TODO: 2019/6/23 这里dataList实际上保存了两种bean，为什么要这样呢？
            //添加title
            SectionBean sectionBean = new SectionBean(true,title);
            sectionBean.setId(id);
            sectionBean.setMore(true);
            dataList.add(sectionBean);

            JSONArray goods = jsonObject.getJSONArray("goods");
            int goodsSize = goods.size();
            for (int j = 0; j < goodsSize; j++) {
                JSONObject contentItem = goods.getJSONObject(j);
                Integer goodsId = contentItem.getInteger("goods_id");
                String goodsName = contentItem.getString("goods_name");
                String goodsThumb = contentItem.getString("goods_thumb");
                //获取内容
                SectionContentItemEntity itemEntity = new SectionContentItemEntity();
                itemEntity.setGoodsId(goodsId);
                itemEntity.setGoodsName(goodsName);
                itemEntity.setGoodsThumb(goodsThumb);
                //添加内容
                dataList.add(new SectionBean(itemEntity));
            }
            //商品内容循环结束
        }
        //section循环结束
        return dataList;
    }


}
