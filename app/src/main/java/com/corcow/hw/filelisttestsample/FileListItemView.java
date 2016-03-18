package com.corcow.hw.filelisttestsample;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by multimedia on 2016-03-18.
 */
public class FileListItemView extends FrameLayout {

    TextView fileNameView;

    public FileListItemView(Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.file_list_item, this);
        fileNameView = (TextView)findViewById(R.id.text_filename);
    }

    public void setListItem(String fileName) {
        fileNameView.setText(fileName);
    }


}
