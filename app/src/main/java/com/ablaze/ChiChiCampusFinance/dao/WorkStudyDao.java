package com.ablaze.ChiChiCampusFinance.dao;

import com.ablaze.ChiChiCampusFinance.entity.WorkStudy;

import java.util.List;

public interface WorkStudyDao {

    /**
     * 查询所有的工作信息
     * @return 工作列表
     */
    List<WorkStudy> findWorkAll();

}
