package com.example.actionbarusage;




import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import android.widget.Toast;



@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity implements OnQueryTextListener
{
	TextView mSearchText;
	int mSortMode = -1;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mSearchText = new TextView(this);
        mSearchText.setText("Hi~Android");
        setContentView(mSearchText);
    }
    
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		Log.e("MenuInflater",""+inflater);
		Log.e("before_menu",""+menu);

		inflater.inflate(R.menu.actions, menu);
		Log.e("after_menu",""+menu);
	//	SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
	//	searchView.setOnQueryTextListener(this);
		return true;
	}


	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		Log.e("onPrepareOptionsMenu: ","onPrepareOptionsMenu");
		if(mSortMode !=-1){
			Log.e("onPrepare_mSortMode: ",""+mSortMode);
			Drawable icon = menu.findItem(mSortMode).getIcon();
			menu.findItem(R.id.action_sort).setIcon(icon);// 여기서 아이콘 모양을 변화시킨다.
		}
		return super.onPrepareOptionsMenu(menu);
		//return false; false이면 메뉴가 보여지지 않는다.
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
		Log.e("onOptionsItemSelected: ",""+mSortMode);
		return true;
	}
	
	public void onSort(MenuItem item) {
		Log.e("before_onSort",""+mSortMode);
		mSortMode = item.getItemId();
		Log.e("after_onSort",""+mSortMode);
		invalidateOptionsMenu();
	}
	// SearchView를 사용하는 예제는 ApiDemos/src/com/example/android/apis/view 에서 Search예제를 참고!!!!

	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		newText = newText.isEmpty() ?"": "Query so far: " + newText;
		mSearchText.setText(newText);
		return true;
	}
	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Searching for: " + query + "....", Toast.LENGTH_SHORT).show();
		return true;
	}
}
