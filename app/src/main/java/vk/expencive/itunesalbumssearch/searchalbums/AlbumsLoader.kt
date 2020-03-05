package vk.expencive.itunesalbumssearch.searchalbums

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vk.expencive.itunesalbumssearch.api.ApiClient
import vk.expencive.itunesalbumssearch.api.ApiInterface
import vk.expencive.itunesalbumssearch.models.Album
import vk.expencive.itunesalbumssearch.models.Results

class AlbumsLoader {
    private val TAG = "AlbumsLoader"

    private var mAlbumsList: MutableLiveData<List<Album>?>? = null



    fun getAlbumsList(): MutableLiveData<List<Album>?>? {
        if (mAlbumsList == null) {
            mAlbumsList = MutableLiveData()
        }
        return mAlbumsList
    }

    fun loadAlbums(albumSearchWord: String) {

        val apiInterface: ApiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)

        val call: Call<Results> = apiInterface.getAlbumsSearch(albumSearchWord)

        call.enqueue(object : Callback<Results> {

            override fun onResponse(call: Call<Results>, response: Response<Results>) {

                if (response.isSuccessful) {
                    val albums = response.body()!!

                    val albumsList = albums.results?.sortedBy { it?.collectionName }

                    mAlbumsList?.setValue(albumsList as List<Album>?)







                    Log.d(TAG, "onResponse: ${albumsList?.size}")

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
