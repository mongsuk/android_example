package com.example.fragmentlayout;

import android.annotation.SuppressLint;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



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

    		setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1,Shakespeare.TITLES));
    		View detailsFrame = getActivity().findViewById(R.id.details);
    		mDualPane = detailsFrame !=null && detailsFrame.getVisibility() == View.VISIBLE;
    		
    		if(savedInstatnceState != null) {
    			mCurCheckPosition = savedInstatnceState.getInt("curChoice", 0);
    		}

    		
    		if(mDualPane) {
    			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    			showDetails(mCurCheckPosition);
    		}
    	}
    	
    	public void onSavedInstanceState(Bundle outState) {
    		super.onSaveInstanceState(outState);
    		outState.putInt("curChoice", mCurCheckPosition);
    	}
    	
    	public void onListItemClick(ListView l, View v , int position, long id) {
    		showDetails(position);
    	}
    	
    	void showDetails(int index) {
    		mCurCheckPosition = index;
    		
    		if(mDualPane) {
    			getListView().setItemChecked(index, true);
    			
    			DetailsFragment details = (DetailsFragment) getFragmentManager().findFragmentById(R.id.details);
    			if(details == null || details.getShownIndex() !=index) {
    				FragmentTransaction ft = getFragmentManager().beginTransaction();
    				
    				if(index == 0) {
    					ft.replace(R.id.details, details);
    				} else {
    					ft.replace(R.id.a_item, details);
    				}
    				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    				ft.commit();
    				
    			} else {
    				Intent intent = new Intent();
    				intent.setClass(getActivity(), DetailsActivity.class);
    				intent.putExtra("index", index);
    				startActivity(intent);
    			}
    			
    		}
    	} 	

    }
    @SuppressLint("NewApi")
	public static class DetailsFragment extends Fragment {
    	
    	public static DetailsFragment newInstance(int index) {
    		DetailsFragment f = new DetailsFragment();
    		
    		Bundle args = new Bundle();
    		args.putInt("index", index);
    		f.setArguments(args);
    		
    		return f;
    	}
    	public int getShownIndex() {
    		return getArguments().getInt("index",0);
    	}
    	
    	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
    			Bundle savedInstanceState) {
    		if(container == null) {
    			return null;
    		}
    		
    		ScrollView scroller = new ScrollView(getActivity());
    		TextView text = new TextView(getActivity());
    		int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, 
    				getActivity().getResources().getDisplayMetrics());
    		text.setPadding(padding, padding, padding,padding);
    		scroller.addView(text);
    		text.setText(Shakespeare.DIALOGUE[getShownIndex()]);
    		return scroller;
    	}
    }
}



