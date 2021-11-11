package com.example.webapplication

import androidx.lifecycle.*
import kotlinx.coroutines.launch

/*
Don't keep a reference to a Context that has a shorter lifecycle than your ViewModel! Examples are:

    Activity
    Fragment
    View

Keeping a reference can cause a memory leak, e.g. the ViewModel has a reference to a destroyed Activity! All these objects can be destroyed by the operating
system and recreated when there's a configuration change, and this can happen many times during the lifecycle of a ViewModel.
* Important: ViewModels don't survive the app's process being killed in the background when the OS needs more resources.
*  For UI data that needs to survive process death due to running out of resources,
*  you can use the Saved State module for ViewModels.
* */
class UrlViewModel(private val repository: UrlRepository):ViewModel() {


    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allLinks: LiveData<List<ALink>> = repository.allLinks.asLiveData()
    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(link: ALink) = viewModelScope.launch {
        repository.insert(link)
    }

    class WordViewModelFactory(private val repository: UrlRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UrlViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return UrlViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }



}