UniversalCommunityAccount
=========================

第三方账号登录通用包
-------------
务必在Manifest中声明这些

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
        <!-- 自定义页面 -->
        <activity
            android:name="com.tenpage.uca.LoginSinaWeiboActivity"
            android:label="@string/app_name" >
        </activity>
        <activity
            android:name="com.tenpage.uca.LoginRenrenActivity"
            android:label="@string/app_name" >
        </activity>
        <activity
            android:name="com.tenpage.uca.LoginQqActivity"
            android:label="@string/app_name" >
        </activity>
        <!-- 第三方页面 -->
        <activity
            android:name="com.renren.api.connect.android.AuthorizationHelper$BlockActivity"
            android:theme="@android:style/Theme.Dialog" >
        </activity>
        
        <activity
            android:name="com.tencent.tauth.AuthActivity"
            android:launchMode="singleTask"
            android:noHistory="true" >
            <intent-filter>
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />
                <data android:scheme="tencent2222" /><!-- 数字为APP_KEY -->
            </intent-filter>
        </activity>
        

