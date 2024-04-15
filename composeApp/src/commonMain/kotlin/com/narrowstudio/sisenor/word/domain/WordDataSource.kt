package com.narrowstudio.sisenor.word.domain

import kotlinx.coroutines.flow.Flow

interface WordDataSource {
    suspend fun getWords(): Flow<List<Word>>
    suspend fun getRangeWords(startIndex: Long, endIndex: Long): Flow<List<Word>>
    suspend fun getSimilarWords(): Flow<List<Word>>
}