package com.ablaze.ChiChiCampusFinance.entity;

import java.io.Serializable;

public class WorkStudy implements Serializable {
    /**
     * 勤工俭学id
     */
    private int id;
    /**
     * 工作名称
     */
    private String workName;
    /**
     * 日薪
     */
    private String dailySalary;
    /**
     * 工作人员联系电话
     */
    private String telephone;
    /**
     * 工作地点
     */
    private String place;
    /**
     * 备注
     */
    private String Remarks;

    public WorkStudy() {
    }

    public WorkStudy(int id, String workName, String dailySalary, String telephone, String place, String remarks) {
        this.id = id;
        this.workName = workName;
        this.dailySalary = dailySalary;
        this.telephone = telephone;
        this.place = place;
        Remarks = remarks;
    }

    @Override
    public String toString() {
        return "WorkStudy{" +
                "id=" + id +
                ", workName='" + workName + '\'' +
                ", dailySalary='" + dailySalary + '\'' +
                ", telephone='" + telephone + '\'' +
                ", place='" + place + '\'' +
                ", Remarks='" + Remarks + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getDailySalary() {
        return dailySalary;
    }

    public void setDailySalary(String dailySalary) {
        this.dailySalary = dailySalary;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }
}
