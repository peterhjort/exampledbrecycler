package com.example.exampledbrecycler

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exampledbrecycler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: PlayerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        adapter = PlayerListAdapter(this)
        binding.playerView.adapter = adapter
        binding.playerView.layoutManager = LinearLayoutManager(this)

        viewModel.players.observe(this) {
            adapter.submitList(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.addPlayer -> {
                startActivity(Intent(this, AddPlayerActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

class PlayerListAdapter(private val context: Context): ListAdapter<Player, PlayerViewHolder>(PlayerDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.player_item_view, parent, false)
        return PlayerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        // could use binding? anyway, skipped here
        holder.itemView.findViewById<TextView>(R.id.playername).text = getItem(position).name
        holder.itemView.findViewById<TextView>(R.id.playerteam).text = getItem(position).team
    }
}

class PlayerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

class PlayerDiffCallback: DiffUtil.ItemCallback<Player>() {
    override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem.name == newItem.name && oldItem.team == newItem.team
    }
}

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = PlayerRepository()
    val players = repository.getPlayers()
}