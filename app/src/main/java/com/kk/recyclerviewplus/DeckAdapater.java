package com.kk.recyclerviewplus;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.v7.widget.helper.ItemTouchHelper2;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Method;

import static android.support.v7.widget.helper.ItemTouchHelper.ACTION_STATE_IDLE;

public class DeckAdapater extends RecyclerView.Adapter<DeckViewHolder> {
    private Context mContext;
    private RecyclerView mRecyclerView;
    private LayoutInflater mLayoutInflater;
    private DeckLayoutManager mDeckLayoutManager;
    private int mWidth;
    private int mHeight;
    private int mMainCount = 60, mExtraCount = 15, mSideCount = 15;

    public DeckAdapater(Context context, RecyclerView recyclerView) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mRecyclerView = recyclerView;
        recyclerView.addItemDecoration(new DeckItemDecoration());
        mDeckLayoutManager = new DeckLayoutManager(getContext(), DeckAdapater.LINE_COUNT, mSpanSizeLookup);
        recyclerView.setLayoutManager(mDeckLayoutManager);
        ItemTouchHelper2 touchHelper = new ItemTouchHelper2(getContext(), mCallback);
        touchHelper.setEnableClickDrag(true);
        touchHelper.attachToRecyclerView(recyclerView);
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
        if (isLabel(position)) {
            holder.textlayout.setVisibility(View.VISIBLE);
            holder.cardImage.setVisibility(View.GONE);
            holder.numText.setVisibility(View.GONE);
            holder.setSize(-1, -1);
        } else {
            holder.textlayout.setVisibility(View.GONE);
            holder.cardImage.setVisibility(View.VISIBLE);
            holder.numText.setVisibility(View.VISIBLE);
            if(TextUtils.isEmpty(holder.numText.getText())) {
                holder.numText.setText("" + position);
            }
            if (mHeight <= 0) {
                makeHeight();
            }
            holder.setSize(mWidth, mHeight);
            if (isMain(position)) {
                position = position - getMainStart();
                if (position >= getMainCount()) {
                    holder.cardImage.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return getMainCount() + getExtraCount() + getSideCount() + 3;
    }

    private void makeHeight() {
        mWidth = getWidth10();
        mHeight = scaleHeight(mWidth);
    }

    public int getMaxWidth() {
        return mRecyclerView.getMeasuredWidth()
                - mRecyclerView.getPaddingRight()
                - mRecyclerView.getPaddingLeft();
    }

    public int getWidth15() {
        return getMaxWidth() / 15;
    }

    public int getWidth10() {
        return getMaxWidth() / 10;
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

    public boolean isMain(int pos) {
        return pos >= getMainStart() && pos <= getMainEnd();
    }

    public boolean isExtra(int pos) {
        return pos >= getExtraStart() && pos <= getExtraEnd();
    }

    public boolean isSide(int pos) {
        return pos >= getSideStart() && pos <= getSideEnd();
    }

    public boolean isLabel(int position) {
        if (position == getMainLabel() || position == getExtraLabel() || position == getSideLabel()) {
            return true;
        }
        return false;
    }

    private int scaleHeight(int width) {
        return Math.round((float) width * ((float) 255 / 177));
    }

    public int getMainCount() {
        return Math.max(40, mMainCount);
    }

    public void setMainCount(int mainCount) {
        if (mainCount >= 60) {
            mainCount = 60;
        } else if (mainCount < 0) {
            mainCount = 0;
        }
        mMainCount = mainCount;
    }

    public int getExtraCount() {
        return mExtraCount;
    }

    public void setExtraCount(int extraCount) {
        if (extraCount >= 15) {
            extraCount = 15;
        } else if (extraCount < 0) {
            extraCount = 0;
        }
        mExtraCount = extraCount;
    }

    public int getSideCount() {
        return mSideCount;
    }

    public void setSideCount(int sideCount) {
        if (sideCount >= 15) {
            sideCount = 15;
        } else if (sideCount < 0) {
            sideCount = 0;
        }
        mSideCount = sideCount;
    }

    private int getMainLimit() {
        return (int) Math.ceil(getMainCount() / 4.0f);
    }

    private int getExtraLimit() {
        return Math.max(10, getExtraCount());
    }

    private int getSideLimit() {
        return Math.max(10, getSideCount());
    }

    public static final int LINE_COUNT = 15;

    int getMainLabel() {
        return 0;
    }

    int getMainStart() {
        return getMainLabel() + 1;
    }

    int getMainEnd() {
        return getMainStart() + getMainCount() - 1;
    }

    int getExtraLabel() {
        return getMainEnd() + 1;
    }

    int getExtraStart() {
        return getExtraLabel() + 1;
    }

    int getExtraEnd() {
        return getExtraStart() + getExtraCount() - 1;
    }

    int getSideLabel() {
        return getExtraEnd() + 1;
    }

    int getSideStart() {
        return getSideLabel() + 1;
    }

    int getSideEnd() {
        return getSideStart() + getSideCount() - 1;
    }


    private class DeckItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
//            parent.getChildAdapterPosition(view);
            if (isLabel(position)) {
                super.getItemOffsets(outRect, view, parent, state);
                return;
            }
            int w = getWidth10() - getWidth15();
            if (isMain(position)) {
                position = position - getMainStart();
                int limit = getMainLimit();
                int _w = w / (limit - 1);
                if (limit < LINE_COUNT) {
                    w = getWidth15() * (LINE_COUNT - limit);
                } else {
                    w = (int) (w / 2.0f);
                }
                int w2 = (w / (limit - 1)) - _w;
                int linePos = position % limit;
                outRect.left = (w2 * linePos);
            } else if (isExtra(position)) {
                position = position - getExtraStart();
                int limit = getExtraLimit();//10
                int _w = w / (limit - 1);
                if (limit < LINE_COUNT) {
                    w = getWidth15() * (LINE_COUNT - limit);
                } else {
                    w = (int) (w / 2.0f);
                }
                int w2 = (w / (limit - 1)) - _w;
                int linePos = position % limit;
                outRect.left = (w2 * linePos);
            } else if (isSide(position)) {
                position = position - getSideStart();
                int limit = getSideLimit();//10
                int _w = w / (limit - 1);
                if (limit < LINE_COUNT) {
                    w = getWidth15() * (LINE_COUNT - limit);
                } else {
                    w = (int) (w / 2.0f);
                }
                int w2 = (w / (limit - 1)) - _w;
                int linePos = position % limit;
                outRect.left = (w2 * linePos);
            }
        }
    }

    int mLastStart, mLastEnd;

    void moveItem(int fromPosition, int toPosition) {
        //min(toPosition,fromPosition)-max(toPosition,fromPosition)的span
        notifyItemMoved(fromPosition, toPosition);
        mLastStart = Math.min(fromPosition, toPosition);
        mLastEnd = Math.max(fromPosition, toPosition);
    }

    private GridLayoutManager.SpanSizeLookup mSpanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
        @Override
        public int getSpanSize(int position) {
            if (isLabel(position)) {
                return DeckAdapater.LINE_COUNT;
            } else if (isMain(position)) {
                position = position - getMainStart();
                int limit = getMainLimit();
                if (position % limit == (limit - 1)) {
                    Log.d("kk", "pos=" + position + ",limit=" + limit + ",span=" + (DeckAdapater.LINE_COUNT - limit + 1));
                    return DeckAdapater.LINE_COUNT - limit + 1;
                }
            }
            return 1;
        }

        @Override
        public boolean isSpanIndexCacheEnabled() {
            return super.isSpanIndexCacheEnabled();
        }
    };

