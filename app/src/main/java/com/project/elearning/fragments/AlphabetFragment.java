package com.project.elearning.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.project.elearning.adapters.FragmentAdapter;
import com.project.elearning.R;

public class AlphabetFragment extends Fragment {

    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    FragmentAdapter adapter;

    public AlphabetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alphabet, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        viewPager2 = view.findViewById(R.id.alphabetViewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
        adapter = new FragmentAdapter(getChildFragmentManager(), getLifecycle());

        adapter.addFragment(new HiraganaFragment());
        adapter.addFragment(new KatakanaFragment());

        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Hiragana"));
        tabLayout.addTab(tabLayout.newTab().setText("Katakana"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }
}