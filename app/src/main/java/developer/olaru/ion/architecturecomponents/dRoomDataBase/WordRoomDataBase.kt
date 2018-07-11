package developer.olaru.ion.architecturecomponents.dRoomDataBase

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import developer.olaru.ion.architecturecomponents.dRoomDataBase.dao.WordDao
import developer.olaru.ion.architecturecomponents.dRoomDataBase.entity.Word

@Database(entities = [Word::class], version = 1)//When you modify the database schema, you'll need to update the version number and define how to handle migrations
abstract class WordRoomDataBase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {

        private var INSTANCE: WordRoomDataBase? = null

        fun getDatabase(context: Context): WordRoomDataBase {
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
            return INSTANCE!!
        }

        private val sRoomdataBaseCallback = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

            /*    launch(CommonPool){ //when the app starts a background thread deletes all the word and add 2 new ones
                    val mDao = INSTANCE?.wordDao()
                    var word = Word("Hello")
                    mDao?.deleteAll() // this part deletes all the words
                    mDao?.insert(word)
                    word = Word("World")
                    mDao?.insert(word)
                }*/
                
            }
        }
    }
}
