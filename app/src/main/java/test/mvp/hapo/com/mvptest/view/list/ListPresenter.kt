package test.mvp.hapo.com.mvptest.view.list

import android.content.Context
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.list_layout.*
import test.mvp.hapo.com.mvptest.network.ApiClient
import test.mvp.hapo.com.mvptest.network.api.UserApiService
import test.mvp.hapo.com.mvptest.network.model.User
import test.mvp.hapo.com.mvptest.view.list.adapter.UserAdapter

/**
 * MVPTest
 * Class: ListPresenter
 * Created by JEONGWOOKIM on 2019-01-23.
 * Description:
 */
class ListPresenter(_view: ListContract.View): ListContract.Presenter{

    private val tag = ListPresenter::class.java.simpleName
    private var view: ListContract.View = _view
    private lateinit var userApiService: UserApiService
    private var mCompositeDisposable : CompositeDisposable = CompositeDisposable()

    init {
        view.initView()
    }

    override fun getUser(context: Context) {
        userApiService = ApiClient.getClient(context).create(UserApiService::class.java)

        mCompositeDisposable?.add(userApiService.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::updateUserList, view::toastErrorMsg)
        )
    }

    override fun clearCompositeDisposable() {
        mCompositeDisposable?.clear()
    }


}
