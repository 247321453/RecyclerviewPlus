package com.kk.recyclerviewplus.cards;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

class DeckItemDecoration extends RecyclerView.ItemDecoration {
    private IDeckLayout mDeckLayout;

    DeckItemDecoration(IDeckLayout deckLayout) {
        mDeckLayout = deckLayout;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        final int LINE_COUNT = mDeckLayout.getLineLimitCount();
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (mDeckLayout.isLabel(position)) {
            super.getItemOffsets(outRect, view, parent, state);
            return;
        }
        float w = mDeckLayout.getWidth10() - mDeckLayout.getWidth15();
        if (mDeckLayout.isMain(position)) {
            position = mDeckLayout.getMainIndex(position);
            int limit = mDeckLayout.getMainLimit();
            float _w = w / (limit - 1.0f);
            if (limit < LINE_COUNT) {
                w = mDeckLayout.getWidth15() * (LINE_COUNT - limit);
            } else {
                w = (int) (w / 2.0f);
            }
            int w2 = (int)(w / (limit - 1.0f)) - (int)_w;
            int linePos = position % limit;
            outRect.left = (w2 * linePos);
        } else if (mDeckLayout.isExtra(position)) {
            position = mDeckLayout.getExtraIndex(position);
            int limit = mDeckLayout.getExtraLimit();//10
            float _w = w / (limit - 1.0f);
            if (limit < LINE_COUNT) {
                w = mDeckLayout.getWidth15() * (LINE_COUNT - limit);
            } else {
                w = (int) (w / 2.0f);
            }
            int w2 = (int)(w / (limit - 1.0f)) - (int)_w;
            int linePos = position % limit;
            outRect.left = (w2 * linePos);
        } else if (mDeckLayout.isSide(position)) {
            position = mDeckLayout.getSideIndex(position);
            int limit = mDeckLayout.getSideLimit();//10
            float _w = w / (limit - 1.0f);
            int w2;
            if (limit < LINE_COUNT) {
                w = mDeckLayout.getWidth15() * (LINE_COUNT - limit);
                w2 = (int)(w / (limit - 1.0f)) - (int)_w;
            } else {
                w2 = -(int)(w / (limit - 1.0f));
            }
            int linePos = position % limit;
            outRect.left = (w2 * linePos);
        }
    }
}
