package com.kk.recyclerviewplus.cards;

import android.support.v7.widget.GridLayoutManagerPlus;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kk.recyclerviewplus.R;

class DeckViewHolder extends GridLayoutManagerPlus.GridViewHolder {
    DeckViewHolder(View view) {
        super(view);
        this.view = view;
        view.setTag(view.getId(), this);
        cardImage = $(R.id.card_image);
        labelText = $(R.id.label);
        textLayout = $(R.id.layout_label);
        numText = $(R.id.tv_text);
    }

    @SuppressWarnings("unchecked")
    private <T extends View> T $(int id) {
        return (T) view.findViewById(id);
    }

    void setSize(int width, int height) {
        view.setMinimumWidth(width);
        view.setMinimumHeight(height);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            if (width > 0) {
                layoutParams.width = width;
            } else {
                layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            }
            if (height > 0) {
                layoutParams.height = height;
            } else {
                layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            }
        }
        view.setLayoutParams(layoutParams);
    }

    public void empty(){
        numText.setVisibility(View.INVISIBLE);
        cardImage.setVisibility(View.INVISIBLE);
    }

    private final View view;
    final View textLayout;
    final TextView labelText, numText;
    final ImageView cardImage;
}
