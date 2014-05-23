package com.example.subactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subactivity);
        
        Button btnClose = (Button)findViewById(R.id.close);
        btnClose.setOnClickListener(new Button.OnClickListener() {
        	public void onClick(View  v) {
        		finish();
        	}
        });
        
       
    }
}
