package com.example.dechproduct.hotelreservationapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.dechproduct.hotelreservationapp.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {
    private lateinit var fragmentInfoBinding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentInfoBinding = FragmentInfoBinding.bind(view)
        val args : InfoFragmentArgs by navArgs()
        val article =  args.selectedArticle

        //display fragment article to webview
        fragmentInfoBinding.wvInfo.apply{
            webViewClient = WebViewClient()
            if(article.url!= ""){
                loadUrl(article.url)
            }

        }

    }

}