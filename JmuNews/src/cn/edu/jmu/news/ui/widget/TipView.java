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
 * 提示视图，用于显示加载动画、网络异常信息
 * @author Administrator
 *
 */
public class TipView extends FrameLayout implements OnClickListener {

	// UI Component
	private LoadingView mLoadingView;					// 数据加载动画
	private RelativeLayout mTipNetworkAbnormalLayout;	// 提示网络异常布局
	private Button mRefreshBtn;							// 刷新按钮
	
	private RefreshListener mRefreshListener;			// 刷新监听器
	
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
		inflate(context, R.layout.tip_view, this);	//加载布局文件
		
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
	 * 显示加载动画
	 */
	public void showLoadingView() {
		if(mLoadingView != null) {
			mLoadingView.setVisibility(VISIBLE);
			mTipNetworkAbnormalLayout.setVisibility(GONE);
		}
	}
	
	/**
	 * 显示网络异常提示
	 */
	public void showNetworkAbnormal() {
		if(mTipNetworkAbnormalLayout != null) {
			mTipNetworkAbnormalLayout.setVisibility(VISIBLE);
			mLoadingView.setVisibility(GONE);
		}
	}
	
	/**
	 * 隐藏加载动画
	 */
	public void hideLoadingView() {
		if(mLoadingView != null) {
			mLoadingView.setVisibility(GONE);
		}
	}
	
	/**
	 * 隐藏网络异常提示
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
	 * 刷新监听器，用于让给前台页面执行刷新
	 * @author Administrator
	 *
	 */
	public interface RefreshListener {
		public void toRefresh();
	}
}
