package com.example.mymemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataSingleton {
	private static DataSingleton instance = null;
	private DataSingleton(Context ct){
		SetContext(ct);
		Init();
//		Log.i("abc","init");
	}
	//定数
	public static final int DATATYPE_TITLE = 0;
	public static final int DATATYPE_MAIN = 1;
    public static final int DATATYPE_DATE = 2;
	
	private List<String> title_list;
	private List<String> main_list;
	private List<String> date_list;

	private Context main_c;
	
	private int data_size;
	
	public static DataSingleton getInstance(Context c){
		if(instance == null){
			instance = new DataSingleton(c);
		}
		return instance;
	}
	public void Init(){
		SharedPreferences save_pre = PreferenceManager.getDefaultSharedPreferences(main_c);
		
		if(!save_pre.getString("isfirst", "").equals("not")){
			Log.i("abc","初回起動");
			Log.i("abc","isfirst"+save_pre.getString("isfirst", ""));
			//初期のみ
			title_list=new ArrayList<String>(Arrays.asList("太陽","月","星","節制"));
			main_list=new ArrayList<String>(Arrays.asList("それはまぶしすぎる","疑心","たったひとつの真実を求めて","金"));
			date_list= new ArrayList<String>(Arrays.asList("2015年12月31日","2015年12月31日","2015年12月31日","2015年12月31日","2015年12月31日"));

			SaveData();
			SetDataSize();
			
			Editor editor = save_pre.edit();
			editor.putString("isfirst","not");
			editor.apply();
		}
		else{
			Log.i("abc","すでに起動してますね");
			//data_size = 4;
			if(GetDataSize() != -1){
				title_list = new ArrayList<String>();
				main_list = new ArrayList<String>();
                date_list = new ArrayList<String>();
				for(int i = 0;i<GetDataSize();i++){
					title_list.add(save_pre.getString("title"+i, ""));
					main_list.add(save_pre.getString("main"+i, ""));
                    date_list.add(save_pre.getString("date"+i,""));
				}
			}
			else {
				Log.i("abc","取得できなかったので初回起動を行います");
				Editor editor = save_pre.edit();
				editor.putString("isfirst","first");
				editor.apply();
				Init();
			}
		}
		
	}
	public void SetContext(Context c){
		main_c = c;
	}
	public String[] getData(int type){
		String[] data = null;
		int index = 0;
		switch(type){
		case DATATYPE_TITLE:
			//data = new String[]{"太陽","月","星"};
			data = new String[title_list.size()];
			for (String str_data : title_list){
				data[index] = str_data;
				index++;
			}
			break;
		case DATATYPE_MAIN:
			//data = new String[]{"それはまぶしすぎる","疑心","たったひとつの真実を求めて","金"};
			data = new String[main_list.size()];
			for (String str_data : main_list){
				data[index] = str_data;
				index++;
			}
			break;

        case DATATYPE_DATE:
            data = new String[date_list.size()];
            for(String str_data : date_list){
                data[index] = str_data;
                index++;
            }
            break;
		}
	     return data;
	}
	public boolean AddData(String title_str,String main_str){
		if(title_str.equals("")){
			return false;
		}
		else{
			title_list.add(title_str);
			main_list.add(main_str);
            //時間取得
            date_list.add(GetDateTime());
			return true;
		}
		//Log.i(title_str,main_str);
		//Log.i(title_str,title_list.get(4));
	}
	public boolean EditData(String title_str,String main_str,int pos){
		if(title_str.equals("")){
			return false;
		}
		else{
			title_list.set(pos,title_str);
			main_list.set(pos, main_str);
            date_list.set(pos,GetDateTime());
			return true;
		}
	}
	public void DeleteData(int position){
		title_list.remove(position);
		main_list.remove(position);
        date_list.remove(position);
	}
	
	public void SetDataSize(){
		data_size = title_list.size();
		SharedPreferences save_pre = PreferenceManager.getDefaultSharedPreferences(main_c);
		Editor editor = save_pre.edit();
		editor.putInt("dataSize",data_size);
		editor.apply();
	}
	public int GetDataSize(){
		SharedPreferences save_pre = PreferenceManager.getDefaultSharedPreferences(main_c);
		data_size = save_pre.getInt("dataSize", -1);
		return data_size;
	}
	
	public void SaveData(){
		SharedPreferences save_pre = PreferenceManager.getDefaultSharedPreferences(main_c);
		Editor editor = save_pre.edit();
		for(int i = 0;i<title_list.size();i++){
			editor.putString("title"+i, title_list.get(i));
			Log.i("abc",title_list.get(i)+"を保存しました。title");
		}
		for(int i = 0;i<main_list.size();i++){
			editor.putString("main"+i, main_list.get(i));
			Log.i("abc",main_list.get(i)+"を保存しました。main");
		}
        for(int i = 0;i<date_list.size();i++){
            editor.putString("date"+i, date_list.get(i));
            Log.i("abc",date_list.get(i)+"を保存しました。date");
        }
		SetDataSize();
		editor.apply();
	}

    public String GetDateTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy'年'MM'月'dd'日'　kk'時'mm'分'ss'秒'");
        return  sdf.format(date);
    }
}
