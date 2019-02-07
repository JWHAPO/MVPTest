package test.mvp.hapo.com.mvptest.network.model

/**
 * MVPTest
 * Class: User
 * Created by JEONGWOOKIM on 2019-01-23.
 * Description: User data
 */
data class User(var _embedded:UserEmbedded, var _links:UserLinks)

data class UserEmbedded(var userList:List<UserUnit>)
data class UserUnit(var name:String, var phone:String, var _links:UserLinks)

data class UserLinks(var self:UserHref, var users:UserHref)
data class UserHref(var href:String)