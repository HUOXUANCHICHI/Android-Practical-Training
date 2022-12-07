package com.ablaze.ChiChiCampusFinance.ui.studywork;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.ablaze.ChiChiCampusFinance.R;
import com.ablaze.ChiChiCampusFinance.util.SPUtils;

/**
 * 求助电话
 */
public class StudentCallActivity extends AppCompatActivity {
    private String phone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_study_desc);
    }


    public void call1(View view){
        phone = "10010";
        if (ContextCompat.checkSelfPermission(this, Manifest.
                permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {
            call();
        }
    }

    public void call2(View view){
        phone = "10086";
        if (ContextCompat.checkSelfPermission(this, Manifest.
                permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {
            call();
        }
    }

    public void call3(View view){
        phone = "10001";
        if (ContextCompat.checkSelfPermission(this, Manifest.
                permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {
            call();
        }
    }

    public void back(View view){
        finish();
    }


    //打电话
    private void call() {
        try {
            Integer userId= (Integer) SPUtils.get(StudentCallActivity.this, SPUtils.USER_ID,0);
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phone));
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    /*
        调用完requestPermission() 方法之后，无论是哪一种结果，最终都回调到 onRequestPermissionsResult() 方法中
        然后对授权结果 grantResults 进行判断
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    Toast.makeText(this, "你禁止了拨打电话的权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}
