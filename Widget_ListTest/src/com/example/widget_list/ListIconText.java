package com.example.widget_list;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

@SuppressLint("NewApi")
public class ListIconText extends Activity {
	
	ArrayList<MyItem> arItem;
	ListView MyList;
	MyListAdapter MyAdapter;
	EditText editText;
	boolean m_edit_onTouchListener_check;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listtest); 

		arItem = new ArrayList<MyItem>(); 
		editText = (EditText) findViewById(R.id.edit);		

		editText.setOnTouchListener(onTouchListener);
		editText.setOnEditorActionListener(onEditorActionListener); 
		editText.setInputType(InputType.TYPE_NULL);

		MyAdapter = new MyListAdapter(ListIconText.this, R.layout.icontext, arItem);		

		MyList = (ListView) findViewById(R.id.list2);

		MyList.setAdapter(MyAdapter);
		
		MyList.setOnItemClickListener(OnItemClickListener);
	}

	View.OnTouchListener onTouchListener = new View.OnTouchListener() {
	
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			editText.setInputType(InputType.TYPE_CLASS_TEXT);
			if (m_edit_onTouchListener_check == false) {
				editText.setText("");
				m_edit_onTouchListener_check = true;			
			}
			return false;			
		}
	};

	TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() { 	
		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			// TODO Auto-generated method stub
			String EditString = editText.getText().toString();
			
			if(EditString.length() == 0){
				Toast.makeText(ListIconText.this, R.string.warring, Toast.LENGTH_SHORT).show();
				return true;
			}
			
			MyItem mi = new MyItem(EditString);			
			arItem.add(0, mi);
			editText.setText(R.string.add_a_new_task);
			editText.setInputType(InputType.TYPE_NULL);
			m_edit_onTouchListener_check = false;			
			update();

			return false;			
		}
	};

	AdapterView.OnItemClickListener OnItemClickListener = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			MyItem Item = arItem.get(position);
			Item.ChangeStatus();			
			update();
		}
	};
	
	public void update() {
		//MyList.clearChoices();
		MyAdapter.notifyDataSetChanged();
	}
}


