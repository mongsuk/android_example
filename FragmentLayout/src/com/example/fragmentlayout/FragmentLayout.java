package com.example.fragmentlayout;

import android.annotation.SuppressLint;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.app.ListFragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Fragment;

@SuppressLint("NewApi")
public class FragmentLayout extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);
    }
    
    @SuppressLint("NewApi")
	public static class DetailsActivity extends Activity {
    	protected void onCreate(Bundle savedInstanceState) {
    		super.onCreate(savedInstanceState);
    		
    		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
    			finish();
    			return;
    		}
    		
    		if(savedInstanceState == null) {
    			DetailsFragment details = new DetailsFragment();
    			details.setArguments(getIntent().getExtras());
    			getFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
    		}
    	}
    }
    
    public static class TitleFragment extends ListFragment {
    	boolean mDualPane;
    	int mCurCheckPosition =0;
    	
    	public void onActivityCreated(Bundle savedInstatnceState) {
    		super.onActivityCreated(savedInstatnceState);
    		
    		setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.sample_list_item_activated_1, Shakespeare.TITLES));
    		
    	}
    }
    @SuppressLint("NewApi")
	public static class DetailsFragment extends Fragment {
    	
    }
}



