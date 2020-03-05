package vk.expencive.itunesalbumssearch.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Album {

    @SerializedName("wrapperType")
    @Expose
    var wrapperType: String? = null


    @SerializedName("collectionId")
    @Expose
    var collectionId: String? = null

    @SerializedName("artistName")
    @Expose
    var artistName: String? = null

    @SerializedName("collectionName")
    @Expose
    var collectionName: String? = null

    @SerializedName("artworkUrl100")
    @Expose
    var artworkUrl100: String? = null

    @SerializedName("trackCount")
    @Expose
    var trackCount = 0

    @SerializedName("copyright")
    @Expose
    var copyright: String? = null

    @SerializedName("primaryGenreName")
    @Expose
    var primaryGenreName: String? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

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
