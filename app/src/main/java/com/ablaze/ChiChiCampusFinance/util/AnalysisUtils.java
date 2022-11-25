package com.ablaze.ChiChiCampusFinance.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.ablaze.ChiChiCampusFinance.dao.AccountDao;
import com.ablaze.ChiChiCampusFinance.dao.AssetsDao;
import com.ablaze.ChiChiCampusFinance.dao.BudgetDao;
import com.ablaze.ChiChiCampusFinance.entity.Account;
import com.ablaze.ChiChiCampusFinance.login.LoginActivity;

import java.util.List;

/**
 * @author ablaze 分析工具
 */
public class AnalysisUtils {

    // 从SharedPreferences中读取登录用户名
    public static String readLoginUserName(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        String userName = sp.getString("loginUserName", "");
        return userName;
    }

    /**
     * 读取资产总金额，显示在账户页面
     * @param context
     * @return
     */
    public static Double showAssSum(Context context) {
        AssetsDao dao = new AssetsDao(context);
        return dao.findAssSumAll(LoginActivity.getLoggingUsername());
    }

    /**
     * 读取资产总金额，显示在账户页面
     * @param context
     * @return
     */
    public static Double showBudSum(Context context) {
        BudgetDao dao = new BudgetDao(context);
        return dao.findBudSumAll(LoginActivity.getLoggingUsername());
    }

}
