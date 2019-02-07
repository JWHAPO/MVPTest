package test.mvp.hapo.com.mvptest.repo

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import test.mvp.hapo.com.mvptest.repo.model.Car
import test.mvp.hapo.com.mvptest.repo.model.CarDao

/**
 * MVPTest
 * Class: AppDatabase
 * Created by JEONGWOOKIM on 2019-02-07.
 * Description: AppDatabase
 */
@Database(entities = [Car::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    abstract fun carDao(): CarDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase?{
            if(INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AppDatabase::class.java, "mvptest")
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance(){
            INSTANCE = null
        }
    }

}