package com.example.latte.ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by 杨淋 on 2018/4/27.
 * Describe：自定义的字体模块，功能与FontAwesomeModule一样
 */

public class FontEcModule implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        return "iconfont2.ttf";
    }

    @Override
    public Icon[] characters() {
        return EcIcons.values();
    }
}
