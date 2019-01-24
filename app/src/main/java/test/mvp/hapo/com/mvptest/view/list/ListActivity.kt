package test.mvp.hapo.com.mvptest.view.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.list_layout.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import test.mvp.hapo.com.mvptest.R
import test.mvp.hapo.com.mvptest.app.BASE_URL
import test.mvp.hapo.com.mvptest.network.ApiClient
import test.mvp.hapo.com.mvptest.network.api.UserApiService
import test.mvp.hapo.com.mvptest.network.model.User
import test.mvp.hapo.com.mvptest.utils.PrefUtils
import test.mvp.hapo.com.mvptest.view.list.adapter.UserAdapter

/**
 * MVPTest
 * Class: ListActivity
 * Created by JEONGWOOKIM on 2019-01-22.
 * Description: Test ListActivity
 */

class ListActivity : AppCompatActivity(), UserAdapter.Listener {

    private val tag = ListActivity::class.java.simpleName
    private var mCompositeDisposable : CompositeDisposable = CompositeDisposable()
    private lateinit var mUserArrayList: ArrayList<User>
    private lateinit var mAdapter : UserAdapter
    private lateinit var userApiService: UserApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_layout)

        initRecyclerView()
        loadJson()
    }

    override fun onItemClick(user: User) {
        Toast.makeText(this, "${user.name} Clicked !", Toast.LENGTH_SHORT).show()
    }

    private fun initRecyclerView(){
        list_rv.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        list_rv.layoutManager = layoutManager
    }

    private fun loadJson(){
        Log.d(tag, "loadJson")

        userApiService = ApiClient().getClient(applicationContext).create(UserApiService::class.java)

        mCompositeDisposable?.add(userApiService.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, this::handleError)
        )
    }

    private fun handleResponse(userList: List<User>){
        Log.d(tag, "handleResponse")
        mUserArrayList = ArrayList(userList)
        mAdapter = UserAdapter(mUserArrayList!!, this)

        list_rv.adapter = mAdapter
    }

    private fun handleError(error: Throwable) {
        Log.d(tag, error.localizedMessage)
        Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable?.clear()
    }

}