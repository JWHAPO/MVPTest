package test.mvp.hapo.com.mvptest.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import test.mvp.hapo.com.mvptest.R
import kotlinx.android.synthetic.main.main_layout.*

/**
 * MVPTest
 * Class: MainActivity
 * Created by JEONGWOOKIM on 2019-01-22.
 * Description: Main Activity
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        val plus = plusBtn.clicks().map { +1 }
        val minus = minusBtn.clicks().map { -1 }

        Observable.merge(plus, minus)
                .scan(0){acc: Int, value: Int -> acc + value}
                .subscribe{resultText.text = it.toString()}
    }

}