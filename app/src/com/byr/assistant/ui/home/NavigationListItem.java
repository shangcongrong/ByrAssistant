package com.byr.assistant.ui.home;

/**
 * User: orange
 * Date: 13-11-20
 * Time: 下午10:00
 */
public class NavigationListItem {

    private int navigationStringId;
    private int navigationDrawableId;

    public NavigationListItem(int navigationStringId, int navigationDrawableId) {
        this.navigationStringId = navigationStringId;
        this.navigationDrawableId = navigationDrawableId;
    }

    public int getNavigationStringId() {
        return navigationStringId;
    }

    public int getNavigationDrawableId() {
        return navigationDrawableId;
    }

}
