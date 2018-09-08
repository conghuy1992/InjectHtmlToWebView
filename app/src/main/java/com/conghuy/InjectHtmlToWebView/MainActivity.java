package com.conghuy.InjectHtmlToWebView;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.loadData("<html>" +
                "<head>" +
//                "<script>" +
//                "function displayDate() {" + //this is function for getting current date
//                "var d = new Date();" +
//                "document.getElementById('demo').innerHTML = 'Date: ' + d.getDate() + '-' + d.getMonth() + '-' + d.getFullYear();" +
//                "}" +
//                "</script>" +
                "</head>" +
                "<body>" +
                "<p>Hello, Android</p>" +
                "<p>Hello, I am <span id='name'></span></p>" +
                "<p id='demo'> </p>" +
                "</body>" +
                "</html>", "text/html", "UTF-8");
        ((Button) findViewById(R.id.btnInject)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ((EditText) findViewById(R.id.ed)).getText().toString();
                // with getElementById
                String javascript = "javascript: document.getElementById('name').innerHTML = '" + name + "'; displayDate();";

                // with getElementsByClassName
//                String javascript = "javascript: document.getElementsByClassName('anchor-btn actived')[0].innerHTML = '" + name + "'; huyFun();";
                webView.loadUrl(javascript);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
