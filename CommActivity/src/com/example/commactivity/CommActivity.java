package com.example.commactivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class CommActivity extends Activity
{
	TextView mText;
	final static int ACT_EDIT = 0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commactivity);
        mText = (TextView)findViewById(R.id.text);
        
        Button btnEdit = (Button)findViewById(R.id.edit);
        btnEdit.setOnClickListener(new Button.OnClickListener() {
        		public void onClick(View v) {
        			Intent intent = new Intent(CommActivity.this, ActEdit.class);
        			intent.putExtra("TextIn", mText.getText().toString());
        			startActivityForResult(intent,ACT_EDIT);
        		}
        });
    }
    
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
    		switch(requestCode) {
    		case ACT_EDIT:
    			if(resultCode == RESULT_OK) {
    				mText.setText(data.getStringExtra("TextOut"));
    			}
    			break;
    		}
    	
    }
}
