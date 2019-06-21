package com.zamil.appbelajar.helper;

import com.zamil.appbelajar.model.ResponseLogin;
import com.zamil.appbelajar.model.ResponseRegister;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestApi {

    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseRegister> registeruser(
            @Field("username") String username,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseLogin> loginuser(
            @Field("email") String email,
            @Field("password") String password
    );
}
