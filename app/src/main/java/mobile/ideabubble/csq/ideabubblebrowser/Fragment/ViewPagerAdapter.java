package mobile.ideabubble.csq.ideabubblebrowser.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Tyrrell-F on 2016-08-14.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return HomeActivity.newInstance();
            case 1:
                return BrowserActivity.newInstance();
            case 2:
                return MemoActivity.newInstance();
        }

        return new Fragment();
    }

    @Override
    public int getCount() {
        return 3;
    }



}
