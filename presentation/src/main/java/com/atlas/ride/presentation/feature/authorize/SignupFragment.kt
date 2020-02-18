package com.atlas.ride.presentation.feature.authorize

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import kotlinx.android.synthetic.main.fragment_signup.*

import com.atlas.ride.presentation.R

class SignupFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_signup, parent, false)
    }

    override fun onViewCreated(view: View, state: Bundle?) {
        super.onViewCreated(view, state)

        signup_server_button.setOnClickListener {
            findNavController().navigate(SignupFragmentDirections.signupToEditorAction())
        }
    }
}
