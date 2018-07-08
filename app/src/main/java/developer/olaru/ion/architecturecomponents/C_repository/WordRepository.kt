package developer.olaru.ion.architecturecomponents.C_repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import developer.olaru.ion.architecturecomponents.D_room_data_base.WordRoomDataBase
import developer.olaru.ion.architecturecomponents.D_room_data_base.dao.WordDao
import developer.olaru.ion.architecturecomponents.D_room_data_base.entity.Word

class WordRepository(application: Application) {

    private var mWordDao: WordDao?=null
    private var mAllWords: LiveData<List<Word>>?

    init {
        val db = WordRoomDataBase.getDatabase(application)
        mWordDao = db?.wordDao()
        mAllWords = mWordDao?.allWords

    }

    fun getmAllWords(): LiveData<List<Word>>? {

        return mAllWords
    }

    fun insert(word: Word) {

        insertAsyncTask(mWordDao).execute(word)
    }


    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: WordDao?) : AsyncTask<Word, Void, Void>() {

        override fun doInBackground(vararg params: Word): Void? {
            mAsyncTaskDao?.insert(params[0])
            return null
        }
    }

}
