package com.izzatismail.pokepal.utils

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction

fun FragmentActivity?.showDialogFragment(dialogFragment: DialogFragment?) {
    if (this != null) else return
    val ft: FragmentTransaction = this.supportFragmentManager.beginTransaction()
    if (!isFinishing && dialogFragment != null) {
        if (dialogFragment.isAdded) {
            dialogFragment.dismissAllowingStateLoss()
        }
        ft.add(dialogFragment, null)
        ft.commitAllowingStateLoss()
    }
}