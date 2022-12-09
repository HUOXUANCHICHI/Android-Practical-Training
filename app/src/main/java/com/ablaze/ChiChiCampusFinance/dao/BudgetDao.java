package com.ablaze.ChiChiCampusFinance.dao;

import com.ablaze.ChiChiCampusFinance.entity.Budget;

import java.util.List;

public interface BudgetDao {

    /**
     * 增加账单
     * @param budgetMoney 预消费金额
     * @param accountType 账目分类 衣食住行其他
     * @param assetsName 所属账户 微信、支付宝
     * @param Remarks 备注
     * @param username 已登录用户
     */
    void addBudget(double budgetMoney, String accountType, String assetsName,
                          String Remarks, String username);

    /**
     * 删除预算
     * @param id 账单id
     */
    void deleteBudget(int id);

    /**
     * 修改预算
     * @param id 账单id
     * @param budgetMoney 预消费金额
     * @param accountType 账目分类 衣食住行其他
     * @param Remarks 备注
     */
    void updateBudget(int id, double budgetMoney, String accountType, String assetsName, String Remarks);

    /**
     * 查询已登录用户的所有的预算信息
     * @param username 已登录用户
     * @return 账单列表
     */
    List<Budget> findBudAll(String username);

    /**
     * 查询所有资金余额总数
     * @param username 登录用户
     * @return 资金余额
     */
    Double findBudSumAll(String username);
}
