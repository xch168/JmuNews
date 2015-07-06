package cn.edu.jmu.news.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import cn.edu.jmu.news.ui.widget.TipView;
import cn.edu.jmu.news.ui.widget.TipView.RefreshListener;

/**
 * 用于封装Fragment的通用操作
 * 
 * @author Administrator
 *
 */
public class BaseFragment extends Fragment {

	/**
	 * tipView用于显示加载动画、网络异常信息
	 */
	private TipView mTipView;
	
	/**
	 * 复用Fragment的rootView，解决ViewPager缓存加载两个，其他重新加载的问题
	 */
	protected View mFragmentView;
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		// 添加提示视图
		addTipView();
	}
	
	/**
	 * 添加提示视图
	 */
	private void addTipView() {
		mTipView = new TipView(getActivity());
		mTipView.setRefreshListener(new RefreshListener() {
			
			@Override
			public void toRefresh() {
				BaseFragment.this.refresh();
			}
		});
		
		ViewGroup container = getContainerView();
		if(container != null) {
			container.addView(mTipView);
		}
	}
	

	/**
	 * 刷新
	 */
	protected void refresh() {
	}

	/**
	 * 显示加载动画
	 */
	protected void showLoading() {
		if(mTipView != null) {
			mTipView.showLoadingView();
		}
	}
	
	/**
	 * 隐藏加载动画
	 */
	protected void hideLoading() {
		if(mTipView != null) {
			mTipView.hideLoadingView();
		}
	}
	
	/**
	 * 显示网络异常提示
	 */
	protected void showNetworkAbnormal() {
		if(mTipView != null) {
			mTipView.showNetworkAbnormal();
		}
	}
	
	/**
	 * 隐藏网络异常提示
	 */
	protected void hideNetworkAbnormal() {
		if(mTipView != null) {
			mTipView.hideNetworkAbnormal();
		}
	}

	/**
	 * 获取布局容器
	 * @return
	 */
	public ViewGroup getContainerView() {
		return null;
	}
}
