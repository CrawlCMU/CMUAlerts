/**
 * 
 */
package com.crawlcmu.adapters;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.crawlcmu.datamodel.CheckBoxDataModel;
import com.example.crawlcmu.R;

/**
 * @author ishan
 *
 */
public class SubscriptionCheckboxListAdapter extends BaseAdapter implements OnClickListener 
{
	/** The inflator used to inflate the XML layout */
	private LayoutInflater inflator;
 
	/** A list containing some sample data to show. */
	private List dataList;
	
	public SubscriptionCheckboxListAdapter(LayoutInflater inflator,List dList)
	{
		super();
		this.inflator = inflator;
		dataList = dList;
	}

	@Override
	public int getCount() 
	{
		return dataList.size();
	}

	@Override
	public Object getItem(int position) 
	{
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) 
	{
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) 
	{
		// create the view only when needed
		if (view == null) 
		{
			view = inflator.inflate(R.layout.subscription_list, null);
 			// Set the click listener for the checkbox
			view.findViewById(R.id.checkBox).setOnClickListener(this);
		}
		
		CheckBoxDataModel data = (CheckBoxDataModel) getItem(position);
		// Set the example text and the state of the checkbox
		CheckBox cb = (CheckBox) view.findViewById(R.id.checkBox);
		cb.setChecked(data.isSelected());
		// We tag the data object to retrieve it on the click listener.
		cb.setTag(data);
 
		TextView tv = (TextView) view.findViewById(R.id.subscription_groupid_text);
		tv.setText(data.getName());
 
		return view;
	}

	@Override
	public void onClick(View view) 
	{
		if(view!=null)
		{
			CheckBoxDataModel data = (CheckBoxDataModel) view.getTag();
			data.setSelected(((CheckBox) view).isChecked());
		}
		
	}
	

}
