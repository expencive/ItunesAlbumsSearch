package vk.expencive.itunesalbumssearch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_album_detail.*
import vk.expencive.itunesalbumssearch.adapters.AdapterSongs
import vk.expencive.itunesalbumssearch.detailalbum.DetailAlbumViewModel
import vk.expencive.itunesalbumssearch.models.Album
import vk.expencive.itunesalbumssearch.models.Song

class DetailAlbumActivity : AppCompatActivity() {

    private val TAG = "AlbumActivity"

    private lateinit var adapter: AdapterSongs;
    private lateinit var recyclerView: RecyclerView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val collectionId = intent.getStringExtra("collectionId")

        val albumViewModel: DetailAlbumViewModel =
            ViewModelProvider(this).get(DetailAlbumViewModel::class.java)

        albumViewModel.getSongsList()?.observe(this, Observer{songs ->

            Log.d(TAG, "onResponse:  ${songs?.size}")
            setRecyclerView(songs!!)


        })
        albumViewModel.getAlbumInfo()?.observe(this, Observer{album ->

            Log.d(TAG, "onResponse:  ${album?.collectionName}")
            setAlbumInfo(album!!)


        })

        albumViewModel.loadSongsAndAlbumInfo(collectionId)
    }

    private fun setRecyclerView(songsList: List<Song>) {
        adapter = AdapterSongs(songsList, this)
        recyclerView = findViewById(R.id.recycler_songs)
        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        recyclerView.setAdapter(adapter)
    }


    private fun setAlbumInfo(albumInfo: Album){

        artist_name.setText(albumInfo.artistName)
        collection_name.setText(albumInfo.collectionName)
        track_count.setText("Tracks ${albumInfo.trackCount}")
        country.setText(albumInfo.country)
        genre.setText(albumInfo.primaryGenreName)
        copyright.setText(albumInfo.copyright)

        Glide
            .with(this)
            .load(albumInfo.artworkUrl100)
            .centerInside()
            .placeholder(R.drawable.ic_launcher_background)
            .into(albums_image);

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
