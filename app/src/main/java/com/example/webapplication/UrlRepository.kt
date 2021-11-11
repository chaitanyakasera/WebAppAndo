package com.example.webapplication

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

//What is a Repository?
//A repository class abstracts access to multiple data sources.
// The repository is not part of the Architecture Components libraries,
// but is a suggested best practice for code separation and architecture.
// A Repository class provides a clean API for data access to the rest of the application.


// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class UrlRepository(private val urlDao: UrlDao) {
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allLinks: Flow<List<ALink>> = urlDao.getAlphabetizedWords()

    // TODO: 11/11/21 we are not using this repository right now

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(link: ALink) {
        urlDao.insertAll(link)
    }


}