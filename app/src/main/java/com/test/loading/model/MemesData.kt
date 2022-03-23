package com.test.loading.model

import io.reactivex.Single

//data class MemesData (
//    @SerializedName("memes")
//    var memes : List<Memes>
//)
//
data class MemesData( var memes: Single<List<Memes>>)
