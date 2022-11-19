package com.ablaze.BookkeepingBook.account;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ablaze.BookkeepingBook.R;
import com.ablaze.BookkeepingBook.entity.Account;

import java.util.List;

public class AccountAdapter extends BaseAdapter {
    Context context;
    private final List<Account> mData;

    public AccountAdapter(Context context, List<Account> mData) {
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
        AccountAdapter.ViewHolder holder = null;
        if (convertView == null) {
            // 将布局转换成view对象的方法
            convertView = LayoutInflater.from(context).inflate(R.layout.item_infolist_lv, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (AccountAdapter.ViewHolder) convertView.getTag();
        }
        // 加载控件显示的内容
        // 获取集合指定位置的数据
        final Account account = mData.get(position);

        int picId = R.drawable.acc_other;
        String as = account.getAccountType();
        switch (as) {
            case "饮食":
                picId = R.drawable.acc_eat;
                break;
            case "工资":
                picId = R.drawable.acc_salary;
                break;
            case "交通":
                picId = R.drawable.acc_traffic;
                break;
            case "医疗":
                picId = R.drawable.acc_medicine;
                break;
            case "其他":
                picId = R.drawable.acc_other;
                break;
            default:
                break;
        }

        holder.tvTitle.setText(account.getAssetsName());
        holder.tvTitle2.setText(String.valueOf(account.getAccountMoney()));
        holder.tvTitle3.setText(account.getRemarks());
        holder.time.setText(account.getTime());
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
        }
    }
}
