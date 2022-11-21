package com.ablaze.ChiChiCampusFinance.assets;

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
import com.ablaze.ChiChiCampusFinance.dao.AssetsDao;
import com.ablaze.ChiChiCampusFinance.login.LoginActivity;

public class AssetsAddActivity extends Activity {

    private TextView tv_main_title, tv_back;
    private EditText et_ass_name, et_ass_money, et_ass_remarks;
    /**
     * 资产类型 现金、银行卡、支付宝、微信、其他
     */
    private Spinner sp_ass_type;
    private Button btn_ass_save;
    private String etAssName = "", etAssRemarks = "", spAssType = "";
    private String etAssMoney;
    private AssetsDao assetsDao = new AssetsDao(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets_add);

        // 设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        // 初始化
        init();

        sp_ass_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 拿到被选择项的值
                spAssType = (String) sp_ass_type.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("sp_ass_type未选择", "NothingSelected");
            }
        });
    }

    // 获取界面控件并处理相关控件的点击事件
    private void init() {
        // 修改顶部样式
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title.setText("添加资产");
        tv_main_title.setBackgroundColor(Color.parseColor("#78A4FA"));
        // rl_title_bar.setBackgroundColor(Color.parseColor("#78A4FA"));

        et_ass_name = (EditText) findViewById(R.id.et_ass_name);
        sp_ass_type = (Spinner) findViewById(R.id.spinner_ass_type);
        et_ass_money = (EditText) findViewById(R.id.et_ass_money);
        et_ass_remarks = (EditText) findViewById(R.id.et_ass_remarks);
        btn_ass_save = (Button) findViewById(R.id.btn_ass_save);

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 关闭
                AssetsAddActivity.this.finish();
            }
        });

        // 保存按钮的点击事件
        btn_ass_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                if (TextUtils.isEmpty(etAssName)) {
                    //资产名字为空
                    Toast.makeText(AssetsAddActivity.this, "请输入账户名称", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etAssMoney)) {
                    //资产金额为空
                    Toast.makeText(AssetsAddActivity.this, "请输入账户金额", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etAssRemarks)) {
                    Toast.makeText(AssetsAddActivity.this, "请输入备注", Toast.LENGTH_SHORT).show();
                } else {
                    //三个输入框都齐全
                    Toast.makeText(AssetsAddActivity.this, spAssType, Toast.LENGTH_SHORT).show();
                    // 保存
                    assetsDao.addAssets(etAssName, spAssType, Double.parseDouble(etAssMoney), etAssRemarks,
                            LoginActivity.getLoggingUsername());
                    AssetsAddActivity.this.finish();
                }
            }
        });
    }

    // 获取控件上的字符串
    private void getEditString() {
        etAssName = et_ass_name.getText().toString().trim();
        etAssMoney = et_ass_money.getText().toString().trim();
        etAssRemarks = et_ass_remarks.getText().toString().trim();
    }

}
