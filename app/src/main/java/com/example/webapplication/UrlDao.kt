package com.example.webapplication

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UrlDao {
    @Query("SELECT * FROM url_table")
    fun getAll(): List<ALink>

    @Query("SELECT * FROM url_table ORDER BY name ASC")
    fun getAlphabetizedWords(): Flow<List<ALink>>



    //   we are not using this
//    @Query("SELECT * FROM url_table WHERE `index` IN (:indexes)")
//    fun loadAllByIds(indexes: IntArray): List<ALink>

    @Query("SELECT * FROM url_table WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): ALink

    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = ALink::class)
    fun insertAll(vararg links: ALink)

    // TODO: 11/11/21 do not use this update method right now
    @Update(entity = ALink::class, onConflict = OnConflictStrategy.IGNORE)
    fun updateName(name: String, newName: String)

    @Delete
    fun delete(link: ALink)

    @Query("DELETE FROM url_table")
    suspend fun deleteAll()
}