package cn.edu.jmu.news.parser;

import java.io.IOException;

import android.os.AsyncTask;

/**
 * �첽�������������ڷ�UI����������ʣ�����������
 * @author Administrator
 *
 */
public class AsyncParseTask extends AsyncTask<BaseParser, Boolean, Void> {

	private BaseParser mParser;
	private IParseEventListener mParseEventListener;
	
	@Override
	protected Void doInBackground(BaseParser... params) {
		
		mParser = params[0];
		try {
			mParser.parseTask();	// ִ�н�������
		} catch (IOException e) {
			e.printStackTrace();
			publishProgress(false);	// ���������쳣
		}
		return null;
	}

	@Override
	protected void onPreExecute() {
		// ����ǰ
		mParseEventListener.onStart();
	}

	@Override
	protected void onPostExecute(Void result) {
		// �������
		mParseEventListener.onFinish();
	}

	@Override
	protected void onProgressUpdate(Boolean... values) {
		boolean isFailure = values[0];
		
		if(isFailure) {
			mParseEventListener.onFailure(); // ����ʧ��
		}
	}
	
	public void setParseEventListener(IParseEventListener parseEventListener) {
		mParseEventListener = parseEventListener;
	}

}
