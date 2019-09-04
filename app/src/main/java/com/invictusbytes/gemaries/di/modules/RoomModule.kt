package com.invictusbytes.gemaries.di.modules

import android.app.Application
import androidx.room.Room
import com.invictusbytes.gemaries.data.db.AppDatabase
import com.invictusbytes.gemaries.data.db.dao.AssignedDao
import com.invictusbytes.gemaries.data.db.dao.CratesDao
import com.invictusbytes.gemaries.data.db.dao.UsersDao
import com.invictusbytes.gemaries.utils.AppConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Suppress("unused")
@Module
class RoomModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, AppConfig.DATABASE_NAME).build()
    }

    @Singleton
    @Provides
    fun providesUsersDao(db: AppDatabase): UsersDao {
        return db.getUsersDao()
    }

    @Singleton
    @Provides
    fun providesCratesDao(db: AppDatabase): CratesDao {
        return db.getCratesDao()
    }


    @Singleton
    @Provides
    fun providesAssignedDao(db: AppDatabase): AssignedDao {
        return db.getAssignedDao()
    }

}