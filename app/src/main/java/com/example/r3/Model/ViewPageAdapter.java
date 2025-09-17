package com.example.r3.Model;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.r3.R8AddFragment;
import com.example.r3.R8AddFragment;
import com.example.r3.R8RecordFragment;

import java.util.ArrayList;

public class ViewPageAdapter extends FragmentPagerAdapter  {
    //The list of fragments
    private final ArrayList<String> fragmentsTitle = new ArrayList<>();
    private Bundle bundle;
    //Constructor of adapter
    public ViewPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    // returns a fragment depending on the selected tab
    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                R8AddFragment r8AddFragment = new R8AddFragment();
                //Setting up the doctor User data
                r8AddFragment.setUserID(bundle.getString("UserID"));
                fragment = r8AddFragment;
                break;
            case 1:
                R8RecordFragment r8RecordFragment= new R8RecordFragment();
                //Setting up the doctor User data
                r8RecordFragment.setUserID(bundle.getString("UserID"));
                fragment = r8RecordFragment;
                break;
        }
        return fragment;
    }



    //Returns the number of tabs
    @Override
    public int getCount() {
        return fragmentsTitle.size();
    }

    //Adding a fragments to ArrayList<String> fragmentsTitle
    public void addFragment(String title, Bundle bundle){
        this.bundle = bundle;
        fragmentsTitle.add(title);
    }

    //Returns the name of tab in this particular (int)position
    @NonNull
    @Override
    public CharSequence getPageTitle(int position){
        return fragmentsTitle.get(position);
    }



}

