package com.ablaze.ChiChiCampusFinance.dao.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ablaze.ChiChiCampusFinance.dao.FundDao;
import com.ablaze.ChiChiCampusFinance.entity.Fund;
import com.ablaze.ChiChiCampusFinance.util.MySqliteHelper;

import java.util.ArrayList;
import java.util.List;

public class FundDaoImpl implements FundDao {

    private final MySqliteHelper helper;

    public FundDaoImpl(Context context) {
        helper = new MySqliteHelper(context);
    }

    /**
     * 修改基金购买状态
     * @param id 基金id
     * @param joined 是否已购买
     */
    public void buyFund(int id, String joined) {
        String sql = "update tb_fund set joined = ?  where id =? ";
        SQLiteDatabase db = helper.getWritableDatabase();
        // 要跟sql语句里面的对齐
        db.execSQL(sql, new Object[] { joined, id });
        db.close();
    }

    /**
     * 查询所有的基金信息
     * @return 基金列表
     */
    public List<Fund> findFundAll() {
        ArrayList<Fund> fundsList = new ArrayList<>();
        String sql = "select * from tb_fund";
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String fundName = cursor.getString(1);
            String rate = cursor.getString(2);
            String joined = cursor.getString(3);
            String Remarks = cursor.getString(4);
            Fund fund = new Fund(id, fundName, rate, joined, Remarks);
            fundsList.add(fund);
        }
        cursor.close();
        return fundsList;
    }

}
