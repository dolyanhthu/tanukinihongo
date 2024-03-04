package com.project.elearning.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.project.elearning.adapters.FragmentAdapter;
import com.project.elearning.fragments.AlphabetFragment;
import com.project.elearning.fragments.KanjiFragment;
import com.project.elearning.fragments.LessonFragment;
import com.project.elearning.fragments.ProfileFragment;
import com.project.elearning.fragments.VocabularyFragment;
import com.project.elearning.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private ChipNavigationBar chipNavigationBar;
    FragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}