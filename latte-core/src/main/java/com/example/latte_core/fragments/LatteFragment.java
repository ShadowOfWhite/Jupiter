package com.example.latte_core.fragments;

/**
 * Created by 杨淋 on 2018/6/1.
 * Describe：
 */

public abstract class LatteFragment extends PermissionCheckFragment {

    public <T extends LatteFragment> T getparentDelegate(){
        return (T)getParentFragment();
    }


}
