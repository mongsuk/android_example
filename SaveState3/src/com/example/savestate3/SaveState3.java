package com.example.savestate3;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.content.SharedPreferences;


public class SaveState3 extends Activity
{
	private MyView vw;
	int x;
	int y;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        if(savedInstanceState == null) {
        	x=50;
        }else {
        	x = savedInstanceState.getInt("x");
        }
        
        SharedPreferences pref = getSharedPreferences("SaveState",0);
        //SaveState가 없으면 SharedPreferencesImpl를 생성.!!
        // frameworks/base/core/java/android/app/ContextImpl.java 참고
        
        y = pref.getInt("y", 50);
        // pref에 y 가 존재하지 않으면 50을 리턴한다.
       
        // Log.e("before_MyView","before_MyView");
        vw = new MyView(this);
       // Log.e("before_setFocusable","before_setFocusable");
        vw.setFocusable(true);
       // Log.e("before_setContentView","before_setContentView");
        setContentView(vw);
       // Log.e("after_setContentView","after_setContentView");
    }
    
    @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.e("onResume","onResume");
	}
    
    protected void onPause() {
    	super.onPause();
    	Log.e("onPause","onPause");
    	SharedPreferences pref = getSharedPreferences("SaveState",0);
    	// frameworks/base/core/java/android/app/ContextImpl.java 에 정의 되어있음.
    	//SaveState가 존재하면 packagePrefs.get(name);으로 얻은 값을 리턴한다.
    	
    	SharedPreferences.Editor edit = pref.edit();
    	Log.e("onPause",""+y);
    	edit.putInt("y", y);
    	edit.commit();
    	//commit을 해야 수정한값이 등록된다.
    }
    
    public void onSaveInstanceState(Bundle outState) {
    	outState.putInt("x", x);
    }
    

	protected class MyView extends View {
		// onCreate 에서 MyView를 생성하고 
		// onDraw, onKeyDown 가 override되서 호출된다. 이 호출은 Service와 관련된것 같다.
    	public MyView(Context context) {
    		super(context);
    		Log.e("MyView", "MyView");
    	}
    	
    	public void onDraw(Canvas canvas) {
    		Log.e("call_onDraw","call_onDraw");
    		Paint p = new Paint();
    		p.setColor(Color.GREEN);
    		Log.e("y", ""+y);
    		canvas.drawCircle(x,y,16,p);
    	}
    	public boolean onKeyDown(int KeyCode, KeyEvent event) {
    		// 하드웨어 키 이벤트가 발생했을때 호출된다.
        	super.onKeyDown(KeyCode, event);
        	if(event.getAction() == KeyEvent.ACTION_DOWN) {
        		switch(KeyCode) {
        		case KeyEvent.KEYCODE_DPAD_LEFT:
        			x-=5;
        			//Log.e("KEYCODE_DPAD_LEFT","KEYCODE_DPAD_LEFT");
        			//invalidate();
        			return true;
        		case KeyEvent.KEYCODE_DPAD_RIGHT:
        			x +=5;
        			//Log.e("KEYCODE_DPAD_RIGHT","KEYCODE_DPAD_RIGHT");
        			invalidate();
        			//onDraw를 호출한다.
        			return true;
        		case KeyEvent.KEYCODE_DPAD_UP:
        			y -=5;
        			//Log.e("KEYCODE_DPAD_UP","KEYCODE_DPAD_UP");
        			invalidate();
        			return true;
        		case KeyEvent.KEYCODE_DPAD_DOWN:
        			y +=5;
        			//Log.e("KEYCODE_DPAD_DOWN","KEYCODE_DPAD_DOWN");
        			invalidate();
        			return true;
        		}
        	}
    		return false;
        }
    }
}
