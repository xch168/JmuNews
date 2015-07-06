package cn.edu.jmu.news.parser;

import java.io.IOException;

import android.os.AsyncTask;

/**
 * 异步解析任务，用于在非UI进行网络访问，并解析数据
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
			mParser.parseTask();	// 执行解析任务
		} catch (IOException e) {
			e.printStackTrace();
			publishProgress(false);	// 解析出现异常
		}
		return null;
	}

	@Override
	protected void onPreExecute() {
		// 解析前
		mParseEventListener.onStart();
	}

	@Override
	protected void onPostExecute(Void result) {
		// 解析完成
		mParseEventListener.onFinish();
	}

	@Override
	protected void onProgressUpdate(Boolean... values) {
		boolean isFailure = values[0];
		
		if(isFailure) {
			mParseEventListener.onFailure(); // 解析失败
		}
	}
	
	public void setParseEventListener(IParseEventListener parseEventListener) {
		mParseEventListener = parseEventListener;
	}

}
