package com.ablaze.ChiChiCampusFinance.dao;

import com.ablaze.ChiChiCampusFinance.entity.Fund;

import java.util.List;

public interface FundDao {

    void buyFund(int id, String joined);

    /**
     * 查询所有的基金信息
     * @return 基金列表
     */
    List<Fund> findFundAll();


}
