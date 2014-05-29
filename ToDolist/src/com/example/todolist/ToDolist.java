package com.example.todolist;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ToDolist extends Activity
{
	int MAX=100;
	String[] todolist = new String[MAX]; // null 로 초기화 되서 문제 
  
	private ArrayAdapter<String> myAdapter;
	private ListView list;
	int i=0;	
	/** Called when the activity is first created. */

	@SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        for(int i=0; i<MAX; i++)
        	todolist[i]="";
        final EditText editText = (EditText) findViewById(R.id.edit);
    //    InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
    //   inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
   /* */    
        editText.setOnKeyListener(new OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
            	Log.e("onKey",""+event.getAction());
            	
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                	Log.e("KeyEvent.ACTION_DOWN","KeyEvent.ACTION_DOWN");
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_ENTER:
                        		Log.e("Keycode_ender","Keycode_ender");
                        		todolist[i]= editText.getText().toString();
                        		i++;
                        		list.setAdapter(myAdapter);
                            return true;
                        default:
                            break;
                    }
                } else{
                	Log.e("KeyEvent.ACTION_else",""+keyCode);
                }
                
                return false;
            }
		
        });
       
        /**/
        myAdapter = new ArrayAdapter<String>(this, 
        		android.R.layout.simple_list_item_single_choice, 
        		todolist);
        
        list= (ListView)findViewById(R.id.listview);
        list.setAdapter(myAdapter);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);    
    
    }
	/* @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		Log.e("KeyEvent.ACTION_DOWN","KeyEvent.ACTION_DOWN");
		return super.onKeyDown(keyCode, event);
	} */
	

}
