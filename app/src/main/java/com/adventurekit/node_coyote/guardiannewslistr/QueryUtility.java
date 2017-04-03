package com.adventurekit.node_coyote.guardiannewslistr;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by node_coyote on 4/3/17.
 */

public final class QueryUtility {

    // A log helper
    private static final String LOG_TAG = QueryUtility.class.getSimpleName();

    /**
     * This class is meant to hold static variables and methods,
     * accessed directly from {@link QueryUtility} class itself
     * and not an instance. Constructor is not to be used
     */
    private QueryUtility() {
        // Remain empty
    }

    /**
     * Helper method checks if a URL is legit
     * @param stringUrl
     * @return
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error creating URL", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        // An empty String to hold a response, most likely JSON
        String response  = "";

        // If empty, leave early
        if (url == null){
            return response;
        }

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            /** Check if Request is successful indicated by 200 responseCode.
             * If successful, get the input stream and begin to read from it.
             */
            if (httpURLConnection.getResponseCode() == 200){
                inputStream = httpURLConnection.getInputStream();
                response = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code " + httpURLConnection.getResponseCode());
            }
        } catch (IOException e){
            Log.e(LOG_TAG, "Problem parsing response");
        } finally {
            if (httpURLConnection != null){
                httpURLConnection.disconnect();
            }
            if (inputStream != null){
                inputStream.close();
            }
        }
        return response;
    }

    /**
     * Use a StringBuilder to assembled the output into usable data
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null){
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     *
     * @param guardianNewsJSON
     * @return
     */
    private static List<NewsArticle> extractStories(String guardianNewsJSON) {

        // Check if JSON is empty and return if so
        if (TextUtils.isEmpty(guardianNewsJSON)){
            return null;
        }

        // Create an empty array to hold the News Articles
        List<NewsArticle> stories = new ArrayList<>();

        // Attempt to parse the JSON. Throw an exception if there is a problem
        try {
            // Begin with root JSON object
            JSONObject response = new JSONObject(guardianNewsJSON);

            // Step into JSON array "items"
            JSONArray items = response.getJSONArray("items");

            // Cycle through items within the array to create new News Articles
            for (int i = 0; i < items.length(); i++ ){

                String author = null;

                // Get a Story
                JSONObject story = items.getJSONObject(i);

                // Get Volume info containing title and author
                JSONObject volumeInfo = story.getJSONObject("volumeInfo");

                // Get the title of a story
                String title = volumeInfo.getString("title");

                // Get the authors array of a story
                JSONArray authors = volumeInfo.getJSONArray("authors");

                // Cycle through Array of authors to get authors
                for (int j = 0; j < authors.length(); j++){
                    author = authors.getString(j);
                }

                NewsArticle newStory = new NewsArticle(title, author);
                stories.add(newStory);
            }

        } catch (JSONException e){
            Log.e(LOG_TAG, "Problem parsing JSON results", e);
        }
        return stories;
    }

    public static List<NewsArticle> fetchNewsData(String requestUrl){

        // Pass in the configured url
        URL url = createUrl(requestUrl);

        // Perform http request to url and receive json back
        String jsonReponse = null;
        try {
            jsonReponse = makeHttpRequest(url);
        } catch (IOException e){
            Log.e(LOG_TAG, "Error making HTTP Request", e);
        }

        List<NewsArticle> stories = extractStories(jsonReponse);

        return stories;
    }

}
