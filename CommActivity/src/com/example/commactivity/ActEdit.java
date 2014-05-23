package com.example.commactivity;

import android.app.Activity;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;



public class ActEdit extends Activity{
	EditText mEdit;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actedit);
		
		mEdit = (EditText)findViewById(R.id.stredit);
		
		Intent intent = getIntent();
		mEdit.setText(intent.getStringExtra("TextIn"));
		
		Button btnOK=(Button)findViewById(R.id.ok);
		btnOK.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v){
				Intent intent = new Intent();
				intent.putExtra("TextOut", mEdit.getText().toString());
				setResult(RESULT_OK, intent);
				finish();
			}
		});
		
		Button btnCancel= (Button)findViewById(R.id.cancel);
		btnCancel.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v){
				setResult(RESULT_CANCELED);
				finish();
			}
		});
	}

}
