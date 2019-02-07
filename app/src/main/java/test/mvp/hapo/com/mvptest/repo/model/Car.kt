package test.mvp.hapo.com.mvptest.repo.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * MVPTest
 * Class: Car
 * Created by JEONGWOOKIM on 2019-02-07.
 * Description: Car Entity
 */

@Entity(tableName = "car")
data class Car(@PrimaryKey(autoGenerate = true) var id: Long?,
          @ColumnInfo(name = "car_name") var carName: String,
          @ColumnInfo(name = "car_year") var carYear: Int,
          @ColumnInfo(name = "car_owner") var carOwner: String)