# ListView
    实践 Android APP 开发中的列表控件。
## 1、ListView 简介
    列表控件（不只是 ListView）是 Android 应用开发中最常用的控件，基本所有应用都会使用该控件。
    ListView 是 Android 系统提供的最基础、最经典的列表控件。列表控件常见于QQ聊天记录、各种APP的空间动态等等。
    列表控件的数据来源一般为网络或数据库。
## 2、ListView 实践
### 2.1、最简单字符串列表页
#### （1）数据
    private String[] mAirlines = {"中国国际航空公司 CA", "中国南方航空公司 CZ",
            "中国东方航空公司 MU", "中国海南航空公司 HU", "中国山东航空公司 SC",
            "中国四川航空公司 3U", "中国厦门航空公司 MF","中国深圳航空公司 ZH",
            "中国吉祥航空公司 HO","中国河北航空公司 NS", "中国祥鹏航空公司 8L",
            "中国奥凯航空公司 BK", "中国上海航空公司 FM","中国春秋航空公司 9C"};
#### （2）布局
    这里使用 Android 系统自带的最基本的布局资源：android.R.layout.simple_list_item_1.xml
#### （3）适配器
    适配器使用 ArrayAdapter<String>。
#### （4）结果
![Alt Text](https://github.com/wq923/ListView/blob/master/image/image01.png)
### 2.2、简单的航空公司列表页
#### （1）数据
    //AirlinesBO.java
    public class AirlinesBO {

        private String code;    //航空公司二字码
        private String name;    //航空公司全称
        private int imageId;    //航空公司logo图片

        public AirlinesBO(String name, String code, int imageId) {
            this.name = name;
            this.code = code;
            this.imageId = imageId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }
    }

#### （2）布局
    //使用自定义的 item 布局文件：airlines_info_item.xml
    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/activity_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="horizontal"
        tools:context="com.github.wq423.listview.MainActivity">

        <ImageView
            android:id="@+id/id_airlines_image"
            android:layout_margin="5dp"
            android:layout_gravity="center_vertical"
            android:layout_width="50dp"
            android:layout_height="50dp">
        </ImageView>
        <TextView
            android:id="@+id/id_airlines_name"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center_vertical"
            android:layout_marginLeft="16dp"
            android:textSize="16dp"/>
        <TextView
            android:id="@+id/id_airlines_code"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center_vertical"
            android:layout_marginLeft="16dp"
            android:textSize="16dp"/>
    </LinearLayout>

#### （3）适配器
    //使用自定义适配器 AirlinesAdapter。
    public class AirlinesAdapter extends ArrayAdapter<AirlinesBO>{

        private int mResuldId;

        public AirlinesAdapter(Context context, int textViewResId, List<AirlinesBO> obj) {
            super(context, textViewResId, obj);
            mResuldId = textViewResId;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            AirlinesBO airlinesBO = getItem(position);
            View view = LayoutInflater.from(getContext()).inflate(mResuldId, parent, false);

            ImageView airlineImage = view.findViewById(R.id.id_airlines_image);
            TextView airlineName = view.findViewById(R.id.id_airlines_name);
            TextView airlineCode = view.findViewById(R.id.id_airlines_code);

            airlineName.setText(airlinesBO.getName());
            airlineImage.setImageResource(airlinesBO.getImageId());
            airlineCode.setText(airlinesBO.getCode());

            return view;
        }
    }

#### （4）结果
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

        private static final String TAG = "AirlinsListActivity";

        private List<AirlinesBO> mAirlinesList = new ArrayList<>();

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
            AirlinesAdapter adapter = new AirlinesAdapter(
                    AirlinsListActivity.this,
                    R.layout.airlines_info_item,
                    mAirlinesList);

            ListView lv = findViewById(R.id.id_lv_demo02);
            lv.setAdapter(adapter);

            //3、设置监听事件
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    AirlinesBO airlinesBo = mAirlinesList.get(position);
                    Log.d(TAG, "onItemClick: "+ airlinesBo.getName() + " "+airlinesBo.getCode());
                }
            });

        }
    }
![Alt Text](https://github.com/wq923/ListView/blob/master/image/image02.png)
