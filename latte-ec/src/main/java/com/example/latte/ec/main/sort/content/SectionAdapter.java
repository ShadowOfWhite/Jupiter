package com.example.latte.ec.main.sort.content;

import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.latte.ec.R;

import java.util.List;

/**
 * 作者：贪欢
 * 时间：2019/6/23
 * 描述：
 */
public class SectionAdapter extends BaseSectionQuickAdapter<SectionBean,BaseViewHolder> {


    //设置图片加载策略
    private static final RequestOptions REQUEST_OPTIONS =
            new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)//全缓存
                    .dontAnimate()//不需要动画
                    .centerCrop();

    public SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SectionBean item) {

        helper.setText(R.id.header,item.header);
        helper.setVisible(R.id.more,item.isMore());
        helper.addOnClickListener(R.id.more);
    }

    @Override
    protected void convert(BaseViewHolder helper, SectionBean item) {

        //item.t 返回SectionContentItemEntity类型
        final String thumb = item.t.getGoodsThumb();
        String goodsName = item.t.getGoodsName();
        int goodsId = item.t.getGoodsId();
        SectionContentItemEntity entity = item.t;
        helper.setText(R.id.tv,goodsName);
        AppCompatImageView imageView = helper.getView(R.id.imgView);
        Glide.with(mContext)
                .load(thumb)
                .apply(REQUEST_OPTIONS)
                .into(imageView);


    }
}
