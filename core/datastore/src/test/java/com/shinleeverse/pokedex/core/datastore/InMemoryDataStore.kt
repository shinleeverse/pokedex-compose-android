package com.shinleeverse.pokedex.core.datastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.updateAndGet

class InMemoryDataStore<T>(initialValue: T) : DataStore<T> {
    override val data = MutableStateFlow(initialValue)
    override suspend fun updateData(transform: suspend (t: T) -> T): T =
        data.updateAndGet { transform(it) }
}