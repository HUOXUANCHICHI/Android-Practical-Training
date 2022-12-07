package com.ablaze.ChiChiCampusFinance.ui.fund;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ablaze.ChiChiCampusFinance.R;
import com.ablaze.ChiChiCampusFinance.dao.FundDao;
import com.ablaze.ChiChiCampusFinance.entity.Fund;

public class FundDescActivity extends Activity {

    private AlertDialog.Builder builder;
    private TextView tv_main_title, tv_back;
    private RelativeLayout rl_title_bar;
    TextView tv_fund_desc_fundName, tv_fund_desc_rate, tv_fund_desc_joined, tv_bud_desc_remarks;

    Button btn_fund_desc_buy, btn_fund_desc_reset;
    private String tvFundName = "", tvFundRate, tvFundJoined = "", tvFundRemarks = "";
    int id;
    private final FundDao fundDao = new FundDao(this);

    /**
     * 获取控件上的字符串
     */
    private void getEditString() {
        tvFundName = tv_fund_desc_fundName.getText().toString().trim();
        tvFundRate = tv_fund_desc_rate.getText().toString().trim();
        tvFundJoined = tv_fund_desc_joined.getText().toString().trim();
        tvFundRemarks = tv_bud_desc_remarks.getText().toString().trim();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund_desc);
        initView();
        // 接受上一级页面传来的数据  列表页面
        Intent intent = getIntent();
        Fund FundBean = (Fund) intent.getSerializableExtra("fund");
        // 设置显示控件
        id = FundBean.getId();
        tv_fund_desc_fundName.setText(FundBean.getFundName());
        tv_fund_desc_rate.setText(FundBean.getRate());
        tv_fund_desc_joined.setText(FundBean.getJoined());
        tv_bud_desc_remarks.setText(FundBean.getRemarks());

    }

    private void initView() {
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        rl_title_bar.setBackgroundColor(Color.parseColor("#78A4FA"));
        tv_main_title.setText("基金详情");

        tv_fund_desc_fundName = findViewById(R.id.tv_fund_desc_fundName);
        tv_fund_desc_rate = findViewById(R.id.tv_fund_desc_rate);
        tv_fund_desc_joined = findViewById(R.id.tv_fund_desc_joined);
        tv_bud_desc_remarks = findViewById(R.id.tv_bud_desc_remarks);

        btn_fund_desc_buy = (Button) findViewById(R.id.btn_fund_desc_buy);
        btn_fund_desc_reset = (Button) findViewById(R.id.btn_fund_desc_reset);

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FundDescActivity.this.finish();
            }
        });

        // 购买按钮的点击事件
        btn_fund_desc_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                if ("已购买".equals(tvFundJoined)) {
                    Toast.makeText(FundDescActivity.this, "已购买基金", Toast.LENGTH_SHORT).show();
                } else if ("未购买".equals(tvFundJoined)) {
                    //两个按钮的 dialog
                    builder = new AlertDialog.Builder(FundDescActivity.this).setIcon(R.mipmap.ic_launcher)
                            .setTitle("修改基金状态")
                            .setMessage("正在修改基金状态：" + tvFundJoined + "，该操作不可撤销，是否确定修改？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(FundDescActivity.this, "购买基金成功", Toast.LENGTH_SHORT).show();
                                    // 保存
                                    fundDao.buyFund(id, "已购买");
                                    FundDescActivity.this.finish();
                                    // 重新启动详情页面
                                    /*Intent intent = new Intent(BudgetDescActivity.this, BudgetList.class);
                                    startActivity(intent);*/
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(FundDescActivity.this, "已取消", Toast.LENGTH_LONG).show();
                                    dialogInterface.dismiss();
                                }
                            });
                    builder.create().show();
                }

            }
        });

        // 取消购买按钮的点击事件

            btn_fund_desc_reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getEditString();
                    //两个按钮的 dialog
                    if ("未购买".equals(tvFundJoined)) {
                        Toast.makeText(FundDescActivity.this, "已是未购买基金", Toast.LENGTH_SHORT).show();
                    } else if ("已购买".equals(tvFundJoined)) {
                        builder = new AlertDialog.Builder(FundDescActivity.this).setIcon(R.mipmap.ic_launcher)
                                .setTitle("修改基金状态")
                                .setMessage("正在修改基金状态：" + tvFundJoined + "，该操作不可撤销，是否确定修改？")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(FundDescActivity.this, "取消购买基金成功", Toast.LENGTH_SHORT).show();
                                        // 保存
                                        fundDao.buyFund(id, "未购买");
                                        FundDescActivity.this.finish();
                                        // 重新启动详情页面
                                /*Intent intent = new Intent(AssetsDescActivity.this, AssetsList.class);
                                startActivity(intent);*/
                                    }
                                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(FundDescActivity.this, "已取消", Toast.LENGTH_LONG).show();
                                        dialogInterface.dismiss();
                                    }
                                });
                        builder.create().show();
                    }
                }
            });

    }

}
