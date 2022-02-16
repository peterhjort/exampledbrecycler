package com.example.exampledbrecycler

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.exampledbrecycler.databinding.ActivityAddPlayerBinding
import kotlinx.coroutines.launch

class AddPlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPlayerBinding
    private lateinit var viewModel: AddPlayerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_player)
        viewModel = ViewModelProvider(this).get(AddPlayerViewModel::class.java)

        binding.button.setOnClickListener {
            if(binding.name.text.isNotEmpty()
                && binding.team.text.isNotEmpty()
                && binding.year.text.isNotEmpty()
                && binding.points.text.isNotEmpty()) {
                viewModel.addPlayer(
                    binding.name.text.toString(),
                    binding.team.text.toString(),
                    binding.year.text.toString().toIntOrNull() ?: 0,
                    binding.points.text.toString().toIntOrNull() ?: 0
                )
                Toast.makeText(applicationContext, "Player added.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Not good.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

class AddPlayerViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = PlayerRepository()

    fun addPlayer(name: String, team: String, year: Int, points: Int) {
        viewModelScope.launch {
            repository.addPlayer(name, team, year, points)
        }
    }
}