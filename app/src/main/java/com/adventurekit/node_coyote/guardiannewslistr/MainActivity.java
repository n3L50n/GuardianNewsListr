package com.adventurekit.node_coyote.guardiannewslistr;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsArticle>> {

    public static final String GUARDIAN_BASE_URL = "https://content.guardianapis.com/search?q=";
    public static final String API_KEY = "&api-key=test";

    private String mArticleUrl;
    private ArticleAdapter mAdapter;
    private TextView mEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find a reference to the {@link ListView}
        ListView articleListView = (ListView) findViewById(R.id.list_view);

        // Find a reference to the empty{@link TextView}
        mEmpty = (TextView) findViewById(R.id.empty_text_view);

        // Create a new Array of News Articles and attach it to an adapter
        mAdapter = new ArticleAdapter(this, new ArrayList<NewsArticle>());
        articleListView.setAdapter(mAdapter);

        LoaderManager loaderManager = getLoaderManager();

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            loaderManager.initLoader(0, null, MainActivity.this);
            mEmpty.setText(R.string.nothing);

        } else {
            View loadingIndicator = findViewById(R.id.activity_indicator);
            loadingIndicator.setVisibility(View.GONE);

            mEmpty.setText(R.string.no_internet);
        }

        articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Fill an ArrayList with News Article data
                NewsArticle currentArticle = mAdapter.getItem(position);

                // Get the URL
                Uri articleUrl = Uri.parse(currentArticle.getArticleUrl());

                // Create an intent to open a web view
                Intent webIntent = new Intent(Intent.ACTION_VIEW, articleUrl);

                // Open the webView of user's choice
                startActivity(webIntent);
            }
        });
    }

    private String urlAssembler(String articleUrl) {

        mArticleUrl = articleUrl;

        String newsQuery = "art";

        mArticleUrl = GUARDIAN_BASE_URL + newsQuery + API_KEY;

        return mArticleUrl;
    }

    @Override
    public Loader<List<NewsArticle>> onCreateLoader(int id, Bundle args) {
        urlAssembler(mArticleUrl);
        Log.v("onCreateLoaderUrl", mArticleUrl);
        return new ArticleLoader(MainActivity.this, mArticleUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsArticle>> loader, List<NewsArticle> articles) {

        View loadingIndicator = findViewById(R.id.activity_indicator);
        loadingIndicator.setVisibility(View.GONE);

        mAdapter.clear();

        if (articles != null && !articles.isEmpty()) {
            mAdapter.addAll(articles);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<NewsArticle>> loader) {
        mAdapter.clear();
    }
}
