package com.example.latte_core.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.example.latte_core.R;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

/**
 * 作者：贪欢
 * 时间：2019/7/27
 * 描述：
 */
public class StarLayout extends LinearLayoutCompat implements View.OnClickListener {

    private final CharSequence ICON_UN_SELECT = "{fa-star-o}";
    private final CharSequence ICON_SELECTED = "{fa-star}";
    private final int STAR_TOTAL_COUNT = 5;
    private final ArrayList<IconTextView> STARS = new ArrayList<>();

    public StarLayout(Context context) {
        this(context,null);
    }

    public StarLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public StarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initStarIcon();
    }

    @Override
    public void onClick(View v) {
        final IconTextView star = (IconTextView) v;
        final int count = (int) star.getTag(R.id.star_count);
        //获取点击状态
        final boolean isSelect = (boolean) star.getTag(R.id.star_is_selected);
        if (!isSelect){
            selectStar(count);
        }else {
            unSelectStar(count);
        }

    }

    private void selectStar(int count){
        for (int i = 0; i <= count; i++) {
                final IconTextView star = STARS.get(i);
                star.setText(ICON_SELECTED);
                star.setTextColor(Color.RED);
                star.setTag(R.id.star_is_selected,true);
        }
    }
    private void unSelectStar(int count){

        for (int i = 0; i < STAR_TOTAL_COUNT; i++) {
            if (i > count){
                final IconTextView star = STARS.get(i);
                star.setText(ICON_UN_SELECT);
                star.setTextColor(Color.GRAY);
                star.setTag(R.id.star_is_selected,false);
            }
        }
    }

    private void initStarIcon(){
        for (int i = 0;i<STAR_TOTAL_COUNT;i++){
            final IconTextView star = new IconTextView(getContext());
            star.setGravity(Gravity.CENTER);
            final LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            star.setLayoutParams(lp);
            star.setText(ICON_UN_SELECT);
            star.setTag(R.id.star_count,i);
            star.setTag(R.id.star_is_selected,false);
            star.setOnClickListener(this);
            STARS.add(star);
            this.addView(star);
        }
    }

    public int getStarCount(){
        int count = 0;
        for (int i = 0; i < STAR_TOTAL_COUNT; i++) {
            final IconTextView star = STARS.get(i);
            final boolean isSelect = (boolean) star.getTag(R.id.star_is_selected);
            if (isSelect){
                count++;
            }
        }

        return count;
    }

}
