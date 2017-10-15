package cn.edu.gdmec.android.mobileguard.m2theftguard;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RadioButton;

import cn.edu.gdmec.android.mobileguard.R;

/**
 * Created by Z_er on 2017/10/14.
 */

public class Setup2Activity extends BaseSetupActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_2);
        //设置第2个小圆点的颜色;
        ((RadioButton) findViewById(R.id.rb_second)).setChecked(true);
    }

    @Override
    public void showNext() {
        startActivityAndFinishSelf(Setup3Activity.class);
    }

    @Override
    public void showPre() {
        startActivityAndFinishSelf(Setup1Activity.class);
    }
}
