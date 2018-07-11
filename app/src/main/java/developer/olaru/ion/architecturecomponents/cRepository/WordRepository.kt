package developer.olaru.ion.architecturecomponents.cRepository

import android.arch.lifecycle.LiveData
import developer.olaru.ion.architecturecomponents.dRoomDataBase.WordRoomDataBase
import developer.olaru.ion.architecturecomponents.dRoomDataBase.dao.WordDao
import developer.olaru.ion.architecturecomponents.dRoomDataBase.entity.Word
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class WordRepository @Inject constructor( dataBase: WordRoomDataBase) {

    private var mWordDao: WordDao = dataBase.wordDao()

    fun getAllWords(): LiveData<List<Word>> = mWordDao.allWords

    fun insert(word: Word) { doAsync {  mWordDao.insert(word) }}

    fun deleteWord(word: Word){ doAsync {mWordDao.deleteWord(word)}}

}
