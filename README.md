# TLog
Android日志工具，支持Logcat输出和文件记录（可自定义大小，默认0.1M），两种显示方式都可以配置是否需要显示。
## 功能介绍
* 控制台日志显示，支持配置TAG过滤
* 保存内容到文件中
## 版本说明
* V1.0.0 项目初始提交
## 使用介绍
1. 下载最新tlog，import到project中；
2. 工程项目的gradle中：<br>
    ```compile project(':tlog')```
3. 如果你的项目中没有更改Application，在**AndroidManifest.xml**中配置如下：<br>
    ```
    <manifest>
        <application
            android:name="com.tao.admin.loglib.TLogApplication"
            ...
        </application>
    </manifest>
    ```
    
    如果你在项目中有自己的Application文件，如：<br>
    ```
    <manifest>
       
           <application
               android:name=".MyApplication"
               ...
           </application>
       
    </manifest>
    ```
    没关系，你可以让自己的Application继承TLogApplication：<br>
    ```
    public class MyApplication extends TLogApplication {
        @Override
        public void onCreate() {
            super.onCreate();
            ...
        }
    }
    ```
    或者你已经继承了其他Application,你可以在onCreate()中调用TLogApplication.initialize(this);<br>
    ```
    public class MyApplication extends Application {
        @Override
        public void onCreate() {
            super.onCreate();
            TLogApplication.initialize(this);
            ...
        }
    }
    ```
 4. 配置是否需要在logcat或文件中记录日志：<br>
    ```
    public class MyApplication extends Application {
        @Override
        public void onCreate() {
            super.onCreate();
            TLogApplication.initialize(this);
            IConfig.SHOW_LOG = true;//是否在logcat中打印log
            IConfig.WRITE_LOG = true;//是否在文件中记录
        }
    }
    ```
    修改文件名称，文件大小和TAG名称需要到Constants中去修改：<br>
    ```
    public class Constants {
    
        public static final String fileName = "tlog.log";//log日志的文件名称
    
        public static final int fileSize = 100000;//日志文件的大小，默认0.1M
    
        public static final String TAG = "LOG";//Logcat中显示的tag
    }
    ```
 5. 打印/记录日志
    * 带标题<br>
        ```
        Logger.i(title, log);
        Logger.w(title, log);
        Logger.e(title, log);
        ```
    * 无标题<br>
        ```
        Logger.i(log);
        Logger.w(log);
        Logger.e(log);
        ```
 6. 查看本地日志<br>
    ```
    String log = FileUtils.readLogText();
    mTextView.setText(log);
    ```
 
 