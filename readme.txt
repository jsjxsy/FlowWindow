推荐文章
http://blog.csdn.net/stevenhu_223/article/details/8504058

悬浮窗口分activity级别和application级别的
在不同手机上显示的情况不一样
小米手机两个级别一样在标准手机上会不一样。

问题:
悬浮窗口不现实。小米手机将悬浮窗口的权限默认给禁掉了,需要在应用中设置
http://blog.csdn.net/wei18359100306/article/details/41824007

Android6.0（Android M） 悬浮窗被禁用，无权限开启悬浮窗的解决方案
http://blog.csdn.net/cxq234843654/article/details/51218540

Android桌面悬浮窗效果实现，仿360手机卫士悬浮窗效果
http://blog.163.com/benben_long/blog/static/1994582432014111652337971/


Android无需权限显示悬浮窗, 兼谈逆向分析app
http://www.jianshu.com/p/167fd5f47d5c

TYPE_PHONE：属于悬浮窗（并且给一个Activity的话按下HOME键会出现看不到桌面上的图标异常情况）
TYPE_TOAST：不属于悬浮窗，但有悬浮窗的功能，缺点是在Android2.3上无法接收点击事件
TYPE_SYSTEM_ALERT：属于悬浮窗，但是会被禁止



mBigLayoutParams.x = 0;
mBigLayoutParams.y = mSmallLayoutParams.y;
//这两个值会影响前面的值
mBigLayoutParams.width = mBigWindow.getViewWidth();
mBigLayoutParams.height = mBigWindow.getViewHeight()