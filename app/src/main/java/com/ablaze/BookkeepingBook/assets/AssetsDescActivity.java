package com.ablaze.BookkeepingBook.assets;

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
import com.ablaze.BookkeepingBook.dao.AssetsDao;
import com.ablaze.BookkeepingBook.entity.Assets;

public class AssetsDescActivity extends Activity {

    private AlertDialog.Builder builder;
    private TextView tv_main_title, tv_back;
    private RelativeLayout rl_title_bar;
    EditText et_ass_desc_name, et_ass_desc_money, et_ass_desc_remarks;
    /**
     * 资产类型
     */
    Spinner spinner_ass_desc_type;
    Button btn_ass_desc_del, btn_ass_desc_update;
    private String etAssName = "", etAssRemarks = "", spAssType = "";
    private Double etAssMoney = 0.00;
    int id;
    private final AssetsDao assetsDao = new AssetsDao(this);

    /**
     * 获取控件上的字符串
     */
    private void getEditString() {
        etAssName = et_ass_desc_name.getText().toString().trim();
        etAssMoney = Double.valueOf(et_ass_desc_money.getText().toString().trim());
        etAssRemarks = et_ass_desc_remarks.getText().toString().trim();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets_desc);
        initView();
        // 接受上一级页面传来的数据  列表页面
        Intent intent = getIntent();
        Assets AssBean = (Assets) intent.getSerializableExtra("assets");
        // 设置显示控件
        id = AssBean.getId();
        et_ass_desc_name.setText(AssBean.getAssetsName());
        et_ass_desc_money.setText(String.valueOf(AssBean.getAssetsMoney()));
        et_ass_desc_remarks.setText(AssBean.getRemarks());

        spinner_ass_desc_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 拿到被选择项的值
                spAssType = (String) spinner_ass_desc_type.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("spinner_ass_desc_type未选择", "NothingSelected");
            }
        });
    }

    private void initView() {
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        rl_title_bar.setBackgroundColor(Color.parseColor("#78A4FA"));
        tv_main_title.setText("资产详情");

        et_ass_desc_name = findViewById(R.id.et_ass_desc_name);
        et_ass_desc_money = findViewById(R.id.et_ass_desc_money);
        et_ass_desc_remarks = findViewById(R.id.et_ass_desc_remarks);
        spinner_ass_desc_type = findViewById(R.id.spinner_ass_desc_type);

        btn_ass_desc_del = (Button) findViewById(R.id.btn_ass_desc_del);
        btn_ass_desc_update = (Button) findViewById(R.id.btn_ass_desc_update);

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssetsDescActivity.this.finish();
            }
        });

        // 修改按钮的点击事件
        btn_ass_desc_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                if (TextUtils.isEmpty(etAssName)) {
                    //用户名为空
                    Toast.makeText(AssetsDescActivity.this, "请输入账户名称", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(String.valueOf(etAssMoney))) {
                    //账户金额为空
                    Toast.makeText(AssetsDescActivity.this, "请输入账户金额", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etAssRemarks)) {
                    //备注为空
                    Toast.makeText(AssetsDescActivity.this, "请输入备注", Toast.LENGTH_SHORT).show();
                } else {
                    //两个按钮的 dialog
                    builder = new AlertDialog.Builder(AssetsDescActivity.this).setIcon(R.mipmap.ic_launcher)
                            .setTitle("修改账户信息")
                            .setMessage("正在修改资金：" + etAssName + "，该操作不可撤销，是否确定修改？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(AssetsDescActivity.this, "资产账户修改成功", Toast.LENGTH_SHORT).show();
                                    // 保存
                                    assetsDao.updateAssets(id, etAssName, spAssType, etAssMoney, etAssRemarks);
                                    AssetsDescActivity.this.finish();
                                    // 重新启动详情页面
                                    Intent intent = new Intent(AssetsDescActivity.this, AssetsList.class);
                                    startActivity(intent);
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(AssetsDescActivity.this, "已取消修改", Toast.LENGTH_LONG).show();
                                    dialogInterface.dismiss();
                                }
                            });
                    builder.create().show();
                }

            }
        });

        // 删除按钮的点击事件
        btn_ass_desc_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                //两个按钮的 dialog
                builder = new AlertDialog.Builder(AssetsDescActivity.this).setIcon(R.mipmap.ic_launcher)
                        .setTitle("删除账户信息")
                        .setMessage("正在删除资金：" + etAssName + "，该操作不可撤销，是否确定删除？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(AssetsDescActivity.this, "资产账户删除成功", Toast.LENGTH_SHORT).show();
                                // 保存
                                assetsDao.deleteAssets(id);
                                AssetsDescActivity.this.finish();
                                //todo 删除弹窗finish后原页面不会刷新

                                // 重新启动详情页面
                                Intent intent = new Intent(AssetsDescActivity.this, AssetsList.class);
                                startActivity(intent);
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(AssetsDescActivity.this, "已取消删除", Toast.LENGTH_LONG).show();
                                dialogInterface.dismiss();
                            }
                        });
                builder.create().show();
            }
        });

    }

}
