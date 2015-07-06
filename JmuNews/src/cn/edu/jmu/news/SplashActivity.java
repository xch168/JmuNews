package cn.edu.jmu.news;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Òýµ¼Ò³
 * 
 * @author Administrator
 *
 */
public class SplashActivity extends Activity {

	private final int MSG_TO_MAIN = 0x123;
	
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			
			if(msg.what == MSG_TO_MAIN) {
				Intent intent = new Intent(SplashActivity.this, MainActivity.class);
				startActivity(intent);
				
				SplashActivity.this.finish();
			}
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		mHandler.sendEmptyMessageDelayed(MSG_TO_MAIN, 2000);
	}

}
