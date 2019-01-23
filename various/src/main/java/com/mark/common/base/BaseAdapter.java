package com.mark.common.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mark.common.util.LogUtil;

import java.util.List;

import butterknife.ButterKnife;

/**
 * @author Marks
 * @date 2018/7/11 16:38
 * email: mottoboy@126.com
 * describe:
 * <li>des</li>
 */
public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {

    private Context mContent;
    private List<String> dates;
    private LayoutInflater inflater;

    public BaseAdapter(Context mContent, List<String> data) {
        this.mContent = mContent;
        this.dates = data;
        inflater = LayoutInflater.from(mContent);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        return new ViewHolder(mContent, view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder != null) {
            LogUtil.e("点击item");
            ViewHolder viewHolder = ((ViewHolder) holder);

        }

    }

    @Override
    public int getItemCount() {
        return dates.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private Context exContext;

        public ViewHolder(Context mContent, View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.exContext = mContent;
        }

    }
}
