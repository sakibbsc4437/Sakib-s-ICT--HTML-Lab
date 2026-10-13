package com.sakibsict.htmlpractice;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
    private WebView webView;
    private View splashView;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private long launchTime;
    private boolean pageReady = false;
    private boolean splashHidden = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        launchTime = System.currentTimeMillis();

        FrameLayout root = new FrameLayout(this);
        root.setBackgroundColor(Color.rgb(243, 246, 251));

        webView = new WebView(this);
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);
        settings.setDefaultTextEncodingName("UTF-8");
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                pageReady = true;
                hideSplashWhenReady();
            }
        });
        root.addView(webView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

        splashView = createSplashScreen();
        root.addView(splashView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        setContentView(root);
        webView.loadUrl("file:///android_asset/index.html");
        handler.postDelayed(this::hideSplashWhenReady, 1600);
    }

    private View createSplashScreen() {
        FrameLayout bg = new FrameLayout(this);
        bg.setBackgroundColor(Color.rgb(16, 42, 86));
        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setGravity(Gravity.CENTER);
        content.setPadding(28, 24, 28, 24);

        TextView brand = new TextView(this);
        brand.setText("Sakib's ICT");
        brand.setTextColor(Color.WHITE);
        brand.setTextSize(32);
        brand.setTypeface(Typeface.create("sans-serif", Typeface.BOLD));
        brand.setGravity(Gravity.CENTER);
        content.addView(brand);

        TextView subtitle = new TextView(this);
        subtitle.setText("HTML Practice Lab");
        subtitle.setTextColor(Color.rgb(219, 234, 254));
        subtitle.setTextSize(16);
        subtitle.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams subParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        subParams.topMargin = 8;
        content.addView(subtitle, subParams);

        ProgressBar progress = new ProgressBar(this);
        progress.setIndeterminate(true);
        progress.getIndeterminateDrawable().setTint(Color.WHITE);
        LinearLayout.LayoutParams progressParams = new LinearLayout.LayoutParams(52, 52);
        progressParams.topMargin = 28;
        content.addView(progress, progressParams);

        TextView loading = new TextView(this);
        loading.setText("Loading...");
        loading.setTextColor(Color.rgb(219, 234, 254));
        loading.setTextSize(13);
        loading.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams loadingParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingParams.topMargin = 10;
        content.addView(loading, loadingParams);

        FrameLayout.LayoutParams contentParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        bg.addView(content, contentParams);
        return bg;
    }

    private void hideSplashWhenReady() {
        if (splashHidden || !pageReady) return;
        long remaining = 1400 - (System.currentTimeMillis() - launchTime);
        if (remaining > 0) {
            handler.postDelayed(this::hideSplashWhenReady, remaining);
            return;
        }
        splashHidden = true;
        if (splashView != null) {
            ((FrameLayout) splashView.getParent()).removeView(splashView);
            splashView = null;
        }
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) webView.goBack();
        else super.onBackPressed();
    }
}
