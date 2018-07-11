package developer.olaru.ion.architecturecomponents.cRepository

import developer.olaru.ion.architecturecomponents.dRoomDataBase.WordRoomDataBase
import developer.olaru.ion.architecturecomponents.dRoomDataBase.dao.WordDao
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock


class WordRepositoryTest {


     lateinit var  mockDatabase: WordRoomDataBase
     lateinit var  mockDao : WordDao

    @Before fun setUp(){
         mockDatabase = mock(WordRoomDataBase::class.java)
         mockDao =mock(WordDao::class.java)
    }


    @Test fun insertTest(){

        //TODO  I cannot init the repository and I don't know why  (HELP)
        val wordRepository = WordRepository(mockDatabase)
    }
}