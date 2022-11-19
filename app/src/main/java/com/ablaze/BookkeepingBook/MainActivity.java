package com.ablaze.BookkeepingBook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ablaze.BookkeepingBook.aboutme.MyInfoView;
import com.ablaze.BookkeepingBook.account.AccountInfoView;
import com.ablaze.BookkeepingBook.assets.AssetsInfoView;
import com.ablaze.BookkeepingBook.util.MySqliteHelper;
import com.ablaze.BookkeepingBook.R;

public class MainActivity extends Activity implements OnClickListener {

    // 视图
    private AccountInfoView mAccountInfoView;
    private AssetsInfoView mAssetsInfoView;
    private MyInfoView mMyInfoView;

    //顶部退出 背景
    private RelativeLayout rl_title_bar;

    //顶部标题词
    private TextView tv_back, tv_main_title;

    // 中间内容栏
    private FrameLayout mBodyLayout;

    // 底部按钮栏
    public LinearLayout mBottomLayout;

    // 底部按钮控件
    private View mAccountBtn, mAssetsBtn, mMyInfoBtn;
    private TextView tv_account, tv_assets, tv_myInfo;
    private ImageView iv_account, iv_assets, iv_myInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 执行创建数据库
        MySqliteHelper helper = new MySqliteHelper(this);
        helper.getWritableDatabase();

