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
               ImageListFragment imageListFragment =new ImageListFragment();
                return imageListFragment;
           case 1:
               ContactListFragment contactListFragment =new ContactListFragment();
               return contactListFragment;

           case 2:
               MailSendFragment mailSendFragment =new MailSendFragment();
               return mailSendFragment;
       }
       return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
