package com.kk.recyclerviewplus;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

class DeckViewHolder extends RecyclerView.ViewHolder {
    public DeckViewHolder(View view) {
        super(view);
        this.view = view;
        view.setTag(view.getId(), this);
        cardImage = $(R.id.card_image);
        labelText = $(R.id.label);
        textlayout = $(R.id.layout_label);
    }

    protected <T extends View> T $(int id) {
        return (T) view.findViewById(id);
    }

    public void setSize(int index, int width, int height) {
        if (DeckItemUtils.isMain(index)) {
            index = index - DeckAdapater.MainStart;
        } else if (DeckItemUtils.isExtra(index)) {
            index = index - DeckAdapater.ExtraStart;
        } else if (DeckItemUtils.isSide(index)) {
            index = index - DeckAdapater.SideStart;
        }
        boolean isLineFirst = index % DeckAdapater.LINE_COUNT == 0;
        view.setMinimumWidth(width);
        view.setMinimumHeight(height);
        cardImage.setMaxWidth(width);

        cardImage.setMaxHeight(height);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = width;
            layoutParams.height = height;
//            if (!isLineFirst) {
//                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
//                marginLayoutParams.leftMargin = -((DeckAdapater.LINE_COUNT - 10) * width / (DeckAdapater.LINE_COUNT - 1));
//            } else {
//                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
//                marginLayoutParams.leftMargin = 0;
//            }
        }
        view.setLayoutParams(layoutParams);
    }

    final View view;
    final View textlayout;
    final TextView labelText;
    final ImageView cardImage;
}
