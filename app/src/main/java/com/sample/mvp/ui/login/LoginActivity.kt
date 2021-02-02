package com.sample.mvp.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.sample.mvp.R
import com.sample.mvp.base.BaseActivity
import com.sample.mvp.ui.posts.PostActivity

class LoginActivity : BaseActivity(), LoginView, View.OnClickListener {
    private lateinit var edtUsername: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button


    var loginPresenter: LoginPresenterImpl? = null

    override fun setLayout(): Int {
        return R.layout.activity_login
    }


    override fun init(savedInstanceState: Bundle?) {
        edtUsername = findViewById(R.id.et_username) as EditText
        edtPassword = findViewById(R.id.et_password) as EditText
        btnLogin = findViewById(R.id.login_bt_login) as Button
        btnLogin.setOnClickListener(this)
    }


    fun getPresenter(): LoginPresenterImpl? {
        loginPresenter = LoginPresenterImpl(this, application)
        return loginPresenter
    }


    override fun onStartScreen() {

    }

    override fun stopScreen() {
        loginPresenter?.let {
            loginPresenter!!.unbindView()
            loginPresenter = null
        }
    }


    override fun onPasswordError() {
        Toast.makeText(this, "Username or Password is invaild, Please try again", Toast.LENGTH_LONG).show()
    }

    override fun onBackPress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigateToHome() {
        Toast.makeText(this, "Login Success", Toast.LENGTH_LONG).show()
        val intent = Intent(this@LoginActivity, PostActivity::class.java)
        startActivity(intent)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.login_bt_login -> {
                loginTransaction()
            }
        }
    }

    private fun loginTransaction() {
        getPresenter()?.let {
            it.validateUser(edtUsername.text.toString().trim(), edtPassword.text.toString().trim())
        }
    }
}
