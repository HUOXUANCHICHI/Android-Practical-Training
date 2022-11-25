package com.ablaze.ChiChiCampusFinance.entity;

import java.io.Serializable;

public class Budget implements Serializable {
    /**
     * 预算id
     */
    private int id;
    /**
     * 预消费金额
     */
    private double budgetMoney;
    /**
     * 账目分类 饮食、工资、交通、医疗、其他
     */
    private String accountType;
    /**
     * 账单所属资产类型名称 现金、银行卡、支付宝、微信、其他
     */
    private String assetsName;
    /**
     * 备注
     */
    private String Remarks;

    public Budget() {
    }

    public Budget(int id, double budgetMoney, String accountType, String assetsName, String remarks) {
        this.id = id;
        this.budgetMoney = budgetMoney;
        this.accountType = accountType;
        this.assetsName = assetsName;
        Remarks = remarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBudgetMoney() {
        return budgetMoney;
    }

    public void setBudgetMoney(double budgetMoney) {
        this.budgetMoney = budgetMoney;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAssetsName() {
        return assetsName;
    }

    public void setAssetsName(String assetsName) {
        this.assetsName = assetsName;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }
}
