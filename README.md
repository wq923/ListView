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
### 2.3、ListView 优化
（1）性能优化

    由前面 2.1、2.2 实践可见，ListView 主要涉及数据、布局、适配器三个方面。

    首先，2.2 中实现的适配器代码运行效率较低，因为在 getView() 方法中，每
    次都将布局重新加载一遍，且每次都通过 findViewById() 重复获取控件。

    其中，
        (1)、重复调用 LayoutInflater 加载布局问题 ———> 通过 convertView 解决。
        (2)、重复调用 findViewById 获取控件的实例问题 ——> 通过 ViewHolder 解决。
        convertView 参数用于将之前加载好的布局进行缓存，以便之后进行重用。
        ViewHolder 内部类用于对控件进行缓存，当 converView 为null时，创建 ViewHoler
        对象，将控件实例存放到 ViewHolder 里，然后调动 View 的 setTag 方法，将 ViewHolder
        中，当 convertView 不为 null 时，通过getTag方法，取出ViewHolder。

        //AirlinesAdapter
        package com.github.wq423.listview.adapter;

        import android.content.Context;
        import android.support.annotation.NonNull;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;
        import com.github.wq423.listview.R;
        import com.github.wq423.listview.bo.AirlinesBO;
        import java.util.List;

        /**
         * Created by 13521838583@163.com on 2018-6-30.
         *
         */

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
                View view;
                ViewHolder viewHolder;

                if (convertView == null) {
                    view = LayoutInflater.from(getContext()).inflate(mResuldId, parent, false);
                    viewHolder = new ViewHolder();
                    viewHolder.airlinesName = view.findViewById(R.id.id_airlines_name);
                    viewHolder.airlinesCode = view.findViewById(R.id.id_airlines_code);
                    viewHolder.airlinesImg = view.findViewById(R.id.id_airlines_image);
                    view.setTag(viewHolder);    //将viewHolder存储在view中

                } else {
                    view = convertView;
                    viewHolder = (ViewHolder) view.getTag();
                }

                viewHolder.airlinesName.setText(airlinesBO.getName());
                viewHolder.airlinesImg.setImageResource(airlinesBO.getImageId());
                viewHolder.airlinesCode.setText(airlinesBO.getCode());

                return view;
            }

            private class ViewHolder {
                ImageView airlinesImg;
                TextView airlinesName;
                TextView airlinesCode;
            }
        }

（2）定义 ListView 属性

a、设置分割线

    <ListView
        android:id="@+id/id_lv_demo02"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:divider="@color/colorPrimary"
        android:dividerHeight="4dp">
    </ListView>
![Alt Text](https://github.com/wq923/ListView/blob/master/image/image03.png)
b、隐藏滚动条

    <ListView
        android:id="@+id/id_lv_demo02"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:divider="@color/colorPrimary"
        android:dividerHeight="4dp"
        android:scrollbars="none">
    </ListView>
c、动态修改 ListView

    @Override
        public void onBackPressed() {
            AirlinesBO abo = new AirlinesBO("中国国际航空公司", "CA", R.drawable.ca);
            mAirlinesList.add(abo);
            adapter.notifyDataSetChanged();
            //瞬间移动到指定的 position
            //lv.setSelection(mAirlinesList.size() - 1);

            //缓慢滚动 ListView 到指定的 position
            lv.smoothScrollBy(10, 8000);
            lv.smoothScrollByOffset(10);
            lv.smoothScrollToPosition(mAirlinesList.size() - 1);
        }