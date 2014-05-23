package com.example.callother;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.ComponentName;
import android.content.Intent;
import java.io.File;


public class CallOther extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.callother);
        
        findViewById(R.id.web).setOnClickListener(mClickListener);
        findViewById(R.id.dial).setOnClickListener(mClickListener);
        findViewById(R.id.picture).setOnClickListener(mClickListener);
        findViewById(R.id.other).setOnClickListener(mClickListener);
    }
    
    Button.OnClickListener mClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent;
			switch (v.getId()) {
			case R.id.web:
				intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
				startActivity(intent);
				break;
			case R.id.dial:
				intent = new Intent(Intent.ACTION_DIAL ,Uri.parse("tel:015-123-4567"));
				startActivity(intent);
				break;
			case R.id.picture:
				intent = new Intent(Intent.ACTION_VIEW);
				Uri uri = Uri.fromFile(new File("/sdcard/test.jpg"));
				intent.setDataAndType(uri, "image/jpeg");
				startActivity(intent);
				break;
			case R.id.other:
				intent = new Intent(Intent.ACTION_MAIN);
				intent.setComponent(new ComponentName("exam.Input", "exam.Input.Input"));
				startActivity(intent);
				break;
			}
		}
	};
}
