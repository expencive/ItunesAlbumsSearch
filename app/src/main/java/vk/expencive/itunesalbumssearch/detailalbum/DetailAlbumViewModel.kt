package vk.expencive.itunesalbumssearch.detailalbum

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vk.expencive.itunesalbumssearch.models.Album
import vk.expencive.itunesalbumssearch.models.Song


class DetailAlbumViewModel : ViewModel() {


    private var mSongsList: MutableLiveData<List<Song>?>? = null
    private var mAlbumInfo: MutableLiveData<Album?>? = null


    var mSongsLoader: SongsLoader = SongsLoader()


    fun getSongsList(): MutableLiveData<List<Song>?>? {
        if (mSongsList == null) {
            mSongsList = mSongsLoader.getSongsList()
        }
        return mSongsList
    }

    fun getAlbumInfo(): MutableLiveData<Album?>? {
        if (mAlbumInfo == null) {
            mAlbumInfo = mSongsLoader.getAlbumInfo()
        }
        return mAlbumInfo
    }


    fun loadSongsAndAlbumInfo(collectionId: String) {
        mSongsLoader.loadSongsAndAlbumInfo(collectionId)
    }


}