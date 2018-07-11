package developer.olaru.ion.architecturecomponents

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import developer.olaru.ion.architecturecomponents.dRoomDataBase.WordRoomDataBase
import developer.olaru.ion.architecturecomponents.dRoomDataBase.entity.Word
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.jetbrains.anko.doAsync
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
  fun init() {
    database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getContext(),
            WordRoomDataBase::class.java).allowMainThreadQueries()
            .build()
  }

  @After
  fun closeDb() {
    database.close()
  }

  @Test
  fun listWordIsEmptyWhenNorWordInsertedTest() {
    val words = database.wordDao().allWords.blockingObserve()

    assertTrue(words!!.isEmpty())

  }


  @Test
  fun wordInsertedMatchWordFetchedTest() {

    val cashedWord = Word("hello")
    database.wordDao().insert(cashedWord)

    val words = database.wordDao().allWords.blockingObserve()


    assertEquals(words!![0], cashedWord)


  }

  @Test
  fun insertAndDeleteOneWordTest() {

    val word = Word("goingToBeDeleted")
    database.wordDao().insert(word)

    database.wordDao().deleteWord(word)

    val words = database.wordDao().allWords.blockingObserve()

    assertTrue(words!!.isEmpty())

  }

  @Test
  fun insertMoreWordsAndCheckIfAllDeletedIsTrue() {
    val word1 = Word("hi")
    val word2 = Word("hello")
    val word3 = Word("android")
    database.wordDao().insert(word1)
    database.wordDao().insert(word2)
    database.wordDao().insert(word3)

    database.wordDao().deleteAll()
    val words = database.wordDao().allWords.blockingObserve()

    assertTrue(words!!.isEmpty())

  }
}
