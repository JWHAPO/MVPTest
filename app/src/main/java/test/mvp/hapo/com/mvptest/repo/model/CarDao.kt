package test.mvp.hapo.com.mvptest.repo.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.OnConflictStrategy.REPLACE


/**
 * MVPTest
 * Class: CarDao
 * Created by JEONGWOOKIM on 2019-02-07.
 * Description:
 */
@Dao
interface CarDao{

    @Query("SELECT * FROM CAR")
    fun getAll(): List<Car>

    @Insert(onConflict = REPLACE)
    fun insert(car: Car)

    @Query("DELETE FROM CAR")
    fun deleteAll()
}