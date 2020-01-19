package com.nevermore.androidplay.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.nevermore.androidplay.R
import com.nevermore.androidplay.data.Result
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel: AccountViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tool_bar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        tv_register.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }


        val changeContent: () -> Unit = {
            viewModel.changeContent(edt_user_name.text.toString(), edt_password.text.toString(),type = 1)
        }

        edt_user_name.afterTextChange {
            changeContent.invoke()
        }

        edt_password.afterTextChange {
            changeContent.invoke()
        }

        viewModel.loginResult.observe(this, Observer {
            progress_bar.visibility = View.GONE
            it?.apply {
                if (this is Result.Success) {
                    toast(getString(R.string.login_fuccess))
                    findNavController().popBackStack()
                } else {
                    val error = this as Result.Error
                    toast(error.e.message.toString())
                }

            }
        })

        viewModel.loginState.observe(this, Observer {
            it?.apply {
                userNameError?.apply { edt_user_name.error = getString(this) }
                    ?: apply { edt_user_name.error = null }
                passwordError?.apply { edt_password.error = getString(this) }
                    ?: apply { edt_password.error = null }
                btn_login.isEnabled = isValie
            }
        })

        btn_login.setOnClickListener {
            progress_bar.visibility = View.VISIBLE
            viewModel.login(edt_user_name.text.toString(), edt_password.text.toString())
        }
    }

}

fun Fragment.toast(text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}