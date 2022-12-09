package com.ablaze.ChiChiCampusFinance.dao;

import com.ablaze.ChiChiCampusFinance.entity.Account;

import java.util.List;

public interface AccountDao {

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
    void addAccount(double accountMoney, String accountType, String payType, String assetsName, String time,
                           String Remarks, String username);

    /**
     * 删除账单
     * @param id 账单id
     */
    void deleteAccount(int id);

    /**
     * 修改账单
     * @param id 账单id
     * @param accountMoney 记账金额
     * @param accountType 账目分类 衣食住行其他
     * @param payType 账目类型 支出、收入
     * @param Remarks 备注
     */
    void updateAccount(int id, double accountMoney, String accountType, String payType, String Remarks);

    /**
     * 查询已登录用户的所有的账单信息
     * @param username 已登录用户
     * @return 账单列表
     */
    List<Account> findAccAll(String username);

    /**
     * 查询所有账单余额总数
     * @param payType 账单类型(收入/支出)
     * @param username 已登录用户
     * @return 账单余额
     */
    Double findAccSumAll(String payType, String username);
}
