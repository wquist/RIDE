package com.atlas.ride.presentation.feature.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import kotlinx.android.synthetic.main.fragment_welcome.*

import com.atlas.ride.presentation.R

class WelcomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_welcome, parent, false)
    }

    override fun onViewCreated(view: View, state: Bundle?) {
        super.onViewCreated(view, state)

        welcome_signup_button.setOnClickListener {
            findNavController().navigate(WelcomeFragmentDirections.welcomeToSignupAction())
        }
    }
}