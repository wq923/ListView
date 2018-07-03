package com.github.wq423.listview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.github.wq423.listview.adapter.AirlinesAdapter;
import com.github.wq423.listview.bo.AirlinesBO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13521838583@163.com on 2018-6-30.
 *
 */

public class AirlinsListActivity extends Activity{

    private static final String TAG = "AirlinsListActivity";

    private List<AirlinesBO> mAirlinesList = new ArrayList<>();

    private ListView lv;
    private AirlinesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lv_demo02);

        //1、初始化数据
        // ctrl + d 复制一行
        AirlinesBO bo1 = new AirlinesBO("中国国际航空公司", "CA", R.drawable.ca);
        AirlinesBO bo2 = new AirlinesBO("中国东方航空公司", "MU", R.drawable.mu);
        AirlinesBO bo3 = new AirlinesBO("中国南方航空公司", "CZ", R.drawable.cz);
        AirlinesBO bo4 = new AirlinesBO("中国海南航空公司", "HU", R.drawable.hu);

        for (int i = 0; i <= 40; i++) {
            mAirlinesList.add(bo1);
            mAirlinesList.add(bo2);
            mAirlinesList.add(bo3);
            mAirlinesList.add(bo4);
        }

        //2、将数据和布局传入适配器
        adapter = new AirlinesAdapter(
                AirlinsListActivity.this,
                R.layout.airlines_info_item,
                mAirlinesList);

        lv = findViewById(R.id.id_lv_demo02);
        ImageView img = findViewById(R.id.id_default_listview);
        lv.setEmptyView(img);

        lv.setAdapter(adapter);

        //3、设置监听事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AirlinesBO airlinesBo = mAirlinesList.get(position);
                Log.d(TAG, "onItemClick: "+ airlinesBo.getName() + " "+airlinesBo.getCode());
            }
        });

        //4、触摸滑动监听事件
        lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        //5、滚动监听事件
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                    {
                        Log.d(TAG, "onScrollStateChanged: " + "停止滚动");
                        break;
                    }
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                    {
                        Log.d(TAG, "onScrollStateChanged: 正在滚动");
                        break;
                    }
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                    {
                        Log.d(TAG, "onScrollStateChanged: 惯性滚动");
                        break;
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //Log.d(TAG, "onScroll: 不停的滚动");
            }
        });
    }

    @Override
    public void onBackPressed() {
        AirlinesBO abo = new AirlinesBO("中国国际航空公司", "CA", R.drawable.ca);
        mAirlinesList.add(0, abo);      //将数据添加到 ListView 第一个位置
        adapter.notifyDataSetChanged();
//        lv.setSelection(mAirlinesList.size() - 1);

//        lv.smoothScrollBy(10, 8000);
//        lv.smoothScrollByOffset(10);
//        lv.smoothScrollToPosition(mAirlinesList.size() - 1);
        lv.smoothScrollToPosition(0);       //滚动到第一个位置
    }
}
