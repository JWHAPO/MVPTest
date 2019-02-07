package test.mvp.hapo.com.mvptest.view.room

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import test.mvp.hapo.com.mvptest.R
import test.mvp.hapo.com.mvptest.databinding.RoomAddLayoutBinding
import test.mvp.hapo.com.mvptest.databinding.RoomLayoutBinding
import test.mvp.hapo.com.mvptest.repo.AppDatabase
import test.mvp.hapo.com.mvptest.repo.model.Car

/**
 * MVPTest
 * Class: RoomAddActivity
 * Created by JEONGWOOKIM on 2019-02-07.
 * Description:
 */
class RoomAddActivity: AppCompatActivity(){

    lateinit var binding: RoomAddLayoutBinding

    private var appDatabase: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.room_add_layout)

        appDatabase = AppDatabase.getInstance(this)

        val addRunnable = Runnable {
            val newCar = Car(
                    null
                    ,binding.roomAddNameTv.text.toString()
                    ,binding.roomAddYearTv.text.toString().toInt()
                    ,binding.roomAddOwnerTv.text.toString()
            )

            appDatabase?.carDao()?.insert(newCar)
        }

        binding.roomAddNextBtn.setOnClickListener {
            val addThread = Thread(addRunnable)
            addThread.start()

            val i = Intent(this, RoomActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    override fun onDestroy() {
        AppDatabase.destroyInstance()
        super.onDestroy()
    }
}
