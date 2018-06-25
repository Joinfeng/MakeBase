package com.mark.common.cview;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mark.common.R;


/**
 * author: marks
 * date: On 2018/6/21
 * email:mottoboy@126.com
 * describe: 搜索栏
 * <li>copy for MyRelativelayout</li>
 * <li>默认的状态是：显示为主页状态内容，搜索栏 + 右边关闭图标</li>
 * <li>默认editText不获取焦点</li>
 * <li>设计思路：</li>
 * <li>
 * -填充利用基础控件
 * --给控件添加接口实现
 * --通过实现接口，可以自定义控件点击行为
 * --通过控件显示隐藏，显示业务需要的功能。
 * ---返回箭头+输入栏+输入栏清空键+退出当前界面(圆形icon)+搜索按钮
 * ---默认显示：输入栏+圆形icon
 * ---默认EditText不获取焦点
 * -可以通过show**(int):显示或者隐藏控件
 * -需要获取context:可以从自定义的baseApplication中获得
 *
 * </li>
 * <li>android library中提示不能使用switch</li>
 *
 */
public class SearchToolbar extends LinearLayout implements View.OnClickListener {

    ImageButton ibBack;
    EditText etSearch;
    ImageButton ibDelete;
    ImageView ivClose;
    Button btnSearch;
    private OnClickSearchListener onClickSearchListener;

    public SearchToolbar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initUI();
    }

    public SearchToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI();
    }

    public SearchToolbar(Context context) {
        super(context);
        initUI();
    }

    private void initUI() {
        View view = View.inflate(getContext(), R.layout.search_layout, this);
        ibBack=(ImageButton)view.findViewById(R.id.ib_back);
        etSearch=(EditText)view.findViewById(R.id.et_search);
        ibDelete=(ImageButton)view.findViewById(R.id.ib_delete);
        ivClose=(ImageView)view.findViewById(R.id.iv_close);
        btnSearch=(Button)view.findViewById(R.id.btn_search);
        ibBack.setOnClickListener(this);
        etSearch.setOnClickListener(this);
        ibDelete.setOnClickListener(this);
        ivClose.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
//                LG.e("start:"+start+";"+"before:"+before+"count:"+count+";");
                if (count > 0) {
                    showClear(View.VISIBLE);
                } else if (start <= 0) {
                    showClear(View.GONE);
                }
                onClickSearchListener.onInputDynamic(charSequence.toString().trim());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    //设置搜索栏中得hint文字
    public void setEditHintString(String str) {
        etSearch.setHint(str);
    }

    //获取editText的内容
    private String getEditString() {
        return etSearch.getText().toString().trim();
    }

    //focusable,设置edit可否获取焦点
    public SearchToolbar setEditFocus(boolean isFocus) {
        etSearch.setFocusable(isFocus);
        etSearch.setFocusableInTouchMode(isFocus);
        return this;
    }

    //显示或者隐藏 ：左边的退出按钮
    public void showBack(int view) {
        ibBack.setVisibility(view);
    }

    //显示或者隐藏 ：右边的退出按钮
    public void showClose(int view) {
        ivClose.setVisibility(view);
    }

    //显示或者隐藏 ：搜索栏中的清空按钮
    private void showClear(int view) {
        ibDelete.setVisibility(view);
    }

    //清空editText
    private void clearEdit() {
        etSearch.setText("");
    }

    //显示或者隐藏 ：搜索按钮
    public void showSearchBtn(int view) {
        btnSearch.setVisibility(view);
    }


    public void setOnClickSearchListener(OnClickSearchListener ls) {
        this.onClickSearchListener = ls;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();//点击editText时的响应
        //清空editText
        if (i == R.id.ib_back) {
            if (onClickSearchListener != null) {
                onClickSearchListener.onClickBack(view);

            }

        } else if (i == R.id.et_search) {
            if (onClickSearchListener != null) {
                onClickSearchListener.onClickSearchEdit(view);

            }

        } else if (i == R.id.ib_delete) {
            clearEdit();

        } else if (i == R.id.iv_close) {
            if (onClickSearchListener != null) {
                onClickSearchListener.onClickCloseIMG(view);

            }

        } else if (i == R.id.btn_search) {
            if (onClickSearchListener != null) {
                String editString = getEditString();
                onClickSearchListener.onClickSearchBtn(view, editString);

            }

        }
    }

    /**
     * 回调接口：
     * view:
     * editString:搜索栏中得字符串
     */
    public interface OnClickSearchListener {
        void onClickSearchBtn(View view, String editString);

        void onClickSearchEdit(View view);

        void onClickCloseIMG(View view);

        void onClickBack(View view);

        //监听动态输入
        void onInputDynamic(String inputStr);
    }


}
