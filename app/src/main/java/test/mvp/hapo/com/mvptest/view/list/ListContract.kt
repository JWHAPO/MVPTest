package test.mvp.hapo.com.mvptest.view.list

import android.content.Context
import test.mvp.hapo.com.mvptest.app.BaseContract
import test.mvp.hapo.com.mvptest.network.model.Account
import test.mvp.hapo.com.mvptest.network.model.User

/**
 * MVPTest
 * Class: ListContract
 * Created by JEONGWOOKIM on 2019-01-23.
 * Description:
 */

interface ListContract{
    interface View : BaseContract.View{
        fun initView()
        fun updateUserList(user: User)
        fun updateAccount(account: Account)
        fun toastErrorMsg(error: Throwable)
    }
    interface Presenter: BaseContract.Presenter<View>{
        fun getUser(context: Context)
        fun getAccount(context: Context)
        fun clearCompositeDisposable()
    }
}