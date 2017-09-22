package com.netpood.admin.netpoodapp.webService;

import com.netpood.admin.netpoodapp.database.LoginItem;
import com.netpood.admin.netpoodapp.database.PoodItem;
import com.netpood.admin.netpoodapp.database.PostItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by jaberALU on 22/08/2017.
 */
public interface ApiInterface {

  @POST("index.php?action=readPouplar")
  Call<ArrayList<PostItem>> getMainItem(@Query("page") int page );

 // , @Header("User-Agent") String app, @Header("code") String code, @Header("country") String country
  @POST("index.php?action=readNumber")
  Call<ArrayList<LoginItem>> login(@Query("number") String number) ;


  @POST("index.php?action=readPood")
  Call<ArrayList<PoodItem>> selectItem();
}
