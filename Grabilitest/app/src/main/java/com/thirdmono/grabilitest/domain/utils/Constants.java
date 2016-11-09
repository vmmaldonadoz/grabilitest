package com.thirdmono.grabilitest.domain.utils;

/**
 * Application constants.
 *
 * @author <a href="mailto:vmmzn20@gmail.com">Victor Maldonado</a>
 * @since 1.0
 */
public class Constants {

    public final static String API_URL = "https://itunes.apple.com/";
    public final static String GENRE_KEY = "GENRE_KEY";
    private final static int QUERY_LIMIT = 20;
    public final static String QUERY = "us/rss/topfreeapplications/limit=" + QUERY_LIMIT + "/genre={" + GENRE_KEY + "}/json";


}
