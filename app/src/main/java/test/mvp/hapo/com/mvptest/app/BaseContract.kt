package test.mvp.hapo.com.mvptest.app

/**
 * MVPTest
 * Class: BaseContract
 * Created by JEONGWOOKIM on 2019-01-25.
 * Description: Base Contract
 */

class BaseContract{
    interface Presenter<in T>{
        fun attach(view: T)
    }

    interface View{

    }
}