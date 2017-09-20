package com.kk.recyclerviewplus;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * 超过10的时候，行最后一个背挡住，只需要让布局自身大小调小
 */
public class DeckLayoutManager extends GridLayoutManager {

    public DeckLayoutManager(Context context, final int span, SpanSizeLookup spanSizeLookup) {
        super(context, span);
        setSpanSizeLookup(spanSizeLookup);
    }


}
