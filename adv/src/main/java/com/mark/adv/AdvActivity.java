package com.mark.adv;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mark.common.base.BaseActivity;

@Route(path = "/adv/advAct")
public class AdvActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getViewLayout() {
        return R.layout.advact_layout;
    }
}
