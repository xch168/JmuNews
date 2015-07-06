package cn.edu.jmu.news;

import android.content.Context;

import com.baidu.frontia.FrontiaApplication;
import com.baidu.mapapi.SDKInitializer;

/**
 *  ȫ��Application�����ڳ�ʼ���ٶȷ���ͻ�ȡ��Context
 * @author Administrator
 *
 */
public class JmuNewsApplication extends FrontiaApplication {

	private static Context mContext;

	@Override
	public void onCreate() {
		super.onCreate();
		
		mContext = getApplicationContext();
		
		// ��ʼ���ٶ����ͷ���
		FrontiaApplication.initFrontiaApplication(mContext);
		// ��ʼ���ٶȵ�ͼ
        SDKInitializer.initialize(getApplicationContext());  
	}

	public static Context getContext() {
		return mContext;
	}

}