        // 设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        // 获取界面 顶部 中间 UI
        this.init();
        // 获取 底部导航 UI
        this.initBottomBar();
        // 设置底部三个按钮的点击监听事件
        this.setListener();
        // 设置界面view的初始化状态
        this.setInitStatus();
    }

    /**
     * 获取界面上的UI控件
     */
    private void init() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("赤赤校园理财");
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_back.setVisibility(View.GONE);
        this.initBodyLayout();
    }

    /**
     * 中间内容栏初始化
     */
    private void initBodyLayout() {
        mBodyLayout = (FrameLayout) findViewById(R.id.main_body);
    }

    /**
     * 获取底部导航栏上的控件
     */
    private void initBottomBar() {
        mBottomLayout = (LinearLayout) findViewById(R.id.main_bottom_bar);
        mAccountBtn = findViewById(R.id.bottom_bar_account_btn);
        mAssetsBtn = findViewById(R.id.bottom_bar_assets_btn);
        mMyInfoBtn = findViewById(R.id.bottom_bar_myinfo_btn);
        tv_account = (TextView) findViewById(R.id.bottom_bar_text_account);
        tv_assets = (TextView) findViewById(R.id.bottom_bar_text_assets);
        tv_myInfo = (TextView) findViewById(R.id.bottom_bar_text_myinfo);
        iv_account = (ImageView) findViewById(R.id.bottom_bar_image_account);
        iv_assets = (ImageView) findViewById(R.id.bottom_bar_image_assets);
        iv_myInfo = (ImageView) findViewById(R.id.bottom_bar_image_myinfo);
    }

    /**
     * 设置底部三个按钮的点击监听事件
     */
    private void setListener() {
        for (int i = 0; i < mBottomLayout.getChildCount(); i++) {
            mBottomLayout.getChildAt(i).setOnClickListener(this);
        }
    }

    /**
     * 设置界面view的初始化状态
     */
    private void setInitStatus() {
        this.clearBottomImageState();
        this.setSelectedStatus(0);
        this.createView(0);
    }

    /**
     * 清除底部按钮的选中状态
     */
    private void clearBottomImageState() {
        tv_account.setTextColor(Color.parseColor("#666666"));
        tv_assets.setTextColor(Color.parseColor("#666666"));
        tv_myInfo.setTextColor(Color.parseColor("#666666"));
        iv_account.setImageResource(R.drawable.index_1);
        iv_assets.setImageResource(R.drawable.index_2);
        iv_myInfo.setImageResource(R.drawable.index_3);
        for (int i = 0; i < mBottomLayout.getChildCount(); i++) {
            mBottomLayout.getChildAt(i).setSelected(false);
        }
    }

    /**
     * 设置底部按钮选中状态
     * @param index 第n个底部按钮
     */
    public void setSelectedStatus(int index) {
        switch (index) {
            case 0:
                mAccountBtn.setSelected(true);
                iv_account.setImageResource(R.drawable.index_1_lan);
                tv_account.setTextColor(Color.parseColor("#0097F7"));
                rl_title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("赤赤校园理财");
                break;
            case 1:
                mAssetsBtn.setSelected(true);
                iv_assets.setImageResource(R.drawable.index_2_lan);
                tv_assets.setTextColor(Color.parseColor("#0097F7"));
                rl_title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("资产账户");
                break;
            case 2:
                mMyInfoBtn.setSelected(true);
                iv_myInfo.setImageResource(R.drawable.index_3_lan);
                tv_myInfo.setTextColor(Color.parseColor("#0097F7"));
                rl_title_bar.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    /**
     * 选择创建视图
     * @param viewIndex 第n个视图
     */
    private void createView(int viewIndex) {
        switch (viewIndex) {
            case 0:
                // 记账界面
                if (mAccountInfoView == null) {
                    mAccountInfoView = new AccountInfoView(this);
                    mBodyLayout.addView(mAccountInfoView.getView());
                } else {
                    mAccountInfoView.showView();
                }
                break;
            case 1:
                // 资产界面
                if (mAssetsInfoView == null) {
                    mAssetsInfoView = new AssetsInfoView(this);
                    mBodyLayout.addView(mAssetsInfoView.getView());
                } else {
                    mAssetsInfoView.showView();
                }
                break;
            case 2:
                // 我的界面
                if (mMyInfoView == null) {
                    mMyInfoView = new MyInfoView(this);
                    mBodyLayout.addView(mMyInfoView.getView());
                } else {
                    mMyInfoView.showView();
                }
                break;
        }
    }

    /**
     * 控件的点击事件，当点击按钮时首先清空底部导航栏的状态，之后将相应的图片和按钮设置为选中状态
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // index1的点击事件
            case R.id.bottom_bar_account_btn:
                clearBottomImageState();
                selectDisplayView(0);
                break;
            // index2的点击事件
            case R.id.bottom_bar_assets_btn:
                clearBottomImageState();
                selectDisplayView(1);
                break;
            // 我的点击事件
            case R.id.bottom_bar_myinfo_btn:
                clearBottomImageState();
                selectDisplayView(2);
                break;
            default:
                break;
        }
    }

    /**
     * 显示对应的页面
     * @param index 第n个页面
     */
    private void selectDisplayView(int index) {
        removeAllView();
        createView(index);
        setSelectedStatus(index);
    }


    /**
     * 移除不需要的视图
     */
    private void removeAllView() {
        for (int i = 0; i < mBodyLayout.getChildCount(); i++) {
            mBodyLayout.getChildAt(i).setVisibility(View.GONE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            // 从设置界面或登录界面传递过来的登录状态
            boolean isLogin = data.getBooleanExtra("isLogin", false);
            if (isLogin) {
                // 登录成功时清除底部按钮选中状态 且 显示第一个界面
                clearBottomImageState();
                selectDisplayView(0);
            }
            if (mMyInfoView != null) {
                // 登录成功或退出登录时根据isLogin设置我的界面
                mMyInfoView.setLoginParams(isLogin);
            }
        }
    }

    /**
     * 记录第一次点击时的时间
     */
    protected long exitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {// 第二次点击时间与第一次时间间隔大于两秒
                Toast.makeText(MainActivity.this, "再按一次退出",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                MainActivity.this.finish();
                if (readLoginStatus()) {
                    // 如果退出此应用时是登录状态，则需要清除登录状态，同时需清除登录时的用户名
                    clearLoginStatus();
                }
                System.exit(0);// 退出
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 获取SharedPreferences中的登录状态
     * @return 是否登录
     */
    private boolean readLoginStatus() {
        SharedPreferences sp = getSharedPreferences("loginInfo",
                Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin", false);
        return isLogin;
    }

    /**
     * 清除登录状态
     */
    private void clearLoginStatus() {
        SharedPreferences sp = getSharedPreferences("loginInfo",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();// 获取编辑器
        editor.putBoolean("isLogin", false);// 清除登录状态
        editor.putString("loginUserName", "");// 清除登录时的用户名
        editor.commit();// 提交修改
    }

}
