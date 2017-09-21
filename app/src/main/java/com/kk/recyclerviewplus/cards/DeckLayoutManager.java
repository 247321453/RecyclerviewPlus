package com.kk.recyclerviewplus.cards;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

class DeckLayoutManager extends GridLayoutManager {
    DeckLayoutManager(Context context, final int span, final IDeckLayout deckAdapter) {
        super(context, span);
        setSpanSizeLookup(new SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (deckAdapter.isLabel(position)) {
                    return span;
                } else if (deckAdapter.isMain(position)) {
                    position = deckAdapter.getMainIndex(position);
                    int limit = deckAdapter.getMainLimit();
                    if (position % limit == (limit - 1)) {
                        return span - limit + 1;
                    }
                }
                return 1;
            }
        });
    }
}
