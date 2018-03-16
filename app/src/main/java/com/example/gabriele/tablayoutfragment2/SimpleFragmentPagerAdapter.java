package com.example.gabriele.tablayoutfragment2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Gabriele on 16/03/2018.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment=new Fragment();
       switch (position){
           case 0:
               Fragment1 fragment1=new Fragment1();
                return fragment1;
           case 1:
               Fragment2 fragment2=new Fragment2();
               return fragment2;

           case 2:
               Fragment3 fragment3=new Fragment3();
               return fragment3;
       }
       return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
