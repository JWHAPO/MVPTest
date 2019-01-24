package test.mvp.hapo.com.mvptest.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * MVPTest
 * Class: PrefUtils
 * Created by JEONGWOOKIM on 2019-01-24.
 * Description: SharedPreferences
 */

class PrefUtils(){

    private fun getSharedPreferences(context: Context):SharedPreferences{
        return context.getSharedPreferences("APP_PREF",Context.MODE_PRIVATE)
    }

    fun storeApiKey(context: Context, apiKey: String){
        var editor:SharedPreferences.Editor = getSharedPreferences(context).edit()
        editor.putString("API_KEY", apiKey)
        editor.commit()
    }

    public fun getApiKey(context: Context): String{
        return getSharedPreferences(context).getString("API_KEY", null)
    }

}