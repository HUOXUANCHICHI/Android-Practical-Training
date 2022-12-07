package com.ablaze.ChiChiCampusFinance.ui.fund;

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
import com.ablaze.ChiChiCampusFinance.dao.FundDao;
import com.ablaze.ChiChiCampusFinance.entity.Fund;

import java.util.ArrayList;
import java.util.List;

/**
 * 账户加载信息页
 */
public class FundList extends Activity {

    private TextView tv_main_title, tv_back;
    private RelativeLayout rl_title_bar;
    /**
     * n 个列表
     */
    ListView showLv;
    List<Fund> mData;
    List<Fund> allList;
    FundDao dao = new FundDao(this);

    private FundAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund_list);
        // 查找控件
        initView();
        // 2.找到ListView对应的数据源
        mData = new ArrayList<>();
        allList = dao.findFundAll();
        mData.addAll(allList);
        // 3.创建适配器 BaseAdapter的子类
        adapter = new FundAdapter(this, mData);
        showLv.setAdapter(adapter); // 4.设置适配器
        // 设置单向点击监听功能
        setListener();
    }

    private void initView() {
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#78A4FA"));
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("基金推荐");

        //列表
        showLv = findViewById(R.id.fund_infoList_lv);

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FundList.this.finish();
            }
        });
    }

    private void setListener() {
        showLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //将信息传递给详情页面
                Fund fundBean = mData.get(position);
                Intent intent = new Intent(FundList.this, FundDescActivity.class);
                intent.putExtra("fund", fundBean);
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
