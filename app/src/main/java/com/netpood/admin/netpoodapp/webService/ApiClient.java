package com.netpood.admin.netpoodapp.webService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jaberALU on 22/08/2017.
 */
public class ApiClient {
  public static final String BaseUrl = "http://www.jaberbabaki.ir/rayka/";
  public static Retrofit retrofit = null;

  public static Retrofit getApiClient() {
    if (retrofit == null) {
      retrofit = new Retrofit.Builder()
        .baseUrl(BaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    }
    return retrofit;
  }
}
