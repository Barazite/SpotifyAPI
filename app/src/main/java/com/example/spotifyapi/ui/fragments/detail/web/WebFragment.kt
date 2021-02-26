package com.example.spotifyapi.ui.fragments.detail.web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.spotifyapi.databinding.FragmentWebBinding


class WebFragment(private val spotifyUrl: String) : Fragment() {

    lateinit var binding: FragmentWebBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentWebBinding.inflate(inflater, container, false)

        binding.wvWebFragment.loadUrl(spotifyUrl)
        binding.wvWebFragment.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return false
            }
        }

        return binding.root
    }



}