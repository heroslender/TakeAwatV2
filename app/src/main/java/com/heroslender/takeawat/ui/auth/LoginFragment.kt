package com.heroslender.takeawat.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.heroslender.takeawat.R
import com.heroslender.takeawat.base.BaseFragment
import com.heroslender.takeawat.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    fun setupView() {
        binding.btnReturn.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnLogin.setOnClickListener {
            viewModel.login(
                binding.txtEmail.text.toString(),
                binding.txtPassword.text.toString()
            )
        }

        var hasFailure = false
        viewModel.failure.observe(viewLifecycleOwner) {
            hasFailure = true
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            binding.txtEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_error, 0)
            binding.txtPassword.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                R.drawable.ic_error,
                0
            )
            binding.tvErrorMsg.text = it.message
        }

        binding.txtEmail.doAfterTextChanged { if (hasFailure) clearErrors() }
        binding.txtPassword.doAfterTextChanged { if (hasFailure) clearErrors() }
    }

    fun clearErrors() {
        binding.txtEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        binding.txtPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        binding.tvErrorMsg.text = ""
    }
}