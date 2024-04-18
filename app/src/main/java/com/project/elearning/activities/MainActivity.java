package com.project.elearning.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.project.elearning.NetworkChangeReceiver;
import com.project.elearning.adapters.FragmentAdapter;
import com.project.elearning.databinding.ActivityMainBinding;
import com.project.elearning.databinding.ActivityNoInternetBinding;
import com.project.elearning.fragments.AlphabetFragment;
import com.project.elearning.fragments.KanjiFragment;
import com.project.elearning.fragments.LessonFragment;
import com.project.elearning.fragments.ProfileFragment;
import com.project.elearning.fragments.VocabularyFragment;
import com.project.elearning.R;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity
        implements NetworkChangeReceiver.NetworkChangeListener{

    ActivityMainBinding mainBinding;
    private FragmentManager fragmentManager;
    private ChipNavigationBar chipNavigationBar;
    private FragmentAdapter adapter;

    Dialog dialog;
    NetworkChangeReceiver networkChangeReceiver = new NetworkChangeReceiver(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        initView();

    }
    private void initView(){
        chipNavigationBar = mainBinding.chipNavBar;

        adapter = new FragmentAdapter(getSupportFragmentManager(), getLifecycle());

        adapter.addFragment(new AlphabetFragment());
        adapter.addFragment(new VocabularyFragment());
        adapter.addFragment(new LessonFragment());


        chipNavigationBar.setItemEnabled(R.id.chipNavBar, true);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameLayout, new AlphabetFragment()).commit();

        chipNavigationBar.setOnItemSelectedListener(i -> {
            Fragment fragment = null;
            if (i == R.id.itHiragana) {
                fragment = new AlphabetFragment();
            } else if (i == R.id.itVocabulary) {
                fragment = new VocabularyFragment();
            } else if (i == R.id.itLesson) {
                fragment = new LessonFragment();
            }

            if (fragment != null) {

                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, fragment)
                        .commit();
            }
            else {
                Log.e("Fragment", "Error");
            }
        });
    }

    private void ShowDialog() {
        ActivityNoInternetBinding noInternetBinding = ActivityNoInternetBinding.inflate(getLayoutInflater());
        dialog = new Dialog(this);
        dialog.setContentView(noInternetBinding.getRoot());
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