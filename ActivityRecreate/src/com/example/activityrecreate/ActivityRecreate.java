package com.example.activityrecreate;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.util.Log;

@SuppressLint("NewApi")
public class ActivityRecreate extends Activity
{
	int mCurTheme;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	Log.e("onCreate","onCreate");
        super.onCreate(savedInstanceState);
        
        if(savedInstanceState !=null) {
        	Log.e("check_savedInstanceState",""+savedInstanceState);
        	mCurTheme = savedInstanceState.getInt("theme");
        	
        	switch(mCurTheme) {
        	case android.R.style.Theme_Holo_Light:
        		mCurTheme = android.R.style.Theme_Holo_Dialog;
        		Log.e("Holo_Light","Holo_Light");
        		break;
        	case android.R.style.Theme_Holo_Dialog:
        		mCurTheme = android.R.style.Theme_Holo;
        		Log.e("Holo_Dialog","Holo_Dialog");
        		break;
        	default:
        		mCurTheme = android.R.style.Theme_Holo_Light;
        		Log.e("default","default");
        		break;
        	}
        	setTheme(mCurTheme); // 콘텍스트의 테마를 설정한다.
        }
        
        setContentView(R.layout.activity_recreate);
        Log.e("Button_before","Button_before");
        Button button = (Button)findViewById(R.id.recreate);
        button.setOnClickListener(mRecreateListener);
    }
    
    
    
    protected void onSaveInstanceState(Bundle savedInstanceState) {
    	//화면 전환시 호출된다.
    	//화면이 꺼졌다 켜질때도 호출된다.
    	
    	super.onSaveInstanceState(savedInstanceState);
    	Log.e("onSaveInstanceState","onSaveInstanceState");
    	savedInstanceState.putInt("theme", mCurTheme);
    	// 이 함수 실행후 Destroy 호출!
    	
    }
    
    private OnClickListener mRecreateListener = new OnClickListener() {
    	
    	public void onClick(View v) {
    		Log.e("Button_click","Button_click");
    		recreate();
    		//onSaveInstanceState 호출한다.!
    	}
    };

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.e("onResume","onResume");
	}



	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.e("onDestroy","onDestroy");
		//화면이 바뀌기전 액티비티는 디스트로이 된다.
	}
    
    
}
