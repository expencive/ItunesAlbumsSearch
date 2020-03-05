package vk.expencive.itunesalbumssearch.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Song {

    @SerializedName("wrapperType")
    @Expose
    var wrapperType: String? = null


    @SerializedName("trackName")
    @Expose
    var trackName: String? = null


    @SerializedName("trackNumber")
    @Expose
    var trackNumber = 0

    @SerializedName("trackTimeMillis")
    @Expose
    var trackTimeMillis = 0


}
