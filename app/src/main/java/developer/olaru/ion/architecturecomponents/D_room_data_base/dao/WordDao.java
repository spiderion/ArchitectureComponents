package developer.olaru.ion.architecturecomponents.D_room_data_base.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;
import developer.olaru.ion.architecturecomponents.D_room_data_base.entity.Word;

@Dao
public interface WordDao {

    @Insert
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();

    // there are also @Delete and @Update but we are not using this in the app
}
