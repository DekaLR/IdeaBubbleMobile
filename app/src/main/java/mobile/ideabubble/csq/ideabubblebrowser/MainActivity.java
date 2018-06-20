package mobile.ideabubble.csq.ideabubblebrowser;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import mobile.ideabubble.csq.ideabubblebrowser.Fragment.BrowserActivity;
import mobile.ideabubble.csq.ideabubblebrowser.Fragment.HomeActivity;
import mobile.ideabubble.csq.ideabubblebrowser.Fragment.MemoActivity;
import mobile.ideabubble.csq.ideabubblebrowser.Fragment.onKeyBackPressedListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomView = (BottomNavigationView) findViewById(R.id.navigation);

        bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment _selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        _selectedFragment = HomeActivity.newInstance();
                        break;
                    case R.id.navigation_browser:
                        _selectedFragment = BrowserActivity.newInstance();
                        break;
                    case R.id.navigation_memo:
                        _selectedFragment = MemoActivity.newInstance();
                        break;
                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.framelayout, _selectedFragment).commit();

                return true;
            }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout, HomeActivity.newInstance()).commit();

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
