package com.example.widget_list;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ListAddDel extends Activity{
	ArrayList<String> Items; 
	ArrayAdapter<String> Adapter;
	ListView list;
	//EditText ed = (EditText)findViewById(R.id.newitem);
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listadddel);
		
		Items = new ArrayList<String>(); // 문자를 저장할 배열 리스트 생성
		Items.add("First");
		Items.add("Second");
		Items.add("Third");
		
		Adapter = new ArrayAdapter<String>(this, android.R.layout.
				simple_list_item_single_choice, Items); 
		//리스트 뷰에 어떻게 표시할 건지 ArrayAdapter에 setting
		list = (ListView)findViewById(R.id.list);
		list.setAdapter(Adapter);// 화면에 Adapter 내용을 보여준다.
		list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		// listView에 item을 어떻게 선택할 것인지 설정
		list.setOnItemClickListener(mItemClickListener);
		// Register a callback to be invoked when an item in this AdapterView has been clicked
		// AdapterView안에 있은 아이템이 클릭 되었을때 작동이 작동이 되는 콜백을 등록한다.
		// 리스너 등록
		findViewById(R.id.add).setOnClickListener(mClickListener);
		// mClickListener 를 이용하여 add 버튼 등록
		// 리스너 등록
		findViewById(R.id.delete).setOnClickListener(mClickListener);
		// mClickListener 를 이용하여 delete 버튼 등록
		// 리스너 등록
	}

	Button.OnClickListener mClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			EditText ed = (EditText)findViewById(R.id.newitem);//에딧텍스트의 객체 값을 얻어온다.
			switch(v.getId()) {
			case R.id.add:
				String text = ed.getText().toString();//에딧 텍스트의 텍스트 값을 얻어와서 스트링 값으려 변환.
				if(text.length() != 0) {// 텍스트 길이가 0이 아닌지 체크한다.
					Items.add(text);// 텍스트 값을 ArrayList 에 추가한다.
					ed.setText("");//텍스트 문장 초기화
					Adapter.notifyDataSetChanged();// ArrayAdpater에 변화를 알린다.
				}
				break;
			case R.id.delete:
				int id;
				id= list.getCheckedItemPosition(); // 현재 선택된 아이템의 위치를 리턴한다.
				
				if(id != ListView.INVALID_POSITION) {// 리스트 뷰에서 접근가능한 포지션인지 확인한다.!
					// 왜 list로 안했는지..
					Items.remove(id);//ArrayList에서 항목을 지운다.
					list.clearChoices();// 선택한 아이템 인덱스를 초기화 한다. 선택 해제
					// 초기화 안하면 선택한 값이 사라지지 않음.
					Adapter.notifyDataSetChanged();// 어텝터는 여기서 원본을 다시 읽고 원본에
					//맞게 뷰를 생성하여 리스트 뷰로 재공급한다.
				}
				break;
			}			
		}
	};
	
	AdapterView.OnItemClickListener mItemClickListener = new //item을 클릭 시 이 객체가 실행된다.
			AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					String mes;
					
					mes = "Select Item = " + Items.get(position);
					// ArrayList로 item에 해당하는 index의 값을 리턴한다.
					Toast.makeText(ListAddDel.this, mes, Toast.LENGTH_SHORT).show();	
					CheckItem(); 
				}		
	};
	
	private void CheckItem() {
		// TODO Auto-generated method stub
		Log.e("CheckItem","CheckItem");
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.e("onResume","onResume");
	}
	
}
