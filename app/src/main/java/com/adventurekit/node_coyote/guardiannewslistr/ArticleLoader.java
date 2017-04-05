package com.adventurekit.node_coyote.guardiannewslistr;

import android.content.AsyncTaskLoader;
import android.content.Context;

/**
 * Created by node_coyote on 4/5/17.
 */

public class ArticleLoader extends AsyncTaskLoader {

    public ArticleLoader(Context context) {
        super(context);
    }

    @Override
    public Object loadInBackground() {
        return null;
    }
}
