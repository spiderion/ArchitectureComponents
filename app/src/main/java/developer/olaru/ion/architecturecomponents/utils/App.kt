package developer.olaru.ion.architecturecomponents.utils

import android.app.Application
import developer.olaru.ion.architecturecomponents.utils.dagger2.AndroidModule
import developer.olaru.ion.architecturecomponents.utils.dagger2.AppComponent
import developer.olaru.ion.architecturecomponents.utils.dagger2.DaggerAppComponent

class App : Application() {

    lateinit var component : AppComponent

    override fun onCreate() {
        super.onCreate()

        component= DaggerAppComponent.builder()
                .androidModule(AndroidModule(this))
                .build()
    }

}