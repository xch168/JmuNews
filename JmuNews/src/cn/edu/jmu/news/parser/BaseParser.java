package cn.edu.jmu.news.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.jmu.news.entity.News;

/**
 * ����������
 * @author Administrator
 *
 */
public abstract class BaseParser {

	/**
	 * WebView�л�ȡ�Ķ�̬��ҳ����
	 */
	protected String mHtmlData;
	
	/**
	 * ��������б�
	 */
	protected List<News> mNewsList = new ArrayList<News>();
	
	/**
	 * �����¼�������
	 */
	private IParseEventListener mParseEventListener;
	
	/**
	 * ��������
	 */
	protected abstract void parse(int page) throws IOException;
	
	
	/**
	 * �첽���������ڷ�UI�߳��н����������
	 */
	private AsyncParseTask mParseTask;
	
	
	/**
	 * ҳ��
	 */
	protected int mPageNum;	
	
	/**
	 * ��parse�������з�װ����
	 */
	public void parseData(int page) {
		if(mParseEventListener == null) {
			return;
		}
		mPageNum = page;
		
		// ������ִ���첽��������
		mParseTask = new AsyncParseTask();
		mParseTask.setParseEventListener(mParseEventListener);
		mParseTask.execute(this);
		
	}
	
	/**
	 * ��������
	 */
	public void parseTask() throws IOException {
		clear();
		parse(mPageNum);
	}
	
	/**
	 * ��ȡ�����б�
	 * @return
	 */
	public List<News> getNewsList() {
		return mNewsList;
	}
	
	/**
	 * ��������б�
	 */
	public void clear() {
		if(mNewsList != null) {
			mNewsList.clear();
		}
	}

	/**
	 * �ж��Ƿ�������
	 * @return
	 */
	public boolean hasData() {
		if(mNewsList != null && mNewsList.size() > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * ��ȡHTML����
	 * @param htmlData
	 */
	public void setHtmlData(String htmlData) {
		mHtmlData = htmlData;
	}
	
	public void setParseEventListener(IParseEventListener parseEventListener) {
		mParseEventListener = parseEventListener;
	}
}
