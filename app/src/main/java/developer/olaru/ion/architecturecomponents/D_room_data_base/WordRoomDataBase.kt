package developer.olaru.ion.architecturecomponents.D_room_data_base

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask

import developer.olaru.ion.architecturecomponents.D_room_data_base.dao.WordDao
import developer.olaru.ion.architecturecomponents.D_room_data_base.entity.Word

@Database(entities = arrayOf(Word::class), version = 1)//When you modify the database schema, you'll need to update the version number and define how to handle migrations
abstract class WordRoomDataBase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    private class PopulateDbAsync internal constructor(db: WordRoomDataBase) : AsyncTask<Void, Void, Void>() {

        private val mDao: WordDao = db.wordDao()

        override fun doInBackground(vararg params: Void): Void? {
            mDao.deleteAll()
            var word = Word("Hello")
            mDao.insert(word)
            word = Word("World")
            mDao.insert(word)
            return null
        }
    }

    companion object {

        private var INSTANCE: WordRoomDataBase? = null

        fun getDatabase(context: Context): WordRoomDataBase? {
            if (INSTANCE == null) {
                synchronized(WordRoomDataBase::class.java) {
                    if (INSTANCE == null) {
                        //Create database here
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                WordRoomDataBase::class.java, "word_database")
                                .addCallback(sRoomdataBaseCallback)
                                .build()
                    }
                }
            }
            return INSTANCE
        }

        private val sRoomdataBaseCallback = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(INSTANCE!!).execute()
            }
        }
    }
}
