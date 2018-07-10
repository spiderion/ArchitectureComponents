package developer.olaru.ion.architecturecomponents

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import developer.olaru.ion.architecturecomponents.dRoomDataBase.WordRoomDataBase
import developer.olaru.ion.architecturecomponents.dRoomDataBase.entity.Word
import developer.olaru.ion.architecturecomponents.utils.blockingObserve
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
  class WordDao1Test {


    private lateinit var database: WordRoomDataBase


  @get: Rule
  var instantTaskExecutorRule = InstantTaskExecutorRule()
    @Before
    fun init(){
        database = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                WordRoomDataBase::class.java).allowMainThreadQueries()
                .build()
    }
    @After
    fun closeDb(){
        database.close()
    }
  @Test
  fun getWordWhenNorWordInserted(){
    val words = database.wordDao().allWords.blockingObserve()

   assertTrue(words!!.isEmpty())

  }


  @Test
  fun daoInsertWordTest(){

    val cashedWord = Word("hello")
    database.wordDao().insert(cashedWord)

    val words = database.wordDao().allWords.blockingObserve()


   assertEquals(words!![0],cashedWord)


  }
}
