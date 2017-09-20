package com.kk.recyclerviewplus;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DeckAdapater extends RecyclerView.Adapter<DeckViewHolder> {
    private Context mContext;
    private RecyclerView mRecyclerView;
    private LayoutInflater mLayoutInflater;
    private int mWidth;
    private int mHeight;

    public DeckAdapater(Context context, RecyclerView recyclerView) {
        mContext = context;
        mRecyclerView = recyclerView;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public DeckViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_deck_card, parent, false);
        return new DeckViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeckViewHolder holder, int position) {
        if (DeckItemUtils.isLabel(position)) {
            holder.textlayout.setVisibility(View.VISIBLE);
            holder.cardImage.setVisibility(View.GONE);
        } else {
//            Log.d("kk", "onBindViewHolder:" + position);
            holder.textlayout.setVisibility(View.GONE);
            holder.cardImage.setVisibility(View.VISIBLE);
            if (mHeight <= 0) {
                makeHeight();
            }
            holder.setSize(position, mWidth, mHeight);
        }
    }

    @Override
    public int getItemCount() {
        return LINE_COUNT * 4 + LINE_COUNT + LINE_COUNT + 3;
    }

    private void makeHeight() {
        int mFullWidth = getMaxWidth();
        mWidth = mFullWidth / 10;
        mHeight = scaleHeight(mWidth);
    }

    public int getMaxWidth() {
        return mRecyclerView.getMeasuredWidth()
                - mRecyclerView.getPaddingRight()
                - mRecyclerView.getPaddingLeft();
    }

    public int getWidth() {
        if (mWidth <= 0) {
            makeHeight();
        }
        return mWidth;
    }

    public int getHeight() {
        if (mWidth <= 0) {
            makeHeight();
        }
        return mHeight;
    }

    private int scaleHeight(int width) {
        return Math.round((float) width * ((float) 255 / 177));
    }

    public static final int LINE_COUNT = 15;
    public final static int MainLabel = 0;
    public final static int MainStart = MainLabel + 1;
    public final static int MainEnd = MainStart + LINE_COUNT * 4 - 1;

    public final static int ExtraLabel = MainEnd + 1;
    public final static int ExtraStart = ExtraLabel + 1;
    public final static int ExtraEnd = ExtraStart + LINE_COUNT - 1;
    public final static int SideLabel = ExtraEnd + 1;
    public final static int SideStart = SideLabel + 1;
    public final static int SideEnd = SideStart + LINE_COUNT - 1;
}

