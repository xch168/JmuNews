package cn.edu.jmu.news.adapter;

import java.util.List;

import android.content.Context;
import cn.edu.jmu.news.R;
import cn.edu.jmu.news.entity.News;

import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

/**
 * 通用新闻适配器
 * 
 * @author Administrator
 *
 */
public class NewsAdapter extends QuickAdapter<News> {

	public NewsAdapter(Context context, int layoutResId) {
		super(context, layoutResId);
	}
	
	public NewsAdapter(Context context, int layoutResId, List<News> data) {
		super(context, layoutResId, data);
	}

	@Override
	protected void convert(BaseAdapterHelper helper, News news) {
		helper.setText(R.id.title, news.getTitle());
		helper.setText(R.id.from, news.getFrom());
		helper.setText(R.id.publishTime, news.getPublishTime());
	}
	
}
