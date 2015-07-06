package cn.edu.jmu.news.controller.weibo;

import cn.edu.jmu.news.controller.BaseWebViewFragment;

/**
 * 二手市场页
 * 
 * @author Administrator
 *
 */
public class SecondaryMarketFragment extends BaseWebViewFragment {

	@Override
	public String getUrl() {
		return "http://weibo.com/534025123?topnav=1&wvr=6&topsug=1";
	}
	
	@Override
	public boolean isSupportZoomByRatio() {
		return false;
	}

}
