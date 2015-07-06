package cn.edu.jmu.news.controller.employment;

import cn.edu.jmu.news.R;
import cn.edu.jmu.news.adapter.CampusRecruitmentNewsAdapter;
import cn.edu.jmu.news.controller.BaseDynamicParseListFragment;
import cn.edu.jmu.news.entity.News;
import cn.edu.jmu.news.parser.BaseParser;
import cn.edu.jmu.news.parser.employment.CampusRecruitmentParser;

import com.joanzapata.android.QuickAdapter;

/**
 * –£‘∞’–∆∏ª·“≥√Ê
 * 
 * @author Administrator
 *
 */
public class CampusRecruitmentFragment extends BaseDynamicParseListFragment {

	@Override
	public BaseParser getParser() {
		return new CampusRecruitmentParser();
	}

	@Override
	protected String getBaseUrl() {
		return "http://jyzd.jmu.edu.cn/jmu/policy/policy.jsp?TypeID=6&SubPolicyTypeID=6&CurPage=";
	}

	@Override
	protected QuickAdapter<News> getAdapter() {
		return new CampusRecruitmentNewsAdapter(getActivity(), R.layout.campus_recruitment_news_item);
	}

	
	
}
