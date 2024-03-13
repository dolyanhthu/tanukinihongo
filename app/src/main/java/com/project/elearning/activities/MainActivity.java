package com.project.elearning.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.project.elearning.NetworkChangeReceiver;
import com.project.elearning.adapters.FragmentAdapter;
import com.project.elearning.fragments.AlphabetFragment;
import com.project.elearning.fragments.KanjiFragment;
import com.project.elearning.fragments.LessonFragment;
import com.project.elearning.fragments.ProfileFragment;
import com.project.elearning.fragments.VocabularyFragment;
import com.project.elearning.R;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity
        implements NetworkChangeReceiver.NetworkChangeListener{

    private ViewPager2 viewPager2;
    private ChipNavigationBar chipNavigationBar;
    private FragmentAdapter adapter;

    Dialog dialog;
    NetworkChangeReceiver networkChangeReceiver = new NetworkChangeReceiver(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }
    private void initView(){
        viewPager2 = findViewById(R.id.viewPager2);
        chipNavigationBar = findViewById(R.id.chipNavBar);

        adapter = new FragmentAdapter(getSupportFragmentManager(), getLifecycle());

        adapter.addFragment(new AlphabetFragment());
        adapter.addFragment(new VocabularyFragment());
        adapter.addFragment(new LessonFragment());
        adapter.addFragment(new KanjiFragment());
        adapter.addFragment(new ProfileFragment());

        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager2.setAdapter(adapter);

        chipNavigationBar.setItemEnabled(R.id.chipNavBar, true);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        chipNavigationBar.setItemSelected(R.id.itHiragana, true);
                        break;
                    case 1:
                        chipNavigationBar.setItemSelected(R.id.itVocabulary, true);
                        break;
                    case 2:
                        chipNavigationBar.setItemSelected(R.id.itLesson, true);
                        break;
                    case 3:
                        chipNavigationBar.setItemSelected(R.id.itKanji, true);
                        break;
                    case 4:
                        chipNavigationBar.setItemSelected(R.id.itPerson, true);
                        break;
                }
                super.onPageSelected(position);
            }
        });

        chipNavigationBar.setOnItemSelectedListener(i -> {
            if (i == R.id.itHiragana) {
                viewPager2.setCurrentItem(0);
            } else if (i == R.id.itVocabulary) {
                viewPager2.setCurrentItem(1);
            } else if (i == R.id.itLesson) {
                viewPager2.setCurrentItem(2);
            } else if (i == R.id.itKanji) {
                viewPager2.setCurrentItem(3);
            } else if (i == R.id.itPerson) {
                viewPager2.setCurrentItem(4);
            }
        });
    }

    private void ShowDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_no_internet);
        dialog.setCancelable(false);

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(R.color.transparent));
        dialog.show();

    }

    @Override
    public void onNetworkChanged(boolean isConnected) {
        if (isConnected) {

            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                dialog = null;
            }

        } else {

            if (dialog == null || !dialog.isShowing()) {
                ShowDialog();
            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(networkChangeReceiver);
    }

}