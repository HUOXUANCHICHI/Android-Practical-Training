package com.ablaze.ChiChiCampusFinance.ui.budget;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ablaze.ChiChiCampusFinance.R;
import com.ablaze.ChiChiCampusFinance.dao.BudgetDao;
import com.ablaze.ChiChiCampusFinance.entity.Budget;

public class BudgetDescActivity extends Activity {

    private AlertDialog.Builder builder;
    private TextView tv_main_title, tv_back;
    private RelativeLayout rl_title_bar;
    EditText et_bud_desc_money, et_bud_desc_remarks;
    /**
     * 账目分类 饮食、工资、交通、医疗、其他
     */
    Spinner spinner_bud_desc_type;
    /**
     * 所属资产账单类型 现金、银行卡、支付宝、微信、其他
     */
    Spinner spinner_bud_desc_assName;
    Button btn_bud_desc_del, btn_bud_desc_update;
    private Double etBudMoney = 0.0;
    private String spBudAccType = "", spBudAssName, etBudRemarks = "";
    int id;
    private final BudgetDao budgetDao = new BudgetDao(this);

    /**
     * 获取控件上的字符串
     */
    private void getEditString() {
        etBudMoney = Double.valueOf(et_bud_desc_money.getText().toString().trim());
        etBudRemarks = et_bud_desc_remarks.getText().toString().trim();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_desc);
        initView();
        // 接受上一级页面传来的数据  列表页面
        Intent intent = getIntent();
        Budget BudBean = (Budget) intent.getSerializableExtra("budget");
        // 设置显示控件
        id = BudBean.getId();
        et_bud_desc_money.setText(String.valueOf(BudBean.getBudgetMoney()));
        //设置单笔账单详情的资产类型(现金、银行卡、支付宝、微信、其他)下拉列表框选中
        System.out.println("BudBean.getAccountType()=" + BudBean.getAccountType());
        switch (BudBean.getAccountType()) {
            case "饮食":
                spinner_bud_desc_type.setSelection(0);
                break;
            case "工资":
                spinner_bud_desc_type.setSelection(1);
                break;
            case "交通":
                spinner_bud_desc_type.setSelection(2);
                break;
            case "医疗":
                spinner_bud_desc_type.setSelection(3);
                break;
            case "其他":
                spinner_bud_desc_type.setSelection(4);
                break;
            default:
                break;
        }
        spinner_bud_desc_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 拿到被选择项的值
                spBudAccType = (String) spinner_bud_desc_type.getSelectedItem();
                System.out.println("spBudAccType=" + spBudAccType);
                Toast.makeText(BudgetDescActivity.this, spBudAccType, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("spinner_ass_desc_type未选择", "NothingSelected");
            }
        });
        System.out.println("BudBean.getAccountType()=" + BudBean.getAssetsName());
        switch (BudBean.getAssetsName()) {
            case "现金":
                spinner_bud_desc_assName.setSelection(0);
                break;
            case "银行卡":
                spinner_bud_desc_assName.setSelection(1);
                break;
            case "支付宝":
                spinner_bud_desc_assName.setSelection(2);
                break;
            case "微信":
                spinner_bud_desc_assName.setSelection(3);
                break;
            case "其他":
                spinner_bud_desc_assName.setSelection(4);
                break;
            default:
                break;
        }
        spinner_bud_desc_assName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 拿到被选择项的值
                spBudAssName = (String) spinner_bud_desc_assName.getSelectedItem();
                System.out.println("spBudAssName=" + spBudAssName);
//                Toast.makeText(AccountDescActivity.this, spAccType, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("spinner_bud_desc_assName未选择", "NothingSelected");

            }
        });
        et_bud_desc_remarks.setText(BudBean.getRemarks());

    }

    private void initView() {
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        rl_title_bar.setBackgroundColor(Color.parseColor("#78A4FA"));
        tv_main_title.setText("预算详情");

        et_bud_desc_money = findViewById(R.id.et_bud_desc_money);
        spinner_bud_desc_type = findViewById(R.id.spinner_bud_desc_type);
        spinner_bud_desc_assName = findViewById(R.id.spinner_bud_desc_assName);
        et_bud_desc_remarks = findViewById(R.id.et_bud_desc_remarks);

        btn_bud_desc_del = (Button) findViewById(R.id.btn_bud_desc_del);
        btn_bud_desc_update = (Button) findViewById(R.id.btn_bud_desc_update);

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BudgetDescActivity.this.finish();
            }
        });

        // 修改按钮的点击事件
        btn_bud_desc_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                if (TextUtils.isEmpty(String.valueOf(etBudMoney))) {
                    //账户金额为空
                    Toast.makeText(BudgetDescActivity.this, "请输入预算金额", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etBudRemarks)) {
                    //备注为空
                    Toast.makeText(BudgetDescActivity.this, "请输入备注", Toast.LENGTH_SHORT).show();
                } else {
                    //两个按钮的 dialog
                    builder = new AlertDialog.Builder(BudgetDescActivity.this).setIcon(R.mipmap.ic_launcher)
                            .setTitle("修改预算信息")
                            .setMessage("正在修改预算金额：" + etBudMoney + "，该操作不可撤销，是否确定修改？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(BudgetDescActivity.this, "资产账户修改成功", Toast.LENGTH_SHORT).show();
                                    // 保存
                                    budgetDao.updateBudget(id, etBudMoney, spBudAccType, spBudAssName, etBudRemarks);
                                    BudgetDescActivity.this.finish();
                                    // 重新启动详情页面
                                    /*Intent intent = new Intent(BudgetDescActivity.this, BudgetList.class);
                                    startActivity(intent);*/
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(BudgetDescActivity.this, "已取消修改", Toast.LENGTH_LONG).show();
                                    dialogInterface.dismiss();
                                }
                            });
                    builder.create().show();
                }

            }
        });

        // 删除按钮的点击事件
        btn_bud_desc_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                //两个按钮的 dialog
                builder = new AlertDialog.Builder(BudgetDescActivity.this).setIcon(R.mipmap.ic_launcher)
                        .setTitle("删除账户信息")
                        .setMessage("正在删除预算金额：" + etBudMoney + "，该操作不可撤销，是否确定删除？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(BudgetDescActivity.this, "资产账户删除成功", Toast.LENGTH_SHORT).show();
                                // 保存
                                budgetDao.deleteBudget(id);
                                BudgetDescActivity.this.finish();
                                // 重新启动详情页面
                                /*Intent intent = new Intent(AssetsDescActivity.this, AssetsList.class);
                                startActivity(intent);*/
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(BudgetDescActivity.this, "已取消删除", Toast.LENGTH_LONG).show();
                                dialogInterface.dismiss();
                            }
                        });
                builder.create().show();
            }
        });

    }

}
