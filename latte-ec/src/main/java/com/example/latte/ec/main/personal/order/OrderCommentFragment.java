package com.example.latte.ec.main.personal.order;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.latte.ec.R;
import com.example.latte.ec.R2;
import com.example.latte_core.fragments.LatteFragment;
import com.example.latte_core.ui.widget.AutoPhotoLayout;
import com.example.latte_core.ui.widget.StarLayout;
import com.example.latte_core.util.callback.CallbackManager;
import com.example.latte_core.util.callback.CallbackType;
import com.example.latte_core.util.callback.IGlobalCallback;
import com.example.latte_core.util.toast.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：贪欢
 * 时间：2019/7/27
 * 描述：
 */
public class OrderCommentFragment extends LatteFragment {



    @BindView(R2.id.custom_star_layout)
    StarLayout mStarLayout = null;
    @BindView(R2.id.custom_auto_photo_layout)
    AutoPhotoLayout mAutoPhotoLayout;

    @OnClick(R2.id.top_tv_comment_commit)
    void onClickSubmit(){
        ToastUtil.showText("评分"+mStarLayout.getStarCount());
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        mAutoPhotoLayout.setDelegate(this);
        CallbackManager.getInstance().addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
            @Override
            public void executeCallback(@NonNull Uri args) {
                mAutoPhotoLayout.onCropTarget(args);
            }
        });
    }
}
