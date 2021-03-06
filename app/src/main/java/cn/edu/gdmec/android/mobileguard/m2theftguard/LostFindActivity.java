package cn.edu.gdmec.android.mobileguard.m2theftguard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;


import cn.edu.gdmec.android.mobileguard.R;



public class LostFindActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView mSafePhoneTv;
    private RelativeLayout mInterSetupRL;
    private SharedPreferences mSharedPreferences;
    private ToggleButton mToggleButton;
    private TextView mProtectStatusTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_find);
        mSharedPreferences = getSharedPreferences("config",MODE_PRIVATE);
        if(!isSetUp()){
            //如果没有进入过设置向导，则进入
            startSetUp1Activity();
        }
        initView();
    }

    private boolean isSetUp(){
        return mSharedPreferences.getBoolean("isSetUp",false);
    }
    /**初始化控件*/
    private void initView(){
        TextView mTitleTV = (TextView) findViewById(R.id.tv_title);
        mTitleTV.setText("手机防盗");
        ImageView mLeftImgv = (ImageView) findViewById(R.id.imgv_leftbtn);
        mLeftImgv.setOnClickListener(this);
        mLeftImgv.setImageResource(R.drawable.back);
        findViewById(R.id.rl_titlebar).setBackgroundColor(
                getResources().getColor(R.color.purple));
        mSafePhoneTv = (TextView) findViewById(R.id.tv_safephone);
        mSafePhoneTv.setText(mSharedPreferences.getString("safephone",""));
        mToggleButton = (ToggleButton) findViewById(R.id.togglebtn_lostfind);
        mInterSetupRL = (RelativeLayout) findViewById(R.id.rl_inter_setup_wizard);
        mInterSetupRL.setOnClickListener(this);
        mProtectStatusTv = (TextView) findViewById(R.id.tv_lostfind_protectstauts);
        //查询手机防盗是否开启，默认为开启
        boolean protecting = mSharedPreferences.getBoolean("protecting",true);
        if(protecting){
            mProtectStatusTv.setText("防盗保护已经开启");
            mToggleButton.setChecked(true);
        }else{
            mProtectStatusTv.setText("防盗保护没有开启");
            mToggleButton.setChecked(false);
        }
        mToggleButton.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked){
                if(isChecked){
                    mProtectStatusTv.setText("防盗保护已经开启");
                }else{
                    mProtectStatusTv.setText("防盗保护没有开启");
                }
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putBoolean("protecting",isChecked);
                editor.commit();
            }
        });
    }
    private void startSetUp1Activity(){
      Intent intent = new Intent(LostFindActivity.this,Setup1Activity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_inter_setup_wizard:
                //重新进入设置向导
                startSetUp1Activity();
                break;
            case R.id.imgv_leftbtn:
                //重新进入设置向导
                finish();
                break;
        }
    }
}
