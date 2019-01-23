package test.mvp.hapo.com.mvptest.ui.list

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
import test.mvp.hapo.com.mvptest.adapter.UserAdapter
import test.mvp.hapo.com.mvptest.api.UserRequestInterface
import test.mvp.hapo.com.mvptest.data.User

/**
 * MVPTest
 * Class: ListActivity
 * Created by JEONGWOOKIM on 2019-01-22.
 * Description: Test ListActivity
 */

class ListActivity : AppCompatActivity(), UserAdapter.Listener {

    private val TAG = ListActivity::class.java.simpleName
    private val BASE_URL = "http://localhost:8080/"
    private var mCompositeDisposable : CompositeDisposable? = CompositeDisposable()
    private var mUserArrayList: ArrayList<User>? = null
    private var mAdapter : UserAdapter? = null

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
        Log.d(TAG, "loadJson")

        val userRequestInterface = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(UserRequestInterface::class.java)

        mCompositeDisposable?.add(userRequestInterface.getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        )
    }



    private fun handleResponse(userList: List<User>){
        Log.d(TAG, "handleResponse")
        mUserArrayList = ArrayList(userList)
        mAdapter = UserAdapter(mUserArrayList!!, this)

        list_rv.adapter = mAdapter
    }

    private fun handleError(error: Throwable) {
        Log.d(TAG, error.localizedMessage)
        Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable?.clear()
    }

}