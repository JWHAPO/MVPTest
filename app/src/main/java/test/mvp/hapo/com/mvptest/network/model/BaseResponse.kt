package test.mvp.hapo.com.mvptest.network.model

/**
 * MVPTest
 * Class: BaseResponse
 * Created by JEONGWOOKIM on 2019-01-24.
 * Description:As every response will have a error node,
 *              we define the error node in BaseResponse class and extend this class in other models
 */
interface BaseResponse{
    val error: String
}