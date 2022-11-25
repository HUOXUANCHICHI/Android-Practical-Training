package com.ablaze.ChiChiCampusFinance.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ablaze.ChiChiCampusFinance.entity.Account;
import com.ablaze.ChiChiCampusFinance.entity.Budget;
import com.ablaze.ChiChiCampusFinance.util.MySqliteHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BudgetDao {

    private final MySqliteHelper helper;

    public BudgetDao(Context context) {
        helper = new MySqliteHelper(context);
    }

    /**
     * 增加账单
     * @param budgetMoney 预消费金额
     * @param accountType 账目分类 衣食住行其他
     * @param assetsName 所属账户 微信、支付宝
     * @param Remarks 备注
     * @param username 已登录用户
     */
    public void addBudget(double budgetMoney, String accountType, String assetsName,
            String Remarks, String username) {
        String sql = "insert into tb_budget(budgetMoney,accountType,assetsName,Remarks,username) values (?,?,?,?,?)";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql, new Object[] { budgetMoney, accountType, assetsName, Remarks, username });
        db.close();
    }

    /**
     * 删除预算
     * @param id 账单id
     */
    public void deleteBudget(int id) {
        String sql = "delete from tb_budget where id = ? ";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql, new Object[] { id });
        db.close();
    }

    /**
     * 修改预算
     * @param id 账单id
     * @param budgetMoney 预消费金额
     * @param accountType 账目分类 衣食住行其他
     * @param Remarks 备注
     */
    public void updateBudget(int id, double budgetMoney, String accountType, String assetsName, String Remarks) {
        String sql = "update tb_budget set  budgetMoney = ?, accountType = ?, assetsName = ?, Remarks = ?  where id =? ";
        SQLiteDatabase db = helper.getWritableDatabase();
        // 要跟sql语句里面的对齐
        db.execSQL(sql, new Object[] { budgetMoney, accountType, assetsName, Remarks, id });
        db.close();
    }

    /**
     * 查询已登录用户的所有的预算信息
     * @param username 已登录用户
     * @return 账单列表
     */
    public List<Budget> findBudAll(String username) {
        ArrayList<Budget> budgetsList = new ArrayList<>();
        String sql = "select * from tb_budget where username = '" + username + "'";
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            double budgetMoney = cursor.getDouble(1);
            String accountType = cursor.getString(2);
            String assetsName = cursor.getString(3);
            String Remarks = cursor.getString(4);
            Budget budget = new Budget(id, budgetMoney, accountType, assetsName, Remarks);
            budgetsList.add(budget);
        }
        cursor.close();
        return budgetsList;
    }

    /**
     * 查询所有资金余额总数
     * @param username 登录用户
     * @return 资金余额
     */
    public Double findBudSumAll(String username) {
        Double moneySum = null;
        String sql = "select SUM(budgetMoney) from tb_budget where username = '" + username + "'";
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            moneySum = cursor.getDouble(0);
        }
        Log.i("sumdao", String.valueOf(moneySum));
        cursor.close();
        return moneySum;
    }
}
