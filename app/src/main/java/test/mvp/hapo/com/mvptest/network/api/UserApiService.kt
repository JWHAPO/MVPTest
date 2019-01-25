package test.mvp.hapo.com.mvptest.network.api

import io.reactivex.Observable
import retrofit2.http.GET
import test.mvp.hapo.com.mvptest.network.model.User

/**
 * MVPTest
 * Class: UserApiService
 * Created by JEONGWOOKIM on 2019-01-23.
 * Description:
 */
interface UserApiService {

    @GET("users")
    fun getUsers() : Observable<User>
}