package com.example.BookkeepingBook.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.BookkeepingBook.dao.AccountDao;
import com.example.BookkeepingBook.dao.AssetsDao;
import com.example.BookkeepingBook.entity.Account;
import com.example.BookkeepingBook.login.LoginActivity;

import java.util.List;

/**
 * @author sailorj 分析工具
 */
public class AnalysisUtils {

    // 从SharedPreferences中读取登录用户名
    public static String readLoginUserName(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        String userName = sp.getString("loginUserName", "");
        return userName;
    }

    // 读取总金额，显示在账户页面
    public static Double showSum(Context context) {
        AssetsDao dao = new AssetsDao(context);
        Double sum = dao.findAssSumAll(LoginActivity.getmUsername());
        return sum;
    }

    // 读取总金额，显示在账户页面
    public static List<Account> accountList(Context context) {
        AccountDao dao = new AccountDao(context);
        return dao.findAccAll(LoginActivity.getmUsername());
    }

}
