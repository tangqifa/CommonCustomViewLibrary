import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kejiwen.commoncustomviewlibrary.R;

public class ExpandableTextView extends LinearLayout {

    private static final String TAG = "ExpandableTextView";

    private TextView mTitleTv;
    private TextView mTitleContentTv;
    private TextView mContentTv;
    private ImageView mArrowImageView;

    private boolean isTextCollapsed = true;

    public ExpandableTextView(Context context) {
        super(context);
        initViews();
    }

    public ExpandableTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initViews();

        final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView);

        try
        {
            final String title = ta.getString(R.styleable.ExpandableTextView_textToTitle);
            final String content = ta.getString(R.styleable.ExpandableTextView_textToContent);
            mTitleTv.setText(title);
            mContentTv.setText(content);
        } finally
        {
            ta.recycle();
        }
    }

    private void initViews() {
        LinearLayout view = (LinearLayout) LayoutInflater.from(getContext()).inflate(
                R.layout.expandable_textview, this, true);
        mTitleTv = (TextView) view.findViewById(R.id.expandable_textview_title);
        mTitleContentTv = (TextView) view.findViewById(R.id.expandable_textview_sub_title);
        mArrowImageView = (ImageView) view.findViewById(R.id.arrow_img);
        mContentTv = (TextView) view.findViewById(R.id.expandable_textview_content);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTextCollapsed) {
                    mContentTv.setVisibility(View.VISIBLE);
                    mArrowImageView.setBackgroundResource(R.drawable.arrow_down);

                } else {
                    mContentTv.setVisibility(View.GONE);
                    mArrowImageView.setBackgroundResource(R.drawable.arrow_right);
                }
                isTextCollapsed = !isTextCollapsed;
            }
        });
    }

    public void setTitleText(final String text)
    {
        mTitleTv.setText(text);
    }

    public void setTitleTextColor(final int color)
    {
        mTitleTv.setTextColor(color);
    }

    public void setTitleContentText(final String text)
    {
        mTitleContentTv.setText(text);
    }

    public void setContentText(final String text)
    {
        mContentTv.setText(text);
    }

}
