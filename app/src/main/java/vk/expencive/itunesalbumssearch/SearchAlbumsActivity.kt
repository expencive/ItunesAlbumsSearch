package vk.expencive.itunesalbumssearch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_search_albums.*
import vk.expencive.itunesalbumssearch.adapters.AdapterAlbums
import vk.expencive.itunesalbumssearch.models.Album
import vk.expencive.itunesalbumssearch.searchalbums.SearchAlbumsViewModel


class SearchAlbumsActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var adapter: AdapterAlbums;
    private lateinit var recyclerView: RecyclerView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_albums)

        val albumViewModel: SearchAlbumsViewModel =
            ViewModelProvider(this).get(SearchAlbumsViewModel::class.java)
        albumViewModel.getAlbumsList()?.observe(this, Observer{albums ->

            if (albums!!.isEmpty()){
                Toast.makeText(this@SearchAlbumsActivity, "No such album",
                    Toast.LENGTH_SHORT).show()
            }
                setRecyclerView(albums)


        })

        button_search.setOnClickListener {
            val searchWord = edit_text_album.text.toString()
            if (searchWord.equals("")){

                Toast.makeText(this@SearchAlbumsActivity, "Enter your album to search",
                    Toast.LENGTH_SHORT).show()


            }else{
            albumViewModel.loadAlbums(searchWord)}
            edit_text_album.setText("")
            closeKeyboard()
        }

    }




    private fun setRecyclerView(albumsList: List<Album>) {
        adapter = AdapterAlbums(albumsList, this)
        recyclerView = findViewById<RecyclerView>(R.id.recycler_albums)
        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        recyclerView.setAdapter(adapter)
        initListener()

    }

    private fun initListener() {
        adapter.setOnItemClickListener(object : AdapterAlbums.OnItemClickListener {
            override fun onItemClick(view: View?, collectionId: String?) {

                Log.d(TAG, "onItemClick: ${collectionId}")
                val intent = Intent(this@SearchAlbumsActivity, DetailAlbumActivity::class.java)
                intent.putExtra("collectionId", collectionId)
                startActivity(intent)
            }
        })
    }

    private fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }


    }


}
