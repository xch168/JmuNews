package cn.edu.jmu.news.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import cn.edu.jmu.news.JmuNewsApplication;
import cn.edu.jmu.news.R;
import cn.edu.jmu.news.entity.News;
import cn.edu.jmu.news.parser.BaseParser;
import cn.edu.jmu.news.parser.IParseEventListener;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.joanzapata.android.QuickAdapter;

/**
 * Fragment基类，通过WebView获取动态网页的数据，然后交给Jsoup解析
 * 
 * @author Administrator
 *
 */
public abstract class BaseDynamicParseListFragment extends BaseFragment {

	// UI Component
	private PullToRefreshListView mPullToRefreshListView;
	private WebView mWebView;		// 用于加载动态网页，获取HTML数据

	private BaseParser mParser;
	private QuickAdapter<News> mAdapter;

	private int mCurrentPage = 1;
	
	/**
	 * HTML数据加载完成
	 */
	private final int MSG_LOAD_HTML_FINISHED = 0x123;
	
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if(msg.what == MSG_LOAD_HTML_FINISHED) {
				mParser.parseData(1);
			}
		}
	};
	
	public BaseDynamicParseListFragment() {
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_base_list, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		initUI();
		initParser();
	}
	
	private void initUI() {
		mPullToRefreshListView = (PullToRefreshListView) getView().findViewById(R.id.pullToRefreshListView);
		mPullToRefreshListView.setMode(Mode.BOTH);	// BOTH 表示上拉和下拉都有
	
		mPullToRefreshListView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				mCurrentPage = 1;
				mWebView.loadUrl(getBaseUrl() + mCurrentPage + "&ShowCount=10&ShowFlag=1");
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				mWebView.loadUrl(getBaseUrl() + (++mCurrentPage) + "&ShowCount=10&ShowFlag=1");
			}
		});

		mAdapter = getAdapter();
		mPullToRefreshListView.setAdapter(mAdapter);
		mPullToRefreshListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(), ContentActivity.class);
				intent.putExtra("url", mAdapter.getItem(position - 1).getLink());
				intent.putExtra("title", mAdapter.getItem(position - 1).getTitle());
				startActivity(intent);
			}
		});
		
		initWebView();
	}
	
	@SuppressLint("SetJavaScriptEnabled")
	private void initWebView() {
		mWebView = new WebView(JmuNewsApplication.getContext());
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.addJavascriptInterface(new WebViewCallback(), "handler");
		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				view.loadUrl("javascript:window.handler.loadHtmlFinished(document.body.innerHTML);");
				super.onPageFinished(view, url);
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
		mWebView.loadUrl(getBaseUrl() + mCurrentPage + "&ShowCount=10&ShowFlag=1");
	}

	private void initParser() {
		mParser = getParser();
		mParser.setParseEventListener(new IParseEventListener() {
			
			@Override
			public void onStart() {
				if(mCurrentPage == 1 && mAdapter.isEmpty()) {
					showLoading();
				} 
			}
			
			@Override
			public void onFinish() {
				if(mCurrentPage == 1) {
					mAdapter.clear();
				}
				
				mAdapter.addAll(mParser.getNewsList());
				mAdapter.notifyDataSetChanged();
				hideLoading();
				mPullToRefreshListView.onRefreshComplete();
				if(mAdapter.isEmpty() && mParser.getNewsList().size() == 0) {
					showNetworkAbnormal();
				}
			}
			
			@Override
			public void onFailure() {
				if(mCurrentPage == 1 && !mParser.hasData()) {
					showNetworkAbnormal();
				}
			}
		});
	}
	
	@Override
	public ViewGroup getContainerView() {
		return (ViewGroup) getView().findViewById(R.id.fl_container);
	}
	
	@Override
	protected void refresh() {
		mWebView.loadUrl(getBaseUrl() + mCurrentPage + "&ShowCount=10&ShowFlag=1");
	}
	
	public abstract BaseParser getParser();
	
	/**
	 * 用于WebView回调,获取WebView的加载的数据
	 * 
	 * @author Administrator
	 *
	 */
	class WebViewCallback {
		
		 @JavascriptInterface
		public void loadHtmlFinished(String data) {
			mParser.setHtmlData(data);
			mHandler.sendEmptyMessage(MSG_LOAD_HTML_FINISHED);
		}
	}
	
	protected abstract String getBaseUrl();
	
	protected abstract QuickAdapter<News> getAdapter();
}
