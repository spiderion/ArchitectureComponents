package developer.olaru.ion.architecturecomponents.dRoomDataBase.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.os.FileObserver.DELETE
import developer.olaru.ion.architecturecomponents.dRoomDataBase.entity.Word

@Dao
interface WordDao  {

    @get:Query("SELECT * from word_table ORDER BY word ASC")
    val allWords: LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: Word)

    @Query("DELETE FROM word_table")
    fun deleteAll()

    @Delete
    fun deleteWord(word: Word)


    // there are also @Delete and @Update but we are not using this in the app
}
