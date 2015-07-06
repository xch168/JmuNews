package cn.edu.jmu.news.controller.employment;

import cn.edu.jmu.news.R;
import cn.edu.jmu.news.adapter.NewsAdapter;
import cn.edu.jmu.news.controller.BaseDynamicParseListFragment;
import cn.edu.jmu.news.entity.News;
import cn.edu.jmu.news.parser.BaseParser;
import cn.edu.jmu.news.parser.employment.TalentExchangeParser;

import com.joanzapata.android.QuickAdapter;

/**
 * 人才交流会页
 * 
 */
public class TalentExchangeFragment extends BaseDynamicParseListFragment {

	@Override
	public BaseParser getParser() {
		return new TalentExchangeParser();
	}

	@Override
	protected String getBaseUrl() {
		return "http://jyzd.jmu.edu.cn/jmu/policy/policy.jsp?TypeID=9&SubPolicyTypeID=9&CurPage=";
	}

	@Override
	protected QuickAdapter<News> getAdapter() {
		return new NewsAdapter(getActivity(), R.layout.news_item);
	}

	
}
