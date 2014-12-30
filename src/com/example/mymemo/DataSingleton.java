package com.example.mymemo;

public class DataSingleton {
	private static DataSingleton instance = null;
	private DataSingleton(){}
	//定数
	public static final int DATATYPE_TITLE = 0;
	public static final int DATATYPE_MAIN = 1;
	
	public static DataSingleton getInstance(){
		if(instance == null){
			instance = new DataSingleton();
		}
		return instance;
	}
	public String[] getData(int type){
		String[] data = null;
		switch(type){
		case DATATYPE_TITLE:
			data = new String[]{"太陽","月","星"};
			break;
		case DATATYPE_MAIN:
			data = new String[]{"それはまぶしすぎる","疑心","たったひとつの真実を求めて"};
			break;
		
		}
	     return data;
	}
}
