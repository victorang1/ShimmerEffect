package com.example.victor_pc.shimmereffect;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.victor_pc.shimmereffect.Database.DatabaseOpenHelper;
import com.example.victor_pc.shimmereffect.databinding.ActivityInsertBinding;
import com.example.victor_pc.shimmereffect.model.Item;

public class InsertActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityInsertBinding activityInsertBinding;
    private ColorSingelton colorSingelton;
    private String type;
    private String name;
    private String pekerjaan;
    private DatabaseOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInsertBinding = DataBindingUtil.setContentView(this, R.layout.activity_insert);
        colorSingelton = ColorSingelton.getInstance();
        db = new DatabaseOpenHelper(this);
        initListener();
        initType();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(getCurrentFocus() != null){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    private void initType() {
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        if(type.equals("insert")) {
            activityInsertBinding.setItem(new Item());
        } else if(type.equals("update")) {
            name = intent.getStringExtra("name");
            pekerjaan = intent.getStringExtra("pekerjaan");
            activityInsertBinding.etNama.setText(name);
            activityInsertBinding.etPekerjaan.setText(pekerjaan);
            activityInsertBinding.btnInsert.setText("Update");
            activityInsertBinding.title.setText("Update");
            Item item = new Item(name, pekerjaan, null, null);
            activityInsertBinding.setItem(item);
        }
    }

    private void initListener() {
        activityInsertBinding.btnInsert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == activityInsertBinding.btnInsert) {
            if(type.equals("insert")) {
                insertValidation();
            } else if(type.equals("update")) {
                int position = getIntent().getIntExtra("position", 0);
                updateValidation(position);
            }
        }
    }

    private void insertValidation() {
        Item item = activityInsertBinding.getItem();
        if(item.getName() == null || item.getName().equals("")){
            errorMessageNamaKosong();
        } else if(item.getPekerjaan() == null || item.getPekerjaan().equals("")) {
            errorMessagePekerjaaanKosong();
        }else {
            db.insertToDb(item);
            Toast.makeText(this, "Insert Success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
//            MainActivity.mainListItem.add(new Item(item.getName(), item.getPekerjaan(), "Male", colorSingelton.generateGradientDrawable()));
        }
    }

    private void updateValidation(int position) {
        Item item = activityInsertBinding.getItem();
        if(item.getName() == null || item.getName().equals("")){
            errorMessageNamaKosong();
        } else if(item.getPekerjaan() == null || item.getPekerjaan().equals("")) {
            errorMessagePekerjaaanKosong();
        }else {
            db.updateDb(item, position+1);
            Toast.makeText(this, "Update Success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
//            MainActivity.mainListItem.set(position, new Item(item.getName(), item.getPekerjaan(), "Male", colorSingelton.generateGradientDrawable()));
        }
    }

    private void errorMessageNamaKosong() {
        activityInsertBinding.errorMessage.setVisibility(View.VISIBLE);
        activityInsertBinding.errorMessage.setText("Nama tidak boleh kosong!");
        activityInsertBinding.errorMessage.postDelayed(new Runnable() {
            @Override
            public void run() {
                activityInsertBinding.errorMessage.setVisibility(View.GONE);
            }
        }, 2000);
    }

    private void errorMessagePekerjaaanKosong() {
        activityInsertBinding.errorMessage.setVisibility(View.VISIBLE);
        activityInsertBinding.errorMessage.setText("Pekerjaan tidak boleh kosong!");
        activityInsertBinding.errorMessage.postDelayed(new Runnable() {
            @Override
            public void run() {
                activityInsertBinding.errorMessage.setVisibility(View.GONE);
            }
        }, 2000);
    }
}
