package developer.olaru.ion.architecturecomponents.utils.dagger2

import dagger.Component
import developer.olaru.ion.architecturecomponents.aActivities.mainActivity.MainActivity
import developer.olaru.ion.architecturecomponents.bViewModel.WordViewModel
import developer.olaru.ion.architecturecomponents.cRepository.WordRepository
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(wordViewModel : WordViewModel)
    fun inject(wordRepository: WordRepository)
}