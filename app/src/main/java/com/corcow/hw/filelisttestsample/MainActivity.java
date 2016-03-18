package com.corcow.hw.filelisttestsample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    // Permission request const
    public static final int MY_PERMISSIONS_REQUEST_READWRITE_STOREAGE = 0;

    // Views
    TextView pathView;
    ListView fileListView;
    FileListAdapter mAdapter;

    // Variables
    String rootPath;
    String currentPath;
    File root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** 1. 권한 확인 변수 설정 (내가 필요로 하는 permission이 이 액티비티에서 허가되었는지를 판단) */
        int permissionCheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionCheck2 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        /** 2. 권한 요청 (PERMISSION_GRANTED = permission 인정) */
        // 이 App에 대해 다음 permission들이 하나라도 허가되지 않았다면,
        if (permissionCheck1 != PackageManager.PERMISSION_GRANTED || permissionCheck2 != PackageManager.PERMISSION_GRANTED) {
            // 액티비티에서 permission들 요청
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READWRITE_STOREAGE
            );
        }

        // View & Adapter Initialize
        pathView = (TextView)findViewById(R.id.text_path);
        fileListView = (ListView)findViewById(R.id.listView);
        final FileListAdapter mAdapter = new FileListAdapter();
        fileListView.setAdapter(mAdapter);

        // root path, root directory, root file list 가져오기
        rootPath = Environment.getExternalStorageDirectory().getPath();
        root = Environment.getExternalStorageDirectory();
        pathView.setText(root.getPath());       // 현재 Path 확인
        File[] files = root.listFiles();        // 현재 경로의 File 리스트 받아옴
        Toast.makeText(MainActivity.this, "" + files.length, Toast.LENGTH_SHORT).show();

        // listview에 파일 추가
        for (File f : files) {
            mAdapter.add(f.getName(), f.getAbsolutePath());     // 파일이름, 경로
        }

        fileListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 선택된 item이 폴더인지 파일인지 구분
                File selected = new File(((FileItem)mAdapter.getItem(position)).absolutePath);
                if (selected.isDirectory()) {
                    // 폴더
                    /** TODO
                     * 1) 경로명 TextView 갱신
                     * 2) 상위 폴더로 "..."
                     */
                    mAdapter.replace(((FileItem) mAdapter.getItem(position)).absolutePath);     // 하위 경로 불러오기
                } else {
                    // 파일
                    // 실행! FileInputStream...
                    Toast.makeText(MainActivity.this, "파일", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // 파일 확장자 가져오기
    public static String getExtension(String fileStr){
        return fileStr.substring(fileStr.lastIndexOf(".")+1,fileStr.length());
    }

    /** 3. Permission 요청에 대한 응답을 Handle하는 callback 함수 override */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READWRITE_STOREAGE :
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the functionality that depends on this permission.
                }
                return;
        }

        // other 'case' lines to check for other permissions this app might request

    }
}
