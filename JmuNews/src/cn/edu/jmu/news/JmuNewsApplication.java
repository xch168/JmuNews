package cn.edu.jmu.news;

import android.content.Context;

import com.baidu.frontia.FrontiaApplication;
import com.baidu.mapapi.SDKInitializer;

/**
 *  全局Application，用于初始化百度服务和获取或Context
 * @author Administrator
 *
 */
public class JmuNewsApplication extends FrontiaApplication {

	private static Context mContext;

	@Override
	public void onCreate() {
		super.onCreate();
		
		mContext = getApplicationContext();
		
		// 初始化百度推送服务
		FrontiaApplication.initFrontiaApplication(mContext);
		// 初始化百度地图
        SDKInitializer.initialize(getApplicationContext());  
	}

	public static Context getContext() {
		return mContext;
	}

}
