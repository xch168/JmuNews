package cn.edu.jmu.news.controller.employment;

import cn.edu.jmu.news.R;
import cn.edu.jmu.news.adapter.NewsAdapter;
import cn.edu.jmu.news.controller.BaseDynamicParseListFragment;
import cn.edu.jmu.news.entity.News;
import cn.edu.jmu.news.parser.BaseParser;
import cn.edu.jmu.news.parser.employment.RecruitmentInfoParser;

import com.joanzapata.android.QuickAdapter;

/**
 * ’–∆∏–≈œ¢“≥
 * 
 */
public class RecruitmentInfoFragment extends BaseDynamicParseListFragment {

	@Override
	public BaseParser getParser() {
		return new RecruitmentInfoParser();
	}

	@Override
	protected String getBaseUrl() {
		return "http://jyzd.jmu.edu.cn/jmu2/more/more.jsp?TypeID=5&CurPage=";
	}

	@Override
	protected QuickAdapter<News> getAdapter() {
		return new NewsAdapter(getActivity(), R.layout.news_item);
	}

	
}
