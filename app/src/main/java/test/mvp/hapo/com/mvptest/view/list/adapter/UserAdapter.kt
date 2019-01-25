package test.mvp.hapo.com.mvptest.view.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_row.view.*
import test.mvp.hapo.com.mvptest.R
import test.mvp.hapo.com.mvptest.network.model.User
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_row,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = userList.count()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(user: UserUnit, listener: Listener, position: Int){
            itemView.user_name_tv.text = user.name
            itemView.user_phone_tv.text = user.phone

            itemView.setOnClickListener{
                listener.onItemClick(user)
            }
        }
    }
}