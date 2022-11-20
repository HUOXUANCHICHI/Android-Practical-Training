package com.ablaze.BookkeepingBook.account;

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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ablaze.BookkeepingBook.R;
import com.ablaze.BookkeepingBook.dao.AccountDao;
import com.ablaze.BookkeepingBook.dao.AssetsDao;
import com.ablaze.BookkeepingBook.entity.Account;
import com.ablaze.BookkeepingBook.entity.Assets;
import com.ablaze.BookkeepingBook.login.LoginActivity;

public class AccountDescActivity extends Activity {

    private AlertDialog.Builder builder;
    private TextView tv_main_title, tv_back;
    private RelativeLayout rl_title_bar;
    EditText et_acc_desc_money, et_acc_desc_remarks;
    /**
     * 账目类型 支出、收入
     */
    Spinner spinner_acc_desc_type;
    /**
     * 所属账户 微信、支付宝
     */
    Spinner spinner_acc_desc_payType;
    Button btn_acc_desc_del, btn_acc_desc_update;
    private String etAccMoney = "", spAccType = "", spAccPayType = "", etAccRemarks = "";
    int id;
    private final AccountDao accountDao = new AccountDao(this);
    private AssetsDao assetsDao = new AssetsDao(this);

    /**
     * 获取控件上的字符串
     */
    private void getEditString() {
        etAccMoney = et_acc_desc_money.getText().toString().trim();
        etAccRemarks = et_acc_desc_remarks.getText().toString().trim();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_desc);
        initView();
        // 接受上一级页面传来的数据
        Intent intent = getIntent();
        Account AccBean = (Account) intent.getSerializableExtra("account");
        // 设置显示控件
        id = AccBean.getId();
        et_acc_desc_money.setText(String.valueOf(0-AccBean.getAccountMoney()));
        //设置单笔账单详情的账目分类(衣食住行其他)下拉列表框选中
        System.out.println("AccBean.getAccountType()=" + AccBean.getAccountType());
        switch (AccBean.getAccountType()) {
            case "饮食":
                spinner_acc_desc_type.setSelection(0);
                break;
            case "工资":
                spinner_acc_desc_type.setSelection(1);
                break;
            case "交通":
                spinner_acc_desc_type.setSelection(2);
                break;
            case "医疗":
                spinner_acc_desc_type.setSelection(3);
                break;
            case "其他":
                spinner_acc_desc_type.setSelection(4);
                break;
            default:
                break;
        }
        spinner_acc_desc_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 拿到被选择项的值
                spAccType = (String) spinner_acc_desc_type.getSelectedItem();
                System.out.println("spAccType=" + spAccType);
//                Toast.makeText(AccountDescActivity.this, spAccType, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("spinner_acc_desc_type未选择", "NothingSelected");

            }
        });
        //设置单笔账单详情的账目类型(支出、收入)下拉列表框选中
        System.out.println("AccBean.getPayType()=" + AccBean.getPayType());
        switch (AccBean.getPayType()) {
            case "支出":
                spinner_acc_desc_payType.setSelection(0);
                break;
            case "收入":
                spinner_acc_desc_payType.setSelection(1);
                break;
            default:
                break;
        }
        spinner_acc_desc_payType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 拿到被选择项的值
                spAccPayType = (String) spinner_acc_desc_payType.getSelectedItem();
                System.out.println("spAccPayType=" + spAccPayType);
