package com.adventurekit.node_coyote.guardiannewslistr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by node_coyote on 4/5/17.
 */

public class ArticleAdapter extends ArrayAdapter<NewsArticle> {

    /**
     * Constructor for building a new NewsArticle UI
     *
     * @param context  Given context
     * @param articles A list of NewsArticles to fill a ListView
     */
    public ArticleAdapter(@NonNull Context context, ArrayList<NewsArticle> articles) {
        super(context, 0, articles);
    }

    /**
     * @param position     the position of the item being selected
     * @param listItemCard a card view
     * @param parent       the parent
     * @return a card view packed with a News Article
     */
    @Override
    public View getView(int position, View listItemCard, ViewGroup parent) {

        // If there are no News Article cards, add them.
        if (listItemCard == null) {
            listItemCard = LayoutInflater.from(getContext()).inflate(R.layout.card_view_frag, parent, false);
        }

        final NewsArticle currentArticle = getItem(position);

        String articleTitle = currentArticle.getArticleTitle();
        TextView titleTextView = (TextView) listItemCard.findViewById(R.id.news_article_title);
        if (articleTitle != null && !articleTitle.isEmpty()) {
            titleTextView.setText(articleTitle);
        }

        String sectionName = currentArticle.getArticleSectionName();
        TextView sectionNameTextView = (TextView) listItemCard.findViewById(R.id.news_article_section_name);
        if (sectionName != null && !sectionName.isEmpty()) {
            sectionNameTextView.setText(sectionName);
        }

        String pubDateOriginal = currentArticle.getDatePublished();
        TextView pubDateTextView = (TextView) listItemCard.findViewById(R.id.news_article_date_published);
        if (pubDateOriginal != null && !pubDateOriginal.isEmpty()) {
            String newPubDate = pubDateOriginal.substring(0, 10);
            pubDateTextView.setText(newPubDate);
        }

        return listItemCard;
    }
}
