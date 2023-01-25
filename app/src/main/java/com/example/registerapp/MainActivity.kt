@file:Suppress("DEPRECATION")

package com.example.registerapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import com.example.registerapp.constance.Constance
import com.example.registerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding
    private var login: String = "empty"
    private var password: String = "empty"
    private var nickname: String = "empty"
    private var surname: String = "empty"
    private var fatherhood: String = "empty"
    private var profileImageId: Int = 0

    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

    }

    @SuppressLint("SetTextI18n")
    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "super.onActivityResult(requestCode, resultCode, data)",
            "androidx.appcompat.app.AppCompatActivity"
        )
    )

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Constance.REQUEST_CODE_SIGN_IN) {
            val l = data?.getStringExtra(Constance.LOGIN)
            val p = data?.getStringExtra(Constance.PASSWORD)
            if (login == l && password == p) {

                bindingClass.imProfileImage.visibility = View.VISIBLE
                bindingClass.imProfileImage.setImageResource(profileImageId)
                val textInfo = "$nickname $surname $fatherhood"
                bindingClass.tvText.text = textInfo

            } else {
                bindingClass.imProfileImage.visibility = View.VISIBLE
                bindingClass.tvText.text = "Invalid data"
                bindingClass.imProfileImage.setImageResource(R.drawable.dula)
            }

        } else if (requestCode == Constance.REQUEST_CODE_SIGN_UP) {

            login = data?.getStringExtra(Constance.LOGIN)!!
            password = data.getStringExtra(Constance.PASSWORD)!!
            nickname = data.getStringExtra(Constance.NICKNAME)!!
            surname = data.getStringExtra(Constance.SURNAME)!!
            fatherhood = data.getStringExtra(Constance.FATHERHOOD)!!
            profileImageId = data.getIntExtra(Constance.PROFILE_IMAGE, 0)
            bindingClass.imProfileImage.visibility = View.VISIBLE
            bindingClass.imProfileImage.setImageResource(profileImageId)
            val textInfo = "$nickname $surname $fatherhood"
            bindingClass.tvText.text = textInfo
            bindingClass.bExit.text = "Log out"
            bindingClass.bHide.visibility = View.GONE
        }
    }

    fun onClickSignIn(view: View) {

        if (bindingClass.imProfileImage.isVisible && bindingClass.tvText.toString() == "Invalid data") {

            bindingClass.imProfileImage.visibility = View.INVISIBLE
            bindingClass.tvText.text = ""
            bindingClass.bExit.text = getString(R.string.sign_in)
            bindingClass.bHide.visibility = View.VISIBLE

        } else {

            val intent = Intent(this, SignInUpAct::class.java)
            intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
            startActivityForResult(intent, Constance.REQUEST_CODE_SIGN_IN)

        }
    }

    fun onClickSignUp(view: View) {
        val intent = Intent(this, SignInUpAct::class.java)
        intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_UP_STATE)
        startActivityForResult(intent, Constance.REQUEST_CODE_SIGN_UP)
    }
}