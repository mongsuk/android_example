package com.example.savecurve2;



import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Parcel;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;

class Vertex implements Parcelable {
	Vertex(float ax, float ay, boolean ad){
		x = ax;
		y = ay;
		Draw = ad;
	}
	float x;
	float y;
	boolean Draw;
	
	public int describeContents() {
		return 0;
	}
	
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeFloat(x);
		dest.writeFloat(y);
		dest.writeBooleanArray(new boolean[] {Draw});
	}
	
	public static final Parcelable.Creator<Vertex> CREATOR = new Creator<Vertex>() {
		public Vertex createFromParcel(Parcel source) {
			int x = source.readInt();
			int y = source.readInt();
			boolean[] td = new boolean[1];
			source.readBooleanArray(td);
			return new Vertex(x,y, td[0]);
		}
		
		public Vertex[] newArray(int size) {
			return new Vertex[size];
		}
	};
	
}
public class SaveCurve2 extends Activity
{
	private MyView vw;
	ArrayList<Vertex> arVertex;
	int Count;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        vw = new MyView(this);
        setContentView(vw);
        
        if(savedInstanceState == null) {
        	arVertex = new ArrayList<Vertex>();
        } else {
        	arVertex = savedInstanceState.getParcelableArrayList("Curve");
        }
    }
    
    public void onSaveInstanceState(Bundle outState) {
    	outState.putParcelableArrayList("Curve", arVertex);
    }
    
    protected class MyView extends View{
    	Paint mPaint;
    	
    	public MyView(Context context) {
    		super(context);
    		Log.e("MyView","MyView");
    		
    		mPaint = new Paint();
    		mPaint.setColor(Color.BLACK);
    		mPaint.setStrokeWidth(3);
    		mPaint.setAntiAlias(true);
    	}
    	public void onDraw(Canvas canvas) {
    		canvas.drawColor(0xffe0e0e0);
    		Log.e("onDraw",""+arVertex.size());
    		for(int i=0; i<arVertex.size(); i++) {
    			if(arVertex.get(i).Draw) { // false, true 검사!!
    				canvas.drawLine(arVertex.get(i-1).x, arVertex.get(i-1).y, 
    						arVertex.get(i).x, arVertex.get(i).y, mPaint);
    			}
    			else {
    				canvas.drawPoint(arVertex.get(i).x, arVertex.get(i).y, mPaint); //  점 그리는것!
    			}
    		}
    	}
    	
    	public boolean onTouchEvent(MotionEvent event) {
    		/**/
    		if(event.getAction() == MotionEvent.ACTION_DOWN) {
    			Log.e("ACTION_DOWN","ACTION_DOWN");
    			arVertex.add(new Vertex(event.getX(), event.getY(), false));
    			// if(arVertex.get(i).Draw) true, false check
    			invalidate();//Draw호출!!
    			return true;
    		}
    		
    		if(event.getAction()== MotionEvent.ACTION_MOVE) {
    			Log.e("ACTION_MOVE","ACTION_MOVE");
    			arVertex.add(new Vertex(event.getX(), event.getY(), true));
    			// if(arVertex.get(i).Draw) true, false check
    			invalidate();
    			return true;
    		}
    		return false;
    	}
    	
    }
}
