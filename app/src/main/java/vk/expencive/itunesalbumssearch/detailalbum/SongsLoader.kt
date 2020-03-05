package vk.expencive.itunesalbumssearch.detailalbum

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vk.expencive.itunesalbumssearch.api.ApiClient
import vk.expencive.itunesalbumssearch.api.ApiInterface
import vk.expencive.itunesalbumssearch.models.Album
import vk.expencive.itunesalbumssearch.models.Results
import vk.expencive.itunesalbumssearch.models.Song

class SongsLoader {

    private val TAG = "SongsLoader"


    private var mSongsList: MutableLiveData<List<Song>?>? = null
    private var mAlbumInfo: MutableLiveData<Album?>? = null


    fun getSongsList(): MutableLiveData<List<Song>?>? {
        if (mSongsList == null) {
            mSongsList = MutableLiveData()
        }
        return mSongsList
    }

    fun getAlbumInfo(): MutableLiveData<Album?>? {
        if (mAlbumInfo == null) {
            mAlbumInfo = MutableLiveData()
        }
        return mAlbumInfo
    }


    fun loadSongsAndAlbumInfo(collectionId: String) {

        val apiInterface: ApiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
        val call: Call<Results> = apiInterface.getAlbumInfo(collectionId)
        call.enqueue(object : Callback<Results> {

            override fun onResponse(call: Call<Results>, response: Response<Results>) {

                if (response.isSuccessful && response.body()!=null) {
                    val songs = response.body()!!

                    val results = songs.results

                    val album = Album()
                    val songsList = arrayListOf<Song>()

                    for (result in results!!){
                        if (result?.wrapperType.equals("collection")){

                            album.wrapperType = result?.wrapperType
                            album.collectionName = result?.collectionName
                            album.collectionId = result?.collectionId
                            album.artistName = result?.artistName
                            album.artworkUrl100 = result?.artworkUrl100
                            album.trackCount = result!!.trackCount
                            album.copyright = result.copyright
                            album.primaryGenreName = result.primaryGenreName
                            album.country = result.country


                        }
                        if (result?.wrapperType.equals("track")){

                            val song = Song()
                            song.wrapperType = result?.wrapperType
                            song.trackNumber = result!!.trackNumber
                            song.trackName = result.trackName
                            song.trackTimeMillis = result.trackTimeMillis

                            songsList.add(song)
                        }
                    }

                    mSongsList?.setValue(songsList as List<Song>?)
                    mAlbumInfo?.setValue(album)

                } else {
                    val errorCode: String = when (response.code()) {
                        404 -> "404. Страница не найдена или удалена"
                        500 -> "500. Сервер не отвечает"
                        else -> "Неизвестная ошибка"
                    }
                    Log.d(TAG, "onResponse: ${errorCode}")
                }
            }

            override fun onFailure(call: Call<Results>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
    }
}

