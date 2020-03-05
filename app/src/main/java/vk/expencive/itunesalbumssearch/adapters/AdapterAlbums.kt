package vk.expencive.itunesalbumssearch.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import vk.expencive.itunesalbumssearch.R
import vk.expencive.itunesalbumssearch.models.Album


class AdapterAlbums(albumsList: List<Album>, context: Context) :
    RecyclerView.Adapter<AdapterAlbums.MyAlbumsViewHolder>() {
    private var albumsList: List<Album> = emptyList()
    private val context: Context
    private var onItemClickListener: OnItemClickListener? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyAlbumsViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_albums, parent, false)
        return MyAlbumsViewHolder(view, onItemClickListener!!)
    }

    override fun onBindViewHolder(viewHolders: MyAlbumsViewHolder, position: Int) {
        val holder = viewHolders
        val album: Album = albumsList[position]

        holder.artistName.setText(album.artistName)
        holder.collectionName.setText(album.collectionName)
        holder.trackCount.setText("Track count ${album.trackCount}")
        holder.country.setText(album.country)
        holder.copyright.setText(album.copyright)
        holder.genre.setText(album.primaryGenreName)

        Glide
            .with(context)
            .load(album.artworkUrl100)
            .centerInside()
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.imageViewAlbum);

    }

    override fun getItemCount(): Int {
        return albumsList.size
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, collectionId: String?)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    inner class MyAlbumsViewHolder(itemView: View, onItemClickListener: OnItemClickListener) :
        ViewHolder(itemView), View.OnClickListener {
        var artistName: TextView
        var collectionName: TextView
        var trackCount: TextView
        var country: TextView
        var copyright: TextView
        var genre: TextView
        var imageViewAlbum: ImageView
        var onItemClickListener: OnItemClickListener


        override fun onClick(v: View) {
            onItemClickListener.onItemClick(
                v,
                albumsList[adapterPosition].collectionId
            )
        }

        init {
            itemView.setOnClickListener(this)
            artistName = itemView.findViewById(R.id.artist_name)
            collectionName = itemView.findViewById(R.id.collection_name)
            trackCount = itemView.findViewById(R.id.track_count)
            country = itemView.findViewById(R.id.country)
            copyright = itemView.findViewById(R.id.copyright)
            genre = itemView.findViewById(R.id.genre)
            imageViewAlbum = itemView.findViewById(R.id.albums_image)
            this.onItemClickListener = onItemClickListener
        }
    }

    init {
        this.albumsList = albumsList
        this.context = context
    }
}
