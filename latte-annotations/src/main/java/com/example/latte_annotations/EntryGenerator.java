package com.example.latte_annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作者：贪欢
 * 时间：2019/6/2
 * 描述：
 */

@Target(ElementType.TYPE)//用在类上面
@Retention(RetentionPolicy.SOURCE)//在源码阶段处理
public @interface EntryGenerator {

    String packageName();

    Class<?> entryTemplete();
}
