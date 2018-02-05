package com.note.cesar.weathernow.API;

import com.note.cesar.weathernow.models.CurrentConditionModel;
import com.note.cesar.weathernow.models.GeoPostionModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by shekh on 05-02-2018.
 */

public interface APIInterface {

    String weather_url = ConstantsHolder.WEATHER;


    @GET(ConstantsHolder.LOCATION_KEY)
    Call<GeoPostionModel> getKey(@Query("apikey") String key, @Query("q") String latLong, @Query("language") String language, @Query("details") Boolean details, @Query("toplevel") Boolean toplevel);


    @GET
    Call< List<CurrentConditionModel> > getWether(@Url String url, @Query("apikey") String key, @Query("language") String language, @Query("details") Boolean details);


}
