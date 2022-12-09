package com.ablaze.ChiChiCampusFinance.dao.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ablaze.ChiChiCampusFinance.dao.AccountDao;
import com.ablaze.ChiChiCampusFinance.entity.Account;
import com.ablaze.ChiChiCampusFinance.util.MySqliteHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccountDaoImpl implements AccountDao {

    private final MySqliteHelper helper;

    public AccountDaoImpl(Context context) {
        helper = new MySqliteHelper(context);
    }

    /**
     * 增加账单
     * @param accountMoney 记账金额
     * @param accountType 账目分类 衣食住行其他
     * @param payType 账目类型 支出、收入
     * @param assetsName 所属账户 微信、支付宝
     * @param time 创建时间
     * @param Remarks 备注
     * @param username 已登录用户
     */
    public void addAccount(double accountMoney, String accountType, String payType, String assetsName, String time,
            String Remarks, String username) {
        String sql = "insert into tb_account(accountMoney,accountType,payType,assetsName,time,Remarks,username) values (?,?,?,?,?,?,?)";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql, new Object[] { accountMoney, accountType, payType, assetsName, time, Remarks, username });
        db.close();
    }

    /**
     * 删除账单
     * @param id 账单id
     */
    public void deleteAccount(int id) {
        String sql = "delete from tb_account where id = ? ";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql, new Object[] { id });
        db.close();
    }

    /**
     * 修改账单
     * @param id 账单id
     * @param accountMoney 记账金额
     * @param accountType 账目分类 衣食住行其他
     * @param payType 账目类型 支出、收入
     * @param Remarks 备注
     */
    public void updateAccount(int id, double accountMoney, String accountType, String payType, String Remarks) {
        String sql = "update tb_account set  accountMoney = ?, accountType = ?, payType = ?,Remarks = ?  where id =? ";
        SQLiteDatabase db = helper.getWritableDatabase();
        // 要跟sql语句里面的对齐
        db.execSQL(sql, new Object[] { accountMoney, accountType, payType, Remarks, id });
        db.close();
    }

    /**
     * 查询已登录用户的所有的账单信息
     * @param username 已登录用户
     * @return 账单列表
     */
    public List<Account> findAccAll(String username) {
        ArrayList<Account> assetsList = new ArrayList<>();
        String sql = "select * from tb_account where username = '" + username + "'";
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            double accountMoney = cursor.getDouble(1);
            String accountType = cursor.getString(2);
            String payType = cursor.getString(3);
            String assetsName = cursor.getString(4);
            String time = cursor.getString(5);
            String Remarks = cursor.getString(6);
            Account account = new Account(id, accountMoney, accountType, payType, assetsName, time, Remarks);
            assetsList.add(account);
        }
        cursor.close();
        return assetsList;
    }

    /**
     * 查询所有账单余额总数
     * @param payType 账单类型(收入/支出)
     * @param username 已登录用户
     * @return 账单余额
     */
    public Double findAccSumAll(String payType, String username) {
        Double moneySum = null;
        // SELECT sum(accountMoney) FROM tb_account WHERE payType = '收入' 或者支出
        String sql = "select SUM(accountMoney) from tb_account WHERE payType ='" +
                payType + "' and username = '"
                + username + "'";
        Log.i("该月收支sql语句", sql);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            moneySum = cursor.getDouble(0);
        }
        Log.i("该月收支sql语句", String.valueOf(moneySum));
        cursor.close();
        return moneySum;
    }

    private final ArrayList<HashMap<String, String>> list = new ArrayList<>();

}
