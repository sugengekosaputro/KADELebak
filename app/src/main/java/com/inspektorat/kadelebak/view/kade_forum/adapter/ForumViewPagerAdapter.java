package com.inspektorat.kadelebak.view.kade_forum.adapter;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.view.kade_forum.fragment.MyForumFragment;
import com.inspektorat.kadelebak.view.kade_forum.fragment.TimeLineFragment;
import com.inspektorat.kadelebak.view.kade_forum.presenter.ForumPresenter;

public class ForumViewPagerAdapter extends FragmentStatePagerAdapter {

    private static final int PAGE_TITLE = 2;
    private Context context;
    private String[] forumPortalTabTitle;

    public ForumViewPagerAdapter(FragmentManager fm, Context mContext) {
        super(fm);

        context = mContext;
        forumPortalTabTitle = context.getResources().getStringArray(R.array.forum_portal_tab_title);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new Fragment();
        switch (position) {
            case 0:
                fragment = new TimeLineFragment();
                break;
            case 1:
                fragment = new MyForumFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_TITLE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return forumPortalTabTitle[position];
    }

}