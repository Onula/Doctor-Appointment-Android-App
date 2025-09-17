package com.example.r3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.r3.Model.ViewPageAdapter;
import com.example.r3.databinding.FragmentR8VisitAdditionAndActionLogginBinding;
import com.google.android.material.tabs.TabLayout;


public class R8VisitAdditionAndActionLoggin extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPageAdapter viewPageAdapter;
    private Bundle bundle;
    private FragmentR8VisitAdditionAndActionLogginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentR8VisitAdditionAndActionLogginBinding.inflate(inflater, container, false);
        return binding.getRoot();
        // Inflate the layout for this fragment

    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        tabLayout=binding.tabLayout;
        viewPager=binding.viewPage;
        bundle = new Bundle();
        bundle.putString("UserID", getArguments().getString("UserID"));
        addFragments();

    }
    public  void addFragments(){
        tabLayout.setupWithViewPager(viewPager);
        viewPageAdapter = new ViewPageAdapter(getActivity().getSupportFragmentManager());
        //Add fragments to adapter & send the Doctor User data to them
        viewPageAdapter.addFragment("ΠΡΟΣΘΗΚΗ ΕΠΙΣΚΕΨΗΣ",bundle);
        viewPageAdapter.addFragment("ΚΑΤΑΓΡΑΦΗ ΕΝΕΡΓΕΙΩΝ", bundle);
        // Set the adapter to the ViewPager
        viewPager.setAdapter(viewPageAdapter);

        System.out.println("OK");
    }



}
