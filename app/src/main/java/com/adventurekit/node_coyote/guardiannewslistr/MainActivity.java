package com.adventurekit.node_coyote.guardiannewslistr;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsArticle>> {

    public static final String GUARDIAN_BASE_URL = "https://content.guardianapis.com/search?q=";
    public static final String API_KEY = "&api-key=test";

    private String mArticleUrl;
    private ArticleAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find a reference to the {@link ListView}
        ListView articleListView = (ListView) findViewById(R.id.list);

        // Create a new Array of News Articles and attach it to an adapter
        mAdapter = new ArticleAdapter(this, new ArrayList<NewsArticle>());
        articleListView.setAdapter(mAdapter);

        LoaderManager loaderManager = getLoaderManager();

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            loaderManager.initLoader(1, null, MainActivity.this);
        }
    }

    private String urlAssembler(){

        String newsQuery = "art";

        mArticleUrl = GUARDIAN_BASE_URL+ newsQuery + API_KEY;

        return mArticleUrl;
    }

    @Override
    public Loader<List<NewsArticle>> onCreateLoader(int id, Bundle args) {
        urlAssembler();
        mAdapter.clear();
        return new ArticleLoader(MainActivity.this, mArticleUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsArticle>> loader, List<NewsArticle> articles) {

        if (articles != null && !articles.isEmpty()){
            mAdapter.addAll(articles);
        }

        mAdapter.clear();
    }

    @Override
    public void onLoaderReset(Loader<List<NewsArticle>> loader) {
        mAdapter.clear();
    }
}
