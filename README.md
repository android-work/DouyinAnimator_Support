# DouyinAnimator_Support
一系列的加载动画（仿抖音的视频缓冲拉伸动画、仿抖音视频数据加载的两个小球切换动画、三个小球来回切换加载动画、仿抖音视频下载弹窗动画）

1、导入仓库
allprojects {
repositories {
...
maven { url 'https://jitpack.io' }
}
}

2、导入依赖
dependencies {
implementation 'com.github.android-work:DouyinAnimator_Support:v1.0.1'
}

相关的动画调用方法
#### 抖音进度条拉伸动画
  ##### 1、添加布局
      <com.work.android.loadview.DouyinLoadingBarView
        android:layout_width="match_parent"
        android:layout_height="10dp"/>
  ##### 2、初始化
       设置动画颜色 setColor("ffffff")
       启动动画 startAnimator()
       停止动画 stopAnimator()
       
       
#### 抖音两个小球来回切换动画
  ##### 1、添加布局
      <com.work.android.loadview.DouYinLoadTwoBallView
        android:layout_marginTop="10dp"
        android:layout_width="200dp"
        android:layout_height="200dp"
        android:id="@+id/load_ball"/>
        
  ##### 2、初始化<br>
      设置小球颜色 setColor(int color1,int color2)
      启动动画 startAnimator()
      停止动画 stopAnimator()


#### 三个小球来回选中加载动画
  ##### 1、添加布局
       <com.work.android.loadview.ThreePointCycleSwitching
          android:layout_marginTop="10dp"
          android:layout_width="100dp"
          android:layout_height="20dp"
          android:id="@+id/load_three"/>
          
  ##### 2、初始化
        设置选中和未选中小球颜色 setColor(int normalColor,int selectColor)
        启动动画 startAnimator()
        停止动画 stopAnimator()
        
        
#### 仿抖音下载视频弹窗进度动画
   ##### 1、添加布局
        <com.work.android.loadview.DownloadPopupWindowView
            android:layout_marginTop="10dp"
            android:layout_width="300dp"
            android:layout_height="300dp"
            android:id="@+id/load_down"/>
           
   ##### 2、初始化
        设置提示内容，默认"正在保存到本地" setContent(String content)
        实时调用setCurrentPorgress(float progress)更新下载进度（传的是分数：0.5表示完成50%）
        设置进度轮廓宽度 setStrokeWidth(float strokeWidth）  参数传的是dp值
        设置文字大小 setProgressTvSize(float progressTvSize) 参数传的是dp值 
        设置颜色 setColor(int color)
        设置环形进度大小 setSize(float size)
    

### 图片描述<br>
![图片描述](https://github.com/android-work/DouyinAnimator_Support/blob/master/IMAGE/Screenshot_2019-08-23-15-35-18.png)
