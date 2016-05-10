package com.sky31.gonggong.module.article.detail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.gc.materialdesign.widgets.ProgressDialog;
import com.sky31.gonggong.R;
import com.sky31.gonggong.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ArticleDetailActivity extends BaseActivity {


    /***
     *  本 Activity主要用于网页跳转。所有必须通过该网页跳转的详情页都可以通过此Activity打开。
     */
    @Bind(R.id.article_detail_toolbar)
    Toolbar articleDetailToolbar;
    @Bind(R.id.article_show_detail_webview)
    WebView articleShowDetailWebview;
    @Bind(R.id.article_detail_progressbar)
    ProgressBar articleDetailProgressbar;


    ProgressDialog progressDialog;
    private String url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        ButterKnife.bind(this);


        if (getIntent()!=null){
            initToolbar();
            initWebView();
        }
        else {
            finish();
        }


        progressDialog = new ProgressDialog(this,"翼宝在拼命地加载");
        //progressDialog.setTitle("");

    }

    private void initWebView() {


        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        articleShowDetailWebview.loadUrl(url);
        articleDetailToolbar.setTitle(intent.getStringExtra("title"));
        articleShowDetailWebview.getSettings().setJavaScriptEnabled(true);
        articleShowDetailWebview.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {


                super.onPageStarted(view, url, favicon);
                progressDialog.show();
                articleShowDetailWebview.setClickable(false);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                progressDialog.dismiss();
                articleShowDetailWebview.setClickable(true);
            }
        });

    }


    private void initToolbar() {

        setSupportActionBar(articleDetailToolbar);
        articleDetailToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
