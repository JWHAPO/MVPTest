package test.mvp.hapo.com.mvptest.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import test.mvp.hapo.com.mvptest.R
import test.mvp.hapo.com.mvptest.ui.main.MainActivity

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

        val button01 = findViewById<Button>(R.id.button01)
        val button02 = findViewById<Button>(R.id.button02)
        val button03 = findViewById<Button>(R.id.button03)
        button01.setOnClickListener(clickListener)
        button02.setOnClickListener(clickListener)
        button03.setOnClickListener(clickListener)
    }

    val clickListener = View.OnClickListener { view->
        when(view.id){
            R.id.button01 -> showToast("1")
            R.id.button02 -> showToast("2")
            R.id.button03 -> goMainActivity()
        }
    }

    fun goMainActivity(){
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        val message = "go Main!"

        intent.putExtra("message",message)
        startActivity(intent)
    }

    override fun showToast(title: String) {
        Toast.makeText(this, "OnClick Item : $title", Toast.LENGTH_SHORT).show()
    }
}
