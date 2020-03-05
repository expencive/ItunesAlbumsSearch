package vk.expencive.itunesalbumssearch.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import vk.expencive.itunesalbumssearch.models.Results

interface ApiInterface {

    @GET("search")
    fun getAlbumsSearch(
        @Query("term") searchword: String,
        @Query("entity") entity: String = "album",
        @Query("attribute") attribute: String = "albumTerm"
    ): Call<Results>


    @GET("lookup")
    fun getAlbumInfo(
        @Query("id") id: String,
        @Query("media") mediaSource: String = "music",
        @Query("entity") entity: String = "song",
        @Query("attribute") attribute: String = "songTerm"
    ): Call<Results>


}