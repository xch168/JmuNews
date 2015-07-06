package cn.edu.jmu.news.ui.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.edu.jmu.news.R;

/**
 * 通用标题栏
 * 
 * @author Administrator
 *
 */
public class TitleBar extends RelativeLayout {
    private Context mContext;

    private ImageButton mBackButton;
    private TextView mTitleText;
    
    public TitleBar(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();

    }

    public TitleBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.titlebar_layout, this);

        mBackButton = (ImageButton) findViewById(R.id.btn_back);
        mTitleText = (TextView) findViewById(R.id.tv_title);

        mBackButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((Activity) mContext).finish();
            }
        });
    }

    public void setTitle(String title) {
        mTitleText.setText(title);
    }
}
