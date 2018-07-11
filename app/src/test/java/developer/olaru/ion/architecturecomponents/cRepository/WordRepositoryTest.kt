package developer.olaru.ion.architecturecomponents.cRepository

import android.app.Application
import com.nhaarman.mockitokotlin2.mock
import developer.olaru.ion.architecturecomponents.dRoomDataBase.WordRoomDataBase
import developer.olaru.ion.architecturecomponents.dRoomDataBase.dao.WordDao
import developer.olaru.ion.architecturecomponents.utils.App
import developer.olaru.ion.architecturecomponents.utils.dagger2.AndroidModule
import developer.olaru.ion.architecturecomponents.utils.dagger2.DaggerAppComponent
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock


class WordRepositoryTest {


     lateinit var  mockDatabase: WordRoomDataBase

    @Before fun setUp(){
         mockDatabase = mock(WordRoomDataBase::class.java)

    }


    @Test fun insertTest(){

        //TODO  I cannot init the repository and I don't know why  (HELP)
        val wordRepository = WordRepository(mockDatabase)
    }


}