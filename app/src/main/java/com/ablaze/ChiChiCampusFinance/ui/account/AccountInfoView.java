package com.ablaze.ChiChiCampusFinance.ui.account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ablaze.ChiChiCampusFinance.R;
import com.ablaze.ChiChiCampusFinance.dao.impl.AccountDaoImpl;
import com.ablaze.ChiChiCampusFinance.ui.login.LoginActivity;

public class AccountInfoView extends Activity {

    private TextView tv01, acc_money_sum;
    private Activity mContext;
    private LayoutInflater mInflater;//布局加载器
    private View mCurrentView;
    private RelativeLayout rt_acc_add_records, btn_acc;
    Double sum = 0.0;

    public AccountInfoView(Activity context) {
        mContext = context;
        // 为以后将Layout转换为view时用
        mInflater = LayoutInflater.from(mContext);
    }

    /**
     * 创建视图
     */
    private void createView() {
        initView();
    }

    /**
     * 获取界面控件
     */
    private void initView() {
        // 设置布局文件
        mCurrentView = mInflater.inflate(R.layout.main_view_account, null);
        rt_acc_add_records = (RelativeLayout) mCurrentView.findViewById(R.id.rt_acc_add_records);
        btn_acc = (RelativeLayout) mCurrentView.findViewById(R.id.rt_acc_list);
        // 是否显示组件
        mCurrentView.setVisibility(View.VISIBLE);

        acc_money_sum = (TextView) mCurrentView.findViewById(R.id.acc_money_sum);
        // 该月收支
        AccountDaoImpl accountDao = new AccountDaoImpl(mContext);
        Double inCome = accountDao.findAccSumAll("收入", LoginActivity.getLoggingUsername());
        Log.i("该月收支-->收入", String.valueOf(inCome));
        Double payOut = accountDao.findAccSumAll("支出", LoginActivity.getLoggingUsername());
        Log.i("该月收支-->支出", String.valueOf(payOut));
        sum = inCome + payOut;
        Log.i("该月收支", "=" + inCome + "" + payOut + " 最终=" + sum);
        if (readLoginStatus()) {
            acc_money_sum.setText(String.valueOf(sum));
        } else {
            acc_money_sum.setText("0.00");
        }
        // 添加的点击事件
        rt_acc_add_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (readLoginStatus()) {
                    // 跳转到添加界面
                    Intent intent = new Intent(mContext, AccountAddActivity.class);
                    mContext.startActivityForResult(intent, 1);
                } else {
                    Toast.makeText(mContext, "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (readLoginStatus()) {
                    // 跳转到详情界面
                    Intent intent = new Intent(mContext, AccountList.class);
                    mContext.startActivityForResult(intent, 1);
                } else {
                    Toast.makeText(mContext, "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 获取当前导航栏上方显示对应的View
     *
     * @return
     */
    public View getView() {
        if (mCurrentView == null) {
            createView();
        }
        return mCurrentView;
    }

    // 显示当前导航栏上方显示对应的View界面
    public void showView() {
        mCurrentView.setVisibility(View.VISIBLE);
        acc_money_sum = (TextView) mCurrentView.findViewById(R.id.acc_money_sum);
        // 该月收支
        AccountDaoImpl accountDao = new AccountDaoImpl(mContext);
        Double inCome = accountDao.findAccSumAll("收入", LoginActivity.getLoggingUsername());
        Log.i("该月收支-->收入", String.valueOf(inCome));
        Double payOut = accountDao.findAccSumAll("支出", LoginActivity.getLoggingUsername());
        Log.i("该月收支-->支出", String.valueOf(payOut));
        sum = inCome + payOut;
        Log.i("该月收支", "=" + inCome + "-" + payOut + "最终=" + sum);
        if (readLoginStatus()) {
            acc_money_sum.setText(String.valueOf(sum));
        } else {
            acc_money_sum.setText("0.00");
        }
    }

    // 从SharedPreferences中读取登录状态
    private boolean readLoginStatus() {
        SharedPreferences sp = mContext.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin", false);
        return isLogin;
    }

}
