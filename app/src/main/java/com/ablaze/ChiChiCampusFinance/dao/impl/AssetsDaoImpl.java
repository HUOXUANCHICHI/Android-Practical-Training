package com.ablaze.ChiChiCampusFinance.dao.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ablaze.ChiChiCampusFinance.dao.AssetsDao;
import com.ablaze.ChiChiCampusFinance.util.MySqliteHelper;
import com.ablaze.ChiChiCampusFinance.entity.Assets;

import java.util.ArrayList;
import java.util.List;

public class AssetsDaoImpl implements AssetsDao {

    private final MySqliteHelper helper;

    public AssetsDaoImpl(Context context) {
        helper = new MySqliteHelper(context);
    }

    /**
     * 增加资产
     * @param assetsName 资产名称
     * @param assetsType 资产类型 现金、银行卡、支付宝、微信、其他
     * @param assetsMoney 资产数目
     * @param Remarks 备注
     * @param username 已登录用户名
     */
    public void addAssets(String assetsName, String assetsType, Double assetsMoney, String Remarks, String username) {
        String sql = "insert into tb_assets (assetsName, assetsType, assetsMoney, Remarks, username) values (?,?,?,?,?)";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql, new Object[] { assetsName, assetsType, assetsMoney, Remarks, username });
        db.close();
    }

    /**
     * 删除资产
     * @param id 资产id
     */
    public void deleteAssets(int id) {
        String sql = "delete from tb_assets where id = ? ";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql, new Object[] { id });
        db.close();
    }

    /**
     * 修改资产
     * @param id 资产id
     * @param assetsName 资产名称
     * @param assetsType 资产类型 现金、银行卡、支付宝、微信、其他
     * @param assetsMoney 资产数目
     * @param Remarks 备注
     */
    public void updateAssets(int id, String assetsName, String assetsType, Double assetsMoney, String Remarks) {
        String sql = "update tb_assets set  assetsName = ?, assetsType = ?, assetsMoney = ?, Remarks = ? where id =? ";
        SQLiteDatabase db = helper.getWritableDatabase();
        // 要跟sql语句里面的对齐
        db.execSQL(sql, new Object[] { assetsName, assetsType, assetsMoney, Remarks, id });
        db.close();
    }

    /**
     * 查询已登录用户的所有资产信息
     * @param username 已登录用户
     * @return 资产列表
     */
    public List<Assets> findAssAll(String username) {
        ArrayList<Assets> assetsList = new ArrayList<>();
        String sql = "select * from tb_assets where username = '" + username + "'";
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String assetsName = cursor.getString(1);
            String assetsType = cursor.getString(2);
            double assetsMoney = cursor.getDouble(3);
            String Remarks = cursor.getString(4);
            Assets assets = new Assets(id, assetsName, assetsType, assetsMoney, Remarks);
            assetsList.add(assets);
        }
        cursor.close();
        return assetsList;
    }

    /**
     * 查询所有资金余额总数
     * @param username 登录用户
     * @return 资金余额
     */
    public Double findAssSumAll(String username) {
        Double moneySum = null;
        String sql = "select SUM(assetsMoney) from tb_assets where username = '" + username + "'";
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            moneySum = cursor.getDouble(0);
        }
        Log.i("sumdao", String.valueOf(moneySum));
        cursor.close();
        return moneySum;
    }

    /**
     * 查询某类型资产的全部资产信息
     * @param name 资产类型名
     * @param username 登录用户名
     * @return 某类型资产的全部资产信息
     */
    public Assets findByAssName(String name, String username) {
        // String sql = "select * from tb_assets where assetsName = '" + name + "' and
        // '" + username + "'";
        String sql = "SELECT * FROM tb_assets WHERE assetsType = ? and username = ?";
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[] { name, username });
        // 判断cursor.moveToFirst()的值为true或false来确定查询结果是否为空
        // cursor.moveToNext()是用来做循环的
        if (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String assetsName = cursor.getString(1);
            String assetsType = cursor.getString(2);
            double assetsMoney = cursor.getDouble(3);
            String Remarks = cursor.getString(4);
            Assets assets = new Assets(id, assetsName, assetsType, assetsMoney, Remarks);
            return assets;
        }
        db.close();
        return null;
    }

    /**
     *  查询某类型名称的全部资产信息
     * @param assets_type_name 资产类型 现金、银行卡、支付宝、微信、其他
     * @param username 已登录用户名
     * @return 查询某类型名称的全部资产信息
     */
    public Assets findByAssType(String assets_type_name, String username) {
        // String sql = "select * from tb_assets where assetsName = '" + name + "' and
        // '" + username + "'";
        String sql = "SELECT * FROM tb_assets WHERE assetsType = '" + assets_type_name + "' and username = '" + username + "'";
        Log.i("该月查询根据id资产sql语句", sql);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        // 判断cursor.moveToFirst()的值为true或false来确定查询结果是否为空
        // cursor.moveToNext()是用来做循环的
        if (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String assetsName = cursor.getString(1);
            String assetsType = cursor.getString(2);
            double assetsMoney = cursor.getDouble(3);
            String Remarks = cursor.getString(4);
            Assets assets = new Assets(id, assetsName, assetsType, assetsMoney, Remarks);
            return assets;
        }
        db.close();
        return null;
    }
}
