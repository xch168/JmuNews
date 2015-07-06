package cn.edu.jmu.news.controller.jwc;

import cn.edu.jmu.news.controller.BaseListFragment;
import cn.edu.jmu.news.parser.BaseParser;
import cn.edu.jmu.news.parser.jwc.JwcParser;

/**
 * ½ÌÎñ´¦Ò³
 * 
 */
public class JwcFragment extends BaseListFragment {

	@Override
	public BaseParser getParser() {
		return new JwcParser();
	}

	
}
