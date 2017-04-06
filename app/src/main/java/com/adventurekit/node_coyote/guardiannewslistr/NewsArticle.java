package com.adventurekit.node_coyote.guardiannewslistr;

/**
 * Created by node_coyote on 4/3/17.
 */

public class NewsArticle {

    public final String mArticleTitle;
    public final String mArticleSectionName;
    public final String mDatePublished;
    public final String mArticleUrl;

    /**
     * Constructor for the case of all members queried
     * @param articleTitle       the Title of article
     * @param articleSectionName the Section name of the article
     * @param datePublished      the date the News article was published
     * @param articleUrl         when tapped, the ListView item should redirect user to this url
     */
    public NewsArticle(String articleTitle, String articleSectionName, String datePublished, String articleUrl) {
        mArticleTitle = articleTitle;
        mArticleSectionName = articleSectionName;
        mDatePublished = datePublished;
        mArticleUrl = articleUrl;
    }

    public String getArticleTitle() {
        return mArticleTitle;
    }

    public String getArticleSectionName() {
        return mArticleSectionName;
    }

    public String getDatePublished() {
        return mDatePublished;
    }

    public String getArticleUrl(){
        return mArticleUrl;
    }
}
