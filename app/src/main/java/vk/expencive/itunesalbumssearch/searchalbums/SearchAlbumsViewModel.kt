package vk.expencive.itunesalbumssearch.searchalbums

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import vk.expencive.itunesalbumssearch.models.Album

class SearchAlbumsViewModel : ViewModel() {


    private var mAlbumsList: MutableLiveData<List<Album>?>? = null
    var mAlbumsLoader: AlbumsLoader = AlbumsLoader()


    fun getAlbumsList(): MutableLiveData<List<Album>?>? {
        if (mAlbumsList == null) {
            mAlbumsList = mAlbumsLoader.getAlbumsList()
        }
        return mAlbumsList
    }


    fun loadAlbums(albumSearchWord: String) {
        mAlbumsLoader.loadAlbums(albumSearchWord)
    }


}