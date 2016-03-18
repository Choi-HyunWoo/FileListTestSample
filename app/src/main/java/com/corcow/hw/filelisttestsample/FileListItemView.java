package com.corcow.hw.filelisttestsample;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by multimedia on 2016-03-18.
 */
public class FileListItemView extends FrameLayout {

    TextView fileNameView;
    TextView filePathView;

    public FileListItemView(Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.file_list_item, this);
        fileNameView = (TextView)findViewById(R.id.text_fileName);
        filePathView = (TextView)findViewById(R.id.text_filePath);
    }

    public void setListItem(FileItem item) {
        fileNameView.setText(item.name);
        filePathView.setText(item.absolutePath);
    }


}
