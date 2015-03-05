package com.listmenu.Menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.TwoLineListItem;

public class ExpandableMenuListAdapter extends BaseExpandableListAdapter implements Menu {
    private MenuBuilder builder;
    private Context context;

    public ExpandableMenuListAdapter(Context context){
        this.context = context;
        builder=new MenuBuilder(context);
    }

    public ExpandableMenuListAdapter(Context context,int menuResId){
        this(context);
        inflate(menuResId);
    }

    public void inflate(int menuRes){
        getMenuInflater().inflate(menuRes,this);
    }

    public MenuInflater getMenuInflater(){
        return new MenuInflater(context);
    }

    @Override
    public int getGroupCount() {
        return builder.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(builder.getItem(groupPosition).getSubMenu()==null)return 0;
        return builder.getItem(groupPosition).getSubMenu().size();
    }

    @Override
    public MenuItem getGroup(int groupPosition) {
        return builder.getItem(groupPosition);
    }

    @Override
    public MenuItem getChild(int groupPosition, int childPosition) {
        return builder.getItem(groupPosition).getSubMenu().getItem(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return builder.getItem(groupPosition).getItemId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return builder.getItem(groupPosition).getSubMenu().getItem(childPosition).getItemId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView==null)convertView= LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2,parent,false);
        MenuItem item=getGroup(groupPosition);
        TextView text1=(TextView)convertView.findViewById(android.R.id.text1);
        TextView text2=(TextView)convertView.findViewById(android.R.id.text2);
        text1.setText(item.getTitle());
        text1.setCompoundDrawablesWithIntrinsicBounds(item.getIcon(), null, null, null);
        if(item.getTitleCondensed()==null||item.getTitleCondensed().toString().isEmpty()||item.getTitleCondensed().equals(item.getTitle())) {
            text2.setVisibility(View.GONE);
        }
        else text2.setText(item.getTitleCondensed());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView==null)convertView=LayoutInflater.from(context).inflate(android.R.layout.simple_expandable_list_item_2,parent,false);
        ((TwoLineListItem)convertView).setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        MenuItem item=getChild(groupPosition,childPosition);
        TextView text1=(TextView)convertView.findViewById(android.R.id.text1);
        TextView text2=(TextView)convertView.findViewById(android.R.id.text2);
        text1.setText(item.getTitle());
        text1.setCompoundDrawablesWithIntrinsicBounds(item.getIcon(), null, null, null);
        if(item.getTitleCondensed()==null||item.getTitleCondensed().toString().isEmpty()||item.getTitleCondensed().equals(item.getTitle())) {
            text2.setVisibility(View.GONE);
        }
        else text2.setText(item.getTitleCondensed());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public MenuItem add(CharSequence title) {
        MenuItem menuItem = builder.add(title);
        notifyDataSetChanged();
        return menuItem;
    }

    @Override
    public MenuItem add(int titleRes) {
        MenuItem menuItem = builder.add(titleRes);
        notifyDataSetChanged();
        return menuItem;
    }

    @Override
    public MenuItem add(int groupId, int itemId, int order, CharSequence title) {
        MenuItem menuItem = builder.add(groupId,itemId,order,title);
        notifyDataSetChanged();
        return menuItem;
    }

    @Override
    public MenuItem add(int groupId, int itemId, int order, int titleRes) {
        MenuItem menuItem = builder.add(groupId,itemId,order,titleRes);
        notifyDataSetChanged();
        return menuItem;
    }

    @Override
    public SubMenu addSubMenu(CharSequence title) {
        SubMenu subMenu = builder.addSubMenu(title);
        notifyDataSetChanged();
        return subMenu;
    }

    @Override
    public SubMenu addSubMenu(int titleRes) {
        SubMenu subMenu = builder.addSubMenu(titleRes);
        notifyDataSetChanged();
        return subMenu;
    }

    @Override
    public SubMenu addSubMenu(int groupId, int itemId, int order, CharSequence title) {
        SubMenu subMenu = builder.addSubMenu(groupId, itemId, order, title);
        notifyDataSetChanged();
        return subMenu;
    }

    @Override
    public SubMenu addSubMenu(int groupId, int itemId, int order, int titleRes) {
        SubMenu subMenu = builder.addSubMenu(groupId, itemId, order, titleRes);
        notifyDataSetChanged();
        return subMenu;
    }

    @Override
    public int addIntentOptions(int groupId, int itemId, int order, ComponentName caller, Intent[] specifics, Intent intent, int flags, MenuItem[] outSpecificItems) {
        int options = builder.addIntentOptions(groupId, itemId, order, caller, specifics, intent, flags, outSpecificItems);
        notifyDataSetChanged();
        return options;
    }

    @Override
    public void removeItem(int id) {
        builder.removeItem(id);
        notifyDataSetChanged();
    }

    @Override
    public void removeGroup(int groupId) {
        builder.removeGroup(groupId);
        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        builder.clear();
        notifyDataSetChanged();
    }

    @Override
    public void setGroupCheckable(int group, boolean checkable, boolean exclusive) {
        builder.setGroupCheckable(group, checkable, exclusive);
    }

    @Override
    public void setGroupVisible(int group, boolean visible) {
        builder.setGroupVisible(group, visible);
    }

    @Override
    public void setGroupEnabled(int group, boolean enabled) {
        builder.setGroupVisible(group, enabled);
    }

    @Override
    public boolean hasVisibleItems() {
        return builder.hasVisibleItems();
    }

    @Override
    public MenuItem findItem(int id) {
        return builder.findItem(id);
    }

    @Override
    public int size() {
        return builder.size();
    }

    @Override
    public MenuItem getItem(int index) {
        return builder.getItem(index);
    }

    @Override
    public void close() {
        builder.close();
    }

    @Override
    public boolean performShortcut(int keyCode, KeyEvent event, int flags) {
        return builder.performShortcut(keyCode, event, flags);
    }

    @Override
    public boolean isShortcutKey(int keyCode, KeyEvent event) {
        return builder.isShortcutKey(keyCode, event);
    }

    @Override
    public boolean performIdentifierAction(int id, int flags) {
        return builder.performIdentifierAction(id, flags);
    }

    @Override
    public void setQwertyMode(boolean isQwerty) {
        builder.setQwertyMode(isQwerty);
    }
}
