package com.xgh.mytextsizedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView mTvTopContent;
    private RecyclerView mRvCoupon;
    private CouponAdapter mCouponAdapter;
    private List<String> list = new ArrayList<>();
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mTvTopContent = findViewById(R.id.tv_top_content);
        mRvCoupon = findViewById(R.id.rv_coupon);
        mTvTopContent.setText("1234567890123456");
        list.add("1");
        list.add("123");
        list.add("123456");
        list.add("123456");
        list.add("12345678901234567890");
        list.add("123456");
        list.add("123456");
        list.add("123456");
        list.add("123456");
        list.add("2");
        list.add("1234567890");
        list.add("12345678901234567890");
        list.add("1");

        mRvCoupon.setLayoutManager(new LinearLayoutManager(mContext));
        mCouponAdapter = new CouponAdapter(mContext, list);
        mRvCoupon.setAdapter(mCouponAdapter);
    }
}
