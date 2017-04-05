package com.adventurekit.node_coyote.guardiannewslistr;

/**
 * Created by node_coyote on 4/3/17.
 */

public class NewsArticle {

    private String mArticleTitle;
    private String mArticleSectionName;
    private String mAuthor;
    private String mDatePublished;
    private String mArticleUrl;

    /**
     * Constructor for bare minimum news information
     *
     * @param articleTitle       the Title of article
     * @param articleSectionName the Section name of the article
     * @param articleUrl         when tapped, the ListView item should redirect user to this url
     */
    public NewsArticle(String articleTitle, String articleSectionName, String articleUrl) {
        mArticleTitle = articleTitle;
        mArticleSectionName = articleSectionName;
        mArticleUrl = articleUrl;
    }

    /**
     * Constructor for the case of no date published
     *
     * @param articleTitle       the Title of article
     * @param articleSectionName the Section name of the article
     * @param author             the Author of the News article
     * @param articleUrl         when tapped, the ListView item should redirect user to this url
     */
    public NewsArticle(String articleTitle, String articleSectionName, String author, String articleUrl) {
        mArticleTitle = articleTitle;
        mArticleSectionName = articleSectionName;
        mAuthor = author;
        mArticleUrl = articleUrl;
    }

    /**
     * Constructor for the case of all members queried
     *
     * @param articleTitle       the Title of article
     * @param articleSectionName the Section name of the article
     * @param author             the Author of the News article
     * @param datePublished      the date the News article was published
     * @param articleUrl         when tapped, the ListView item should redirect user to this url
     */
    public NewsArticle(String articleTitle, String articleSectionName, String author, String datePublished, String articleUrl) {
        mArticleTitle = articleTitle;
        mArticleSectionName = articleSectionName;
        mAuthor = author;
        mDatePublished = datePublished;
        mArticleUrl = articleUrl;
    }

    public String getArticleTitle() {
        return mArticleTitle;
    }

    public String getArticleSectionName() {
        return mArticleSectionName;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getDatePublished() {
        return mDatePublished;
    }
}
