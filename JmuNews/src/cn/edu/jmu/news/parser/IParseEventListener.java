package cn.edu.jmu.news.parser;

/**
 * �����¼��������ӿ�
 * @author Administrator
 *
 */
public interface IParseEventListener {
	
	/**
	 * ������ʼ
	 */
	public void onStart();
	
	/**
	 * �������
	 */
	public void onFinish();
	
	/**
	 * ����ʧ��
	 */
	public void onFailure();
}
