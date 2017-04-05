package com.adventurekit.node_coyote.guardiannewslistr;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by node_coyote on 4/5/17.
 */

public class ArticleLoader extends AsyncTaskLoader<List<NewsArticle>> {

    private String mUrl;

    public ArticleLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public List<NewsArticle> loadInBackground() {

        if (mUrl == null) {
            return null;
        }
        List<NewsArticle> articles = QueryUtility.fetchNewsData(mUrl);

        return articles;
    }

    @Override
    protected void onStartLoading(){
        forceLoad();
    }
}
