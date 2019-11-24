package com.inspektorat.kadelebak.view.kade_forum;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.view.kade_forum.adapter.ForumViewPagerAdapter;
import com.inspektorat.kadelebak.view.kade_forum.fragment.MyForumFragment;
import com.inspektorat.kadelebak.view.kade_forum.fragment.TimeLineFragment;
import com.inspektorat.kadelebak.view.kade_forum.view.CreateForumActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForumActivity extends AppCompatActivity {

    @BindView(R.id.forum_tab)
    TabLayout forumTab;
    @BindView(R.id.forum_viewpager)
    ViewPager forumViewpager;
    /*    @BindView(R.id.edt_search)
    TextInputLayout edtSearch;*/

    TimeLineFragment timeLineFragment;
    MyForumFragment myForumFragment;

    private boolean firstViewTimeline = false;
    private boolean firstViewMyForum = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        handleViewPager();
        handlePosition();
    }

    private void handlePosition() {
        forumViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        if (firstViewTimeline) {
                            timeLineFragment = new TimeLineFragment();
                            firstViewTimeline = false;
                        }
                    case 1:
                        if (firstViewMyForum) {
                            myForumFragment = new MyForumFragment();
                            firstViewMyForum = false;
                        }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void handleViewPager() {
        ForumViewPagerAdapter vpAdapter = new ForumViewPagerAdapter(getSupportFragmentManager(), this);
        forumViewpager.setAdapter(vpAdapter);
        forumViewpager.setOffscreenPageLimit(2);
        forumTab.setupWithViewPager(forumViewpager);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            case R.id.action_create:
                Intent intent = new Intent(this, CreateForumActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_create, menu);
        return true;
    }
}