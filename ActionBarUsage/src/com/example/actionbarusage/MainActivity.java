package com.example.actionbarusage;



import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
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
        setContentView(mSearchText);
    }
    
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actions, menu);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
		searchView.setOnQueryTextListener(this);
		return true;
	}


	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		if(mSortMode !=-1){
			Drawable icon = menu.findItem(mSortMode).getIcon();
			menu.findItem(R.id.action_sort).setIcon(icon);
		}
		return super.onPrepareOptionsMenu(menu);
	}

	public void onSort(MenuItem item) {
		mSortMode = item.getItemId();
		invalidateOptionsMenu();
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
		return true;
	}


	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		newText = newText.isEmpty() ?"": "Query so far: " + newText;
		mSearchText.setText(newText);
		return false;
	}
	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Searching for: " + query + "....", Toast.LENGTH_SHORT).show();
		return false;
	}
}
