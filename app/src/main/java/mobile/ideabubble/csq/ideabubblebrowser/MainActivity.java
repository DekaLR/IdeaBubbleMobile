package mobile.ideabubble.csq.ideabubblebrowser;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import mobile.ideabubble.csq.ideabubblebrowser.Fragment.BrowserActivity;
import mobile.ideabubble.csq.ideabubblebrowser.Fragment.HomeActivity;
import mobile.ideabubble.csq.ideabubblebrowser.Fragment.MemoActivity;
import mobile.ideabubble.csq.ideabubblebrowser.Fragment.ViewPagerAdapter;
import mobile.ideabubble.csq.ideabubblebrowser.Fragment.onKeyBackPressedListener;

import static android.support.v4.view.ViewPager.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final BottomNavigationView bottomView = (BottomNavigationView) findViewById(R.id.navigation);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(new OnPageChangeListener() {
            private MenuItem prevBottomNavigation;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevBottomNavigation != null) {
                    prevBottomNavigation.setChecked(false);
                }

                prevBottomNavigation = bottomView.getMenu().getItem(position);
                prevBottomNavigation.setChecked(true);

                if(position == 1) setColor("#4db6ac");
                else setColor("#f0f0f0");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String color = null;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        viewPager.setCurrentItem(0);
                        color = "#f0f0f0";
                        break;
                    case R.id.navigation_browser:
                        viewPager.setCurrentItem(1);
                        color = "#4db6ac";
                        break;
                    case R.id.navigation_memo:
                        viewPager.setCurrentItem(2);
                        color = "#f0f0f0";

                        break;
                }
                setColor(color);
                return true;
            }
        });

        setColor("#f0f0f0");
    }

    void setColor(String color) {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.parseColor(color));
    }

    //-------- 브라우저 뒤로가기 처리 ---------

    private onKeyBackPressedListener mOnKeyBackPressedListener;

    public void setOnKeyBackPressedListener(onKeyBackPressedListener listener) {
        mOnKeyBackPressedListener = listener;
    }

    @Override
    public void onBackPressed() {
        if(mOnKeyBackPressedListener != null) mOnKeyBackPressedListener.onBack();
        else super.onBackPressed();
    }
}
