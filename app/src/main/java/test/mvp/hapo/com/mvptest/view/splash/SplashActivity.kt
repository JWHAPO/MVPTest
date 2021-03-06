package test.mvp.hapo.com.mvptest.view.splash

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.splash_layout.*
import test.mvp.hapo.com.mvptest.R
import test.mvp.hapo.com.mvptest.network.model.User
import test.mvp.hapo.com.mvptest.view.list.ListActivity
import test.mvp.hapo.com.mvptest.view.main.MainActivity
import test.mvp.hapo.com.mvptest.view.room.RoomActivity

/**
 * MVPTest
 * Class: MainActivity
 * Created by JEONGWOOKIM on 2019-01-22.
 * Description: Splash Activity
 */

class SplashActivity : AppCompatActivity(), SplashContract.View {

    private lateinit var presenter: SplashPresenter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)

        button01.setOnClickListener(clickListener)
        button02.setOnClickListener(clickListener)
        button03.setOnClickListener(clickListener)
        button04.setOnClickListener(clickListener)
        button05.setOnClickListener(clickListener)
    }

    private val clickListener = View.OnClickListener { view->
        when(view.id){
            R.id.button01 -> showToast("1")
            R.id.button02 -> showToast("2")
            R.id.button03 -> goMainActivity()
            R.id.button04 -> goListActivity()
            R.id.button05 -> goRoomActivity()
        }
    }

    private fun goMainActivity(){
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        val message = "go Main!"

        intent.putExtra("message",message)
        startActivity(intent)
    }
    private fun goListActivity(){
        val intent = Intent(this@SplashActivity, ListActivity::class.java)
        val message = "go List!"

        intent.putExtra("message",message)
        startActivity(intent)
    }
    private fun goRoomActivity(){
        val intent = Intent(this@SplashActivity, RoomActivity::class.java)
        val message = "go Room Test!"

        intent.putExtra("message",message)
        startActivity(intent)
    }

    override fun showToast(title: String) {
        Toast.makeText(this, "OnClick Item : $title", Toast.LENGTH_SHORT).show()
    }
}
