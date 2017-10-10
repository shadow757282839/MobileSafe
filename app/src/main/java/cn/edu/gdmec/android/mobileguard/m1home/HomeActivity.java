package cn.edu.gdmec.android.mobileguard.m1home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import cn.edu.gdmec.android.mobileguard.m1home.adapter.HomeAdapter;


public class HomeActivity extends AppCompatActivity {
    private GridView gv_home;
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(cn.edu.gdmec.android.mobileguard.R.layout.activity_home);
        getSupportActionBar().hide();
        gv_home = (GridView) findViewById(cn.edu.gdmec.android.mobileguard.R.id.gv_home);
        gv_home.setAdapter(new HomeAdapter(HomeActivity.this));
        gv_home.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    //有空再写
                }
            }
        });
    }
    @Override
public boolean onKeyDown(int keyCode, KeyEvent event){
    if(keyCode==KeyEvent.KEYCODE_BACK){
        if((System.currentTimeMillis()-mExitTime)<2000){
            System.exit(0);
        }else{
            Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_LONG).show();
            mExitTime = System.currentTimeMillis();
        }
        return true;
    }
    return super.onKeyDown(keyCode,event);
    }
}
