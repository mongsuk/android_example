package com.example.widget_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListFromArray extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listtest);
		Log.e("ListFromArray","ListFromArray");
		
		ArrayAdapter<CharSequence> Adapter;
		Adapter = ArrayAdapter.createFromResource(this, R.array.country,android.R.layout.simple_list_item_1);
		
		ListView list = (ListView)findViewById(R.id.list2);
		// 각각의 layout이 같아도 Acivity에서 설정한 layout을 참고한다.
		list.setAdapter(Adapter);
		
		String[] array = getResources().getStringArray(R.array.country);
		// getStringArray() Return the string array associated with a particular resource ID.
		// getResources() Return a Resources instance for your application's package
		// getStringArray() 에서 string 배열을 리턴해주고 getResources()에서 string 배열을 객체로 변환하여 리턴한다.
		
		Log.e("array.Length",""+array.length);
		
		List<String>list2 =  Arrays.asList(array);
		//asList() Returns a List of the objects in the specified array.
		ArrayList<String> arrayList = new ArrayList<String>(list2);
		arrayList.add("TTS");
		
		ArrayAdapter<String> Adapter2;
		Adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
		list.setAdapter(Adapter2);		
	}
}
