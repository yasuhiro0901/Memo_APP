package com.example.mymemo;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
//import android.view.Menu;
//import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class InputActivity extends Activity implements OnClickListener{
	private EditText edit_title,edit_main;
	private Button delete_btn;
	private DataSingleton dataS;
	private HashMap<String,String> btn_option_data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input_new);
		 dataS = DataSingleton.getInstance(this);
		
		 ImageButton return_button = (ImageButton)findViewById(R.id.imageButton1);
		 return_button.setOnClickListener(this);
		 Button do_button = (Button)findViewById(R.id.button1);
		 do_button.setOnClickListener(this);
		 delete_btn = (Button)findViewById(R.id.button2);
		 delete_btn.setOnClickListener(this);
		 
		 edit_title = (EditText)findViewById(R.id.editText1);
		 edit_main = (EditText)findViewById(R.id.editText2);
		 //edit_title.setBackgroundColor(Color.WHITE);
		 //edit_main.setBackgroundColor(Color.WHITE);
		 
	}
	
	@Override
    protected void onStart() {
    	super.onStart();
    	
    	//値の受け取り
    	Intent intent = getIntent();
    	btn_option_data = (HashMap<String, String>) intent.getSerializableExtra("AddType");
    	
    	if(btn_option_data.get("doType").equals("new")){
    		delete_btn.setVisibility(View.INVISIBLE);
    	}
    	else if(btn_option_data.get("doType").equals("edit")){
    		edit_title.setText(btn_option_data.get("title_str"));
    		edit_main.setText(btn_option_data.get("main_str"));
    	}
	}
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.imageButton1://戻るボタン
			finish();
			break;
		case R.id.button1:
			boolean isAdd = false;
			if(btn_option_data.get("doType").equals("new")){
				isAdd = dataS.AddData(edit_title.getText().toString(), edit_main.getText().toString());
			}
			else if(btn_option_data.get("doType").equals("edit")){
				isAdd = dataS.EditData(edit_title.getText().toString(), edit_main.getText().toString(), Integer.valueOf(btn_option_data.get("position")));
			}
			
			//エラー
			if(!isAdd){
				Toast.makeText(getApplicationContext(), "タイトルを入力してください", Toast.LENGTH_LONG).show();
			}
			else{
				dataS.SaveData();
				finish();
			}
			break;
		case R.id.button2:
			dataS.DeleteData( Integer.valueOf(btn_option_data.get("position")));
			dataS.SaveData();
			finish();
			break;
		}
	}
}
