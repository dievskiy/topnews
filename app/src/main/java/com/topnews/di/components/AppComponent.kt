package com.topnews.di.components

import com.topnews.MainApplication
import com.topnews.di.modules.ActivityBindingModule
import com.topnews.di.modules.AppModule
import com.topnews.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBindingModule::class,
        ViewModelModule::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<MainApplication> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MainApplication): Builder

        fun build(): AppComponent
    }
}
