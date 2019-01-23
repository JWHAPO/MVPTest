package test.mvp.hapo.com.mvptest.api

import io.reactivex.Observable
import retrofit2.http.GET
import test.mvp.hapo.com.mvptest.data.User

/**
 * MVPTest
 * Class: UserRequestInterface
 * Created by JEONGWOOKIM on 2019-01-23.
 * Description:
 */
interface UserRequestInterface {

    @GET("users")
    fun getUsers() : Observable<List<User>>
}