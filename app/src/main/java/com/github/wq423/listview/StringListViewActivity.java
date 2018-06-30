package com.github.wq423.listview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by 13521838583@163.com on 2018-6-30.
 *
 */

public class StringListViewActivity extends Activity{


    private String[] mAirlines = {"中国国际航空公司 CA", "中国南方航空公司 CZ",
            "中国东方航空公司 MU", "中国海南航空公司 HU", "中国山东航空公司 SC",
            "中国四川航空公司 3U", "中国厦门航空公司 MF","中国深圳航空公司 ZH",
            "中国吉祥航空公司 HO","中国河北航空公司 NS", "中国祥鹏航空公司 8L",
            "中国奥凯航空公司 BK", "中国上海航空公司 FM","中国春秋航空公司 9C"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lv_demo01);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                StringListViewActivity.this,
                android.R.layout.simple_list_item_1,
                mAirlines);

        ((ListView)findViewById(R.id.id_lv_demo01)).setAdapter(adapter);

    }
}
