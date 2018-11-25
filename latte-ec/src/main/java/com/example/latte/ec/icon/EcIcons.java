package com.example.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by 杨淋 on 2018/4/27.
 * Describe：
 */

public enum  EcIcons implements Icon{
    icon_moon('\ue501'),
    icon_sun('\ue502');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
