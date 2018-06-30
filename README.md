# ListView
    实践 Android APP 开发中的列表控件。
## 1、ListView 简介
    列表控件（不只是 ListView）是 Android 应用开发中最常用的控件，基本所有应用都会使用该控件。
    ListView 是 Android 系统提供的最基础、最经典的列表控件。列表控件常见于QQ聊天记录、各种APP的空间动态等等。
    列表控件的数据来源一般为网络或数据库。


## 2、ListView 实践
### 2.1、 最简单字符串列表页
####（1）数据

    private String[] mAirlines = {"中国国际航空公司 CA", "中国南方航空公司 CZ",
            "中国东方航空公司 MU", "中国海南航空公司 HU", "中国山东航空公司 SC",
            "中国四川航空公司 3U", "中国厦门航空公司 MF","中国深圳航空公司 ZH",
            "中国吉祥航空公司 HO","中国河北航空公司 NS", "中国祥鹏航空公司 8L",
            "中国奥凯航空公司 BK", "中国上海航空公司 FM","中国春秋航空公司 9C"};

####（2）布局
    这里使用 Android 系统自带的最基本的布局资源：android.R.layout.simple_list_item_1.xml
####（3）适配器
    适配器使用 ArrayAdapter<String>。

####（4）结果
![Alt Text](https://github.com/wq923/ListView/blob/master/image/image01.png)