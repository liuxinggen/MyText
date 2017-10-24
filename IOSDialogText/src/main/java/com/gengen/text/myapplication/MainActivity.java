package com.gengen.text.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });


    }

    /**
     * 显示DialogFragment
     */
    private void showDialog() {
       IOSDialogFragment dialogFragment=new IOSDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),"IOSDialogFragment");
//        dialogFragment.showAnim();

    }
}
