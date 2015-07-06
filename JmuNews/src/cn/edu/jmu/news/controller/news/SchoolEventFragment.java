package cn.edu.jmu.news.controller.news;

import cn.edu.jmu.news.controller.BaseListFragment;
import cn.edu.jmu.news.parser.BaseParser;
import cn.edu.jmu.news.parser.news.SchoolEventParser;

/**
 * Ğ£ÊÂÒ³
 * 
 * @author Administrator
 *
 */
public class SchoolEventFragment extends BaseListFragment {

	@Override
	public BaseParser getParser() {
		return new SchoolEventParser();
	}

}
