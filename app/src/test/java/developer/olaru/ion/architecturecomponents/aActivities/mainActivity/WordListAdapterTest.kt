package developer.olaru.ion.architecturecomponents.aActivities.mainActivity


import com.nhaarman.mockitokotlin2.verify
import developer.olaru.ion.architecturecomponents.dRoomDataBase.entity.Word
import org.junit.Test
import org.mockito.Mockito.mock

class WordListAdapterTest{


    @Test fun longClickOnWordWasOccurred(){

        val words = listOf(Word("hi"))
        val adapter =mock(WordListAdapter::class.java)
        adapter.setWords(words)

        verify(adapter).setWords(words)
    }
}