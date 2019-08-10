package com.example.latte.ec.main.personal.list;

import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.latte.ec.R;

import java.util.List;

/**
 * 作者：贪欢
 * 时间：2019/7/14
 * 描述：
 */
public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {

    //设置图片加载策略
    private static final RequestOptions REQUEST_OPTIONS =
            new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)//全缓存
                    .dontAnimate()//不需要动画
                    .centerCrop();

    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ListItemType.ITEM_NORMAL, R.layout.arrow_item_layout);
        addItemType(ListItemType.ITEM_AVATAR, R.layout.arrow_item_avatar);
        addItemType(ListItemType.ITEM_SWITCH, R.layout.arrow_switch_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {

        switch (helper.getItemViewType()) {
            case ListItemType.ITEM_NORMAL:

                helper.setText(R.id.tv_arrow_text, item.getmText());
                helper.setText(R.id.tv_arrow_value, item.getmValue());
                break;

            case ListItemType.ITEM_AVATAR:
                Glide.with(mContext)
                        .load(item.getmImageUrl())
                        .apply(REQUEST_OPTIONS)
                        .into((ImageView) helper.getView(R.id.img_arrow_avatar));
                break;


            case ListItemType.ITEM_SWITCH:

                helper.setText(R.id.tv_arrow_switch_text, item.getmText());
                final SwitchCompat switchCompat = helper.getView(R.id.list_item_switch);
                switchCompat.setChecked(true);
                switchCompat.setOnCheckedChangeListener(item.getmOnCheckedChangeListener());
                break;

            default:
                break;
        }
    }
}
