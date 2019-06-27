package com.example.latte_core.ui.banner;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.latte_core.R;
import com.example.latte_core.util.log.LatteLogger;

/**
 * 作者：贪欢
 * 时间：2019/6/22
 * 描述：
 */
public class ImageHolder implements Holder<String> {
    private AppCompatImageView mImageView = null;

    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {

//        LatteLogger.i("更新一次UI");
        Glide.with(context)
                .load(data)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//全缓存
                .dontAnimate()//不需要动画
                .centerCrop()
                .into(mImageView);

    }
}
