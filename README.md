# TLog
Android日志工具，支持Logcat输出和文件记录（可自定义大小，默认0.1M），两种显示方式都可以配置是否需要显示。
## 功能介绍
* 控制台日志显示，支持配置TAG过滤
* 保存内容到文件中
## 版本说明
* V1.0.0 项目初始提交
## 使用介绍
1. 在根**build.gradle**中添加：<br>
    ```
    allprojects {
    	repositories {
    		...
    		maven { url 'https://jitpack.io' }
        }
    }
    ```
2. 项目的**build.gradle**中添加：<br>
    ```
    dependencies {
        compile 'com.github.wangjintao:TLog:V1.0.0'
    }

    ```
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
            IConfig.getInstance().isShowLog(true)//是否在logcat中打印log,默认不打印
                    .isWriteLog(true)//是否在文件中记录，默认不记录
                    .fileSize(100000)//日志文件的大小，默认0.1M,以bytes为单位
                    .tag("myTag");//logcat 日志过滤tag
        }
    }
    ```
 5. 打印/记录日志
    * 带标题<br>
        ```
        Logger.i(title, log);
        Logger.w(title, log);
        Logger.e(title, log);
        ```
        ![带标题](https://github.com/wangjintao/TLog/blob/master/pictures/pic1.png)
    * 无标题<br>
        ```
        Logger.i(log);
        Logger.w(log);
        Logger.e(log);
        ```
        ![无标题](https://github.com/wangjintao/TLog/blob/master/pictures/pic2.png)
 6. 查看本地日志<br>
    ```
    String log = FileUtils.readLogText();
    mTextView.setText(log);
    ```
    ![本地日志](https://github.com/wangjintao/TLog/blob/master/pictures/pic3.png)
 ## 关于作者
 **作者：王金涛**<br>
 **邮箱：wangjintao1988@163.com**