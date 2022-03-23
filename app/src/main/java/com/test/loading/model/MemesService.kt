package com.test.loading.model

import android.util.Log
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MemesService {
    val url = "https://api.imgflip.com/"

    private val apiData :MemesAPI
    init {
      apiData=  Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
          .create(MemesAPI::class.java)
           Log.e("callapi", "true${apiData.getMemesList()}")

    }


//    fun getMemesAPIData():Single<List<Memes>>{
//        return   apiData
//            .getMemesList()
//    }

    fun getMemesAPIData(): Single<List<Memes>>? {
        var result = apiData.getMemesList().body()
        Log.e("abc: ", result.toString())
        return result?.data?.memes

    }

}