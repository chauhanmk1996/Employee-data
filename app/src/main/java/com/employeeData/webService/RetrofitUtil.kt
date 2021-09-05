package com.employeeData.webService

import com.employeeData.constant.NetworkConstants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitUtil {

    /*
        Setup Retrofit Api Service
    */
    fun apiService(
        baseUrl: String = NetworkConstants.BASE_URL_FOR_DEVELOPMENT,
        connectionTimeOutInSec: Long = 30,
        readTimeOutInSec: Long = 90,
        writeTimeOutInSec: Long = 360

    ): ApiInterface {
        return getRetrofitClient(
            getOkHttpClientBuilder(
                connectionTimeOutInSec,
                readTimeOutInSec,
                writeTimeOutInSec
            ), baseUrl
        ).create(ApiInterface::class.java)
    }

    /*
        Get Retrofit Client
    */
    private fun getRetrofitClient(okHttpClientBuilder: OkHttpClient.Builder, baseUrl: String) =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(okHttpClientBuilder.build())
            .baseUrl(baseUrl)
            .build()

    /*
        Ok Http Client Builder
    */
    private fun getOkHttpClientBuilder(
        connectTimeoutInSec: Long,
        readTimeoutInSec: Long,
        writeTimeoutInSec: Long
    ): OkHttpClient.Builder {
        val okHttpClientBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
        okHttpClientBuilder.connectTimeout(connectTimeoutInSec, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(readTimeoutInSec, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(writeTimeoutInSec, TimeUnit.SECONDS)
        return okHttpClientBuilder
    }
}