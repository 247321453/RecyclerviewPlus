package com.kk.recyclerviewplus;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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
        mDeckAdapater = new DeckAdapater(this, mRecyclerView);
        mRecyclerView.setAdapter(mDeckAdapater);
        findViewById(R.id.main1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeckAdapater.setMainCount(mDeckAdapater.getMainCount() + 1);
                mDeckAdapater.notifyDataSetChanged();
            }
        });
        findViewById(R.id.main2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeckAdapater.setMainCount(mDeckAdapater.getMainCount() - 1);
                mDeckAdapater.notifyDataSetChanged();
            }
        });

        findViewById(R.id.extra1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeckAdapater.setExtraCount(mDeckAdapater.getExtraCount() + 1);
                mDeckAdapater.notifyDataSetChanged();
            }
        });
        findViewById(R.id.extra2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeckAdapater.setExtraCount(mDeckAdapater.getExtraCount() - 1);
                mDeckAdapater.notifyDataSetChanged();
            }
        });

        findViewById(R.id.side1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeckAdapater.setSideCount(mDeckAdapater.getSideCount() + 1);
                mDeckAdapater.notifyDataSetChanged();
            }
        });
        findViewById(R.id.side2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeckAdapater.setSideCount(mDeckAdapater.getSideCount() - 1);
                mDeckAdapater.notifyDataSetChanged();
            }
        });
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
