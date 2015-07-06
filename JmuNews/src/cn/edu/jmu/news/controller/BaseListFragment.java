package cn.edu.jmu.news.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import cn.edu.jmu.news.R;
import cn.edu.jmu.news.adapter.NewsAdapter;
import cn.edu.jmu.news.parser.BaseParser;
import cn.edu.jmu.news.parser.IParseEventListener;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * 通用的带ListView的Fragment
 * 
 * @author Administrator
 *
 */
public abstract class BaseListFragment extends BaseFragment {
	
	// UI Component
	private PullToRefreshListView mPullToRefreshListView;
		
	private BaseParser mParser;
	private NewsAdapter mAdapter;
	
	private int mCurrentPage = 1;
	
	public BaseListFragment() {
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
				mParser.parseData(mCurrentPage);
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				mParser.parseData(++mCurrentPage);
			}
		});

		mAdapter = new NewsAdapter(getActivity(), R.layout.news_item);
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
		mParser.parseData(1);
	}
	
	@Override
	public ViewGroup getContainerView() {
		return (ViewGroup) getView().findViewById(R.id.fl_container);
	}
	
	@Override
	protected void refresh() {
		mParser.parseData(1);
	}
	
	public abstract BaseParser getParser();
}
