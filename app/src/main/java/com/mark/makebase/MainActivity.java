package com.mark.makebase;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mark.common.util.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.btn_arouter)
    Button btnArouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tvTips.setText("你好吗！");

        /*1-需要要初始化
        * 2-权限同时需要在配置清单上配置*/
        RxPermissions rxPermissions = new RxPermissions(this);
        tvTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disposable subscribe = rxPermissions.request(Manifest.permission.READ_CONTACTS)
                        .subscribe(granted -> {
                            if (granted) {
                                ToastUtils.showLongToast("获取联系人权限成功！");
                            } else {
                                ToastUtils.showLongToast("获取联系人权限失败！");
                            }

                        });

            }
        });


    }

    @OnClick({R.id.btn_arouter,R.id.btn_okhttp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_arouter:
                /*1-注意记得将组件依赖到主app中
                * 2-每个用的的组件都有必要加入annotationProcessor的依赖
                * 3-可运行*/
                ARouter.getInstance().build("/adv/advAct").navigation();
                break;
            case R.id.btn_okhttp:
                /*OkHttpUtils的简单实例*/
                String url = "http://www.csdn.net/";
                OkHttpUtils.get().url(url).build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                ToastUtils.showLongToast(""+e);
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                ToastUtils.showLongToast(""+response);
                            }
                        });
                break;
            default:
                ToastUtils.showLongToast("无符合类型！！");
                    break;
        }
    }
}
