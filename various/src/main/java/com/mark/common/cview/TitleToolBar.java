package com.mark.common.cview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mark.common.R;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * author: marks
 * date: On 2018/6/21
 * email:mottoboy@126.com
 * describe: 标题栏
 * <li>默认状态：icon + 左标题 + 右编辑提示</li>
 * <li>编辑状态判断：isEdit=false,默认可点击编辑</li>
 * <li>显示可点击完成</li>
 * <li>实现回调接口时，判断isEdit，
 *      --true,进入编辑状态，显示可点的"完成"文字
 *      --false，完成编辑，显示可点的"编辑全部"文字
 *
 * </li>
 */
public class TitleToolBar extends RelativeLayout implements View.OnClickListener {

    ImageButton ibBack;
    ImageButton ibTrolley;
    TextView tvTitle;
    TextView tvEdit;

    private boolean isEdit=false;

    private OnTitleBarClickListener onTitleBarClickListener;

    public TitleToolBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initUI();
    }

    public TitleToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI();
    }

    public TitleToolBar(Context context) {
        super(context);
        initUI();
    }

    private void initUI() {
        View view = View.inflate(getContext(), R.layout.title_toolbar, this);
        ibBack=(ImageButton)view.findViewById(R.id.ib_back);
        ibTrolley=(ImageButton)view.findViewById(R.id.ib_trolley);
        tvTitle=(TextView)view.findViewById(R.id.tv_title);
        tvEdit=(TextView)view.findViewById(R.id.tv_edit);
        ibBack.setOnClickListener(this);
        ibTrolley.setOnClickListener(this);
        tvTitle.setOnClickListener(this);
        tvEdit.setOnClickListener(this);

    }

    //显示&隐藏右边的“编辑全部”
    public void showEditString(int view){
        tvEdit.setVisibility(view);
    }

    //显示&隐藏返回图标
    public void showFunctionIcon(int view) {
        ibBack.setVisibility(view);
        ibTrolley.setVisibility(View.GONE);
    }

    //设置title
    public void setTitle(String str) {
        tvTitle.setText(str);
    }


    public void setOnTitleBarClickListener(OnTitleBarClickListener ls) {
        this.onTitleBarClickListener = ls;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.ib_back) {
            if (onTitleBarClickListener != null) {
                onTitleBarClickListener.onClickBack(view);
            }

        } else if (i == R.id.ib_trolley) {
            if (onTitleBarClickListener != null) {
                onTitleBarClickListener.onClickIcon(view);
            }

        } else if (i == R.id.tv_edit) {
            if (!isEdit) {
                tvEdit.setText(R.string.shopping_title_bar_finish);
                isEdit = true;
            } else {
                tvEdit.setText(R.string.shopping_title_bar_edit);
                isEdit = false;
            }
            onTitleBarClickListener.onEditStateChanged(view, isEdit);

        }
    }

    public interface OnTitleBarClickListener {
        void onClickBack(View view);

        void onClickIcon(View view);

        void onEditStateChanged(View view, boolean isEdit);

    }
}
