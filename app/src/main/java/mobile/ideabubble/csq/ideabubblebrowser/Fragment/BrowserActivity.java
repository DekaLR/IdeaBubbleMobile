package mobile.ideabubble.csq.ideabubblebrowser.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View Rview = inflater.inflate(R.layout.fragment_browser, container, false);


        final WebView mWebView = (WebView) Rview.findViewById(R.id.webview);
        final EditText search_text = (EditText) Rview.findViewById(R.id.url_edittext);

        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("https://www.google.com");

        search_text.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if(search_text.getText().toString().contains(".")) mWebView.loadUrl(search_text.getText().toString());
                    else mWebView.loadUrl("https://google.com/search?q="+search_text.getText().toString()+"&oq="+search_text.getText().toString());
                    return true;
                }
                return false;
            }
        });

        return Rview;
    }
}
