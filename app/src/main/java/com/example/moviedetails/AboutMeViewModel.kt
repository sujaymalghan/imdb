package com.example.moviedetails

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class AboutMeViewModel : ViewModel() {
    val currentFragment: MutableLiveData<Fragment?> = MutableLiveData()
    init {
        currentFragment.value = InitalFragment()
    }

    var currentPage: MutableLiveData<Int> = MutableLiveData(0)

    fun setCurrentPage(index: Int) {
        currentPage.value = index
    }

    fun selectFragment(fragment: Fragment) {
        currentFragment.value = fragment
    }


    fun toggleFragment(fragment: Fragment) {
        if (currentFragment.value?.javaClass == fragment.javaClass) {
            currentFragment.value = InitalFragment()
        } else {
            currentFragment.value = fragment
        }
    }
}
