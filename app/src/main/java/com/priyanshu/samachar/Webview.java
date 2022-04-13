package com.priyanshu.samachar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class Webview extends AppCompatActivity {
    Toolbar toolbar;
    WebView webview;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_webview);
        back = findViewById(R.id.back);
        toolbar =findViewById(R.id.toolbar);
        webview = findViewById(R.id.webview);
        setSupportActionBar(toolbar);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Webview.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        Intent intent =getIntent();
        String url = intent.getStringExtra("url");
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(url);
    }
}