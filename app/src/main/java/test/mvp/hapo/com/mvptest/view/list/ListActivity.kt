package test.mvp.hapo.com.mvptest.view.list

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import test.mvp.hapo.com.mvptest.R
import test.mvp.hapo.com.mvptest.databinding.ListLayoutBinding
import test.mvp.hapo.com.mvptest.network.model.Account
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

    private lateinit var presenter: ListPresenter

    private lateinit var mUserArrayList: ArrayList<UserUnit>
    private lateinit var mAdapter : UserAdapter

    lateinit var binding:ListLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.list_layout)

        presenter = ListPresenter()
        presenter.attach(this)

    }

    override fun initView() {
        Log.d(tag, "initView")

        initRecyclerView()
        presenter.getUser(applicationContext)
        presenter.getAccount(applicationContext)

        binding.listRefreshBtn.setOnClickListener {
            presenter.getUser(applicationContext)
        }
        binding.listAlertBtn.setOnClickListener {
            presenter.getAccount(applicationContext)
        }
    }

    override fun updateAccount(account: Account) {
        binding.account = account
    }

    override fun updateUserList(user: User) {
        Log.d(tag, "updateUserList")

        mUserArrayList = ArrayList(user._embedded.userList)
        mAdapter = UserAdapter(mUserArrayList, this)
        binding.listRv.adapter = mAdapter
    }

    override fun onItemClick(user: UserUnit) {
        Toast.makeText(this, "${user.name} Clicked !", Toast.LENGTH_SHORT).show()
    }

    private fun initRecyclerView(){
        Log.d(tag, "initRecyclerView")
        binding.listRv.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.listRv.layoutManager = layoutManager
    }

    override fun toastErrorMsg(error: Throwable) {
        Log.d(tag, error.localizedMessage)
        Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.clearCompositeDisposable()
    }

}