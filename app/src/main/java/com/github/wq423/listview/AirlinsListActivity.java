package com.github.wq423.listview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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


    private String[] mAirlines = {"中国国际航空公司 ca", "中国南方航空公司 cz",
            "中国东方航空公司 mu", "中国海南航空公司 hu", "中国山东航空公司 SC",
            "中国四川航空公司 3U", "中国厦门航空公司 MF","中国深圳航空公司 ZH",
            "中国吉祥航空公司 HO","中国河北航空公司 NS", "中国祥鹏航空公司 8L",
            "中国奥凯航空公司 BK", "中国上海航空公司 FM","中国春秋航空公司 9C"};
    private static final String TAG = "AirlinsListActivity";

    private List<AirlinesBO> mAirlinesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lv_demo02);

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

        AirlinesAdapter adapter = new AirlinesAdapter(
                AirlinsListActivity.this,
                R.layout.airlines_info_item,
                mAirlinesList);

        ListView lv = findViewById(R.id.id_lv_demo02);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AirlinesBO airlinesBo = mAirlinesList.get(position);
                Log.d(TAG, "onItemClick: "+ airlinesBo.getName() + " "+airlinesBo.getCode());
            }
        });

    }
}
