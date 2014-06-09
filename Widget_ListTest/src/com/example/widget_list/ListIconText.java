package com.example.widget_list;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

@SuppressLint("NewApi")
public class ListIconText extends Activity {
	ArrayList<MyItem> arItem;// MyItem의 ArrayList 선언
	ListView MyList;// 화면에 보여질 리스트
	MyListAdapter MyAdapter;// 화면에 보여질 리스트를 구성하는 부분
	EditText editText;// 에딧 텍스트
	boolean edit_touchcheck;// 에딧 텍스트 화면이 터치 됐는지 확인하는 부분.

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listtest); // view를 세팅한다.

		arItem = new ArrayList<MyItem>(); //  MyItem의 ArrayList 선언 
		editText = (EditText) findViewById(R.id.edit);// layout의 EditText 객체 아이디를 받음
		// MyItem객체를 생성한후 ArrayList에 등록

		editText.setOnTouchListener(editTouch); // editText 터치 리스트 등록
		editText.setOnEditorActionListener(editclick); // editText ActionListener등록
		editText.setInputType(0);// 처음 화면 구성시 editText의 커서를 활성화 하지 않는다. 

		MyAdapter = new MyListAdapter(ListIconText.this, R.layout.icontext, arItem);
		// arItem ArrayList를 R.layout.icontext로 설정한데로 보여지게 설정하는 
		// MyListAdapter 객체 생성

		MyList = (ListView) findViewById(R.id.list2);// ListView 객체를 얻어온다.
		MyList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);//LivstView를 싱글 모드로 설정
		Log.d("before_setAdapter", "before_setAdapter");
		MyList.setAdapter(MyAdapter);// MyAdapter를 MyList에 등록한다. MyListAdapter를 ListView에 등록한다.
		Log.d("_after_setAdapter", "_after_setAdapter");
		MyList.setOnItemClickListener(mItemClickListener);//  MyList의 리스터를 등록 ListView의 리스너를 등록한다.

	}

	View.OnTouchListener editTouch = new View.OnTouchListener() {
		// 에딧텍스트를 클릭했을때 호출된다.

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			editText.setInputType(1);// 에딧 텍스트를 보이게 설정
			if (edit_touchcheck == false) {// edit_touchcheck는 디폴드 값으로 펄스 이기때문에 참!
				editText.setText("");//에딧텍스트의 텍스트를 비운다.
				edit_touchcheck = true;// edit_touchcheck true를 설정 작성중에 
				//에딧 텍스트를 터치 했을 때 텍스트가 비워지면 안되기 때문에
			}
			return false;// 여기에 할일이 없으므로 하위 이벤트 리스너로 이벤트를 내려준다.
			// true로 설정하면 다음 하위 이벤트 리스너로 이벤트를 내려주지 않는다.
		}
	};

	TextView.OnEditorActionListener editclick = new TextView.OnEditorActionListener() { 
		// EditText에서 action을 취했을때 호출된다. 확인을 누리면 호출된다.

		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			// TODO Auto-generated method stub
	
			MyItem mi = new MyItem(R.drawable.gallery_photo_8, editText.getText()
					.toString()); // MyItem 객체 생성.
			arItem.add(0, mi);// MyItem ArrayList의 첫번째에 추가.
			editText.setText(R.string.add_a_new_task);//EditText의 text를 설정한 스트링으로 변환
			editText.setInputType(0);//editText의 커서를 보이지 않게한다.
			edit_touchcheck = false;// edit_touchcheck를 false로 설정하여 
			//EditText 를 터치했을 onTouch함수에서 텍스트를 비울수 있게 한다.
			
			MyAdapter.notifyDataSetChanged();// 이함수를 호출 하여 MyListAdapter의 getView를 다시 호출한다.

			return false;// 할일이 없으므로 하위 이벤트로 이벤트를 넘겨준다.
			//  true로 설정하면 하위 이벤트로 이벤트를 내려주지 않아 자판이 사라지지 않는다.
		}
	};

	AdapterView.OnItemClickListener mItemClickListener = new // ListView 의 item을 클릭 시 실행된다.
	AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub

			arItem.get(position).Complete = arItem.get(position).Complete == true ? false
					: true;
			//  클릭할때 마다 ArrayList의 해당 position MyItem 멤버변수 Complete를 바꿔준다.!

			update();// 업데이트 함수 호출
		}
	};

	public void update() {

		MyList.clearChoices();// List에 선택된 값을 초기화
		MyAdapter.notifyDataSetChanged();// MyAdapter에 변화를 알려서 getView를 호출.

	}

	class MyItem {
		
		int Icon;// 사진 이미지의 아이디를 저장하는 변수
		String Name;// EditText의 text를 저장하는 변수
		boolean Complete;// 스케줄이 완료 되었는지 안됐는지 확인하는 변수 
		
		MyItem(int aIcon, String aName) { 
			// MyItem  생성자 MyItem 생성될때 호출된다.
			Icon = aIcon;
			Name = aName;
		}

		boolean isComplete() { // Complete 변수 리턴
			return Complete;
		}
	}

	class MyListAdapter extends BaseAdapter {
		Context maincon;
		LayoutInflater Inflater;
		ArrayList<MyItem> arSrc;
		int layout;

		public MyListAdapter(Context context, int alayout,
				ArrayList<MyItem> aarSrc) { // MyListAdapter 생성자
			maincon = context;
			Inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //LayoutInflator객체를 받아온다. 
			arSrc = aarSrc; // ArrayList 참조값 저장
			layout = alayout; // layout layout 참조값 저장.
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			Log.d("getCount", "" + "getCount");
			return arSrc.size(); // getCount를 호출한뒤. getItemId 호출
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			Log.d("getItem", "" + position);
			return arSrc.get(position).Name;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			Log.d("getItemId", "" + position); //getItemId를 호출 몇번째인지 확인.
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			
			// position은 생성할 항목의 순서값
			// parent는 생성되는 뷰의 부모, 리스트 뷰
			// convertView는 이전에 생성된 차일드 뷰. 최초 호출시 null이 전달된다.
			
			Log.d("getView", "getView");
			if (convertView == null) {
				
				convertView = Inflater.inflate(layout, parent, false);
				//convertView = Inflater.inflate(layout, null, true); //이렇게 실행 시켜도 동작된다.
				// true로 설정하면 에러 발생!
				// true로 설정할 경우 뷰를 생성할때 자동으로 parent의 자식으로 추가된다.
				// false일 경우 root는 View의  LayoutParam을 생성하는데만 사용된다.
				// layout이 붙여질 상위 뷰가 parent이고 따라서 layout이 붙여질 상위 뷰가 있기 때문에
				//  false로 설정,  true로 설정하면 layout이 붙여질 상위 뷰가 두 개가 되므로 에러 발생.
			}
			ImageView img = (ImageView) convertView.findViewById(R.id.img);
			img.setImageResource(arSrc.get(position).Icon);

			TextView txt = (TextView) convertView.findViewById(R.id.text);
			txt.setText(arSrc.get(position).Name);

			if (arSrc.get(position).isComplete()) {
				txt.setPaintFlags(txt.getPaintFlags()
						| Paint.STRIKE_THRU_TEXT_FLAG);
			} else {
				txt.setPaintFlags(txt.getPaintFlags()
						& (~Paint.STRIKE_THRU_TEXT_FLAG));
			}

			/*
			 * Button btn = (Button)convertView.findViewById(R.id.btn);
			 * btn.setOnClickListener(new Button.OnClickListener() {
			 * 
			 * @Override public void onClick(View v) { // TODO Auto-generated
			 * method stub String str = arSrc.get(pos).Name + "를 주문합니다.";
			 * Toast.makeText(maincon, str, Toast.LENGTH_SHORT).show(); } })버튼을
			 * 넣으면 클릭이 되지 않는다.
			 */

			Log.e("convertView", "" + convertView);
			return convertView;// 리턴하면 리스트 뷰가 이 항목 뷰를 해당 위치에 출력한다.
		}
	}
}
