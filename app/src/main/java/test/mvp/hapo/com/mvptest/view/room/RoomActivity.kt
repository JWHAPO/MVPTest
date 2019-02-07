package test.mvp.hapo.com.mvptest.view.room

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import test.mvp.hapo.com.mvptest.R
import test.mvp.hapo.com.mvptest.repo.AppDatabase
import test.mvp.hapo.com.mvptest.databinding.RoomLayoutBinding
import test.mvp.hapo.com.mvptest.repo.model.Car
import test.mvp.hapo.com.mvptest.view.room.adapter.CarAdapter


/**
 * MVPTest
 * Class: RoomActivity
 * Created by JEONGWOOKIM on 2019-02-07.
 * Description:
 */

class RoomActivity: AppCompatActivity() {
    private var appDatabase: AppDatabase? = null
    private var carList = listOf<Car>()
    lateinit var mAdapter: CarAdapter

    private lateinit var binding:RoomLayoutBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.room_layout)


        appDatabase = AppDatabase.getInstance(this)

        mAdapter = CarAdapter(this,carList)

        val r = Runnable {
            try {
                carList = appDatabase?.carDao()?.getAll()!!

                mAdapter = CarAdapter(this,carList)
                mAdapter.notifyDataSetChanged()

                binding.roomRecyclerView.adapter = mAdapter
                binding.roomRecyclerView.layoutManager = LinearLayoutManager(this)
                binding.roomRecyclerView.hasFixedSize()
            }catch (e: Exception){
                Log.d("tag", "error -> $e")
            }
        }

        val thread = Thread(r)
        thread.start()

        binding.roomAddBtn.setOnClickListener {
            val i = Intent(this, RoomAddActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    override fun onDestroy() {
        AppDatabase.destroyInstance()
        appDatabase = null
        super.onDestroy()
    }
}