package com.ablaze.ChiChiCampusFinance.entity;

import java.io.Serializable;

public class Fund implements Serializable {
    /**
     * 基金id
     */
    private int id;
    /**
     * 基金名称
     */
    private String fundName;
    /**
     * 利率
     */
    private String rate;
    /**
     * 是否购买
     */
    private String joined;
    /**
     * 备注
     */
    private String Remarks;

    public Fund() {
    }

    public Fund(int id, String fundName, String rate, String joined, String remarks) {
        this.id = id;
        this.fundName = fundName;
        this.rate = rate;
        this.joined = joined;
        Remarks = remarks;
    }

    @Override
    public String toString() {
        return "Fund{" +
                "id=" + id +
                ", fundName=" + fundName +
                ", rate='" + rate + '\'' +
                ", joined='" + joined + '\'' +
                ", Remarks='" + Remarks + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getJoined() {
        return joined;
    }

    public void setJoined(String joined) {
        this.joined = joined;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }
}
