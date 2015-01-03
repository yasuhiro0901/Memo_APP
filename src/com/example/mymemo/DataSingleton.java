package com.example.mymemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class DataSingleton {
	private static DataSingleton instance = null;
	private DataSingleton(){
		Init();
		Log.i("abc","init");
	}
	//定数
	public static final int DATATYPE_TITLE = 0;
	public static final int DATATYPE_MAIN = 1;
	
	private List<String> title_list;
	private List<String> main_list;
	
	private Context main_c;
	
	public static DataSingleton getInstance(){
		if(instance == null){
			instance = new DataSingleton();
		}
		return instance;
	}
	public void Init(){
		//初期のみ
		title_list=new ArrayList<String>(Arrays.asList("太陽","月","星","節制"));
		main_list=new ArrayList<String>(Arrays.asList("それはまぶしすぎる","疑心","たったひとつの真実を求めて","金"));
		
		//SharedPreferences save_pre = PreferenceManager.getDefaultSharedPreferences(main_c);
		//save_pre.edit().putStringSet("title",title_list);
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
			return true;
		}
		//Log.i(title_str,main_str);
		//Log.i(title_str,title_list.get(4));
	}
	public void DeleteData(int position){
		title_list.remove(position);
		main_list.remove(position);
	}
}
