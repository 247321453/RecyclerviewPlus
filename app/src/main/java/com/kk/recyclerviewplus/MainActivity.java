package com.kk.recyclerviewplus;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper2;
import android.view.View;

import com.kk.recyclerviewplus.cards.DeckAdapter;

public class MainActivity extends Activity implements ItemTouchHelper2.OnDragListner {
    private RecyclerView mRecyclerView;
    private DeckAdapter mDeckAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mDeckAdapter = new DeckAdapter(this, mRecyclerView);
        mRecyclerView.setAdapter(mDeckAdapter);
        findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeckAdapter.notifyDataSetChanged();
            }
        });
        findViewById(R.id.main1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeckAdapter.setMainCount(mDeckAdapter.getMainCount() + 1);
                mDeckAdapter.notifyDataSetChanged();
            }
        });
        findViewById(R.id.main2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeckAdapter.setMainCount(mDeckAdapter.getMainCount() - 1);
                mDeckAdapter.notifyDataSetChanged();
            }
        });

        findViewById(R.id.extra1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeckAdapter.setExtraCount(mDeckAdapter.getExtraCount() + 1);
                mDeckAdapter.notifyDataSetChanged();
            }
        });
        findViewById(R.id.extra2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeckAdapter.setExtraCount(mDeckAdapter.getExtraCount() - 1);
                mDeckAdapter.notifyDataSetChanged();
            }
        });

        findViewById(R.id.side1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeckAdapter.setSideCount(mDeckAdapter.getSideCount() + 1);
                mDeckAdapter.notifyDataSetChanged();
            }
        });
        findViewById(R.id.side2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeckAdapter.setSideCount(mDeckAdapter.getSideCount() - 1);
                mDeckAdapter.notifyDataSetChanged();
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
