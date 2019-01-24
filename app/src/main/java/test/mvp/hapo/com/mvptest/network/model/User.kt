package test.mvp.hapo.com.mvptest.network.model

/**
 * MVPTest
 * Class: User
 * Created by JEONGWOOKIM on 2019-01-23.
 * Description: User data
 */
data class User(val name:String, val phone:String, override val error: String) : BaseResponse
