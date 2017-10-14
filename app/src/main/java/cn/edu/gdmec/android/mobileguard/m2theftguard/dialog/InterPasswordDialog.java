package cn.edu.gdmec.android.mobileguard.m2theftguard.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.edu.gdmec.android.mobileguard.R;

/**
 * Created by Z_er on 2017/10/13.
 */

public class InterPasswordDialog extends Dialog implements View.OnClickListener {
    /**对话框标题**/
    private TextView mTitleTV;
    /**输入密码文本框**/
    private EditText mInterET;
    /**确认按钮**/
    private Button mOKBtn;
    /**取消按钮**/
    private Button mCancelBtn;
    /**回调接口**/
    private MyCallBack myCallBack;
    private Context context;
    public InterPasswordDialog(@NonNull Context context){
        super(context, R.style.dialog_custom);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inter_password_dialog);
        initView();
    }
    public void initView(){
        mTitleTV = (TextView) findViewById(R.id.tv_interpwd_title);
        mInterET = (EditText) findViewById(R.id.et_inter_password);
        mOKBtn = (Button) findViewById(R.id.btn_comfirm);
        mCancelBtn = (Button) findViewById(R.id.btn_dismiss);
        mOKBtn.setOnClickListener(this);
        mCancelBtn.setOnClickListener(this);
    }

    /**
     * 设置对话框标题
     * @param title
     */
    public void setTitle(String title){
        if (!TextUtils.isEmpty(title)){
            mTitleTV.setText(title);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_comfirm:
                myCallBack.confirm();
                break;
            case R.id.btn_dismiss:
                myCallBack.cancel();
                break;
        }
    }
    public String getPassword(){
        return mInterET.getText().toString();
    }
    public void setCallBack(MyCallBack myCallBack){
        this.myCallBack = myCallBack;
    }
    /**
     * 回调接口
     * @author admin
     */
    public interface MyCallBack{
        void confirm();
        void cancel();
    }
}
