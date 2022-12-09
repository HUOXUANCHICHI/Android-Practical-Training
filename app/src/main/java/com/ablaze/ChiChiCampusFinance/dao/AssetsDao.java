package com.ablaze.ChiChiCampusFinance.dao;

import com.ablaze.ChiChiCampusFinance.entity.Assets;

import java.util.List;

public interface AssetsDao {

    /**
     * 增加资产
     * @param assetsName 资产名称
     * @param assetsType 资产类型 现金、银行卡、支付宝、微信、其他
     * @param assetsMoney 资产数目
     * @param Remarks 备注
     * @param username 已登录用户名
     */
    void addAssets(String assetsName, String assetsType, Double assetsMoney, String Remarks, String username);

    /**
     * 删除资产
     * @param id 资产id
     */
    void deleteAssets(int id);

    /**
     * 修改资产
     * @param id 资产id
     * @param assetsName 资产名称
     * @param assetsType 资产类型 现金、银行卡、支付宝、微信、其他
     * @param assetsMoney 资产数目
     * @param Remarks 备注
     */
    void updateAssets(int id, String assetsName, String assetsType, Double assetsMoney, String Remarks);

    /**
     * 查询已登录用户的所有资产信息
     * @param username 已登录用户
     * @return 资产列表
     */
    List<Assets> findAssAll(String username);

    /**
     * 查询所有资金余额总数
     * @param username 登录用户
     * @return 资金余额
     */
    Double findAssSumAll(String username);

    /**
     * 查询某类型资产的全部资产信息
     * @param name 资产类型名
     * @param username 登录用户名
     * @return 某类型资产的全部资产信息
     */
    Assets findByAssName(String name, String username);

    /**
     *  查询某类型名称的全部资产信息
     * @param assets_type_name 资产类型 现金、银行卡、支付宝、微信、其他
     * @param username 已登录用户名
     * @return 查询某类型名称的全部资产信息
     */
    Assets findByAssType(String assets_type_name, String username);
}
