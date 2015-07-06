package cn.edu.jmu.news.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.jmu.news.entity.News;

/**
 * 解析器基类
 * @author Administrator
 *
 */
public abstract class BaseParser {

	/**
	 * WebView中获取的动态网页数据
	 */
	protected String mHtmlData;
	
	/**
	 * 存放新闻列表
	 */
	protected List<News> mNewsList = new ArrayList<News>();
	
	/**
	 * 解析事件监听器
	 */
	private IParseEventListener mParseEventListener;
	
	/**
	 * 解析操作
	 */
	protected abstract void parse(int page) throws IOException;
	
	
	/**
	 * 异步解析任务，在非UI线程中进行网络操作
	 */
	private AsyncParseTask mParseTask;
	
	
	/**
	 * 页数
	 */
	protected int mPageNum;	
	
	/**
	 * 对parse操作进行封装处理
	 */
	public void parseData(int page) {
		if(mParseEventListener == null) {
			return;
		}
		mPageNum = page;
		
		// 创建并执行异步解析任务
		mParseTask = new AsyncParseTask();
		mParseTask.setParseEventListener(mParseEventListener);
		mParseTask.execute(this);
		
	}
	
	/**
	 * 解析任务
	 */
	public void parseTask() throws IOException {
		clear();
		parse(mPageNum);
	}
	
	/**
	 * 获取新闻列表
	 * @return
	 */
	public List<News> getNewsList() {
		return mNewsList;
	}
	
	/**
	 * 清除新闻列表
	 */
	public void clear() {
		if(mNewsList != null) {
			mNewsList.clear();
		}
	}

	/**
	 * 判断是否有新闻
	 * @return
	 */
	public boolean hasData() {
		if(mNewsList != null && mNewsList.size() > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 获取HTML数据
	 * @param htmlData
	 */
	public void setHtmlData(String htmlData) {
		mHtmlData = htmlData;
	}
	
	public void setParseEventListener(IParseEventListener parseEventListener) {
		mParseEventListener = parseEventListener;
	}
}
