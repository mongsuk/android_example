package com.example.todolist;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.RandomAccess;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class ToDolist extends Activity // Android system 에서 ToDolist를 호출해야 되기
// 때문에 class를 public으로 하는것같다.
{
	ArrayList<String> todolist = new ArrayList<String>();
	EditText editText;
	private ArrayAdapter<String> myAdapter;
	BrrayList<String> myList ;
	private ListView list;
	

	BrrayList<String> myToList;
	CrrayList<String> myBestList;

	boolean check= false;
	/** Called when the activity is first created. */

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		myAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, todolist);

		list = (ListView) findViewById(R.id.listview);
		list.setAdapter(myAdapter);
		list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		editText = (EditText) findViewById(R.id.edit);
		editText.setInputType(0);
		editText.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				// TODO Auto-generated method stub
				if (actionId == EditorInfo.IME_ACTION_DONE) { // actionId를 비교 확인이 맞으면 실행된다.
					
					todolist.add(0, editText.getText().toString());// ArrayList의 0번째에 글자를 넣는다. 항상 첫번째에 넣는다.
					editText.setText(R.string.add_a_new_task);//글자를 넣고 add a new task를 에딧 텍스트에 삽입.
					editText.setInputType(0);// 에딧텍스트 뷰 비활성화
					check = false;//
			
					if (checkline == true) { // 아이템이 클릭되었는지 체크한다. 리스트에 아이템이 존재하는지 체크. 
						//이거 없이 실행하면 리스트가 존재하지 않았을때도 실행 되므로 error 
					View view2 = list.getChildAt(0);// 첫번째 뷰를 얻어온다.
					Log.e("ListView",""+((TextView) view2).getPaintFlags());
						if(((TextView) view2).getPaintFlags() == 273){ // view에 글씨에 선이 그어져 있으면
						((TextView) view2).setPaintFlags(((TextView) view2)
								.getPaintFlags() ^ Paint.STRIKE_THRU_TEXT_FLAG);//  XOR을 이용하여 선을 취소 한다.
						}
					}
					
					for(int i=0; i<100; i++){
						
					}
					
					Log.e("todolist_size",""+todolist.size());
					Log.e("index",""+index);
					int position;
					View view;
					if (checkline == true){
					for(int i=0; i<index; i++) {
						Log.e("Schedule",Schedule[i]); 
						position = todolist.indexOf(Schedule[i]);
						 
						 Log.e("position",""+position);
						 view = list.getChildAt(position);
						 Log.e("view",""+view);
						/* 
						 if(((TextView) view).getPaintFlags() == 273){ // view에 글씨에 선이 그어져 있지 않으면.
								((TextView) view).setPaintFlags(((TextView) view)
										.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);//  XOR을 이용하여 선을 취소 한다.
								}*/ 
						}
					}
				}
				return false;
			}
		});
		editText.setOnTouchListener(new OnTouchListener() {
			//editText를 터치했을 때 실행된다.
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				editText.setInputType(1);// 에딧텍스트 뷰 활성화
				
				if (check == false) {
					editText.setText("");
					check = true;
				}
	
				return false;
			}
		
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override // 리스트 아이템 클릭시 실행
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ListView listView = (ListView) parent;
				String item = (String) listView.getItemAtPosition(position); // position에
				View view2 = listView.getChildAt(position);
				((TextView) view2).setPaintFlags(((TextView) view2)
						.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

				checkline = true;// 아이템이 체크되었는지 확인
				Schedule[index++]=item;
				
			}
		});
	}

	int number = 0;
	View[] lineposition = new View[100];

	void linecheck(View view, int position) {
		sureCapacitiy(number + 1);
		lineposition[number++] = view;

		for (int i = 0; i < lineposition.length; i++) {
			if (lineposition[i] == null)
				return;
			((TextView) lineposition[i]).setPaintFlags(((TextView) view)
					.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
		}
	}

	@SuppressLint("NewApi")
	public void sureCapacitiy(int minnumber) {
		int oldnumber = lineposition.length;
		if (minnumber > oldnumber) {
			View oldData[] = lineposition;
			int newNumber = (oldnumber * 3) / 2 + 1;
			if (newNumber < minnumber)
				newNumber = minnumber;
			//lineposition = Arrays.copyOf(lineposition, newNumber);
		}

	}
}

@SuppressWarnings("serial")
class BrrayList<E> extends AbstractList<E> implements Cloneable, Serializable,
		RandomAccess {
	int size;

	transient Object[] array;
	private static final int MIN_CAPACITY_INCREMENT = 4;

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		if (index >= size) {
			throwIndexOutOfBoundsException(index, size);
		}
		return (E) array[index];
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	public BrrayList() {

		Log.e("MyBrrayList", "MyBrrayList");

		array = EmptArray.OBJECT;
		Log.e("array", "" + array);
	}

	@Override
	public boolean add(E object) {
		// TODO Auto-generated method stub

		Object[] a = array;
		int s = size;
		Log.e("before_size", "" + s);
		Log.e("before_a.length", "" + a.length);

		if (s == a.length) {
			Object[] newArray = new Object[s
					+ (s < (MIN_CAPACITY_INCREMENT / 2) ? MIN_CAPACITY_INCREMENT
							: s >> 1)];

			Log.e("newArray_Length", "" + newArray.length);

			System.arraycopy(a, 0, newArray, 0, s);
			array = a = newArray;
		}

		a[s] = object;
		size = s + 1;
		modCount++;
		Log.e("after_size", "" + size);
		Log.e("after_modCount", "" + modCount);
		Log.e("object", "" + object);
		Log.e("after_a.length", "" + a.length);

		return true;
	}

	public BrrayList(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("capacity <0 : " + capacity);
		}
		array = (capacity == 0 ? EmptArray.OBJECT : new Object[capacity]);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	static IndexOutOfBoundsException throwIndexOutOfBoundsException(int index,
			int size) {
		throw new IndexOutOfBoundsException("Invalid index " + index
				+ ", si    ze is " + size);
	}
}

class EmptArray {
	private EmptArray() {
	}

	public static final Object[] OBJECT = new Object[0];
}

@SuppressWarnings("serial")
class CrrayList<E> extends AbstractList<E> implements List<E>, RandomAccess,
		Cloneable, java.io.Serializable {

	private transient Object[] elementData;

	private int size;

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		RangeCheck(index);
		return (E) elementData[index];
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	public CrrayList(int initialCapacity) {
		super();// 자동으로 삽입 되는데;;
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Illegal Capacity:"
					+ initialCapacity);
		this.elementData = new Object[initialCapacity];

	}

	public CrrayList() {
		this(10);
	}

	public boolean add(E e) {
		ensureCapacity(size + 1);
		elementData[size++] = e;
		return true;
	}

	public void add(int index, E element) {
		if (index > size || index < 0)
			error(index, size);

		ensureCapacity(size + 1);
		System.arraycopy(elementData, index, elementData, index + 1, size
				- index);
		elementData[index] = element;
		size++;
	}

	public void ensureCapacity(int minCapacity) {
		modCount++;
		int oldCapacity = elementData.length;
		if (minCapacity > oldCapacity) {
			@SuppressWarnings("unused")
			Object oldData[] = elementData;
			int newCapacity = (oldCapacity * 3) / 2 + 1;
			if (newCapacity < minCapacity)
				newCapacity = minCapacity;
			elementData = Arrays.copyOf(elementData, newCapacity);

		}
	}

	private void RangeCheck(int index) {
		if (index >= size)
			error(index, size);
	}

	public void error(int index, int size) {
		throw new IndexOutOfBoundsException("Index: " + index + ", Size" + size);
	}
}
