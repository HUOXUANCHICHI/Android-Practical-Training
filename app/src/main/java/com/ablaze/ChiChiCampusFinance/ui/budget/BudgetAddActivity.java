package com.ablaze.ChiChiCampusFinance.ui.budget;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ablaze.ChiChiCampusFinance.R;
import com.ablaze.ChiChiCampusFinance.dao.BudgetDao;
import com.ablaze.ChiChiCampusFinance.dao.impl.BudgetDaoImpl;
import com.ablaze.ChiChiCampusFinance.ui.login.LoginActivity;

public class BudgetAddActivity extends Activity {

    private TextView tv_main_title, tv_back;
    private EditText et_bud_money, et_bud_remarks;
    /**
     * 账目分类 饮食、工资、交通、医疗、其他
     */
    private Spinner sp_bud_type;
    /**
     * 所属资产账单类型 现金、银行卡、支付宝、微信、其他
     */
    private Spinner sp_bud_assName;
    private Button btn_bud_save;
    private String etBudMoney, spBudAccType = "", spBudAssName, etBudRemarks = "";
    private BudgetDao budgetDao = new BudgetDaoImpl(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_add);

        // 设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        // 初始化
        init();

        sp_bud_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 拿到被选择项的值
                spBudAccType = (String) sp_bud_type.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("sp_bud_type未选择", "NothingSelected");
            }
        });

        sp_bud_assName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 拿到被选择项的值
                spBudAssName = (String) sp_bud_assName.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("sp_bud_assName未选择", "NothingSelected");
            }
        });

    }

    // 获取界面控件并处理相关控件的点击事件
    private void init() {
        // 修改顶部样式
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title.setText("添加预购物品");
        tv_main_title.setBackgroundColor(Color.parseColor("#78A4FA"));
        // rl_title_bar.setBackgroundColor(Color.parseColor("#78A4FA"));

        et_bud_money = (EditText) findViewById(R.id.et_bud_money);
        sp_bud_type = (Spinner) findViewById(R.id.spinner_bud_type);
        sp_bud_assName = (Spinner) findViewById(R.id.spinner_bud_assName);
        et_bud_remarks = (EditText) findViewById(R.id.et_bud_remarks);
        btn_bud_save = (Button) findViewById(R.id.btn_bud_save);

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 关闭
                BudgetAddActivity.this.finish();
            }
        });

        // 保存按钮的点击事件
        btn_bud_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                if (TextUtils.isEmpty(etBudMoney)) {
                    //预算金额为空
                    Toast.makeText(BudgetAddActivity.this, "请输入预算金额", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etBudRemarks)) {
                    //资产金额为空
                    Toast.makeText(BudgetAddActivity.this, "请输入预算备注", Toast.LENGTH_SHORT).show();
                } else {
                    //两个输入框都齐全
                    Toast.makeText(BudgetAddActivity.this, "预购添加成功", Toast.LENGTH_SHORT).show();
                    // 保存
                    budgetDao.addBudget(Double.parseDouble(etBudMoney), spBudAccType, spBudAssName, etBudRemarks,
                            LoginActivity.getLoggingUsername());
                    BudgetAddActivity.this.finish();
                }
            }
        });
    }

    // 获取控件上的字符串
    private void getEditString() {
        etBudMoney = et_bud_money.getText().toString().trim();
        etBudRemarks = et_bud_remarks.getText().toString().trim();
    }

}
