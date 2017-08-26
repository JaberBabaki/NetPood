package com.netpood.admin.netpoodapp.webService;

import com.netpood.admin.netpoodapp.database.LoginItem;
import com.netpood.admin.netpoodapp.database.PostItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by jaberALU on 22/08/2017.
 */
public interface ApiInterface {

  @POST("index.php?action=readPouplar")
  Call<ArrayList<PostItem>> getMainItem(@Body int page);

 // , @Header("User-Agent") String app, @Header("code") String code, @Header("country") String country
  @POST("index.php?action=readNumber")
  Call<ArrayList<LoginItem>> login(@Body String number);
}
