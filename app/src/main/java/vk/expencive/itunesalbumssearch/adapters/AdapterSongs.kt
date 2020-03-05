package vk.expencive.itunesalbumssearch.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vk.expencive.itunesalbumssearch.R
import vk.expencive.itunesalbumssearch.models.Song
import java.text.SimpleDateFormat
import java.util.*


class AdapterSongs(songsList: List<Song>, context: Context) :
    RecyclerView.Adapter<AdapterSongs.MySongsViewHolder>() {
    private var songsList: List<Song> = emptyList()
    private val context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySongsViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_songs, parent, false)
        return MySongsViewHolder(view)
    }

    override fun onBindViewHolder(viewHolders: MySongsViewHolder, position: Int) {
        val holder = viewHolders
        val song: Song = songsList[position]


        val cal: Calendar = Calendar.getInstance()
        cal.setTimeInMillis((song.trackTimeMillis).toLong())
        val format = SimpleDateFormat("mm:ss")

        holder.trackName.setText(song.trackName)
        holder.trackTime.setText(format.format(cal.getTime()))

    }

    override fun getItemCount(): Int {
        return songsList.size
    }


    inner class MySongsViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var trackName: TextView
        var trackTime: TextView
        init {
            trackName = itemView.findViewById(R.id.song_title)
            trackTime = itemView.findViewById(R.id.song_time)
        }
    }

    init {
        this.songsList = songsList
        this.context = context
    }
}