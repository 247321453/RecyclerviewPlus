package com.kk.recyclerviewplus;

class DeckItemUtils {

    public static boolean isMain(int pos) {
        return pos >= DeckAdapater.MainStart && pos <= DeckAdapater.MainEnd;
    }

    public static boolean isExtra(int pos) {
        return pos >= DeckAdapater.ExtraStart && pos <= DeckAdapater.ExtraEnd;
    }

    public static boolean isSide(int pos) {
        return pos >= DeckAdapater.SideStart && pos <= DeckAdapater.SideEnd;
    }

    public static boolean isLabel(int position) {
        if (position == DeckAdapater.MainLabel || position == DeckAdapater.SideLabel || position == DeckAdapater.ExtraLabel) {
            return true;
        }
        return false;
    }
}
