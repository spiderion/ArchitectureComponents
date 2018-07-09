package developer.olaru.ion.architecturecomponents.utils.dagger2

import android.app.Application
import dagger.Module
import dagger.Provides
import developer.olaru.ion.architecturecomponents.cRepository.WordRepository
import developer.olaru.ion.architecturecomponents.dRoomDataBase.WordRoomDataBase
import javax.inject.Singleton


@Module
class AndroidModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesApplication()= application

    @Provides
    @Singleton
    fun providesWordRepository():WordRepository = WordRepository(application)

    @Provides
    @Singleton
    fun providesWordDataBase():WordRoomDataBase =  WordRoomDataBase.getDatabase(application)




}