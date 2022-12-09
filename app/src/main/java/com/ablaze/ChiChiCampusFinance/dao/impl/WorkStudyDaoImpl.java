package com.ablaze.ChiChiCampusFinance.dao.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ablaze.ChiChiCampusFinance.dao.WorkStudyDao;
import com.ablaze.ChiChiCampusFinance.entity.WorkStudy;
import com.ablaze.ChiChiCampusFinance.util.MySqliteHelper;

import java.util.ArrayList;
import java.util.List;

public class WorkStudyDaoImpl implements WorkStudyDao {

    private final MySqliteHelper helper;

    public WorkStudyDaoImpl(Context context) {
        helper = new MySqliteHelper(context);
    }

    /**
     * 查询所有的工作信息
     * @return 工作列表
     */
    public List<WorkStudy> findWorkAll() {
        ArrayList<WorkStudy> worksList = new ArrayList<>();
        String sql = "select * from tb_work_study";
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String workName = cursor.getString(1);
            String dailySalary = cursor.getString(2);
            String telephone = cursor.getString(3);
            String place = cursor.getString(4);
            String Remarks = cursor.getString(5);
            WorkStudy workStudy = new WorkStudy(id, workName, dailySalary, telephone, place, Remarks);
            worksList.add(workStudy);
        }
        cursor.close();
        return worksList;
    }

}
