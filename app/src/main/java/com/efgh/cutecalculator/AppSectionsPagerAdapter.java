package com.efgh.cutecalculator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Vishnu on 18-Jul-16.
 */
public class AppSectionsPagerAdapter extends FragmentPagerAdapter
{


    public AppSectionsPagerAdapter(FragmentManager fm)
    {
        super(fm);

    }

    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0:
                return new BasicCalculatorFragment();
            case 1:
                return new ScientificCalculatorFragment();

        }
        return null;
    }

    @Override
    public int getCount()
    {
        return 2;
    }
}
