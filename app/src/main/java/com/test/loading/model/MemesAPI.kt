package com.test.loading.model

import retrofit2.Response
import retrofit2.http.GET

interface MemesAPI {

@GET("get_memes")
fun getMemesList() : Response<InitialData>
}
