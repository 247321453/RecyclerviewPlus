package com.kk.recyclerviewplus;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.OnItemDragListener;
import android.view.View;
import android.widget.Toast;

import com.kk.recyclerviewplus.cards.DeckAdapter;

public class MainActivity extends Activity implements OnItemDragListener {
    private RecyclerView mRecyclerView;
    private DeckAdapter mDeckAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mDeckAdapter = new DeckAdapter(this, mRecyclerView, this);
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
                mDeckAdapter.setMainCount(mDeckAdapter.getMainRealCount() + 1);
                mDeckAdapter.notifyDataSetChanged();
            }
        });
        findViewById(R.id.main2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeckAdapter.setMainCount(mDeckAdapter.getMainRealCount() - 1);
                mDeckAdapter.notifyDataSetChanged();
            }
        });

        findViewById(R.id.extra1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeckAdapter.setExtraCount(mDeckAdapter.getExtraRealCount() + 1);
                mDeckAdapter.notifyDataSetChanged();
            }
        });
        findViewById(R.id.extra2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeckAdapter.setExtraCount(mDeckAdapter.getExtraRealCount() - 1);
                mDeckAdapter.notifyDataSetChanged();
            }
        });

        findViewById(R.id.side1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeckAdapter.setSideCount(mDeckAdapter.getSideRealCount() + 1);
                mDeckAdapter.notifyDataSetChanged();
            }
        });
        findViewById(R.id.side2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDeckAdapter.setSideCount(mDeckAdapter.getSideRealCount() - 1);
                mDeckAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDragStart() {

    }

    @Override
    public void onDragLongPress(int pos) {
        Toast.makeText(this, "on long press :" + pos, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if(getRequestedOrientation()!=newConfig.orientation){
            mDeckAdapter.notifyDataSetChanged();
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onDragLongPressEnd() {

    }

    @Override
    public void onDragEnd() {

    }
}
