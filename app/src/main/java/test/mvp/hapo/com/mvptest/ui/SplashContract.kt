package test.mvp.hapo.com.mvptest.ui

/**
 * MVPTest
 * Class: SplashContract
 * Created by JEONGWOOKIM on 2019-01-22.
 * Description:
 */

interface SplashContract{
    interface View {
        fun showToast(title: String)
    }

    interface Presenter{
        var view: SplashContract.View
    }
}