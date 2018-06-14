package mobile.ideabubble.csq.ideabubblebrowser.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mobile.ideabubble.csq.ideabubblebrowser.R;

/**
 * Created by csquattro on 2018. 6. 2
 */

public class HomeActivity extends Fragment {

    public static HomeActivity newInstance() {
        HomeActivity f = new HomeActivity();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

}
