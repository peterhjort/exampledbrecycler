package com.example.exampledbrecycler

object PlayerRepository {
    suspend fun addPlayer(name: String, team: String, year: Int, points: Int) {
        PlayerDB.getInstance()
            .playerDAO
            .insertOrUpdate(Player(0, name, team, year, points))
    }
    val players = PlayerDB.getInstance().playerDAO.getAll()
}