package cn.edu.jmu.news.controller.news;

import cn.edu.jmu.news.controller.BaseListFragment;
import cn.edu.jmu.news.parser.BaseParser;
import cn.edu.jmu.news.parser.news.AcademicParser;

/**
 * ѧ��ҳ
 * 
 * @author Administrator
 *
 */
public class AcademicFragment extends BaseListFragment {

	@Override
	public BaseParser getParser() {
		return new AcademicParser();
	}

}
