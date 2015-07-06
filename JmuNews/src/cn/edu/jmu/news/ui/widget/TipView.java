package cn.edu.jmu.news.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import cn.edu.jmu.news.R;

import com.mingle.widget.LoadingView;

/**
 * ��ʾ��ͼ��������ʾ���ض����������쳣��Ϣ
 * @author Administrator
 *
 */
public class TipView extends FrameLayout implements OnClickListener {

	// UI Component
	private LoadingView mLoadingView;					// ���ݼ��ض���
	private RelativeLayout mTipNetworkAbnormalLayout;	// ��ʾ�����쳣����
	private Button mRefreshBtn;							// ˢ�°�ť
	
	private RefreshListener mRefreshListener;			// ˢ�¼�����
	
	public TipView(Context context) {
		super(context);
		
		init(context);
	}

	public TipView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		init(context);
	}

	public TipView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		init(context);
	}

	private void init(Context context) {
		inflate(context, R.layout.tip_view, this);	//���ز����ļ�
		
		mLoadingView = (LoadingView) findViewById(R.id.loadView);
		mTipNetworkAbnormalLayout = (RelativeLayout) findViewById(R.id.rl_tip_network_abnormal);
		mRefreshBtn = (Button) findViewById(R.id.btn_refresh);
		mRefreshBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(mRefreshListener != null) {
			mRefreshListener.toRefresh();
		}
	}
	
	/**
	 * ��ʾ���ض���
	 */
	public void showLoadingView() {
		if(mLoadingView != null) {
			mLoadingView.setVisibility(VISIBLE);
			mTipNetworkAbnormalLayout.setVisibility(GONE);
		}
	}
	
	/**
	 * ��ʾ�����쳣��ʾ
	 */
	public void showNetworkAbnormal() {
		if(mTipNetworkAbnormalLayout != null) {
			mTipNetworkAbnormalLayout.setVisibility(VISIBLE);
			mLoadingView.setVisibility(GONE);
		}
	}
	
	/**
	 * ���ؼ��ض���
	 */
	public void hideLoadingView() {
		if(mLoadingView != null) {
			mLoadingView.setVisibility(GONE);
		}
	}
	
	/**
	 * ���������쳣��ʾ
	 */
	public void hideNetworkAbnormal() {
		if(mTipNetworkAbnormalLayout != null) {
			mTipNetworkAbnormalLayout.setVisibility(GONE);
		}
	}
	
	public void setRefreshListener(RefreshListener listener) {
		mRefreshListener = listener;
	}
	
	/**
	 * ˢ�¼������������ø�ǰ̨ҳ��ִ��ˢ��
	 * @author Administrator
	 *
	 */
	public interface RefreshListener {
		public void toRefresh();
	}
}
