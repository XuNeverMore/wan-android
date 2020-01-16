package com.nevermore.androidplay.account


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.nevermore.androidplay.R
import com.nevermore.androidplay.data.Result
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment() {


    private val accountViewModel: AccountViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tool_bar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        val changeContent: () -> Unit = {
            accountViewModel.changeContent(
                edt_user_name.text.toString(),
                edt_password.text.toString(),
                edt_repeat_password.text.toString()
            )
        }
        edt_user_name.afterTextChange { changeContent.invoke() }
        edt_password.afterTextChange { changeContent.invoke() }
        edt_repeat_password.afterTextChange { changeContent.invoke() }

        //show error tips
        accountViewModel.registerState.observe(this, Observer {
            it?.apply {
                it.passwordError?.apply { edt_password.error = getString(it.passwordError) }
                    ?: apply { edt_password.error = null }
                it.userNameError?.apply { edt_user_name.error = getString(it.userNameError) }
                    ?: apply { edt_user_name.error = null }
                it.rePassword?.apply { edt_repeat_password.error = getString(it.rePassword) }
                    ?: apply { edt_repeat_password.error = null }
                btn_register.isEnabled = it.isValie
            }

        })

        accountViewModel.registerResult.observe(this, Observer {
            progress_bar.visibility = View.GONE

            it?.apply {
                if (this is Result.Success) {
                    Toast.makeText(context,R.string.register_success,Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                    accountViewModel.clearRegisterResult()
                } else {
                    val error = this as Result.Error
                    Toast.makeText(context, error.e.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

        btn_register.setOnClickListener {
            progress_bar.visibility = View.VISIBLE
            accountViewModel.register(
                edt_user_name.text.toString(),
                edt_password.text.toString(),
                edt_repeat_password.text.toString()
            )
        }


    }

}

fun EditText.afterTextChange(after: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            after.invoke(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

    })
}
