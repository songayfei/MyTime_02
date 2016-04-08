package com.atguigu.mytime.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/3/25.
 */
public class SpUtils {
    public static final String GUIDE ="guide";//标识是否进入主页面
    /*public static final String URL = URLPath.WEB_SERVICE_URL;
    public static final String READ_ARRAY_ID = "read_news_id_array_key";//保存点击过的item的id
    public static final String PHOTOS_IMAGE = URLPath.PHOTO_IMAGE_URL;*/
    private static SpUtils spUtils;
    private static SharedPreferences sp;
    public SpUtils() {

    }
    /*
    静态创建对象的方法
     */
    public static  SpUtils getInitialize(Context context){
        if(spUtils==null){
            //设置保存数据的存储发方式和文件名称
            sp=context.getSharedPreferences("beijinnews",Context.MODE_PRIVATE);
            spUtils=new SpUtils();
        }
        return spUtils;
    }
    /**
     * 保存数据的方法(根据不同的参数类型 保存不同的数据)
     */
    public boolean save(String key,Object value){
        if(value instanceof String){
            sp.edit().putString(key, (String) value).commit();
        }else if(value instanceof Integer){
            sp.edit().putInt(key, (Integer) value).commit();
        }else if(value instanceof Boolean){
            sp.edit().putBoolean(key, (Boolean) value).commit();
        }else if(value instanceof Double){
            sp.edit().putFloat(key, (Float) value).commit();
        }
        return false;
    }
    /**
     * 读取保存的数据
     */
    public <T> T getValue(String key,Object value ){
        T t=null;
        if(value instanceof String ||value==null){//若value为空 就返回String类型
            t= (T) sp.getString(key, (String) value);
        }else if(value instanceof Integer){
           Integer v=sp.getInt(key, (Integer) value);//必须把结果转换为对象类型
            t= (T) v;
        }else if(value instanceof Double){
            Float v=sp.getFloat(key, (Float) value);
            t= (T) v;
        }else if(value instanceof Boolean){
            Boolean v=sp.getBoolean(key, (Boolean) value);
            t= (T) v;
        }
        return  t;
    }

    /**
     * 删除数据
     */
    public void delete(String key){
        sp.edit().remove(key).commit();
    }
}
