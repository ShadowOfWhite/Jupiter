package com.example.latte_core.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * 作者：贪欢
 * 时间：2019/6/20
 * 描述：
 */
public class MultipleItemEntity implements MultiItemEntity {

    private final ReferenceQueue<LinkedHashMap<Object,Object>> ITEM_QUEUE = new ReferenceQueue<>();
    private final LinkedHashMap<Object,Object> MULTIPLE_FIELDS = new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object,Object>> FIELDS_REFERENCE =
            new SoftReference<>(MULTIPLE_FIELDS,ITEM_QUEUE);

    public MultipleItemEntity(LinkedHashMap<Object,Object> fields) {
        FIELDS_REFERENCE.get().putAll(fields);
    }


    public static MultipleItemEntityBuilder builder(){
        return new MultipleItemEntityBuilder();
    }

    @Override
    public int getItemType() {
        return (int) FIELDS_REFERENCE.get().get(MultipleFields.ITEM_TYPE);//控制每一个item的样式和表现特征
    }

    // TODO: 2019/6/20 看看怎么调用这个方法的
    public final <T> T getField(Object key){
        return (T) FIELDS_REFERENCE.get().get(key);
    }

    public final LinkedHashMap<?,?> getFields(){
        return FIELDS_REFERENCE.get();
    }

    public final MultiItemEntity setField(Object key,Object value){
        FIELDS_REFERENCE.get().put(key,value);
        return this;
    }
}
