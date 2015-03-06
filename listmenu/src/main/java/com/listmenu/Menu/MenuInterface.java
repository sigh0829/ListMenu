package com.listmenu.Menu;

import android.graphics.drawable.Drawable;

public interface MenuInterface {
    public void initialize(MenuBuilder menu);
    public int getWindowAnimations();
    public interface ItemView {
        public void initialize(MenuItemImpl itemData, int menuType);
        public MenuItemImpl getItemData();
        public void setTitle(CharSequence title);
        public void setEnabled(boolean enabled);
        public void setCheckable(boolean checkable);
        public void setChecked(boolean checked);
        public void setShortcut(boolean showShortcut, char shortcutKey);
        public void setIcon(Drawable icon);
        public boolean prefersCondensedTitle();
        public boolean showsIcon();
    }
}
