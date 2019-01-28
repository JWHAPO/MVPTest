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


//class User{
//    lateinit var _embedded:UserEmbedded
//    lateinit var _links:UserLinks
//
//    inner class UserEmbedded{
//        lateinit var userList: List<UserUnit>
//    }
//
//    inner class UserUnit{
//        lateinit var name:String
//        lateinit var phone:String
//        lateinit var _links:UserLinks
//    }
//
//    inner class UserLinks{
//        lateinit var self:UserHref
//        lateinit var users:UserHref
//    }
//
//    inner class UserHref{
//        lateinit var href:String
//    }
//}