package com.finalandroid.Module


import android.app.Application
import android.content.Context
import com.finalandroid.Memo.DAO.MemoDAO
import com.finalandroid.Memo.Helpers.MemoDatabaseHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun providememoDAO(applicationContext: Context): MemoDAO {
        return MemoDatabaseHelper.getDatabase(applicationContext).memoDAO()
    }
}