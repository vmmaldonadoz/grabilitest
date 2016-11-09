package com.thirdmono.grabilitest.data.api;

import com.thirdmono.grabilitest.data.entity.FeedWrapper;
import com.thirdmono.grabilitest.domain.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * API interface for retrieving the most popular apps in the App Store.
 *
 * @author <a href="mailto:vmmzn20@gmail.com">Victor Maldonado</a>
 * @since 1.0
 */
public interface FreeAppsService {

    @GET(Constants.QUERY)
    Call<FeedWrapper> getFeedResponse();

}
