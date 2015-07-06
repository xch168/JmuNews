package cn.edu.jmu.news.controller.news;

import cn.edu.jmu.news.controller.BaseListFragment;
import cn.edu.jmu.news.parser.BaseParser;
import cn.edu.jmu.news.parser.news.FocusParser;

/**
 * ½¹µãĞÂÎÅÒ³
 * 
 * @author Administrator
 *
 */
public class FocusFragment extends BaseListFragment {
	
	@Override
	public BaseParser getParser() {
		return new FocusParser();
	}
	
}
