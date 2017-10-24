package com.gengen.text.myapplication;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class IOSDialogFragment extends DialogFragment {

    private View viewroot;

    private ListView lvMenu;
    private TextView tvTitle;
    private Button btnCancel;

    private boolean isAnimation = false;//判断是否对次点击，防止多次执行

    //
    private List<String> name;
    private ArrayAdapter<String> adapter;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initView(inflater, container);
        showAnim(viewroot);//显示
        initEvent();
        return viewroot;
    }

    private void initEvent() {
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        /**
         * 取消按钮
         */
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogfinish();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initView(LayoutInflater inflater, ViewGroup container) {
        //去除DialogFragment的默认标题头
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        //改变DecorView的背景色，设置为透明
        View decorView = getDialog().getWindow().getDecorView();
        decorView.setBackground(new ColorDrawable(Color.TRANSPARENT));
        decorView.setPadding(10,0,10,20);

        /**
         * 解决点击Dialog外部的空白区域的消失事件
         */
        decorView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    dialogfinish();
                }
                return true;
            }
        });

        viewroot = inflater.inflate(R.layout.layout_ios_dialog, container, false);
        //放在底部,使dialog置于底部
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);
        //展示DialogFragment的标题
//        getDialog().setTitle("我是dialogFragment的标题");

        lvMenu = (ListView) viewroot.findViewById(R.id.lv_menu);
        tvTitle = (TextView) viewroot.findViewById(R.id.tv_title);
        btnCancel = (Button) viewroot.findViewById(R.id.btn_cancel);

        tvTitle.setText("你最帅");

        name = new ArrayList<>();
        name.add("是");
        name.add("不是");
        name.add("待定");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, name);
        lvMenu.setAdapter(adapter);


    }

    /**
     * 弹出动画
     */
    public void showAnim(View view) {
        /**
         * 四个参数代表坐标值
         */
        Animation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f
        );
        animation.setDuration(400);
        animation.setFillAfter(true);
        animation.setFillEnabled(true);
        view.startAnimation(animation);

    }

    /**
     * 关闭动画
     */
    private void dismissAnim(View view) {
        /**
         * 四个参数代表坐标值
         */
        Animation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f
        );
        animation.setDuration(400);
        animation.setFillAfter(true);
        animation.setFillEnabled(true);
        view.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isAnimation = false;//用来判断是否是多次点击
                IOSDialogFragment.this.dismiss();//弹框消失
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    /**
     * 对话框消失
     */
    private void dialogfinish() {

        if (isAnimation) {
            return;
        }
        isAnimation = true;
        dismissAnim(viewroot);


    }

}
