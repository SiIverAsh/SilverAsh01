package com.app.android.homestay.adapter;

import android.graphics.Paint;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.app.android.homestay.GlideEngine;
import com.app.android.homestay.R;
import com.app.android.homestay.bean.HouseInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
//用于管理员房屋列表的适配器
public class AdminHouseListAdapter extends BaseQuickAdapter<HouseInfo, BaseViewHolder> {
    //设置适配器的布局资源文件为 admin_feed_item。
    public AdminHouseListAdapter() {
        super(R.layout.admin_house_item);
    }

    private TextView original_price;
//，根据 feedBackInfo 对象的数据，使用 holder 对象设置对应视图项的文本内容
    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, HouseInfo houseInfo) {
        baseViewHolder.setText(R.id.title, houseInfo.getIntroduce());
        baseViewHolder.setText(R.id.discount_price, "￥" + houseInfo.getDiscount_price());
        original_price = baseViewHolder.getView(R.id.original_price);
        original_price.setText("原价" + houseInfo.getOriginal_price());
        original_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        original_price.getPaint().setAntiAlias(true);
        //
        baseViewHolder.setText(R.id.address, houseInfo.getAddress());
        baseViewHolder.setText(R.id.create_time, "发布时间：" + houseInfo.getCreate_time());
        GlideEngine.createGlideEngine().loadImage(getContext().getApplicationContext(), houseInfo.getHouse_img(), baseViewHolder.getView(R.id.house_img));

    }
}
