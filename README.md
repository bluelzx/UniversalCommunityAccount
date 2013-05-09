UniversalCommunityAccount
=========================

第三方账号登录通用包
-------------
务必在Manifest中声明这些

        <activity
            android:name="com.tenpage.uca.LoginSinaWeiboActivity"
            android:label="@string/app_name" >
        </activity>
        <activity
            android:name="com.tenpage.uca.LoginRenrenActivity"
            android:label="@string/app_name" >
        </activity>
        <!-- 人人网必须声明的 -->
        <activity
            android:name="com.renren.api.connect.android.AuthorizationHelper$BlockActivity"
            android:theme="@android:style/Theme.Dialog" >
        </activity>
        

