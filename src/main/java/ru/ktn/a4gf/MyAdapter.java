package ru.ktn.a4gf;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;

/**
 * Created by Антон on 27.03.2018.
 */

public class MyAdapter extends FragmentPagerAdapter {

    private Context context = null;
    private PagerTabStrip pts;

    public MyAdapter(Context contex, FragmentManager mgr) {
        super(mgr);
        this.context = contex;
    }

    @Override
    public int getCount() {
        return(3);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case (0):
                return (TestsFragment.newInstance(position));
            case (1):
                return (CompareFragment.newInstance(position));
            default:
                return (EllipseFragment.newInstance(position));
        }

    }

    @Override
    public String getPageTitle(int position) {
        switch (position){
            case(0):
                return (TestsFragment.getTitle(context, position));
            case(1):
                return (CompareFragment.getTitle(context, position));
            default:
                return (EllipseFragment.getTitle(context, position));
        }
    }
}