    private ItemTouchHelper2.Callback mCallback = new ItemTouchHelper2.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int id = viewHolder.getAdapterPosition();
            if (isLabel(id)) {
                return makeMovementFlags(0, 0);
            }
            int dragFlags;
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
            } else {
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            }
            return makeMovementFlags(dragFlags, 0);
        }

        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            super.onSelectedChanged(viewHolder, actionState);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int id = target.getAdapterPosition();
            int src = viewHolder.getAdapterPosition();
            if (isLabel(id)) {
                return false;
            }
            if ((isMain(id) && isMain(src))
                    || (isExtra(id) && isExtra(src))
                    || (isSide(id) && isSide(src))) {

                moveItem(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            } else if (isMain(id) && isSide(src)) {

            } else if (isSide(id) && isMain(src)) {

            } else if (isSide(id) && isExtra(src)) {

            }
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };
}

class DeckLayoutManager extends GridLayoutManager {
    DeckLayoutManager(Context context, final int span, SpanSizeLookup spanSizeLookup) {
        super(context, span);
        setSpanSizeLookup(spanSizeLookup);
    }

    @Override
    public void onItemsMoved(RecyclerView recyclerView, int from, int to, int itemCount) {
        super.onItemsMoved(recyclerView, from, to, itemCount);
    }
}

class DeckViewHolder extends RecyclerView.ViewHolder {
    DeckViewHolder(View view) {
        super(view);
        this.view = view;
        view.setTag(view.getId(), this);
        cardImage = $(R.id.card_image);
        labelText = $(R.id.label);
        textlayout = $(R.id.layout_label);
        numText = $(R.id.tv_text);
    }

    <T extends View> T $(int id) {
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

    final View view;
    final View textlayout;
    final TextView labelText, numText;
    final ImageView cardImage;
}
