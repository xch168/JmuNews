package cn.edu.jmu.news.controller.news;

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
 * A simple {@link Fragment} subclass.
 * 
 */
public class NewsFragment extends BaseFragment {

	// UI Component
	private TabPageIndicator mTabIndicator;		// tabָʾ��
	private ViewPager mViewPager;	
	
	private Fragment mFocusFragment;			// ����ҳ
	private Fragment mSchoolEventFragment;		// У��ҳ
	private Fragment mWeiNewsFragment;			// ΢����ҳ
	private Fragment mAcademicFragment;			// ѧ��ҳ
	
	private List<Fragment> mFragmentList;		// ҳ�漯��
	
	private final String[] TAB_TITLES = {"����", "У��", "΢����", "ѧ��"}; 
	
	public NewsFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_news, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		initFragment();
		
		initUI();
	}

	/**
	 * ��ʼ��Fragment
	 */
	private void initFragment() {
		mFragmentList = new ArrayList<Fragment>();
		
		mFocusFragment = new FocusFragment();
		mSchoolEventFragment = new SchoolEventFragment();
		mWeiNewsFragment = new WeiNewsFragment();
		mAcademicFragment = new AcademicFragment();
		
		mFragmentList.add(mFocusFragment);
		mFragmentList.add(mSchoolEventFragment);
		mFragmentList.add(mWeiNewsFragment);
		mFragmentList.add(mAcademicFragment);
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
