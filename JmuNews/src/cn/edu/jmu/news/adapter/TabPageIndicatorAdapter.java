package cn.edu.jmu.news.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * ViewPager ������
 * @author Administrator
 *
 */
public class TabPageIndicatorAdapter extends FragmentPagerAdapter {
	
	private List<Fragment> mFragmentList;
	private String[] mTabTitles;
	
	public TabPageIndicatorAdapter(Fragment fragment, List<Fragment> fragments, String[] titles) { 
		// ʹ��getChildFragmentManager()��Ϊ�˽��Ƕ��Fragment��ʹ��ViewPager��bug
        super(fragment.getChildFragmentManager());  
        mFragmentList = fragments;
        mTabTitles = titles;
    }  

    @Override  
    public Fragment getItem(int position) {  
        
        return mFragmentList.get(position);  
    }  

    @Override  
    public CharSequence getPageTitle(int position) {  
        return mTabTitles[position % mTabTitles.length];  
    }  

    @Override  
    public int getCount() {  
        return mTabTitles.length;  
    }  
    
    public void setTitles(String[] titles) {
    	mTabTitles = titles;
    }
    
    public void setFragments(List<Fragment> fragments) {
    	mFragmentList = fragments;
    }
}
