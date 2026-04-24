package com.tao.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tao.sample.databinding.ActivityMainBinding
import com.tao.tlog.v2.TLog

class MainActivity : AppCompatActivity() {

    private lateinit var mViewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        mViewBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        mViewBinding.addLogBtn.setOnClickListener {
            //打印长日志
            val poem = buildLongPoemLog()
//            TLog.d(poem)
            //打印不同级别日志
            val log = buildShortLog()
//            TLog.v(log)
//            TLog.d(log)
//            TLog.i(log)
//            TLog.w(log)
//            TLog.e(log)
            //添加tag
//            TLog.tag("tag1").d(log)
//            TLog.tag("tag2").d(log)
//            TLog.tag("tag3").d(log)
            //记录日志到文件
            TLog.file().d(log)
            TLog.file().d(poem)

        }
    }

    private fun buildShortLog(): String {
        return "伟大的中华人民共和国万岁！"
    }

    private fun buildLongPoemLog(): String {
        val poem = """
            《行路难》
            作者：李白
            金樽清酒斗十千，玉盘珍羞直万钱。
            停杯投箸不能食，拔剑四顾心茫然。
            欲渡黄河冰塞川，将登太行雪满山。
            闲来垂钓碧溪上，忽复乘舟梦日边。
            行路难，行路难，多歧路，今安在？
            长风破浪会有时，直挂云帆济沧海。
        """.trimIndent()

        return buildString {
            appendLine("长日志切片输出测试开始")
            repeat(20) { index ->
                appendLine("第${index + 1}遍")
                appendLine(poem)
            }
            append("长日志切片输出测试结束")
        }
    }
}
