package mobile.ideabubble.csq.ideabubblebrowser.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import mobile.ideabubble.csq.ideabubblebrowser.MainActivity;
import mobile.ideabubble.csq.ideabubblebrowser.R;

/**
 * Created by csquattro on 2018. 6. 2
 */

public class BrowserActivity extends Fragment implements onKeyBackPressedListener {
    EditText search_text;
    WebView mWebView;
    String SaveOriginalUrl;
    InputMethodManager inputManager;

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
        inputManager= (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        mWebView = (WebView) Rview.findViewById(R.id.webview);
        search_text = (EditText) Rview.findViewById(R.id.url_edittext);
        mWebView.setWebViewClient(new WebViewClient() {
            boolean is_error=false;

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageFinished(view, url);

                if (!is_error)
                    search_text.setText(url);
                is_error = false;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);

                if(!SaveOriginalUrl.contains(".")) {

                    if(!search_text.getText().toString().contains("ERROR :/")) {
                        //mWebView.loadUrl("file:///android_asset/myerrorpage.html");
                        search_text.setText("ERROR :/");
                        is_error=true;
                    }

                    String ReturnGoogleUrl = "https://www.google.co.kr/search?q=" + SaveOriginalUrl + "&oq=" + SaveOriginalUrl;
                    mWebView.loadUrl(ReturnGoogleUrl);
                    search_text.setText(ReturnGoogleUrl);
                }
            }
        });

        mWebView.loadUrl("https://www.google.com");
        mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        mWebView.getSettings().setJavaScriptEnabled(true);

        search_text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean checkfocus) {
                if(!checkfocus)
                    inputManager.hideSoftInputFromWindow(search_text.getWindowToken(),0); //hide keyboard
            }
        });

        search_text.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //inputManager.hideSoftInputFromWindow(search_text.getWindowToken(),0); //hide keyboard
                SaveOriginalUrl = search_text.getText().toString();
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER) ) {

                    String Url = CheckString(search_text.getText().toString());
                    mWebView.loadUrl(Url);

                }
                return false;
            }
        });

        return Rview;

    }


    private String CheckString(String str)
    {
        if(!str.startsWith("http://") && !str.startsWith("http://") && !str.startsWith("ftp://"))
            str = "http://" + str;

        return str;
    }

    @Override
    public void onBack() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            MainActivity activity = (MainActivity) getActivity();
            activity.setOnKeyBackPressedListener(null);
            activity.onBackPressed();
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).setOnKeyBackPressedListener(this);
    }
}
