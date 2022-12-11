package com.ablaze.ChiChiCampusFinance.ui.workstudy;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.ablaze.ChiChiCampusFinance.R;
import com.ablaze.ChiChiCampusFinance.entity.WorkStudy;

public class WorkStudyDescActivity extends Activity {

    private TextView tv_main_title, tv_back;
    private RelativeLayout rl_title_bar;
    TextView tv_work_study_desc_workName, tv_work_study_desc_dailySalary, tv_work_study_desc_telephone, tv_work_study_desc_place, tv_work_study_desc_remarks;

    private String tvWorkStudyName = "", tvWorkStudyDailySalary, tvWorkStudyTelephone = "", tvWorkStudyPlace = "", tvWorkStudyRemarks;
    int id;

    /**
     * 获取控件上的字符串
     */
    private void getEditString() {
        tvWorkStudyName = tv_work_study_desc_workName.getText().toString().trim();
        tvWorkStudyDailySalary = tv_work_study_desc_dailySalary.getText().toString().trim();
        tvWorkStudyTelephone = tv_work_study_desc_telephone.getText().toString().trim();
        tvWorkStudyPlace = tv_work_study_desc_place.getText().toString().trim();
        tvWorkStudyRemarks = tv_work_study_desc_remarks.getText().toString().trim();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_study_desc);
        initView();
        // 接受上一级页面传来的数据  列表页面
        Intent intent = getIntent();
        WorkStudy WorkStudyBean = (WorkStudy) intent.getSerializableExtra("workStudy");
        // 设置显示控件
        id = WorkStudyBean.getId();
        tv_work_study_desc_workName.setText(WorkStudyBean.getWorkName());
        tv_work_study_desc_dailySalary.setText(WorkStudyBean.getDailySalary());
        tv_work_study_desc_telephone.setText(WorkStudyBean.getTelephone());
        tv_work_study_desc_place.setText(WorkStudyBean.getPlace());
        tv_work_study_desc_remarks.setText(WorkStudyBean.getRemarks());

    }

    private void initView() {
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        rl_title_bar.setBackgroundColor(Color.parseColor("#78A4FA"));
        tv_main_title.setText("工作详情");

        tv_work_study_desc_workName = findViewById(R.id.tv_work_study_desc_workName);
        tv_work_study_desc_dailySalary = findViewById(R.id.tv_work_study_desc_dailySalary);
        tv_work_study_desc_telephone = findViewById(R.id.tv_work_study_desc_telephone);
        tv_work_study_desc_place = findViewById(R.id.tv_work_study_desc_place);
        tv_work_study_desc_remarks = findViewById(R.id.tv_work_study_desc_remarks);

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkStudyDescActivity.this.finish();
            }
        });

    }

    private String phone;

    public void contactUs(View view) {
        getEditString();
        phone = tvWorkStudyTelephone;
        if (ContextCompat.checkSelfPermission(this, Manifest.
                permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //无权限，申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {
            //有权限
            call();
        }
    }

    /**
     * 打电话
     */
    private void call() {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phone));
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用完requestPermission() 方法之后，无论是哪一种结果，最终都回调到 onRequestPermissionsResult() 方法中
     * 然后对授权结果 grantResults 进行判断
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //权限被用户同意
                    call();
                } else {
                    //权限被用户拒绝
                    Toast.makeText(this, "你禁止了拨打电话的权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

}
