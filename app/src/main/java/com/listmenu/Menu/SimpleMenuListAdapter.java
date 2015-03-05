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
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SimpleMenuListAdapter extends BaseAdapter implements Menu {
    private MenuBuilder builder;
    private Context context;

    public SimpleMenuListAdapter(Context context){
        this.context = context;
        builder=new MenuBuilder(context);
    }
    public SimpleMenuListAdapter(Context context,int menuResId){
        this(context);
        inflate(menuResId);
    }
    @Override
    public int getCount() {
        return builder.size();
    }

    @Override
    public MenuItem getItem(int position) {
        return builder.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return builder.getItem(position).getItemId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)convertView=LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2,parent,false);
        MenuItem item=getItem(position);
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
    public MenuItem add(CharSequence title) {
        return builder.add(title);
    }
    @Override
    public MenuItem add(int titleRes) {
        return builder.add(titleRes);
    }

    @Override
    public MenuItem add(int groupId, int itemId, int order, CharSequence title) {
        return builder.add(groupId,itemId,order,title);
    }

    @Override
    public MenuItem add(int groupId, int itemId, int order, int titleRes) {
        return builder.add(groupId,itemId,order,titleRes);
    }

    @Override
    public SubMenu addSubMenu(CharSequence title) {
        return builder.addSubMenu(title);
    }

    @Override
    public SubMenu addSubMenu(int titleRes) {
        return builder.addSubMenu(titleRes);
    }

    @Override
    public SubMenu addSubMenu(int groupId, int itemId, int order, CharSequence title) {
        return builder.addSubMenu(groupId, itemId, order, title);
    }

    @Override
    public SubMenu addSubMenu(int groupId, int itemId, int order, int titleRes) {
        return builder.addSubMenu(groupId, itemId, order, titleRes);
    }

    @Override
    public int addIntentOptions(int groupId, int itemId, int order, ComponentName caller, Intent[] specifics, Intent intent, int flags, MenuItem[] outSpecificItems) {
        return builder.addIntentOptions(groupId, itemId, order, caller, specifics, intent, flags, outSpecificItems);
    }

    @Override
    public void removeItem(int id) {
        builder.removeItem(id);
    }

    @Override
    public void removeGroup(int groupId) {
        builder.removeGroup(groupId);
    }

    @Override
    public void clear() {
        builder.clear();
    }

    @Override
    public void setGroupCheckable(int group, boolean checkable, boolean exclusive) {
        builder.setGroupCheckable(group, checkable, exclusive);
    }

    @Override
    public void setGroupVisible(int group, boolean visible) {
        builder.setGroupVisible(group,visible);
    }

    @Override
    public void setGroupEnabled(int group, boolean enabled) {
        builder.setGroupEnabled(group, enabled);
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

    public void inflate(int menuRes){
        getMenuInflater().inflate(menuRes,this);
    }

    public MenuInflater getMenuInflater(){
        return new MenuInflater(context);
    }
}
