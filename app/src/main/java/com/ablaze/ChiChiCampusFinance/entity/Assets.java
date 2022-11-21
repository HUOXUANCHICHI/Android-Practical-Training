package com.ablaze.ChiChiCampusFinance.entity;

import java.io.Serializable;

public class Assets implements Serializable {
    /**
     * 资产id
     */
    private int id;
    /**
     * 资产名称
     */
    private String assetsName;
    /**
     * 资产类型 现金、银行卡、支付宝、微信、其他
     */
    private String assetsType;
    /**
     * 资产数目
     */
    private Double assetsMoney;
    /**
     * 备注
     */
    private String Remarks;

    @Override
    public String toString() {
        return "Assets{" +
                "id=" + id +
                ", assetsName='" + assetsName + '\'' +
                ", assetsType='" + assetsType + '\'' +
                ", assetsMoney=" + assetsMoney +
                ", Remarks='" + Remarks + '\'' +
                '}';
    }

    public Assets() {

    }

    public Assets(int id, String assetsName, String assetsType, double assetsMoney, String remarks) {
        this.id = id;
        this.assetsName = assetsName;
        this.assetsType = assetsType;
        this.assetsMoney = assetsMoney;
        this.Remarks = remarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAssetsName() {
        return assetsName;
    }

    public void setAssetsName(String assetsName) {
        this.assetsName = assetsName;
    }

    public String getAssetsType() {
        return assetsType;
    }

    public void setAssetsType(String assetsType) {
        this.assetsType = assetsType;
    }

    public Double getAssetsMoney() {
        return assetsMoney;
    }

    public void setAssetsMoney(Double assetsMoney) {
        this.assetsMoney = assetsMoney;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

}
