package cn.edu.jmu.news.controller.weibo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.edu.jmu.news.R;
import cn.edu.jmu.news.adapter.TabPageIndicatorAdapter;
import cn.edu.jmu.news.controller.BaseFragment;

import com.viewpagerindicator.TabPageIndicator;

/**
 * 微博页
 * 
 * @author Administrator
 *
 */
public class WeiboFragment extends BaseFragment {

	// UI Component
	private TabPageIndicator mTabIndicator;		// tab指示器
	private ViewPager mViewPager;	
	
	private Fragment mEatAndEnjoyFragment;		// 吃喝玩乐页
	private Fragment mSecondaryMarketFragment;	// 二手市场页
	private Fragment mCampusRecruitmentFragment;// 校园招聘页
	
	private List<Fragment> mFragmentList;		// 页面集合
	
	private final String[] TAB_TITLES = {"吃喝玩乐", "二手市场", "校园招聘"}; 
	
	public WeiboFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_weibo, container, false);
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		initFragment();
		
		initUI();
	}

	/**
	 * 初始化Fragment
	 */
	private void initFragment() {
		mFragmentList = new ArrayList<Fragment>();
		
		mEatAndEnjoyFragment = new EatAndEnjoyFragment();
		mSecondaryMarketFragment = new SecondaryMarketFragment();
		mCampusRecruitmentFragment = new CampusRecruitmentFragment();
		
		mFragmentList.add(mEatAndEnjoyFragment);
		mFragmentList.add(mSecondaryMarketFragment);
		mFragmentList.add(mCampusRecruitmentFragment);
		
	}

	private void initUI() {
		mTabIndicator = (TabPageIndicator) getView().findViewById(R.id.indicator);
		mViewPager = (ViewPager) getView().findViewById(R.id.viewPager);
		mViewPager.setOffscreenPageLimit(3);
		FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(this, mFragmentList, TAB_TITLES);  
		mViewPager.setAdapter(adapter);  
		mTabIndicator.setViewPager(mViewPager);
	}

}
