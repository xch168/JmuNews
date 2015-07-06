package cn.edu.jmu.news.controller.employment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
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
 * 就业网页
 * 
 * @author Administrator
 *
 */
public class EmploymentFragment extends BaseFragment {

	// UI Component
	private TabPageIndicator mTabIndicator;			// tab指示器
	private ViewPager mViewPager;	
	
	private Fragment mCampusRecruitmentFragment;	// 社区热点页
	private Fragment mRecruitmentInfoFragment;		// 集大热点
	private Fragment mTalentExchangeFragment;		// 兼职招聘页
	
	private List<Fragment> mFragmentList;		// 页面集合
	
	private final String[] TAB_TITLES = {"校园招聘会", "招聘信息", "人才交流会"}; 
	
	public EmploymentFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_employment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		initFragment();
		
		initUI();
	}

	/**
	 * 初始化Fragment
	 */
	private void initFragment() {
		mFragmentList = new ArrayList<Fragment>();
		
		mCampusRecruitmentFragment = new CampusRecruitmentFragment();
		mRecruitmentInfoFragment = new RecruitmentInfoFragment();
		mTalentExchangeFragment = new TalentExchangeFragment();
		
		mFragmentList.add(mCampusRecruitmentFragment);
		mFragmentList.add(mRecruitmentInfoFragment);
		mFragmentList.add(mTalentExchangeFragment);
		
	}

	private void initUI() {
		mTabIndicator = (TabPageIndicator) getView().findViewById(R.id.indicator);
		mViewPager = (ViewPager) getView().findViewById(R.id.viewPager);
		FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(this, mFragmentList, TAB_TITLES);  
		mViewPager.setAdapter(adapter);  
		mTabIndicator.setViewPager(mViewPager);
	}

}
