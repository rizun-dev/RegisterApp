package com.example.registerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.registerapp.constance.Constance
import com.example.registerapp.databinding.ActivitySignInUpBinding

class SignInUpAct : AppCompatActivity() {
    private lateinit var bindingClass: ActivitySignInUpBinding
    private var signState = "empty"

    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        bindingClass = ActivitySignInUpBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        signState = intent.getStringExtra(Constance.SIGN_STATE)!!
        if (signState == Constance.SIGN_IN_STATE) {

            bindingClass.tvNickname.visibility = View.GONE
            bindingClass.tvSurname.visibility = View.GONE
            bindingClass.tvFatherhood.visibility = View.GONE
            bindingClass.bProfileIm.visibility = View.GONE

        }
    }

    fun onClickProfileImage(view: View) {

        bindingClass.imAvatar.visibility = View.VISIBLE

    }

    fun onClickDone(view: View) {
        if (signState == Constance.SIGN_UP_STATE) {
            val intent = Intent()
            intent.putExtra(Constance.LOGIN, bindingClass.tvLogin.text.toString())
            intent.putExtra(Constance.PASSWORD, bindingClass.tvPassword.text.toString())
            intent.putExtra(Constance.NICKNAME, bindingClass.tvNickname.text.toString())
            intent.putExtra(Constance.SURNAME, bindingClass.tvSurname.text.toString())
            intent.putExtra(Constance.FATHERHOOD, bindingClass.tvFatherhood.text.toString())
            if (bindingClass.imAvatar.isVisible)
                intent.putExtra(Constance.PROFILE_IMAGE, R.drawable.photo)
            setResult(RESULT_OK, intent)
            finish()
        } else if (signState == Constance.SIGN_IN_STATE)
            intent.putExtra(Constance.LOGIN, bindingClass.tvLogin.text.toString())
        intent.putExtra(Constance.PASSWORD, bindingClass.tvPassword.text.toString())
        setResult(RESULT_OK, intent)
        finish()
    }
}