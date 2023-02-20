package com.izzatismail.pokepal.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.izzatismail.pokepal.R

abstract class BaseActivity: AppCompatActivity() {

    private var viewActionBar: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun initialize() {
        showActionBar(title = getString(R.string.app_name), showBackButton = false)
    }

    fun showActionBar(title: String?, showBackButton: Boolean) {
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayShowCustomEnabled(true)
        actionBar?.elevation = 0f
        viewActionBar = layoutInflater.inflate(R.layout.view_actionbar, null)
        actionBar?.setCustomView(viewActionBar, ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
        actionBar?.customView?.parent
        actionBar?.customView?.findViewById<ImageView>(R.id.ivBack)?.setOnClickListener { finish() }
        actionBar?.customView?.findViewById<TextView>(R.id.tvTitle)?.text = title

        actionBar?.customView?.findViewById<ImageView>(R.id.ivBack)?.visibility = if (showBackButton) View.VISIBLE else View.GONE
    }

    fun hideActionBar() {
        supportActionBar?.hide()
    }
}