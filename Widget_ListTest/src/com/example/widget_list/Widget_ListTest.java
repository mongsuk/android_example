package com.example.widget_list;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Widget_ListTest extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ArrayList<String> arGeneral = new ArrayList<String>();
        arGeneral.add("ListFromArray");
        arGeneral.add("ListAddDel");
        arGeneral.add("ListIconText");
        
        ArrayAdapter<String> Adapter;
        Adapter = new ArrayAdapter<String>(this,
        		android.R.layout.simple_list_item_1, arGeneral);// 리스트 뷰에 출력 내용을 세팅한다.
        ListView list = (ListView)findViewById(R.id.list);
        list.setAdapter(Adapter);
        
        
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override // 리스트 아이템 클릭시 실행
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ListView listView = (ListView) parent;
				String item = (String) listView.getItemAtPosition(position); // position에
				Log.e("item",""+item);
			//	Intent intent;
				
				switch(item) {
				case "ListFromArray":
					Log.e("item",""+item);
					Intent	intent1 = new Intent(Widget_ListTest.this,ListFromArray.class);
					startActivity(intent1);			
					break;
				case "ListAddDel":
					Log.e("item",""+item);
					Intent intent2 = new Intent(Widget_ListTest.this,ListAddDel.class);
					startActivity(intent2);			
					break;
					
				case "ListIconText":
				//	Log.e("item",""+item);
					Intent intent3 = new Intent(Widget_ListTest.this,ListIconText.class);
					startActivity(intent3);			
					break;
				default:
						Log.e("default","default");
				
				}

			}
		});
    }
}
