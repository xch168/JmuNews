package cn.edu.jmu.news.parser;

/**
 * 解析事件监听器接口
 * @author Administrator
 *
 */
public interface IParseEventListener {
	
	/**
	 * 解析开始
	 */
	public void onStart();
	
	/**
	 * 解析完成
	 */
	public void onFinish();
	
	/**
	 * 解析失败
	 */
	public void onFailure();
}
