package com.reactnativenavigation.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import com.google.android.material.tabs.TabLayout; // import android.support.design.widget.TabLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reactnativenavigation.params.StyleParams;
import com.reactnativenavigation.views.utils.TopTabsIconColorHelper;

public class TopTabs extends TabLayout {

    public TopTabs(Context context) {
        super(context);
    }

    void setSelectedTabIndicatorStyle(StyleParams style) {
        if (style.selectedTopTabIndicatorColor.hasColor()) {
            setSelectedTabIndicatorColor(style.selectedTopTabIndicatorColor.getColor());
        }

        if (style.selectedTopTabIndicatorHeight >= 0) {
            setSelectedTabIndicatorHeight(style.selectedTopTabIndicatorHeight);
        }
    }

    void setTopTabsTextColor(StyleParams style) {
        ColorStateList originalTabColors = getTabTextColors();
        int selectedTabColor = originalTabColors != null ? originalTabColors.getColorForState(TabLayout.SELECTED_STATE_SET, -1) : -1;
        int tabTextColor = originalTabColors != null ? originalTabColors.getColorForState(TabLayout.EMPTY_STATE_SET, -1) : -1;

        if (style.topTabTextColor.hasColor()) {
            tabTextColor = style.topTabTextColor.getColor();
        }

        if (style.selectedTopTabTextColor.hasColor()) {
            selectedTabColor = style.selectedTopTabTextColor.getColor();
        }

        setTabTextColors(tabTextColor, selectedTabColor);
    }

    void setTopTabsTextFontFamily(StyleParams style) {
        if (style.topTabTextFontFamily.hasFont()) {
            ViewGroup viewGroup = (ViewGroup) this.getChildAt(0);

            for (int tab = 0; tab < viewGroup.getChildCount(); tab++) {
                ViewGroup tabViewGroup = (ViewGroup) viewGroup.getChildAt(tab);

                for (int i = 0; i < tabViewGroup.getChildCount(); i++) {
                    View tabViewChild = tabViewGroup.getChildAt(i);
                    if (tabViewChild instanceof TextView) {
                        ((TextView) tabViewChild).setTypeface(
                                style.topTabTextFontFamily.get(), Typeface.NORMAL);
                    }
                }
            }
        }
    }

    void setScrollable(StyleParams style) {
        if (style.topTabsScrollable) {
            setTabMode(TabLayout.MODE_SCROLLABLE);
        } else {
            setTabMode(TabLayout.MODE_FIXED);
        }
    }

    public void setTopTabsIconColor(StyleParams style) {
        new TopTabsIconColorHelper(this, style).colorIcons(getSelectedIconColor(), getUnselectedIconColor());
    }

    private int getSelectedIconColor() {
        ColorStateList originalTabColors = getTabTextColors();
        return originalTabColors != null ? originalTabColors.getColorForState(TabLayout.SELECTED_STATE_SET, -1) : -1;
    }

    private int getUnselectedIconColor() {
        ColorStateList originalTabColors = getTabTextColors();
        return originalTabColors != null ? originalTabColors.getColorForState(TabLayout.SELECTED_STATE_SET, -1) : -1;
    }
}
