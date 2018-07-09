package developer.olaru.ion.architecturecomponents.cRepository

import android.app.Application
import android.arch.lifecycle.LiveData
import developer.olaru.ion.architecturecomponents.dRoomDataBase.WordRoomDataBase
import developer.olaru.ion.architecturecomponents.dRoomDataBase.dao.WordDao
import developer.olaru.ion.architecturecomponents.dRoomDataBase.entity.Word
import developer.olaru.ion.architecturecomponents.utils.App
import developer.olaru.ion.architecturecomponents.utils.dagger2.DaggerAppComponent
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class WordRepository( application: Application ) {

    private var mWordDao: WordDao?=null
    private var mAllWords: LiveData<List<Word>>?

    @Inject
    lateinit var dataBase: WordRoomDataBase

    init {
        (application as App).component.inject(this)

        mWordDao = dataBase.wordDao()
        mAllWords = mWordDao?.allWords
    }


    fun getAllWords(): LiveData<List<Word>>? = mAllWords

    // using coroutines to insert in the database on the  background thread
    fun insert(word: Word) { launch(CommonPool){ mWordDao?.insert(word)}}

    fun deleteWord(word: Word){ launch(CommonPool){mWordDao?.deleteWord(word)}}




}
