package mobile.ideabubble.csq.ideabubblebrowser.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import mobile.ideabubble.csq.ideabubblebrowser.R;

/**
 * Created by csquattro on 2018. 6. 2
 */

public class BrowserActivity extends Fragment {
    public static BrowserActivity newInstance() {
        BrowserActivity f = new BrowserActivity();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View Rview = inflater.inflate(R.layout.fragment_browser, container, false);

        WebView mWebView = (WebView) Rview.findViewById(R.id.webview);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.loadUrl("https://www.google.com"); //fi
        return Rview;
    }
}
