package com.foody.devf.foody;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Ariana on 29/10/2014.
 */
public class viewPager extends PagerAdapter {

    private final activityHome activity;

    /**
     * @param simpleViewPagerActivity
     */
    viewPager(activityHome simpleViewPagerActivity) {
        activity = simpleViewPagerActivity;
    }

    public int getCount() {
        return 3;
    }

    public Object instantiateItem(View collection, int position) {

        LayoutInflater inflater = (LayoutInflater) collection.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        int fragmentId = 0;
        switch (position) {
            case 0:
                fragmentId = R.layout.fragment_refri;
                break;
            case 1:
                fragmentId = R.layout.fragment_receta;
                break;
            case 2:
                fragmentId = R.layout.fragment_comer;
                break;
        }

        View view = inflater.inflate(fragmentId, null);

        ((ViewPager) collection).addView(view, 0);

        return view;
    }

    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);

    }

    public void finishUpdate(View arg0) {
        // TODO Auto-generated method stub

    }

    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == ((View) arg1);

    }

    public void restoreState(Parcelable arg0, ClassLoader arg1) {
        // TODO Auto-generated method stub

    }

    public Parcelable saveState() {
        // TODO Auto-generated method stub
        return null;
    }

    public void startUpdate(View arg0) {
        // TODO Auto-generated method stub

    }

}
