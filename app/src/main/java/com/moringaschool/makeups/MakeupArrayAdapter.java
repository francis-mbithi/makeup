package com.moringaschool.makeups;

import android.content.Context;
import android.widget.ArrayAdapter;


public class MakeupArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mMakeup;
    private String[] mCosmetics;

    public MakeupArrayAdapter(Context mContext, int resource, String[] mMakeup, String[] mCosmetics) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mMakeup = mMakeup;
        this.mCosmetics = mCosmetics;
    }
    @Override
    public Object getItem(int position){
        String makeup = mMakeup[position];
        String cosmetics = mCosmetics[position];
        return String.format("%s \nServes great:%s", makeup, cosmetics);
    }

    @Override
    public int getCount(){
        return mMakeup.length;
    }
}
