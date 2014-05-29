package com.example.keytest;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.view.View.OnTouchListener;
@SuppressLint("NewApi")
public class KeyTest extends Activity  
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        EditText emailTxt = (EditText) findViewById(R.id.edit);
        emailTxt.setId(0);
        
        emailTxt.setOnEditorActionListener(new OnEditorActionListener(){

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				 if(actionId==EditorInfo.IME_ACTION_GO){
					 Log.e("IME_ACTION_GO","IME_ACTION_GO");
					 
				 }
				return false;
			}
        	
        });  
  
        
        emailTxt.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Log.e("OnTouchListener","OnTouchListener");
				return false;
			}
    });
    }
}
