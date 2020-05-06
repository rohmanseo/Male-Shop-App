package com.komsi.maleshop.helper

import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar

object ToolbarHelper {
    private lateinit var actionBar: ActionBar
    private lateinit var toolbar: Toolbar
    var prevTitle: String? = null

    fun setTitle(title: String): ToolbarHelper {
        if (this::toolbar.isInitialized) {
            toolbar.title = title
            prevTitle = title
        }
        return this
    }

    fun setToolbar(toolbar: Toolbar) {
        this.toolbar = toolbar
        if (prevTitle!=null){
            toolbar.title = prevTitle
        }
    }

    fun displayBackArrow(display: Boolean) {
        if (this::actionBar.isInitialized) {
            actionBar.setDisplayHomeAsUpEnabled(display)
            actionBar.setDisplayShowHomeEnabled(display)
        }
    }

    fun setActionBar(actionBar: ActionBar) {
        this.actionBar = actionBar
    }
}