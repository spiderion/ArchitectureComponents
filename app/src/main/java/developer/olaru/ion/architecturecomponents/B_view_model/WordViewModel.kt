package developer.olaru.ion.architecturecomponents.B_view_model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import developer.olaru.ion.architecturecomponents.C_repository.WordRepository
import developer.olaru.ion.architecturecomponents.D_room_data_base.entity.Word

class WordViewModel(application: Application) : AndroidViewModel(application) {// Warning never pass context into ViewModel instance ..do not store activity fragment or view (MEMORY LEAK)

    private var mRepository: WordRepository?=null

    private var mAllWords: LiveData<List<Word>>?=null

    init {
        mRepository = WordRepository(application)
        mAllWords = mRepository!!.getmAllWords()
    }

    fun getmAllWords(): LiveData<List<Word>>? {
        return mAllWords
    }

    fun insert(word: Word) {
        mRepository?.insert(word)
    }
}
//Important: ViewModel is not a replacement for the onSaveInstanceState() method, because the ViewModel does not survive a process shutdown.