package com.tao.tlog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tao.admin.loglib.Logger;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText logEdit, titleEdit;
    private Button logButton, checkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logEdit = (EditText) findViewById(R.id.et_log);
        titleEdit = (EditText) findViewById(R.id.et_title);
        logButton = (Button) findViewById(R.id.btn_log);
        logButton.setOnClickListener(this);
        checkButton = (Button) findViewById(R.id.btn_check);
        checkButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_log:
                String title = titleEdit.getText().toString().trim();
                String log = logEdit.getText().toString().trim();
                Logger.w(title,log);
//                Logger.w(log);
//                Logger.e(title,log);
                break;
            case R.id.btn_check:
                startActivity(new Intent(this, LogActivity.class));
                break;
        }
    }
}
