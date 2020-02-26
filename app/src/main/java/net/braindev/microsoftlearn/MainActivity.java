package net.braindev.microsoftlearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.session.MediaSessionManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import net.braindev.microsoftlearn.Utilities.Constant;
import net.braindev.microsoftlearn.Utilities.Shared_Preferences;
import net.braindev.microsoftlearn.Utilities.Tools;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //region [ ATTRIUTS ]
    public Context context = MainActivity.this;
    private ProgressBar progressBar;
    private WebView webView;
    //private ProgressDialog progressDialog;
    public Shared_Preferences sharedPreferences;
    String url_Api ="";
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher_round);// set drawable icon
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            //progressDialog = new ProgressDialog(this);
            sharedPreferences = new Shared_Preferences(context);
            //url_Api = GetDefaultLanguage();

            progressBar = this.findViewById(R.id.progressBar);
            progressBar.setMax(100);
            progressBar.setVisibility(ProgressBar.GONE);
            webView = this.findViewById(R.id.webView);

            if (savedInstanceState != null) {
                webView.restoreState(savedInstanceState);
            } else {
            }
            LoadUrlWebView(GetDefaultLanguage());
        } catch (Exception e) {
            Tools.LogCat(this, "onCreate", e);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        try{

        }catch (Exception e){
            Tools.LogCat(this, "onCreateOptionsMenu", e);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        try{
            if( item.getItemId()==android.R.id.home){
                sharedPreferences.setPageWebView("");
                LoadUrlWebView( GetDefaultLanguage( ) );
            }
        }catch (Exception e){
            Tools.LogCat(this, "onOptionsItemSelected", e);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        try {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (webView.canGoBack()) {
                    webView.goBack();
                }else{
                    url_Api=GetDefaultLanguage( );
                    if( url_Api.equalsIgnoreCase( Constant.URL_MICROSOFT_LEARN_EN )
                            || url_Api.equalsIgnoreCase( Constant.URL_MICROSOFT_LEARN_FR ) ){
                        finish();
                    }else {
                        sharedPreferences.setPageWebView("");
                        url_Api=GetDefaultLanguage( );
                        LoadUrlWebView( url_Api );
                    }
                }
                Tools.LogCat("KEYCODE_BACK()");
                return true;
            } else if (keyCode == KeyEvent.KEYCODE_HOME) {
                Tools.LogCat("KEYCODE_HOME()");
                return true;
            } else {
                Tools.LogCat("else onKeyDown()");
                return super.onKeyDown(keyCode, event);
            }
        } catch (Exception ex) {
            Tools.LogCat("Exception:onKeyDown()", ex);
        }
        return true;
    }


    public String GetDefaultLanguage( ) {
        String url="";
        try {
            String langue = Locale.getDefault().toString(); //        ---> en_US
            /*
            Tools.LogCat( Locale.getDefault().getLanguage() ); //       ---> en
            Tools.LogCat( Locale.getDefault().getISO3Language()  ); //  ---> eng
            Tools.LogCat( Locale.getDefault().getCountry()  ); //       ---> US
            Tools.LogCat( Locale.getDefault().getISO3Country()  ); //   ---> USA
            Tools.LogCat( Locale.getDefault().getDisplayCountry() ); // ---> United States
            Tools.LogCat( Locale.getDefault().getDisplayName() ); //    ---> English (United States)
            Tools.LogCat( Locale.getDefault().toString()   ); //        ---> en_US
            Tools.LogCat( Locale.getDefault().getDisplayLanguage() ); //---> English
            */
            if( sharedPreferences.getPageWebView().equalsIgnoreCase("")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    langue = Locale.getDefault().toLanguageTag(); //     ---> en-US
                    url = Constant.getUrlMicrosoftLearn(langue);
                    Tools.LogCat(url);
                    Tools.LogCat(langue);
                } else {
                    langue = langue.replace("_", "-"); //     ---> en-US
                    url = Constant.getUrlMicrosoftLearn(langue);
                    Tools.LogCat(url);
                    Tools.LogCat(langue);
                }
            }else{
                url = sharedPreferences.getPageWebView();
            }
        }catch (Exception ex) {
            Tools.LogCat( "Exception:GetDefaultLanguage()", ex);
        }
        return url;
    }

    private void LoadUrlWebView( String url_api ) {
        try {
            //webView.setWebChromeClient(new MyWebChromeClient( url_api ));
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setSupportZoom(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setDisplayZoomControls(false);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webView.getSettings().setAllowContentAccess(true);
            webView.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    //public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    Tools.LogCat("setWebViewClient::shouldOverrideUrlLoading::url: "+url);
                    webView.loadUrl(url);
                    CookieManager.getInstance().setAcceptCookie(true);
                    return true;
                    //return super.shouldOverrideUrlLoading(view, request);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    Tools.LogCat("setWebViewClient::onPageFinished::url: "+url);
                }
            });
            webView.setWebChromeClient(new WebChromeClient(){
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    super.onProgressChanged(view, newProgress);
                    try {
                        progressBar.setProgress(newProgress);
                        if ( newProgress < 100 && progressBar.getVisibility()==ProgressBar.GONE) {
                            progressBar.setVisibility(ProgressBar.VISIBLE);
                        }
                        if (newProgress == 100) {
                            progressBar.setVisibility(ProgressBar.GONE);
                        }
                    }catch (Exception e){
                        Tools.LogCat( "onProgressChanged", e);
                    }
                }

                @Override
                public void onReceivedTitle(WebView view, String title) {
                    super.onReceivedTitle(view, title);
                    setTitle( title );
                    sharedPreferences = new Shared_Preferences( context );
                    sharedPreferences.setPageWebView(view.getUrl());
                    Tools.LogCat("setWebChromeClient::onReceivedTitle:: getUrl:"+ view.getUrl() + "\ntitle:"+title );
                }
            });

            webView.loadUrl(url_api);
        } catch (Exception e) {
            Tools.LogCat(this, "setUpNavigationView", e);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }
}
