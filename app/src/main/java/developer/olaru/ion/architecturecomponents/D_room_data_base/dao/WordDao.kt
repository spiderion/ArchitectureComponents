package developer.olaru.ion.architecturecomponents.D_room_data_base.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import developer.olaru.ion.architecturecomponents.D_room_data_base.entity.Word

@Dao
interface WordDao  {

    @get:Query("SELECT * from word_table ORDER BY word ASC")
    val allWords: LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: Word)

    @Query("DELETE FROM word_table")
    fun deleteAll()

    // there are also @Delete and @Update but we are not using this in the app
}
