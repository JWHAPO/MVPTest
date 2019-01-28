package test.mvp.hapo.com.mvptest.view.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import test.mvp.hapo.com.mvptest.databinding.ListRowBinding
import test.mvp.hapo.com.mvptest.network.model.UserUnit

/**
 * MVPTest
 * Class: UserAdapter
 * Created by JEONGWOOKIM on 2019-01-23.
 * Description:
 */
class UserAdapter(private val userList:ArrayList<UserUnit>, private val listener:Listener) : RecyclerView.Adapter<UserAdapter.ViewHolder>()
{
    interface Listener{
        fun onItemClick(user : UserUnit)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList.get(position), listener,position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListRowBinding.inflate(inflater,parent,false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = userList.count()

    class ViewHolder(val binding: ListRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user: UserUnit, listener: Listener, position: Int){

            binding.userList = user
            binding.executePendingBindings()

            itemView.setOnClickListener{
                listener.onItemClick(user)
            }
        }
    }
}