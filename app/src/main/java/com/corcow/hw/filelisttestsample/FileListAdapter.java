package com.corcow.hw.filelisttestsample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by multimedia on 2016-03-18.
 */
public class FileListAdapter extends BaseAdapter {
    List<String> fileNames = new ArrayList<String>();

    public void add(String fileName) {
        fileNames.add(fileName);
    }

    @Override
    public int getCount() {
        return fileNames.size();
    }

    @Override
    public Object getItem(int position) {
        return fileNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FileListItemView view;
        if (convertView != null) {
            view = (FileListItemView) convertView;
        } else {
            view = new FileListItemView(parent.getContext());
        }
        view.setListItem(fileNames.get(position));
        return view;
    }
}
