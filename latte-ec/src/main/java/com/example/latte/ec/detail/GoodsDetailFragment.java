package com.example.latte.ec.detail;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.daimajia.androidanimations.library.YoYo;
import com.example.latte.ec.R;
import com.example.latte.ec.R2;
import com.example.latte_core.fragments.LatteFragment;
import com.example.latte_core.net.RestClient;
import com.example.latte_core.net.callback.ISuccess;
import com.example.latte_core.ui.animation.BezierAnimation;
import com.example.latte_core.ui.animation.BezierUtil;
import com.example.latte_core.ui.banner.HolderCreator;
import com.example.latte_core.ui.widget.CircleTextView;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 作者：贪欢
 * 时间：2019/6/23
 * 描述：
 */
public class GoodsDetailFragment extends LatteFragment implements
        AppBarLayout.OnOffsetChangedListener ,
        BezierUtil.AnimationListener {

    @BindView(R2.id.goods_detail_toolbar)
    Toolbar mToolbar = null;
    @BindView(R2.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager;
    @BindView(R2.id.detail_banner)
    ConvenientBanner<String> mBanner;
    @BindView(R2.id.collapsing_toolbar_detail)
    CollapsingToolbarLayout mCollapsingToolbarDetail;
    @BindView(R2.id.app_bar_detail)
    AppBarLayout mAppBar;


    //底部
    @BindView(R2.id.icon_favor)
    IconTextView mIconFavor;
    @BindView(R2.id.tv_shopping_cart_amount)
    CircleTextView mCircleTextView;
    @BindView(R2.id.rl_add_shop_cart)
    RelativeLayout mRlAddShopCart;
    @BindView(R2.id.icon_shop_cart)
    IconTextView mIconShopCart;


    private static final String ARG_GOODS_ID = "ARG_GOODS_ID";
    private int mGoodsId = -1;

    private String mGoodsThumbUrl = null;
    private int mShopCount = 0;
    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate()
            .override(100,100);


    public static GoodsDetailFragment create(@NonNull int goodsId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_GOODS_ID, goodsId);
        final GoodsDetailFragment delegate = new GoodsDetailFragment();
        delegate.setArguments(args);
        return delegate;
    }

    @OnClick(R2.id.rl_add_shop_cart)
    void onClickAddShopCart(){
        final CircleImageView animImg = new CircleImageView(getContext());
        Glide.with(this)
                .load(mGoodsThumbUrl)
                .apply(OPTIONS)
                .into(animImg);

        BezierAnimation.addCart(this,mRlAddShopCart,mIconShopCart,animImg,this);
    }

    private void setShopCartCount(JSONObject data){
        mGoodsThumbUrl = data.getString("thumb");
        if (mShopCount == 0){
            mCircleTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mGoodsId = args.getInt(ARG_GOODS_ID);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

        mCollapsingToolbarDetail.setContentScrimColor(Color.WHITE);
        mAppBar.addOnOffsetChangedListener(this);
        mCircleTextView.setCircleBackground(Color.RED);
        initData();
        initTabLayout();

    }


    private void initGoodsInfo(JSONObject data) {

        final String goodsData = data.toJSONString();
        getSupportDelegate().loadRootFragment(R.id.frame_goods_info, GoodsInfoFragment.create(goodsData));

    }


    private void initTabLayout() {

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//tab是平均分开的
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.app_main));
        mTabLayout.setTabTextColors(ColorStateList.valueOf(Color.BLACK));
        mTabLayout.setBackgroundColor(Color.WHITE);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        //返回水平的进场动画
        return new DefaultHorizontalAnimator();
    }

    private void initPager(JSONObject data) {

        final PagerAdapter adapter = new TabPagerAdapter(getFragmentManager(),data);
        mViewPager.setAdapter(adapter);

    }

    private void initData() {
        RestClient.builder()
                .url("goods_detail.php")
                .params("goods_id", mGoodsId)
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject data = JSON.parseObject(response).getJSONObject("data");
                        initBanner(data);
                        initGoodsInfo(data);
                        initPager(data);
                        setShopCartCount(data);
                    }
                })
                .build()
                .get();
    }


    private void initBanner(JSONObject data) {
        final JSONArray array = data.getJSONArray("banners");
        final List<String> images = new ArrayList<>();
        int size = array.size();
        for (int i = 0; i < size; i++) {
            images.add(array.getString(i));
        }

        mBanner.setPages(new HolderCreator(), images)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .startTurning(3000)
                .setCanLoop(true);
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

    }

    @Override
    public void onAnimationEnd() {
        YoYo.with(new ScaleUpAnimator())
                .duration(1000)
                .playOn(mIconShopCart);

        RestClient.builder()
                .url("")
                .params("count",mShopCount)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        mShopCount++;
                        mCircleTextView.setVisibility(View.VISIBLE);
                        mCircleTextView.setText(String.valueOf(mShopCount));
                    }
                })
                .build()
                .post();
    }
}
