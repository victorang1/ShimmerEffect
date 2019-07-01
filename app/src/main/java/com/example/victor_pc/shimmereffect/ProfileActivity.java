package com.example.victor_pc.shimmereffect;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.victor_pc.shimmereffect.databinding.ActivityProfileBinding;
import com.example.victor_pc.shimmereffect.model.Item;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityProfileBinding profileBinding;
    ColorSingelton colorSingelton;
    private static String name;
    private static String pekerjaan;
    private static String gender;
    private static int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        initListener();
    }

    private void initListener() {
        profileBinding.btnUpdate.setOnClickListener(this);
    }

    private void initData(){
        colorSingelton = ColorSingelton.getInstance();
        Intent intent = getIntent();
//        Item item = (Item) intent.getSerializableExtra("item");
        name = intent.getStringExtra("name");
        pekerjaan = intent.getStringExtra("pekerjaan");
        gender = intent.getStringExtra("gender");
        position = intent.getIntExtra("position", 0);
//
        Item item = new Item(name, pekerjaan, gender, colorSingelton.generateGradientDrawable());
        profileBinding.setItem(item);
    }

    private void initDelay(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goneGif();
                initData();
            }
        }, 1000);
    }

    private void goneGif(){
        profileBinding.rlGif.setVisibility(View.GONE);
        profileBinding.profile.setVisibility(View.VISIBLE);
    }

    private void showGif(){
        profileBinding.rlGif.setVisibility(View.VISIBLE);
        profileBinding.profile.setVisibility(View.GONE);
    }

    private void gotoUpdate() {
        Intent intent = new Intent(this, InsertActivity.class);
        intent.putExtra("type", "update");
        intent.putExtra("name", name);
        intent.putExtra("pekerjaan", pekerjaan);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if(v == profileBinding.btnUpdate) {
            gotoUpdate();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showGif();
        initDelay();
    }
}
