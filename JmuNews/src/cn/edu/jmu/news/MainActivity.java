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
 * ������
 * 
 * @author Administrator
 *
 */
public class MainActivity extends FragmentActivity implements OnCheckedChangeListener {

	// UI Component
	private RadioGroup mTasRadioGroup;	// �ײ���ǩ
	
	// LDrawer
	private DrawerLayout mDrawerLayout;			// ���벼��
	private ActionBarDrawerToggle mDrawerToggle;// ���벼�ֿ���
	private DrawerArrowDrawable drawerArrow;	// ��ͷ
	private LinearLayout mDrawerMenuLayout;		// ����˵�����
	private RadioGroup mNavDrawerMenuGoup;		// ����˵���ѡ��ť��
	
	// ������Ѷ
	private Fragment mNewsFragment;			// ����ҳ
	private Fragment mJwcFragment;			// ����ҳ
	private Fragment mEmploymentFragment;	// ��ҵҳ
	private Fragment mWeiboFragment;		// ΢��ҳ
	
	// ����˵���Ӧ��ҳ��
	private Fragment mLibrarySearchFragment;	// ͼ���ҳ
	private Fragment mJwcLoginFragment;			// ���񴦵�¼ҳ
	private Fragment mJmuMapFragment;			// �����ͼҳ
	
	private Fragment mCurrentFragment;			// ��ǰҳ
	
	private FragmentManager mFragmentManager; 	// Fragment������
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if(savedInstanceState == null) {
			mFragmentManager = getSupportFragmentManager();
			initUI();
			
			// �������͹���
			PushManager.startWork(this, PushConstants.LOGIN_TYPE_API_KEY, "RCEhZA5mGMsXYBgTW7w4ZnIE");
		} else {
			Intent intent = new Intent(MainActivity.this, MainActivity.class);
		    startActivity(intent);
		    finish();
		}
		
	}


	/**
	 * ��ʼ��UI���
	 */
	private void initUI() {
		initDrawer();
		mTasRadioGroup = (RadioGroup) findViewById(R.id.rg_tabs);
		mTasRadioGroup.setOnCheckedChangeListener(this);
		
		// Ĭ����ʾ����ҳ
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
        // ��ֹ�����¼��������ݵ������ؼ�
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
                    	getActionBar().setTitle("������Ѷ");
                    	showBottomTabs();
						break;
					case R.id.rbtn_library:
						if(mLibrarySearchFragment == null) {
                    		mLibrarySearchFragment = new LibrarySearchFragment();
        				}
                    	switchContent(mLibrarySearchFragment);
                    	getActionBar().setTitle("ͼ���");
                    	hideBottomTabs();
						break;
					case R.id.rbtn_jwc:
						if(mJwcLoginFragment == null) {
                    		mJwcLoginFragment = new JwcLoginFragment();
        				}
                    	switchContent(mJwcLoginFragment);
                    	getActionBar().setTitle("����");
                    	hideBottomTabs();
						break;
					case R.id.rbtn_map:
						if(mJmuMapFragment == null) {
                    		mJmuMapFragment = new JmuMapFragment();
        				}
                    	switchContent(mJmuMapFragment);
                    	getActionBar().setTitle("�����ͼ");
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
			// ����ҳ
			case R.id.radioBtn_news:
				if(mNewsFragment == null) {
					mNewsFragment = new NewsFragment();
				}
				switchContent(mNewsFragment);
				break;
			// ����ҳ
			case R.id.radioBtn_jwc:
				if(mJwcFragment == null) {
					mJwcFragment = new JwcFragment();
				}
				switchContent(mJwcFragment);
				break;
			// ��ҵ��
			case R.id.radioBtn_employment:
				if(mEmploymentFragment == null) {
					mEmploymentFragment = new EmploymentFragment();
				}
				switchContent(mEmploymentFragment);
				break;
			//΢��ҳ
			case R.id.radioBtn_weibo:
				if(mWeiboFragment == null) {
					mWeiboFragment = new WeiboFragment();
				}
				switchContent(mWeiboFragment);
				break;
		}
		
	}

	/**
	 * �л�����
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
	 * ��ʾ�ײ�������
	 */
	public void showBottomTabs() {
		findViewById(R.id.main_bottom_tabs_layout).setVisibility(View.VISIBLE);
	}
	
	/**
	 * ���صײ�������
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
		    Toast.makeText(this, "�ٰ�һ���˳�Ӧ��", Toast.LENGTH_SHORT).show();
		    touchTime = currentTime;
		} else {
		    finish();
		}
	}
}
