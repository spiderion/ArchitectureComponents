package developer.olaru.ion.architecturecomponents.cRepository

import android.arch.lifecycle.LiveData
import developer.olaru.ion.architecturecomponents.dRoomDataBase.WordRoomDataBase
import developer.olaru.ion.architecturecomponents.dRoomDataBase.dao.WordDao
import developer.olaru.ion.architecturecomponents.dRoomDataBase.entity.Word
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class WordRepository @Inject constructor( dataBase: WordRoomDataBase) {

    private var mWordDao: WordDao
    private var mAllWords: LiveData<List<Word>>?


    init {

        mWordDao = dataBase.wordDao()
        mAllWords = mWordDao.allWords
    }


    fun getAllWords(): LiveData<List<Word>>? = mAllWords

    // using coroutines to insert in the database on the  background thread
    fun insert(word: Word) { doAsync {  mWordDao.insert(word) }}

    fun deleteWord(word: Word){ doAsync {mWordDao.deleteWord(word)}}




}
