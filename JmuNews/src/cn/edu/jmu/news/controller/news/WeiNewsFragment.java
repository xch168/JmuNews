package cn.edu.jmu.news.controller.news;

import cn.edu.jmu.news.controller.BaseListFragment;
import cn.edu.jmu.news.parser.BaseParser;
import cn.edu.jmu.news.parser.news.WeiNewsParser;

/**
 * ΢����ҳ
 * 
 * @author Administrator
 *
 */
public class WeiNewsFragment extends BaseListFragment {

	@Override
	public BaseParser getParser() {
		return new WeiNewsParser();
	}

}
