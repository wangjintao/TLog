package com.tao.tlog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.tao.admin.loglib.FileUtils;

/**
 * 作者：WangJintao
 * 时间：2017/8/26
 * 邮箱：wangjintao1988@163.com
 */

public class LogActivity extends AppCompatActivity {
    private TextView logView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        logView = (TextView) findViewById(R.id.tv_log);
        String log = FileUtils.readLogText();
        logView.setText(log);
    }
}
