package net.braindev.microsoftlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import net.braindev.microsoftlearn.Utilities.Constant;
import net.braindev.microsoftlearn.Utilities.Shared_Preferences;
import net.braindev.microsoftlearn.Utilities.Tools;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //region [ ATTRIUTS ]
    public Context context = MainActivity.this;
    private WebView webView;
    private ProgressDialog progressDialog;
    public Shared_Preferences sharedPreferences;
    String url_Api ="";
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            progressDialog = new ProgressDialog(this);
            sharedPreferences = new Shared_Preferences(context);
            url_Api = sharedPreferences.getPageWebView();
            GetDefaultLanguage( );

            webView = this.findViewById(R.id.webView);

            if (savedInstanceState == null) {
                initControls();
            } else {
                LoadUrlWebView(url_Api);
            }
        }catch (Exception e){
            Tools.LogCat(this, "onCreate", e);
        }
    }


    private void initControls() {
        try {
            progressDialog.setMessage(getString(R.string.connection_Wait));
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
            progressDialog.show();

            LoadUrlWebView( url_Api );
        }catch (Exception e){
            Tools.LogCat(this, "setUpNavigationView", e);
        }
    }
    private void LoadUrlWebView( String url_api ) {
        try {
            webView.setWebViewClient(new WebViewClient());
            webView.setWebChromeClient(new MyWebChromeClient( url_api ));
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setSupportZoom(true);
            webView.getSettings().setAllowContentAccess(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setDisplayZoomControls(false);

            webView.loadUrl(url_api);
        } catch (Exception e) {
            Tools.LogCat(this, "setUpNavigationView", e);
        }
    }

    private class MyWebChromeClient extends WebChromeClient {
        private String urlAccount;

        public MyWebChromeClient( String urlAccount ) {
            this.urlAccount = urlAccount;
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            try {
                //Tools.LogCat(context, "INSIDE MyWebChromeClient | onProgressChanged / newProgress1:" + newProgress);
                progressDialog.setMessage(newProgress + "% " + getString(R.string.connection_Wait));
                if (newProgress < 100 && !progressDialog.isShowing()) {
                    if (progressDialog != null)
                        progressDialog.show();
                }
                if (newProgress == 100) {
                    if (progressDialog != null)
                        progressDialog.dismiss();
                }
            }catch (Exception e){
                Tools.LogCat( "onProgressChanged", e);
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);

            sharedPreferences = new Shared_Preferences( context );
            sharedPreferences.setPageWebView(view.getUrl());
        }

    }


    public void GetDefaultLanguage( ) {
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

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                langue = Locale.getDefault().toLanguageTag(); //     ---> en-US
                url_Api = Constant.getUrlMicrosoftLearn(langue);
                Tools.LogCat( url_Api );
                Tools.LogCat( langue );
            }else{
                langue = langue.replace("_","-"); //     ---> en-US
                url_Api = Constant.getUrlMicrosoftLearn(langue);
                Tools.LogCat( url_Api );
                Tools.LogCat( langue );
            }
        }catch (Exception ex) {
            Tools.LogCat( "Exception:GetDefaultLanguage()", ex);
        }
    }

}
