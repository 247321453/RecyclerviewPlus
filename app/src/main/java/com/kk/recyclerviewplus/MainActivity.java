package com.kk.recyclerviewplus;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.v7.widget.helper.ItemTouchHelper2;
import android.view.View;

public class MainActivity extends Activity implements ItemTouchHelper2.OnDragListner {
    private RecyclerView mRecyclerView;
    private DeckAdapater mDeckAdapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setAdapter((mDeckAdapater = new DeckAdapater(this, mRecyclerView)));
        mRecyclerView.setLayoutManager(new DeckLayoutManager(this, DeckAdapater.LINE_COUNT, new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (DeckItemUtils.isLabel(position)) {
                    return DeckAdapater.LINE_COUNT;
                }
                return 1;
            }
        }));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildAdapterPosition(view);
                if (DeckItemUtils.isLabel(position)) {
                    super.getItemOffsets(outRect, view, parent, state);
                    return;
                }
                if (DeckItemUtils.isMain(position)) {
                    position = position - DeckAdapater.MainStart;
                } else if (DeckItemUtils.isExtra(position)) {
                    position = position - DeckAdapater.ExtraStart;
                } else if (DeckItemUtils.isSide(position)) {
                    position = position - DeckAdapater.SideStart;
                }
                if (position % DeckAdapater.LINE_COUNT != 0) {
                    outRect.left = -((mDeckAdapater.LINE_COUNT - 10) * mDeckAdapater.getWidth() / (mDeckAdapater.LINE_COUNT - 1));
                }
//                super.getItemOffsets(outRect, view, parent, state);
            }
        });
        ItemTouchHelper2 touchHelper = new ItemTouchHelper2(this, new ItemTouchHelper2.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int id = viewHolder.getAdapterPosition();
                if (DeckItemUtils.isLabel(id)) {
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
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int id = target.getAdapterPosition();
                int src = viewHolder.getAdapterPosition();
                if (DeckItemUtils.isLabel(id)) {
                    return false;
                }
                if ((DeckItemUtils.isMain(id) && DeckItemUtils.isMain(src))
                        || (DeckItemUtils.isExtra(id) && DeckItemUtils.isExtra(src))
                        || (DeckItemUtils.isSide(id) && DeckItemUtils.isSide(src))) {
                    mDeckAdapater.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                    return true;
                }
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });
        touchHelper.setEnableClickDrag(true);
        touchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onDragStart() {

    }

    @Override
    public void onDragLongPress(int pos) {

    }

    @Override
    public void onDragLongPressEnd() {

    }

    @Override
    public void onDragEnd() {

    }
}
