package test.mvp.hapo.com.mvptest.view.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.list_layout.*
import test.mvp.hapo.com.mvptest.R
import test.mvp.hapo.com.mvptest.network.model.User
import test.mvp.hapo.com.mvptest.network.model.UserUnit
import test.mvp.hapo.com.mvptest.view.list.adapter.UserAdapter

/**
 * MVPTest
 * Class: ListActivity
 * Created by JEONGWOOKIM on 2019-01-22.
 * Description: Test ListActivity
 */

class ListActivity : AppCompatActivity(), UserAdapter.Listener, ListContract.View {
    private val tag = ListActivity::class.java.simpleName

    private var presenter: ListPresenter? = null

    private lateinit var mUserArrayList: ArrayList<UserUnit>
    private lateinit var mAdapter : UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_layout)

        presenter = ListPresenter(this)

    }

    override fun initView() {
        Log.d(tag, "initView")

        initRecyclerView()
        presenter?.getUser(applicationContext)
    }

    override fun updateUserList(user: User) {
        Log.d(tag, "handleResponse")

        mUserArrayList = ArrayList(user._embedded.userList)
        mAdapter = UserAdapter(mUserArrayList!!, this)
        list_rv.adapter = mAdapter
    }

    override fun onItemClick(user: UserUnit) {
        Toast.makeText(this, "${user.name} Clicked !", Toast.LENGTH_SHORT).show()
    }

    private fun initRecyclerView(){
        Log.d(tag, "initRecyclerView")
        list_rv.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        list_rv.layoutManager = layoutManager
    }

    override fun toastErrorMsg(error: Throwable) {
        Log.d(tag, error.localizedMessage)
        Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.clearCompositeDisposable()
    }

}