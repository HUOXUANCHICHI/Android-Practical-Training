package com.ablaze.BookkeepingBook.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.ablaze.BookkeepingBook.dao.AccountDao;
import com.ablaze.BookkeepingBook.dao.AssetsDao;
import com.ablaze.BookkeepingBook.entity.Account;
import com.ablaze.BookkeepingBook.login.LoginActivity;

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
     * 读取总金额，显示在账户页面
     * @param context
     * @return
     */
    public static Double showSum(Context context) {
        AssetsDao dao = new AssetsDao(context);
        return dao.findAssSumAll(LoginActivity.getLoggingUsername());
    }

    // 读取总金额，显示在账户页面
    public static List<Account> accountList(Context context) {
        AccountDao dao = new AccountDao(context);
        return dao.findAccAll(LoginActivity.getLoggingUsername());
    }

}
