package com.example.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.latte.ec.R;
import com.example.latte.ec.main.sort.content.ContentFragment;
import com.example.latte.ec.main.sort.list.VerticalListFragment;
import com.example.latte_core.fragments.bottom.BottomItemFragment;

/**
 * 作者：贪欢
 * 时间：2019/6/5
 * 描述：
 */
public class SortFragment extends BottomItemFragment {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        Log.i("SortDelegate", "onBindView: 绑定了视图");
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        Log.i("SortDelegate", "onLazyInitView: 开始懒加载");
        final VerticalListFragment listDelegate = new VerticalListFragment();
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container,listDelegate);
        //设置右侧第一个分类显示，默认显示分类一
        getSupportDelegate().loadRootFragment(R.id.sort_content_container,ContentFragment.newInstance(1));
        // TODO: 2019/6/23 新的代码用的是这个，暂时不用，看看效果
//     loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));
    }
}
