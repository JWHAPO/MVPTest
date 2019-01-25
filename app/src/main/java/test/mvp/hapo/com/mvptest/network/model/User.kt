package test.mvp.hapo.com.mvptest.network.model

/**
 * MVPTest
 * Class: User
 * Created by JEONGWOOKIM on 2019-01-23.
 * Description: User data
 */
data class User(val _embedded:UserEmbedded, val _links:UserLinks)

data class UserEmbedded(val userList:List<UserUnit>)
data class UserUnit(val name:String, val phone:String, val _links:UserLinks)

data class UserLinks(val self:UserHref, val users:UserHref)
data class UserHref(var href:String)


