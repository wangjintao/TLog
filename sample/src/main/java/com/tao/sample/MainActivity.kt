package com.tao.sample

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tao.sample.databinding.ActivityMainBinding
import com.tao.tlog.v2.TLog

class MainActivity : AppCompatActivity() {

    private lateinit var mViewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        mViewBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        mViewBinding.addLogBtn.setOnClickListener {
            TLog.d("测试日志")
        }


    }
}