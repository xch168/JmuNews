package cn.edu.jmu.news.controller.weibo;

import cn.edu.jmu.news.controller.BaseWebViewFragment;

/**
 * ≥‘∫»ÕÊ¿÷“≥
 * 
 * @author Administrator
 *
 */
public class EatAndEnjoyFragment extends BaseWebViewFragment {

	@Override
	public String getUrl() {
		return "http://weibo.com/313264666?topnav=1&wvr=6&topsug=1";
	}
	
	@Override
	public boolean isSupportZoomByRatio() {
		return false;
	}

}
