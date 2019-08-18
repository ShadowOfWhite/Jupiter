package com.example.latte.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.latte.ec.R;
import com.example.latte.ec.R2;
import com.example.latte_core.fragments.LatteFragment;
import com.example.latte_core.net.RestClient;
import com.example.latte_core.net.callback.ISuccess;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：贪欢
 * 时间：2019/6/23
 * 描述：
 */
public class ContentFragment extends LatteFragment {

    @BindView(R2.id.rv_list_content)
    RecyclerView mRecyclerView = null;

    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;
    private List<SectionBean> mData = null;

    // TODO: 2019/6/23 可以添加左右列表联动的效果：1内容滑到底部过渡到内容2

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null){
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    // TODO: 2019/6/23 这种创建方法可以学习一下，创建同时传参 
    public static ContentFragment newInstance(int contentId){
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID,contentId);
        final ContentFragment delegate = new ContentFragment();
        delegate.setArguments(args);
        return delegate;
    }
    
    @Override
    public Object setLayout() {
        return R.layout.delegate_list_content;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

        final StaggeredGridLayoutManager manager =
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        initData();

    }

    private void initData(){
        RestClient.builder()
                .url("sort_content_list.php?contentId="+ mContentId)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        mData = new SectionDataConverter().convert(response);
                        SectionAdapter sectionAdapter =
                                new SectionAdapter(R.layout.item_section_content,
                                        R.layout.item_section_header,mData);

                        //快速点击界面被销毁，rv会为null
                        if(mRecyclerView != null){
                            mRecyclerView.setAdapter(sectionAdapter);
                        }else {
                            
                        }

                    }
                })
                .build()
                .get();

    }
}
