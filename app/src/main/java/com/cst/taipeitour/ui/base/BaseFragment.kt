package com.cst.taipeitour.ui.base

import android.view.View
import androidx.fragment.app.Fragment
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI

abstract class BaseFragment : Fragment(), DIAware, View.OnClickListener {

    override val di by closestDI()

    override fun onClick(view: View) {}
}