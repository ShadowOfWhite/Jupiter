package com.example.latte.ec.main.sort.content;

/**
 * 作者：贪欢
 * 时间：2019/6/23
 * 描述：
 */
public class SectionContentItemEntity {

    private int goodsId = 0 ;
    private String goodsName = null;
    private String goodsThumb = null;

    public String getGoodsThumb() {
        return goodsThumb;
    }

    public void setGoodsThumb(String goodsThumb) {
        this.goodsThumb = goodsThumb;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
