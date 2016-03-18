package com.corcow.hw.filelisttestsample;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by multimedia on 2016-03-18.
 */
public class FileListAdapter extends BaseAdapter {

    List<FileItem> items = new ArrayList<FileItem>();    // list items

    public void add(String name, String absolutePath) {
        FileItem item = new FileItem();
        item.name = name;
        item.absolutePath = absolutePath;
        items.add(item);                                // FileItem(이름, 경로) 추가
        notifyDataSetChanged();
    }

    public void replace(String currentPath) {
        clear();
        File currentFile = new File(currentPath);   // 현재 경로 (폴더)
        for (File f : currentFile.listFiles()) {    // 현재 경로의 파일 리스트를
            add(f.getName(), f.getAbsolutePath());  // List에 추가
        }
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
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
        view.setListItem(items.get(position));
        return view;
    }
}
