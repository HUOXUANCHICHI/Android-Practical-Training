package com.ablaze.ChiChiCampusFinance.ui.aboutme;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ablaze.ChiChiCampusFinance.R;
import com.ablaze.ChiChiCampusFinance.ui.login.LoginActivity;
import com.ablaze.ChiChiCampusFinance.util.AnalysisUtils;
import com.ablaze.ChiChiCampusFinance.util.MD5Utils;

public class ModifyPwdActivity extends Activity {

    private TextView tv_main_title, tv_back;//标题 返回键
    private EditText et_original_psw, et_new_psw, et_new_psw_again;//原始密码 新密码 确认密码的编辑框
    private Button btn_save;//保存按钮
    private String originalPsw, newPsw, newPswAgain, userName;//获取控件上的字符串
    private RelativeLayout rl_title_bar;// 标题布局

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pwd);
        // 设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        init();
        userName = AnalysisUtils.readLoginUserName(this);
    }

    // 获取控件上的字符串
    private void getEditString() {
        originalPsw = et_original_psw.getText().toString().trim();
        newPsw = et_new_psw.getText().toString().trim();
        newPswAgain = et_new_psw_again.getText().toString().trim();
    }

    /**
     * 将SharedPreferences中的密码修改为新密码
     * @param newPsw 输入的新密码
     */
    private void modifyPsw(String newPsw) {
        String md5Psw = MD5Utils.MD5(newPsw);// 把密码用MD5加密
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();// 获取编辑器
        editor.putString(userName, md5Psw);// 保存新密码
        editor.commit();// 提交修改

    }

    /**
     * 从SharedPreferences中读取原始密码
     * @return SharedPreferences中读取原始密码
     */
    private Object readPsw() {
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        return sp.getString(userName, "");
    }

    /**
     * 获取界面控件并处理相关控件的点击事件
     */
    private void init() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("修改密码");
        tv_main_title.setBackgroundColor(Color.parseColor("#78A4FA"));
        tv_back = (TextView) findViewById(R.id.tv_back);
        et_original_psw = (EditText) findViewById(R.id.et_original_pwd);
        et_new_psw = (EditText) findViewById(R.id.et_new_pwd);
        et_new_psw_again = (EditText) findViewById(R.id.et_new_pwd_again);
        btn_save = (Button) findViewById(R.id.btn_save);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModifyPwdActivity.this.finish();
            }
        });
        // 保存按钮的点击事件
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                if (TextUtils.isEmpty(originalPsw)) {
                    //输入框输入的原始密码为空
                    Toast.makeText(ModifyPwdActivity.this, "请输入原始密码", Toast.LENGTH_SHORT).show();
                    // 当点击“保存”按钮时需要验证保存密码是否正确
                } else if (TextUtils.isEmpty(newPsw)) {
                    //输入的“新密码"为空
                    Toast.makeText(ModifyPwdActivity.this, "请输入新密码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(newPswAgain)) {
                    //输入的“确认密码"为空
                    Toast.makeText(ModifyPwdActivity.this, "请再次输入新密码", Toast.LENGTH_SHORT).show();
                    // 验证新输入的密码是否相同
                } else if (!MD5Utils.MD5(originalPsw).equals(readPsw())) {
                    //输入的”原始密码“与旧密码不一致
                    Toast.makeText(ModifyPwdActivity.this, "输入的原始密码与旧密码不一致", Toast.LENGTH_SHORT).show();
                } else if (MD5Utils.MD5(newPsw).equals(readPsw())) {
                    //输入的“新密码”与旧密码一致
                    Toast.makeText(ModifyPwdActivity.this, "输入的新密码与旧密码不能一致", Toast.LENGTH_SHORT).show();
                } else if (!newPsw.equals(newPswAgain)) {
                    //输入的“密码”与“确认密码”不相同
                    Toast.makeText(ModifyPwdActivity.this, "两次输入的新密码不一致", Toast.LENGTH_SHORT).show();
                } else {
                    //修改密码成功
                    Toast.makeText(ModifyPwdActivity.this, "新密码设置成功", Toast.LENGTH_SHORT).show();
                    // 修改登录成功时保存在SharedPreferences中的密码
                    modifyPsw(newPsw);
                    Intent intent = new Intent(ModifyPwdActivity.this, LoginActivity.class);
//                    startActivity(intent);

                    intent.putExtra("newPsw",newPsw);
                    System.out.println("newPsw=" + newPsw);
                    setResult(2,intent);
//                    startActivityForResult(intent,2);

                    SettingActivity.instance.finish();
                    System.out.println("===========setting finish====");
                    ModifyPwdActivity.this.finish();
                }
            }
        });
    }

}
