package test.mvp.hapo.com.mvptest.view.list

import android.content.Context
import test.mvp.hapo.com.mvptest.network.model.User

/**
 * MVPTest
 * Class: ListContract
 * Created by JEONGWOOKIM on 2019-01-23.
 * Description:
 */

interface ListContract{
    interface View{
        fun initView()
        fun updateUserList(user: User)
        fun toastErrorMsg(error: Throwable)
    }
    interface Presenter{
        fun getUser(context: Context)
        fun clearCompositeDisposable()
    }
}