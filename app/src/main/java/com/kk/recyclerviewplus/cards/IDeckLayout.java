package com.kk.recyclerviewplus.cards;

public interface IDeckLayout {
    int getMaxWidth();

    int getWidth15();

    int getWidth10();

    boolean isLabel(int position);

    boolean isMain(int position);

    boolean isExtra(int position);

    boolean isSide(int position);

    int getMainCount();

    int getExtraCount();

    int getSideCount();

    int getMainLimit();

    int getExtraLimit();

    int getSideLimit();

    int getMainIndex(int pos);

    int getExtraIndex(int pos);

    int getSideIndex(int pos);

    int getLineLimitCount();

    boolean moveItem(int fromPosition, int toPosition);
}
