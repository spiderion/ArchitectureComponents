package developer.olaru.ion.architecturecomponents.C_repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;
import developer.olaru.ion.architecturecomponents.D_room_data_base.WordRoomDataBase;
import developer.olaru.ion.architecturecomponents.D_room_data_base.dao.WordDao;
import developer.olaru.ion.architecturecomponents.D_room_data_base.entity.Word;

public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

  public WordRepository(Application application){
        WordRoomDataBase db = WordRoomDataBase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();

    }

    public LiveData<List<Word>> getmAllWords(){

        return mAllWords;
    }

    public void insert(Word word){

        new insertAsyncTask(mWordDao).execute(word);
    }


    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
