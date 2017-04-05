package com.adventurekit.node_coyote.guardiannewslistr;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

/**
 * Created by node_coyote on 4/5/17.
 */

public class ArticleAdapter extends ArrayAdapter<NewsArticle> {
    
    public ArticleAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }
}
