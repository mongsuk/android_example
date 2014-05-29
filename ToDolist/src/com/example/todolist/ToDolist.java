package com.example.todolist;


import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class ToDolist extends Activity
{
	ArrayList<String> todolist = new ArrayList<String>(); // null 로 초기화 되서 문제 
	EditText editText;
	private ArrayAdapter<String> myAdapter;
	private ListView list;
	int i=0;
	boolean check= false;
	/** Called when the activity is first created. */


	@SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState)
    {	       
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
  	  myAdapter = new ArrayAdapter<String>(this, 
      		android.R.layout.simple_list_item_single_choice, 
      		todolist);
      
      list= (ListView)findViewById(R.id.listview);
      list.setAdapter(myAdapter);
      list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
      editText = (EditText) findViewById(R.id.edit);
      editText.setInputType(0);
  
      
   //  InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
  //  imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);

     
      /*
         InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
    inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
     */     
        editText.setOnEditorActionListener(new OnEditorActionListener(){

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				 if(actionId==EditorInfo.IME_ACTION_DONE){
					 Log.e("IME_ACTION_DONE","IME_ACTION_DONE");
					 todolist.add(0, editText.getText().toString());
					 editText.setText(R.string.add_a_new_task);
					 editText.setInputType(0);
					 check=false;
				 }
				return false;
			}
        	
        });
        
        editText.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Log.e("OnTouchListener","OnTouchListener");
				editText.setInputType(1);
				
				if(check == false){
				editText.setText("");
				check = true;
				}
				return false;
			}
    });
    }
}
