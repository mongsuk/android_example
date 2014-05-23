package com.example.subactivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class CallActivity extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.callactivity);
		
		Button btnCall = (Button)findViewById(R.id.call);
		btnCall.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v){
				Intent intent = new Intent(CallActivity.this, SubActivity.class);
				startActivity(intent);
			}
		});
	}
}
