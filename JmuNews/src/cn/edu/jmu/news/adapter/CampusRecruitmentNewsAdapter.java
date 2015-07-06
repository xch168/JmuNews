package cn.edu.jmu.news.adapter;

import java.util.List;

import android.content.Context;
import cn.edu.jmu.news.R;
import cn.edu.jmu.news.entity.News;

import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

/**
 *  校园招聘会新闻适配器
 *  
 * @author Administrator
 *
 */
public class CampusRecruitmentNewsAdapter extends QuickAdapter<News> {

	public CampusRecruitmentNewsAdapter(Context context, int layoutResId) {
		super(context, layoutResId);
	}
	
	public CampusRecruitmentNewsAdapter(Context context, int layoutResId, List<News> data) {
		super(context, layoutResId, data);
	}

	@Override
	protected void convert(BaseAdapterHelper helper, News news) {
		helper.setText(R.id.employer, news.getTitle());
		helper.setText(R.id.job, news.getContent());
		helper.setText(R.id.time, news.getTime());
		helper.setText(R.id.place, news.getPlace());
		
	}

	

}
