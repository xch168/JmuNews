package cn.edu.jmu.news.controller;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import cn.edu.jmu.news.R;

/**
 * 通用的带WebView的通用Fragment基类 
 * 
 * @author Administrator
 *
 */
public abstract class BaseWebViewFragment extends BaseFragment {
	
	protected WebView mContentWebView;
	protected boolean isSupportZoomByRatio = true;	// 是否支持屏幕按比例缩放
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_base_webview, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		initUI();
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void initUI() {
		showLoading();
		mContentWebView = (WebView) getView().findViewById(R.id.webview_content);
		
		WebSettings settings = mContentWebView.getSettings();
		// 设置支持JavaScript
		settings.setJavaScriptEnabled(true); 				
		// 设置不缓存
		settings.setCacheMode(WebSettings.LOAD_NO_CACHE);	
		if(isSupportZoomByRatio()) {
			// 支持缩放
			settings.setSupportZoom(true);						
			settings.setBuiltInZoomControls(true);
			settings.setDisplayZoomControls(false);
			// 按比例缩放
			settings.setUseWideViewPort(true);					
			// 自适应屏幕
			settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);	
			settings.setLoadWithOverviewMode(true);
		}
		
		// 在本地WebView中加载网页
		mContentWebView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				hideLoading();
			}
			
			
		});
	
		String url = getUrl();
		mContentWebView.loadUrl(url);
	}
	
	@Override
	public ViewGroup getContainerView() {
		return (ViewGroup) getView().findViewById(R.id.fl_container);
	}

	/**
	 * 获取要加载的URL地址
	 * @return
	 */
	public abstract String getUrl();
	
	public boolean isSupportZoomByRatio() {
		return true;
	}
	
}
