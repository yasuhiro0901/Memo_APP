package com.example.mymemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	private String[] titleText;
	private String[] mainText;
	private DataSingleton dataS;
	private ListView listView1;
	private SimpleAdapter adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ImageButton add_button = (ImageButton)findViewById(R.id.imageButton1);
        add_button.setOnClickListener(this); 
       
        dataS = DataSingleton.getInstance();
        titleText = dataS.getData(DataSingleton.DATATYPE_TITLE);
        mainText = dataS.getData(DataSingleton.DATATYPE_MAIN);
        
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        for(int i=0; i<titleText.length;i++){
        	Map<String,String> map = new HashMap<String, String>();
        	map.put("title", titleText[i]);
        	map.put("main", mainText[i]);
        	list.add(map);
        }
        
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                list,
                android.R.layout.simple_list_item_2,
                new String[]{"title","main"},
                new int[] {android.R.id.text1,android.R.id.text2});
            //adapter.add("listview item 1");
            //adapter.add("listview item 2");
            //adapter.add("listview item 3");

            // リストビューにデータを設定
            ListView listView1 = (ListView)findViewById(R.id.listView1);
            listView1.setAdapter(adapter);
            
         listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	 public void onItemClick(AdapterView<?> parent,View view,int pos,long id){
        		 ListView listView = (ListView) parent;
        		 Map<?, ?> item = (Map<?, ?>) listView.getItemAtPosition(pos);
        		 Toast.makeText(getApplicationContext(), item.get("title").toString(), Toast.LENGTH_LONG).show();
        	 }
		});
    }
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "＋押されました", Toast.LENGTH_LONG).show();
		
		Intent intent = new Intent(MainActivity.this,InputActivity.class);
		intent.putExtra("AddType", "new");
		startActivity(intent);
	}
}
