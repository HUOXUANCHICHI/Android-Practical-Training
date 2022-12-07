package com.ablaze.ChiChiCampusFinance.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ablaze.ChiChiCampusFinance.R;
import com.ablaze.ChiChiCampusFinance.entity.WorkStudy;

import java.util.List;

public class WorkStudyAdapter extends BaseAdapter {
    Context context;
    private final List<WorkStudy> mData;

    public WorkStudyAdapter(Context context, List<WorkStudy> mData) {
        this.context = context;
        this.mData = mData;
    }

    /**
     * 决定了ListView列表展示的行数
     * @return ListView列表展示的行数
     */
    @Override
    public int getCount() {
        return mData.size();
    }

    /**
     * 返回指定位置对应的数据
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    /**
     * 返回指定位置所对应的id
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WorkStudyAdapter.ViewHolder holder = null;
        if (convertView == null) {
            // 将布局转换成view对象的方法
            convertView = LayoutInflater.from(context).inflate(R.layout.item_infolist_lv, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 加载控件显示的内容
        // 获取集合指定位置的数据
        final WorkStudy workStudy = mData.get(position);

        int picId = R.drawable.work_studylist;
        String as = workStudy.getPlace();
        switch (as) {
            case "乐学餐厅":
                picId = R.drawable.place1;
                break;
            case "二食堂":
                picId = R.drawable.place2;
                break;
            case "东苑食堂":
                picId = R.drawable.place3;
                break;
            case "信息楼2楼":
                picId = R.drawable.place4;
                break;
            case "西苑餐厅":
                picId = R.drawable.place5;
                break;
            case "枫叶餐厅":
                picId = R.drawable.place6;
                break;
            default:
                break;
        }


        holder.tvTitle.setText(workStudy.getWorkName());
        holder.tvTitle2.setText(workStudy.getPlace());
        holder.tvTitle3.setText(workStudy.getDailySalary() + "/天");
        holder.tvTitle3.setTextColor(Color.MAGENTA);
        holder.iv.setImageResource(picId);
        return convertView;

    }

    static class ViewHolder {
        ImageView iv;
        TextView tvTitle, tvTitle2, tvTitle3, time;

        public ViewHolder(View view) {
            iv = view.findViewById(R.id.item_info_iv);
            tvTitle = view.findViewById(R.id.item_info_tv_title);
            tvTitle2 = view.findViewById(R.id.item_info_tv_title2);
            tvTitle3 = view.findViewById(R.id.item_info_tv_title3);
            time = view.findViewById(R.id.item_info_tv_time);
            // 隐藏time组件
            time.setVisibility(View.GONE);
        }
    }

}
