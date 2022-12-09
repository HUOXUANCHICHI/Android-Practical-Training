package com.ablaze.ChiChiCampusFinance.ui.workstudy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ablaze.ChiChiCampusFinance.R;
import com.ablaze.ChiChiCampusFinance.adapter.WorkStudyAdapter;
import com.ablaze.ChiChiCampusFinance.dao.WorkStudyDao;
import com.ablaze.ChiChiCampusFinance.dao.impl.WorkStudyDaoImpl;
import com.ablaze.ChiChiCampusFinance.entity.WorkStudy;

import java.util.ArrayList;
import java.util.List;

/**
 * 账户加载信息页
 */
public class WorkStudyList extends Activity {

    private TextView tv_main_title, tv_back;
    private RelativeLayout rl_title_bar;
    /**
     * n 个列表
     */
    ListView showLv;
    List<WorkStudy> mData;
    List<WorkStudy> allList;
    WorkStudyDao dao = new WorkStudyDaoImpl(this);

    private WorkStudyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_study_list);
        // 查找控件
        initView();
        // 2.找到ListView对应的数据源
        mData = new ArrayList<>();
        allList = dao.findWorkAll();
        mData.addAll(allList);
        // 3.创建适配器 BaseAdapter的子类
        adapter = new WorkStudyAdapter(this, mData);
        showLv.setAdapter(adapter); // 4.设置适配器
        // 设置单向点击监听功能
        setListener();
    }

    private void initView() {
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#78A4FA"));
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("勤工俭学");

        //列表
        showLv = findViewById(R.id.work_study_infoList_lv);

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkStudyList.this.finish();
            }
        });
    }

    private void setListener() {
        showLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //将信息传递给详情页面
                WorkStudy workStudyBean = mData.get(position);
                Intent intent = new Intent(WorkStudyList.this, WorkStudyDescActivity.class);
                intent.putExtra("workStudy", workStudyBean);
                startActivity(intent);
            }
        });
    }

    /**
     * 调用onCreate(), 目的是刷新数据,  从另一activity界面返回到该activity界面时, 此方法自动调用
     */
    @Override
    protected void onResume() {
        super.onResume();
        onCreate(null);
    }

}
