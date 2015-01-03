package com.example.mymemo;

import android.app.Activity;
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
	private DataSingleton dataS;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input_new);
		 dataS = DataSingleton.getInstance();
		
		 ImageButton return_button = (ImageButton)findViewById(R.id.imageButton1);
		 return_button.setOnClickListener(this);
		 Button do_button = (Button)findViewById(R.id.button1);
		 do_button.setOnClickListener(this);
		 
		 edit_title = (EditText)findViewById(R.id.editText1);
		 edit_main = (EditText)findViewById(R.id.editText2);
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
			isAdd = dataS.AddData(edit_title.getText().toString(), edit_main.getText().toString());
			if(!isAdd){
				Toast.makeText(getApplicationContext(), "タイトルを入力してください", Toast.LENGTH_LONG).show();
			}
			else{
				finish();
			}
			break;
		}
	}
}
