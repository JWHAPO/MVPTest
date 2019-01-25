package test.mvp.hapo.com.mvptest.view.list

import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import test.mvp.hapo.com.mvptest.network.ApiClient
import test.mvp.hapo.com.mvptest.network.api.UserApiService

/**
 * MVPTest
 * Class: ListPresenter
 * Created by JEONGWOOKIM on 2019-01-23.
 * Description:
 */
class ListPresenter: ListContract.Presenter{

    private val tag = ListPresenter::class.java.simpleName
    private lateinit var view: ListContract.View
    private lateinit var userApiService: UserApiService
    private var mCompositeDisposable : CompositeDisposable = CompositeDisposable()

    override fun attach(view: ListContract.View) {
        this.view = view
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
