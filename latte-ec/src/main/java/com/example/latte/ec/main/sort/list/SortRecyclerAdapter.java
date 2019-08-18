package com.example.latte.ec.main.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.latte.ec.R;
import com.example.latte.ec.main.sort.SortFragment;
import com.example.latte.ec.main.sort.content.ContentFragment;
import com.example.latte_core.fragments.LatteFragment;
import com.example.latte_core.ui.recycler.ItemType;
import com.example.latte_core.ui.recycler.MultipleFields;
import com.example.latte_core.ui.recycler.MultipleItemEntity;
import com.example.latte_core.ui.recycler.MultipleRecyclerAdapter;
import com.example.latte_core.ui.recycler.MultipleViewHolder;

import java.util.List;

import me.yokeyword.fragmentation.SupportHelper;

/**
 * 作者：贪欢
 * 时间：2019/6/23
 * 描述：
 */
public class SortRecyclerAdapter extends MultipleRecyclerAdapter {

    private final SortFragment DELEGATE;
    private int mPrePositon = 0 ;


    protected SortRecyclerAdapter(List<MultipleItemEntity> data, SortFragment delegate) {
        super(data);
        DELEGATE = delegate;
        //添加垂直菜单布局
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, final MultipleItemEntity entity) {
//        super.convert(holder, entity);

        switch (holder.getItemViewType()) {
            case ItemType.VERTICAL_MENU_LIST:
                final String text = entity.getField(MultipleFields.TEXT);
                boolean isClicked = entity.getField(MultipleFields.TAG);
                final AppCompatTextView name = holder.getView(R.id.tv_vertical_item_name);
                final View line = holder.getView(R.id.view_line);
                final View itemView = holder.itemView;//整个itemView

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentPosition = holder.getAdapterPosition();
                        if (mPrePositon != currentPosition){
                            //还原上一个
                            getData().get(mPrePositon).setField(MultipleFields.TAG,false);
                            notifyItemChanged(mPrePositon);//更新

                            //更新选中的item
                            entity.setField(MultipleFields.TAG,true);
                            notifyItemChanged(currentPosition);
                            mPrePositon = currentPosition;

                            final int contentId = getData().get(currentPosition).getField(MultipleFields.ID);
                            showContent(contentId);
                        }
                    }
                });

                if (!isClicked){
                    line.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext,R.color.we_chat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.item_background));
                }else {
                    line.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext,R.color.app_main));
                    line.setBackgroundColor(ContextCompat.getColor(mContext,R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }

                holder.setText(R.id.tv_vertical_item_name,text);
                break;
            default:
                break;

        }
    }

    private void showContent(int contentId){
        final ContentFragment delegate = ContentFragment.newInstance(contentId);
        switchContent(delegate);
    }

    private void switchContent(ContentFragment delegate){
        final LatteFragment contentDelegate =
                SupportHelper.findFragment(DELEGATE.getChildFragmentManager(),ContentFragment.class);
        if (contentDelegate != null){
            contentDelegate.getSupportDelegate().replaceFragment(delegate,false);//false:不需要加入返回栈
        }
    }
}
