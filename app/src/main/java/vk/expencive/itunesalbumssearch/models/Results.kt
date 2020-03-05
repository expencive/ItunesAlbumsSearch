package vk.expencive.itunesalbumssearch.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Results {

    @SerializedName("resultCount")
    @Expose
    var totalResult = 0

    @SerializedName("results")
    @Expose
    var results: List<Album?>? = null




}