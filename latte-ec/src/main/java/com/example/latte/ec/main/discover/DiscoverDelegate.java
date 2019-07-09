package com.example.latte.ec.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.latte.ec.R;
import com.example.latte_core.delegates.LatteDelegate;
import com.example.latte_core.delegates.buttom.ButtomItemDelegate;
import com.example.latte_core.delegates.web.WebDelegateImpl;
import com.example.latte_core.delegates.web.client.WebViewClientImpl;

/**
 * 作者：贪欢
 * 时间：2019/6/23
 * 描述：
 */
public class DiscoverDelegate extends ButtomItemDelegate {
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
        final WebDelegateImpl delegate = WebDelegateImpl.create("file:///android_asset/index.html");
        delegate.setTopdelegate(this.getparentDelegate());
        getSupportDelegate().loadRootFragment(R.id.web_disvover_container,delegate);
    }
}