//                Toast.makeText(AccountDescActivity.this, spAccPayType, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("spinner_acc_desc_payType未选择", "NothingSelected");
            }
        });
        et_acc_desc_remarks.setText(AccBean.getRemarks());
    }

    private void initView() {
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        rl_title_bar.setBackgroundColor(Color.parseColor("#78A4FA"));
        tv_main_title.setText("收支详情");

        et_acc_desc_money = findViewById(R.id.et_acc_desc_money);
        et_acc_desc_remarks = findViewById(R.id.et_acc_desc_remarks);
        spinner_acc_desc_type = findViewById(R.id.spinner_acc_desc_type);
        spinner_acc_desc_payType = findViewById(R.id.spinner_acc_desc_payType);

        btn_acc_desc_update = (Button) findViewById(R.id.btn_acc_desc_update);
        btn_acc_desc_del = (Button) findViewById(R.id.btn_acc_desc_del);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountDescActivity.this.finish();
            }
        });

        // 修改按钮的点击事件
        btn_acc_desc_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                if (TextUtils.isEmpty(String.valueOf(etAccMoney))) {
                    //金额输入框为空
                    Toast.makeText(AccountDescActivity.this, "请输入收支费用", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etAccRemarks)) {
                    //备注输入框为空
                    Toast.makeText(AccountDescActivity.this, "请输入备注", Toast.LENGTH_SHORT).show();
                } else {
                    //两个按钮的 dialog
                    builder = new AlertDialog.Builder(AccountDescActivity.this).setIcon(R.mipmap.ic_launcher)
                            .setTitle("修改账单费用信息")
                            .setMessage("正在修改账单,金额：" + etAccMoney + "，该操作不可撤销，是否确定修改？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(AccountDescActivity.this, "账单费用信息修改成功", Toast.LENGTH_SHORT).show();
                                    // 保存
                                    Double s = 0.0;
                                    if (spAccPayType.equals("收入")) {
                                        s = Double.valueOf(etAccMoney);
                                    } else {
                                        s = 0.0 - Double.valueOf(etAccMoney);
                                    }
                                    accountDao.updateAccount(id, s, spAccType, spAccPayType, etAccRemarks);
                                    AccountDescActivity.this.finish();
                                    // 重新启动详情页面
                                    Intent intent = new Intent(AccountDescActivity.this, AccountList.class);
                                    startActivity(intent);
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(AccountDescActivity.this, "已取消修改", Toast.LENGTH_LONG).show();
                                    dialogInterface.dismiss();
                                }
                            });
                    builder.create().show();
                }

            }
        });

        // 删除按钮的点击事件
        btn_acc_desc_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                //两个按钮的 dialog
                builder = new AlertDialog.Builder(AccountDescActivity.this).setIcon(R.mipmap.ic_launcher)
                        .setTitle("删除账单信息")
                        .setMessage("正在删除账单：" + etAccMoney + "，该操作不可撤销，是否确定删除？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(AccountDescActivity.this, "资产账户删除成功", Toast.LENGTH_SHORT).show();
                                // 保存
                                accountDao.deleteAccount(id);
                                //s为输入框中的数(有正有负)
                                Double s = Double.parseDouble(etAccMoney);
                                System.out.println(s);
                                Intent data = getIntent();
                                Account AccBean = (Account) data.getSerializableExtra("account");
                                Toast.makeText(AccountDescActivity.this, String.valueOf(AccBean.getAssetsName()), Toast.LENGTH_SHORT).show();
                                Assets assets = assetsDao.findByAssId(String.valueOf(AccBean.getAssetsName()),
                                        LoginActivity.getLoggingUsername());// 查询某id的全部资产信息
                                if (assets != null) {
                                    Double s1 = assets.getAssetsMoney() + s;// 资产的和这里的相加
                                    System.out.println(s1);
                                    assetsDao.updateAssets(assets.getId(), assets.getAssetsName(), assets.getAssetsType(),
                                            s1,
                                            assets.getRemarks());
                                } else {
                                    System.out.println("assets为空");
                                }
                                //todo 删除弹窗finish后原页面不会刷新
                                AccountDescActivity.this.finish();
                                // 重新启动详情页面
                                Intent intent = new Intent(AccountDescActivity.this, AccountList.class);
                                startActivity(intent);
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(AccountDescActivity.this, "已取消删除", Toast.LENGTH_LONG).show();
                                dialogInterface.dismiss();
                            }
                        });
                builder.create().show();
            }
        });

    }

}
