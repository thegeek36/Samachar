package com.priyanshu.samachar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    int tabcount;
    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                return new home_fragment();
            case 1:
                return new sports_fragment();
            case 2:
                return new health_fragments();
            case 3:
                return new tech_fragment();
            case 4:
                return new science_fragment();
            case 5:
                return new entertainment_fragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
