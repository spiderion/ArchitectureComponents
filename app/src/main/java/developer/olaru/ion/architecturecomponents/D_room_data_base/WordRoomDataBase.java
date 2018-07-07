package developer.olaru.ion.architecturecomponents.D_room_data_base;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import developer.olaru.ion.architecturecomponents.D_room_data_base.dao.WordDao;
import developer.olaru.ion.architecturecomponents.D_room_data_base.entity.Word;

@Database(entities = {Word.class}, version = 1)//When you modify the database schema, you'll need to update the version number and define how to handle migrations
public abstract class WordRoomDataBase extends RoomDatabase {

    public abstract WordDao wordDao();

    private static WordRoomDataBase INSTANCE;

    public static WordRoomDataBase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (WordRoomDataBase.class){
                if(INSTANCE==null){
                    //Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDataBase.class,"word_database")
                            .addCallback(sRoomdataBaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomdataBaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;

        PopulateDbAsync(WordRoomDataBase db) {
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            Word word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            return null;
        }
    }
}
