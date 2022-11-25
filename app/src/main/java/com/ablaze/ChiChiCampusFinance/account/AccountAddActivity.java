package com.ablaze.ChiChiCampusFinance.account;

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
import com.ablaze.ChiChiCampusFinance.dao.AccountDao;
import com.ablaze.ChiChiCampusFinance.dao.AssetsDao;
import com.ablaze.ChiChiCampusFinance.entity.Assets;
import com.ablaze.ChiChiCampusFinance.login.LoginActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountAddActivity extends Activity {

    private TextView tv_main_title, tv_back;
    private EditText et_acc_money, et_acc_remarks;
    /**
     * 账目分类 饮食、工资、交通、医疗、其他
     */
    private Spinner spinner_acc_type;
    /**
     * 账目类型 支出、收入
     */
    private Spinner spinner_accPay_type;
    /**
     * 所属资产账单类型 现金、银行卡、支付宝、微信、其他
     */
    private Spinner spinner_acc_assName;
    private Button btn_acc_save;
    private String etAccMoney = "", spAccType = "", spAccPayType = "", spAccAssName = "", etAccRemarks = "";
    private AccountDao accountDao = new AccountDao(this);
    private AssetsDao assetsDao = new AssetsDao(this);
    /*List<Assets> allList;*/
    /*private ArrayAdapter<String> arr_adapter;
    private ArrayAdapter<CharSequence> spinner_acc_assName_List;*/

    // 为List集合添加内容，用于列表显示
    /*List<String> data_list = new ArrayList<>();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_add);

        // 设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        // 初始化
        init();

        // 下拉框动态赋值
        /*allList = assetsDao.findAssAll(LoginActivity.getLoggingUsername());
        Log.i("数据", allList.toString());*/
        /*for (int i = 0; i < allList.size(); i++) {
            Assets assetss = allList.get(i);
            String AssetsName = assetss.getAssetsName();
            data_list.add(AssetsName);
        }*/

        spinner_acc_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 拿到被选择项的值
                spAccType = (String) spinner_acc_type.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("spinner_acc_type未选择", "NothingSelected");
            }
        });
        // 获取下拉框的值
        spinner_accPay_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 拿到被选择项的值
                spAccPayType = (String) spinner_accPay_type.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("spinner_accPay_type未选择", "NothingSelected");
            }
        });
        // 获取下拉框的值
        spinner_acc_assName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 拿到被选择项的值
                spAccAssName = (String) spinner_acc_assName.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("spinner_acc_assName未选择", "NothingSelected");

            }
        });
    }

    // 获取界面控件并处理相关控件的点击事件
    private void init() {
        // 修改顶部样式
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title.setText("添加收支记录");
        tv_main_title.setBackgroundColor(Color.parseColor("#78A4FA"));
        // tv_main_title.setTextColor(Color.parseColor("#78A4FA"));

        et_acc_money = (EditText) findViewById(R.id.et_acc_money);
        spinner_acc_type = (Spinner) findViewById(R.id.spinner_acc_type);
        spinner_accPay_type = (Spinner) findViewById(R.id.spinner_accPay_type);
        spinner_acc_assName = (Spinner) findViewById(R.id.spinner_acc_assName);
        et_acc_remarks = (EditText) findViewById(R.id.et_acc_remarks);
        btn_acc_save = (Button) findViewById(R.id.btn_acc_save);

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 关闭
                AccountAddActivity.this.finish();
            }
        });

        // 保存按钮的点击事件
        btn_acc_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                if (TextUtils.isEmpty(etAccMoney)) {
                    //账单金额为空
                    Toast.makeText(AccountAddActivity.this, "请输入收支金额", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etAccRemarks)) {
                    //备注为空
                    Toast.makeText(AccountAddActivity.this, "请输入备注", Toast.LENGTH_SHORT).show();
                } else {
                    //两编辑框边不为空
                    Toast.makeText(AccountAddActivity.this, "收支添加成功", Toast.LENGTH_SHORT).show();
                    // 保存
                    // 获取当前时间
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
                    Date date = new Date(System.currentTimeMillis());
                    String time = simpleDateFormat.format(date);
                    Log.i("name", time);
                    Double s = 0.0;
                    //s为输入框中的数
                    if (spAccPayType.equals("收入")) {
                        //正数
                        s = Double.parseDouble(etAccMoney);
                    } else if (spAccPayType.equals("支出")){
                        //负数
                        s = 0.0 - Double.parseDouble(etAccMoney);
                    }
                    //打印资产名称
                    Toast.makeText(AccountAddActivity.this, spAccAssName, Toast.LENGTH_SHORT).show();
                    // boolean assets = assetsDao.findByAssName(spAssName,
                    // LoginActivity.getLoggingUsername());// 从资产获取资产名称下的多少钱
                    Assets assets = assetsDao.findByAssName(spAccAssName,
                            LoginActivity.getLoggingUsername());// 查询某类型资产的全部资产信息
                    if (assets != null) {
                        Double s1 = assets.getAssetsMoney() + s;// 资产的和这里的相加
                        assetsDao.updateAssets(assets.getId(), assets.getAssetsName(), assets.getAssetsType(),
                                s1,
                                assets.getRemarks());
                    }
                    accountDao.addAccount(s, spAccType, spAccPayType, spAccAssName,
                            time,
                            etAccRemarks, LoginActivity.getLoggingUsername());
                    // double accountMoney,String accountType,String payType,String
                    // assetsName,String time,String Remark
                    AccountAddActivity.this.finish();
                }
            }
        });
    }

    // 获取控件上的字符串
    private void getEditString() {
        etAccMoney = et_acc_money.getText().toString().trim();
        etAccRemarks = et_acc_remarks.getText().toString().trim();
    }
}
