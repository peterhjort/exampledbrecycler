package com.example.exampledbrecycler

import androidx.lifecycle.LiveData

class PlayerRepository {
    suspend fun addPlayer(name: String, team: String, year: Int, points: Int) {
        PlayerDB.getInstance()
            .playerDAO
            .insertOrUpdate(Player(0, name, team, year, points))
    }
    fun getPlayers(): LiveData<List<Player>> = PlayerDB.getInstance().playerDAO.getAll()
}