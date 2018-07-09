package developer.olaru.ion.architecturecomponents.bViewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import developer.olaru.ion.architecturecomponents.cRepository.WordRepository
import developer.olaru.ion.architecturecomponents.dRoomDataBase.entity.Word
import developer.olaru.ion.architecturecomponents.utils.App
import javax.inject.Inject

// Warning never pass context into ViewModel instance ..do not store activity fragment or view can cause (MEMORY LEAK)
class WordViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var mRepository : WordRepository


    private var mAllWords: LiveData<List<Word>>?=null

    init {
       (application as App).component.inject(this)
        mAllWords = mRepository.getAllWords()
    }

    fun getAllWords(): LiveData<List<Word>>? {
        return mAllWords
    }

    fun insert(word: Word) {
        mRepository.insert(word)
    }

    fun deleteWord(word: Word){
        mRepository.deleteWord(word)

    }
}
//Important: ViewModel is not a replacement for the onSaveInstanceState() method, because the ViewModel does not survive a process shutdown.