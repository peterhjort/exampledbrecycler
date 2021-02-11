package com.example.exampledbrecycler

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity
class Player(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val team: String,
    val year: Int,
    val points: Int
)

@Dao
interface PlayerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(player: Player)
    @Query("select * from Player order by team")
    fun getAll(): LiveData<List<Player>>
}