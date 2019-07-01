package com.example.victor_pc.shimmereffect;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;

import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.victor_pc.shimmereffect.Database.DatabaseOpenHelper;
import com.example.victor_pc.shimmereffect.databinding.ActivityMainBinding;
import com.example.victor_pc.shimmereffect.model.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding mBinding;
    public static List<Item> mainListItem = new ArrayList<>();
    public static MyAdapter mAdapter;
//    private MyRunnable myRunnable;
    private ColorSingelton colorSingelton = ColorSingelton.getInstance();
    private int currentItemIndex = 0;
    private boolean isLastItem;
    private DatabaseOpenHelper db = new DatabaseOpenHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        myRunnable = new MyRunnable();
//        new Thread(myRunnable).start();
        initListener();
        initSearchListener();
        initAdapter();
    }

    private void initSearchListener() {
        mBinding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void prepareItemData(List<Item> item) {
        isLastItem = false;
        currentItemIndex = 0;
        mainListItem.clear();
        mAdapter.notifyDataSetChanged();
//        mBinding.etSearch.setText("");
        if(item.size() <= 5){
            if(!item.isEmpty()) {
                for (int i = 0; i < item.size(); i++) {
                    currentItemIndex++;
                    mainListItem.add(item.get(i));
                }
                isLastItem = true;
            }else{
                isLastItem = true;
            }
        } else {
            currentItemIndex = 5;
            for(int i = 0; i < currentItemIndex; i++){
                mainListItem.add(item.get(i));
            }
            isLastItem = false;
        }
        showViewMore(isLastItem);
//        if(mainListItem.isEmpty()){
//            for (int i = 1; i <= 17; i++){
//                Item item = new Item("Name " + (i), "Pekerjaan " + (i)
//                        , "Gender " + (i), colorSingelton.generateGradientDrawable());
//                mainListItem.add(item);
//            }
//        }
    }

    private void showViewMore(boolean viewMore) {
        if(viewMore) {
            mBinding.layoutShowMore.llShowMore.setVisibility(View.GONE);
        } else {
            mBinding.layoutShowMore.llShowMore.setVisibility(View.VISIBLE);
        }
    }

    private void filter(String nama) {
        ArrayList<Item> filteredList = new ArrayList<>();
        List<Item> dbItemList = db.getFilterList(nama);
        if(dbItemList.size() <= 5) {
            currentItemIndex = filteredList.size();
            isLastItem = true;
        } else {
            isLastItem = false;
        }
        prepareItemData(dbItemList);
        showViewMore(isLastItem);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(getCurrentFocus() != null){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    private void loadItem(String name){
        mainListItem.clear();
        mAdapter.notifyDataSetChanged();
        List<Item> item = db.getFilterList(name);
        if(currentItemIndex+5 < item.size()) {
            currentItemIndex +=5;
            for (int i = 0; i < currentItemIndex; i++){
                mainListItem.add(item.get(i));
            }

        } else {
            for(int i = 0; i < item.size(); i++) {
                currentItemIndex++;
                mainListItem.add(item.get(i));
                isLastItem = true;
            }
        }

        if(isLastItem) {
            mBinding.layoutShowMore.llShowMore.setVisibility(View.GONE);
        }
//        for(int i = 0; i < mainListItem.size(); i++){
//            if(mainListItem.get(i).getPageNumber() <= current_page) {
//                itemList.add(mainListItem.get(i));
//            }else{
//                break;
//            }
//        }
//        if(itemList.size() == max_item) {
//            mBinding.layoutShowMore.llShowMore.setVisibility(View.GONE);
//        }
    }

    private void initListener() {
        mBinding.layoutShowMore.llShowMore.setOnClickListener(this);
        mBinding.fabButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mBinding.layoutShowMore.llShowMore) {
            String name = mBinding.etSearch.getText().toString();
            loadItem(name);
        }
        else if(v == mBinding.fabButton) {
            Intent intent = new Intent(this, InsertActivity.class);
            intent.putExtra("type", "insert");
            startActivity(intent);
        }
    }

    private void initAdapter(){
        OnClick listener = new OnClick() {
            @Override
            public void Click(Item item, int position) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("name", item.getName());
                intent.putExtra("pekerjaan", item.getPekerjaan());
                intent.putExtra("gender", item.getGender());
                intent.putExtra("position", position);
                startActivity(intent);
            }
        };

        mAdapter = new MyAdapter(this, mainListItem, listener);
//        mAdapter = new MyAdapter(this, itemList, new MyAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(Item item) {
//                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
//                intent.putExtra("name", item.getName());
//                intent.putExtra("pekerjaan", item.getPekerjaan());
//                intent.putExtra("gender", item.getGender());
//                startActivity(intent);
//            }
//        });
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setNestedScrollingEnabled(false);
        mBinding.recyclerView.setAdapter(mAdapter);
    }

    private void initData(){
        List<Item> item = db.getItemData();
        prepareItemData(item);
        initAdapter();
        startAnimation();
        Handler mainHandler = new Handler();
        mainHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stopAnimation();
            }
         }, 1000);
    }

//    class MyRunnable implements Runnable {
//
//        @Override
//        public void run() {
//            prepareItemData();
//
//            mBinding.shimmerLayout.startShimmerAnimation();
//            try{
//                Thread.sleep(3000);
//            }catch (Exception ex){
//
//            }
//            mainHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    mBinding.shimmerLayout.stopShimmerAnimation();
//                    mBinding.shimmerLayout.setVisibility(View.GONE);
//                    mBinding.recyclerView.setVisibility(View.VISIBLE);
//                }
//            });
//        }
//    }

    private void startAnimation(){
        mBinding.shimmerLayout.startShimmerAnimation();
        mBinding.shimmerLayout.setVisibility(View.VISIBLE);
        mBinding.llContent.setVisibility(View.GONE);
        mBinding.layoutShowMore.llShowMore.setVisibility(View.GONE);
        mBinding.rlFloatingActionButton.setVisibility(View.GONE);
    }

    private void stopAnimation() {
        mBinding.shimmerLayout.stopShimmerAnimation();
        mBinding.shimmerLayout.setVisibility(View.GONE);
        mBinding.llContent.setVisibility(View.VISIBLE);
        if(db.getItemData().size() <= 5){
            mBinding.layoutShowMore.llShowMore.setVisibility(View.GONE);
        } else {
            mBinding.layoutShowMore.llShowMore.setVisibility(View.VISIBLE);
        }
        mBinding.rlFloatingActionButton.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

//    public void bebas(){
//
//    }

    private void showExitDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to close the applicatoin?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public interface OnClick {
        void Click(Item item, int i);
    }

    @Override
    public void onBackPressed() {
        showExitDialog();
    }
}
