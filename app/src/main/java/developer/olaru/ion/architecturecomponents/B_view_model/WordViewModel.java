package developer.olaru.ion.architecturecomponents.B_view_model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import java.util.List;
import developer.olaru.ion.architecturecomponents.C_repository.WordRepository;
import developer.olaru.ion.architecturecomponents.D_room_data_base.entity.Word;

public class WordViewModel extends AndroidViewModel {// Warning never pass context into ViewModel instance ..do not store activity fragment or view (MEMORY LEAK)

    private WordRepository mRepository;

    private LiveData<List<Word>> mAllWords;

    public WordViewModel(Application application){
        super(application);
        mRepository = new WordRepository(application);
       mAllWords = mRepository.getmAllWords();
    }

    public LiveData<List<Word>> getmAllWords() {
        return mAllWords;
    }

    public void insert(Word word){mRepository.insert(word);}
}
//Important: ViewModel is not a replacement for the onSaveInstanceState() method, because the ViewModel does not survive a process shutdown.