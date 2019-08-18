package com.example.latte.ec.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.latte.ec.R;
import com.example.latte_core.fragments.bottom.BottomItemFragment;
import com.example.latte_core.fragments.web.WebFragmentImpl;

/**
 * 作者：贪欢
 * 时间：2019/6/23
 * 描述：
 */
public class DiscoverFragment extends BottomItemFragment {
    @Override
    public Object setLayout() {
        return R.layout.delegate_disvocer;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebFragmentImpl delegate = WebFragmentImpl.create("file:///android_asset/index.html");
        delegate.setTopdelegate(this.getparentDelegate());
        getSupportDelegate().loadRootFragment(R.id.web_disvover_container,delegate);
    }
}
