package test.mvp.hapo.com.mvptest.view.room.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import test.mvp.hapo.com.mvptest.repo.model.Car
import test.mvp.hapo.com.mvptest.databinding.RoomRowBinding;

/**
 * MVPTest
 * Class: CarAdapter
 * Created by JEONGWOOKIM on 2019-02-07.
 * Description:
 */

class CarAdapter(val context: Context, val cars:List<Car>): RecyclerView.Adapter<CarAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RoomRowBinding.inflate(inflater)

        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return cars.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(cars.get(position), position)
    }


    inner class Holder(val binding: RoomRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(car: Car, position: Int){
            binding.carList = car;
            binding.executePendingBindings()

        }
    }
}