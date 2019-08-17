package com.example.latte.ec.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.example.latte.ec.R;
import com.example.latte.ec.R2;
import com.example.latte_core.delegates.LatteDelegate;
import com.example.latte_core.ui.recycler.ItemType;
import com.example.latte_core.ui.recycler.MultipleFields;
import com.example.latte_core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 作者：贪欢
 * 时间：2019/8/13
 * 描述：
 */
public class ImageDelegate extends LatteDelegate {


    @BindView(R2.id.rv_image_container)
    RecyclerView mRecyclerView;

    private static final String ARG_PICTURES = "ARG_PICTURES";

    public static ImageDelegate create(@NonNull ArrayList<String> pictures) {
        final Bundle args = new Bundle();
        args.putStringArrayList(ARG_PICTURES,pictures);
        final ImageDelegate delegate = new ImageDelegate();
        delegate.setArguments(args);
        return delegate;
    }




    @Override
    public Object setLayout() {
        return R.layout.delegate_image;
    }



    private void initImage(){
        final ArrayList<String> picture = getArguments().getStringArrayList(ARG_PICTURES);
        ArrayList<MultipleItemEntity> entities = new ArrayList<>();
        int size;
        if (picture != null){
            size = picture.size();
            for (int i = 0; i < size; i++) {
                String imageUrl = picture.get(i);
                MultipleItemEntity entity = MultipleItemEntity
                        .builder()
                        .setItemType(ItemType.SINGLE_BIG_IMAGE)
                        .setField(MultipleFields.IMAGE_URL,imageUrl)
                        .build();
                entities.add(entity);
            }
            RecyclerImageAdapter adapter = new RecyclerImageAdapter(entities);
            mRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

        LogUtils.e("创建了一次");
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        initImage();
    }
}
