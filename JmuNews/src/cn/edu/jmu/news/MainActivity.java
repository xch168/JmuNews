package cn.edu.jmu.news;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;
import cn.edu.jmu.news.controller.employment.EmploymentFragment;
import cn.edu.jmu.news.controller.jwc.JwcFragment;
import cn.edu.jmu.news.controller.leftdrawer.JmuMapFragment;
import cn.edu.jmu.news.controller.leftdrawer.JwcLoginFragment;
import cn.edu.jmu.news.controller.leftdrawer.LibrarySearchFragment;
import cn.edu.jmu.news.controller.news.NewsFragment;
import cn.edu.jmu.news.controller.weibo.WeiboFragment;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.ikimuhendis.ldrawer.ActionBarDrawerToggle;
import com.ikimuhendis.ldrawer.DrawerArrowDrawable;

/**
 * 主界面
 * 
 * @author Administrator
 *
 */
public class MainActivity extends FragmentActivity implements OnCheckedChangeListener {

	// UI Component
	private RadioGroup mTasRadioGroup;	// 底部标签
	
	// LDrawer
	private DrawerLayout mDrawerLayout;			// 抽屉布局
	private ActionBarDrawerToggle mDrawerToggle;// 抽屉布局开关
	private DrawerArrowDrawable drawerArrow;	// 箭头
	private LinearLayout mDrawerMenuLayout;		// 抽屉菜单布局
	private RadioGroup mNavDrawerMenuGoup;		// 抽屉菜单单选按钮组
	
	// 集大资讯
	private Fragment mNewsFragment;			// 新闻页
	private Fragment mJwcFragment;			// 教务处页
	private Fragment mEmploymentFragment;	// 就业页
	private Fragment mWeiboFragment;		// 微博页
	
	// 抽屉菜单对应的页面
	private Fragment mLibrarySearchFragment;	// 图书馆页
	private Fragment mJwcLoginFragment;			// 教务处登录页
	private Fragment mJmuMapFragment;			// 集大地图页
	
	private Fragment mCurrentFragment;			// 当前页
	
	private FragmentManager mFragmentManager; 	// Fragment管理者
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if(savedInstanceState == null) {
			mFragmentManager = getSupportFragmentManager();
			initUI();
			
			// 开启推送工作
			PushManager.startWork(this, PushConstants.LOGIN_TYPE_API_KEY, "RCEhZA5mGMsXYBgTW7w4ZnIE");
		} else {
			Intent intent = new Intent(MainActivity.this, MainActivity.class);
		    startActivity(intent);
		    finish();
		}
		
	}


	/**
	 * 初始化UI组件
	 */
	private void initUI() {
		initDrawer();
		mTasRadioGroup = (RadioGroup) findViewById(R.id.rg_tabs);
		mTasRadioGroup.setOnCheckedChangeListener(this);
		
		// 默认显示新闻页
		mNewsFragment = new NewsFragment();
		switchContent(mNewsFragment);
	}


	@SuppressLint({ "ResourceAsColor", "CutPasteId" })
	private void initDrawer() {
		ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        
        mNavDrawerMenuGoup = (RadioGroup) findViewById(R.id.rg_nav_menus);
        mDrawerMenuLayout = (LinearLayout) findViewById(R.id.navdrawer);
        // 防止触摸事件继续传递到其他控件
        mDrawerMenuLayout.setOnTouchListener(new OnTouchListener() {
			
			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});

        drawerArrow = new DrawerArrowDrawable(this) {
            @Override
            public boolean isLayoutRtl() {
                return false;
            }
        };
        
        mNavDrawerMenuGoup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch(checkedId) {
					case R.id.rbtn_news:
						if(mNewsFragment == null) {
        					mNewsFragment = new NewsFragment();
        				}
                    	switchContent(mNewsFragment);
                    	getActionBar().setTitle("集大资讯");
                    	showBottomTabs();
						break;
					case R.id.rbtn_library:
						if(mLibrarySearchFragment == null) {
                    		mLibrarySearchFragment = new LibrarySearchFragment();
        				}
                    	switchContent(mLibrarySearchFragment);
                    	getActionBar().setTitle("图书馆");
                    	hideBottomTabs();
						break;
					case R.id.rbtn_jwc:
						if(mJwcLoginFragment == null) {
                    		mJwcLoginFragment = new JwcLoginFragment();
        				}
                    	switchContent(mJwcLoginFragment);
                    	getActionBar().setTitle("教务处");
                    	hideBottomTabs();
						break;
					case R.id.rbtn_map:
						if(mJmuMapFragment == null) {
                    		mJmuMapFragment = new JmuMapFragment();
        				}
                    	switchContent(mJmuMapFragment);
                    	getActionBar().setTitle("集大地图");
                    	hideBottomTabs();
						break;
				}
                mDrawerLayout.closeDrawers();
			}
		});
        
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
            drawerArrow, R.string.drawer_open,
            R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

	}
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
			// 新闻页
			case R.id.radioBtn_news:
				if(mNewsFragment == null) {
					mNewsFragment = new NewsFragment();
				}
				switchContent(mNewsFragment);
				break;
			// 教务处页
			case R.id.radioBtn_jwc:
				if(mJwcFragment == null) {
					mJwcFragment = new JwcFragment();
				}
				switchContent(mJwcFragment);
				break;
			// 就业网
			case R.id.radioBtn_employment:
				if(mEmploymentFragment == null) {
					mEmploymentFragment = new EmploymentFragment();
				}
				switchContent(mEmploymentFragment);
				break;
			//微博页
			case R.id.radioBtn_weibo:
				if(mWeiboFragment == null) {
					mWeiboFragment = new WeiboFragment();
				}
				switchContent(mWeiboFragment);
				break;
		}
		
	}

	/**
	 * 切换内容
	 * @param mNewsFragment2
	 */
	private void switchContent(Fragment mTargetFragment) {
		FragmentTransaction transaction = mFragmentManager.beginTransaction();
		if(mCurrentFragment == null) {
			transaction.add(R.id.fl_content, mTargetFragment).commit();
		} else if(mTargetFragment.isAdded()) {
			transaction.hide(mCurrentFragment).show(mTargetFragment).commitAllowingStateLoss();
		} else {
			transaction.hide(mCurrentFragment).add(R.id.fl_content, mTargetFragment).commit();
		}
		mCurrentFragment = mTargetFragment;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(mDrawerMenuLayout)) {
                mDrawerLayout.closeDrawer(mDrawerMenuLayout);
            } else {
                mDrawerLayout.openDrawer(mDrawerMenuLayout);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
	
	/**
	 * 显示底部导航栏
	 */
	public void showBottomTabs() {
		findViewById(R.id.main_bottom_tabs_layout).setVisibility(View.VISIBLE);
	}
	
	/**
	 * 隐藏底部导航栏
	 */
	public void hideBottomTabs() {
		findViewById(R.id.main_bottom_tabs_layout).setVisibility(View.GONE);
	}
	
	private long waitTime = 2000;
	private long touchTime = 0;
	@Override
	public void onBackPressed() {
		long currentTime = System.currentTimeMillis();
		if ((currentTime - touchTime) >= waitTime) {
		    Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
		    touchTime = currentTime;
		} else {
		    finish();
		}
	}
}
