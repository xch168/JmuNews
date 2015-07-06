package cn.edu.jmu.news.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import cn.edu.jmu.news.R;
import cn.edu.jmu.news.ui.view.TitleBar;

/**
 * ��ʾ��������
 * 
 * @author Administrator
 *
 */
public class ContentActivity extends Activity {

	private WebView webView;
	private TitleBar mTitleBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content);
		
		initUI();
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void initUI() {
		mTitleBar = (TitleBar) findViewById(R.id.title);
		webView = (WebView) findViewById(R.id.webView);
		
		mTitleBar.setTitle(getIntent().getStringExtra("title"));
		
		WebSettings settings = webView.getSettings();
		// ����֧��JavaScript
		settings.setJavaScriptEnabled(true); 				
		
		// ֧������
		settings.setSupportZoom(true);						
		settings.setBuiltInZoomControls(true);
		settings.setDisplayZoomControls(false);
		// ����������
		settings.setUseWideViewPort(true);					
		// ����Ӧ��Ļ
		settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);	
		settings.setLoadWithOverviewMode(true);
		String url = getIntent().getStringExtra("url");
		webView.loadUrl(url);
	}
}
