package cn.edu.jmu.news.controller.weibo;

import cn.edu.jmu.news.controller.BaseWebViewFragment;

/**
 * У԰��Ƹҳ
 * 
 * @author Administrator
 *
 */
public class CampusRecruitmentFragment extends BaseWebViewFragment {

	@Override
	public String getUrl() {
		return "http://weibo.com/belinexiaoyuer?topnav=1&wvr=6&topsug=1";
	}

	@Override
	public boolean isSupportZoomByRatio() {
		return false;
	}
}
