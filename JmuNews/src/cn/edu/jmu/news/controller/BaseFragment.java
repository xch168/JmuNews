package cn.edu.jmu.news.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import cn.edu.jmu.news.ui.widget.TipView;
import cn.edu.jmu.news.ui.widget.TipView.RefreshListener;

/**
 * ���ڷ�װFragment��ͨ�ò���
 * 
 * @author Administrator
 *
 */
public class BaseFragment extends Fragment {

	/**
	 * tipView������ʾ���ض����������쳣��Ϣ
	 */
	private TipView mTipView;
	
	/**
	 * ����Fragment��rootView�����ViewPager��������������������¼��ص�����
	 */
	protected View mFragmentView;
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		// �����ʾ��ͼ
		addTipView();
	}
	
	/**
	 * �����ʾ��ͼ
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
	 * ˢ��
	 */
	protected void refresh() {
	}

	/**
	 * ��ʾ���ض���
	 */
	protected void showLoading() {
		if(mTipView != null) {
			mTipView.showLoadingView();
		}
	}
	
	/**
	 * ���ؼ��ض���
	 */
	protected void hideLoading() {
		if(mTipView != null) {
			mTipView.hideLoadingView();
		}
	}
	
	/**
	 * ��ʾ�����쳣��ʾ
	 */
	protected void showNetworkAbnormal() {
		if(mTipView != null) {
			mTipView.showNetworkAbnormal();
		}
	}
	
	/**
	 * ���������쳣��ʾ
	 */
	protected void hideNetworkAbnormal() {
		if(mTipView != null) {
			mTipView.hideNetworkAbnormal();
		}
	}

	/**
	 * ��ȡ��������
	 * @return
	 */
	public ViewGroup getContainerView() {
		return null;
	}
}
